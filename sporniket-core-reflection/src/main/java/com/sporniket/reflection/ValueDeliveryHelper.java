/**
 *
 */
package com.sporniket.reflection ;

import static com.sporniket.strings.StringPredicates.IS_NOT_BLANK ;
import static java.lang.String.format ;
import static java.util.function.Function.identity ;
import static java.util.stream.Collectors.toMap ;
import static java.util.stream.Collectors.toSet ;
import static java.util.stream.Stream.of ;

import java.lang.reflect.AccessibleObject ;
import java.lang.reflect.Field ;
import java.lang.reflect.InvocationTargetException ;
import java.lang.reflect.Method ;
import java.math.BigDecimal ;
import java.util.HashMap ;
import java.util.HashSet ;
import java.util.Map ;
import java.util.Scanner ;
import java.util.Set ;
import java.util.function.Function ;
import java.util.function.Predicate ;

import com.fasterxml.jackson.annotation.JsonProperty ;

/**
 * Utilitaire pour appliquer une valeur à un champs ou un sous-champs d'un objet, en désignant l'emplacement par un chemin utilisant les noms de propriété (p.ex.
 * "<code>sub1.sub2.targetField</code>").
 *
 * <p>
 * Les noms de propriétés possibles sont découverts par introspection, en répertoriant les accesseurs selon la convention Javabean (e.g. <code>getXxx</code> et <code>setXxx</code>
 * pour la propriété <code>xxx</code>) ; en répertoriant les méthodes et champs annotés avec {@link JsonProperty} avec une valeur non vide ; en utilisant les champs directement
 * sauf accesseur .
 * </p>
 *
 * @author spornda
 *
 */
public class ValueDeliveryHelper {

    static final Map<String, Model> CACHE = new HashMap<>(30) ;

    static final Set<Class<?>> FINAL_TYPES = of(//
            String.class, //
            Integer.class, //
            int.class, //
            boolean.class, //
            Boolean.class, //
            BigDecimal.class //
    ).collect(toSet()) ;

    /**
     * Utilise le nom du champ comme nom de propriété.
     */
    final static Function<? super Field, ? extends String> PROPERTY_NAME_FROM_FIELD_NAME = f -> {
        return f.getName() ;
    } ;

    /**
     * Utilise la valeur du paramètre <code>value</code> de l'annotation <code>JsonProperty</code>
     */
    final static Function<? super AccessibleObject, ? extends String> PROPERTY_NAME_FROM_JSON_ANNOTATION = f -> {
        return f.getAnnotation(JsonProperty.class).value() ;
    } ;

    /**
     * Obtient le nom de la propriété depuis un accesseur (e.g. <code>getXxx</code> et <code>setXxx</code> donneront <code>xxx</code>).
     */
    final static Function<? super Method, ? extends String> PROPERTY_NAME_FROM_METHOD_NAME = m -> {
        return m.getName().substring(3, 4).toLowerCase() + m.getName().substring(4) ;
    } ;

    private static final Predicate<? super AccessibleObject> IS__JSON_ANNOTATED_WITH_VALUE = //
            ao -> (null != ao.getAnnotation(JsonProperty.class) //
            ) && IS_NOT_BLANK.test(ao.getAnnotation(JsonProperty.class).value()) ;

    private static final Predicate<? super AccessibleObject> IS_NOT__JSON_ANNOTATED_WITH_VALUE = //
            ao -> !((null != ao.getAnnotation(JsonProperty.class) //
            ) && IS_NOT_BLANK.test(ao.getAnnotation(JsonProperty.class).value())//
            ) ;

    /**
     * Trouve le getter approprié pour la propriété spécifiée, on recherche dans l'ordre : un getter annoté avec {@link JsonProperty} avec valeur, un getter selon la convention
     * Javabean, un champs annoté avec {@link JsonProperty}, un champs avec le nom recherché.
     *
     * @param propertyName le nom de la propriété.
     * @param annotatedJsonGetters la liste des getters annotés référencés.
     * @param propertyGetters la liste des getters javabean référencés.
     * @param annotatedJsonFields la liste des champs annotés référencés.
     * @param plainFields la liste des champs.
     * @return un getter utilisant le nom approprié.
     */
    private static Getter findGetter(String propertyName, final Map<String, Method> annotatedJsonGetters, final Map<String, Method> propertyGetters,
            final Map<String, Field> annotatedJsonFields, final Map<String, Field> plainFields) {
        Getter _getter = Getter.DO_NOTHING ;
        if (annotatedJsonGetters.containsKey(propertyName)) {
            _getter = new GetterUsingMethod(annotatedJsonGetters.get(propertyName)) ;
        } else if (propertyGetters.containsKey(propertyName)) {
            _getter = new GetterUsingMethod(propertyGetters.get(propertyName)) ;
        } else if (annotatedJsonFields.containsKey(propertyName)) {
            _getter = new GetterDirectFromField(annotatedJsonFields.get(propertyName)) ;
        } else if (plainFields.containsKey(propertyName)) {
            _getter = new GetterDirectFromField(plainFields.get(propertyName)) ;
        }
        return _getter ;
    }

    /**
     * Trouve le setter approprié pour la propriété spécifiée, on recherche dans l'ordre : un setter annoté avec {@link JsonProperty} avec valeur, un setter selon la convention
     * Javabean, un champs annoté avec {@link JsonProperty}, un champs avec le nom recherché.
     *
     * @param propertyName le nom de la propriété.
     * @param annotatedJsonSetters la liste des setters annotés référencés.
     * @param propertySetters la liste des setters javabean référencés.
     * @param annotatedJsonFields la liste des champs annotés référencés.
     * @param plainFields la liste des champs.
     * @return un setter utilisant le canal approprié.
     */
    private static SetterUsingStringValue findSetter(String propertyName, final Map<String, Method> annotatedJsonSetters, final Map<String, Method> propertySetters,
            final Map<String, Field> annotatedJsonFields, final Map<String, Field> plainFields) {
        SetterUsingStringValue _setter = SetterUsingStringValue.DO_NOTHING ;
        if (annotatedJsonSetters.containsKey(propertyName)) {
            _setter = new SetterUsingMethod(annotatedJsonSetters.get(propertyName)) ;
        } else if (propertySetters.containsKey(propertyName)) {
            _setter = new SetterUsingMethod(propertySetters.get(propertyName)) ;
        } else if (annotatedJsonFields.containsKey(propertyName)) {
            final Field _field = annotatedJsonFields.get(propertyName) ;
            if (FINAL_TYPES.contains(_field.getType())) {
                _setter = new SetterDirectToField(_field) ;
            }
        } else if (plainFields.containsKey(propertyName)) {
            final Field _field = plainFields.get(propertyName) ;
            if (FINAL_TYPES.contains(_field.getType())) {
                _setter = new SetterDirectToField(_field) ;
            }
        }
        return _setter ;
    }

    static boolean isFinalType(Class<?> candidate) {
        return FINAL_TYPES.contains(candidate) ;
    }

    static Map<String, Field> lookForAnnotatedJsonField(Class<?> toScan) {
        return of(toScan.getFields())//
                .filter(IS__JSON_ANNOTATED_WITH_VALUE)//
                .collect(toMap(PROPERTY_NAME_FROM_JSON_ANNOTATION, identity())) ;
    }

    static Map<String, Method> lookForAnnotatedJsonGetter(Class<?> toScan) {
        return of(toScan.getMethods())//
                .filter(m -> 0 == m.getParameterCount())//
                .filter(IS__JSON_ANNOTATED_WITH_VALUE)//
                .collect(toMap(PROPERTY_NAME_FROM_JSON_ANNOTATION, identity())) ;
    }

    static Map<String, Method> lookForAnnotatedJsonSetter(Class<?> toScan) {
        return of(toScan.getMethods())//
                .filter(m -> 1 == m.getParameterCount())//
                .filter(m -> FINAL_TYPES.contains(m.getParameters()[0].getType()))//
                .filter(IS__JSON_ANNOTATED_WITH_VALUE)//
                .collect(toMap(PROPERTY_NAME_FROM_JSON_ANNOTATION, identity())) ;
    }

    static Map<String, Field> lookForPlainField(Class<?> toScan) {
        return of(toScan.getFields())//
                .filter(IS_NOT__JSON_ANNOTATED_WITH_VALUE)//
                .collect(toMap(f -> f.getName(), identity())) ;
    }

    static Map<String, Method> lookForPropertyGetter(Class<?> toScan) {
        return of(toScan.getMethods())//
                .filter(m -> 0 == m.getParameterCount())//
                .filter(IS_NOT__JSON_ANNOTATED_WITH_VALUE)//
                .filter(m -> m.getName().startsWith("get") && !m.getName().equals("getClass"))//
                .collect(toMap(PROPERTY_NAME_FROM_METHOD_NAME, identity())) ;
    }

    static Map<String, Method> lookForPropertySetter(Class<?> toScan) {
        return of(toScan.getMethods())//
                .filter(m -> 1 == m.getParameterCount())//
                .filter(m -> FINAL_TYPES.contains(m.getParameters()[0].getType()))//
                .filter(IS_NOT__JSON_ANNOTATED_WITH_VALUE)//
                .filter(m -> m.getName().startsWith("set"))//
                .collect(toMap(PROPERTY_NAME_FROM_METHOD_NAME, identity())) ;
    }

    /**
     * Construit la liste des propriétés (1 niveau de profondeur) de la classe d'objet désignée.
     *
     * @param toScan la classe à examiner.
     * @return un modèle.
     */
    private static Model extractModelFromClass(Class<?> toScan) {
        final Model _result = new Model() ;
        final Map<String, Field> _annotatedJsonFields = lookForAnnotatedJsonField(toScan) ;
        final Map<String, Method> _annotatedJsonGetters = lookForAnnotatedJsonGetter(toScan) ;
        final Map<String, Method> _annotatedJsonSetters = lookForAnnotatedJsonSetter(toScan) ;
        final Map<String, Field> _plainFields = lookForPlainField(toScan) ;
        final Map<String, Method> _propertyGetters = lookForPropertyGetter(toScan) ;
        final Map<String, Method> _propertySetters = lookForPropertySetter(toScan) ;

        final Set<String> propertyRegistry = new HashSet<>() ;
        propertyRegistry.addAll(_annotatedJsonGetters.keySet()) ;
        propertyRegistry.addAll(_annotatedJsonSetters.keySet()) ;
        propertyRegistry.addAll(_propertyGetters.keySet()) ;
        propertyRegistry.addAll(_propertySetters.keySet()) ;
        propertyRegistry.addAll(_annotatedJsonFields.keySet()) ;
        propertyRegistry.addAll(_plainFields.keySet()) ;

        for (final String _propertyName : propertyRegistry) {
            final SetterUsingStringValue _setter = findSetter(_propertyName, _annotatedJsonSetters, _propertySetters, _annotatedJsonFields, _plainFields) ;
            final Getter _getter = findGetter(_propertyName, _annotatedJsonGetters, _propertyGetters, _annotatedJsonFields, _plainFields) ;

            _result.getProperties().put(_propertyName, new Property(_getter, _setter)) ;
        }
        return _result ;
    }

    private static Model modelFromClass(Class<?> toScan) {
        final String _className = toScan.getName() ;
        synchronized (CACHE) {
            if (!CACHE.containsKey(_className)) {
                CACHE.put(_className, extractModelFromClass(toScan)) ;
            }
        }
        return CACHE.get(_className) ;
    }

    /**
     * Applique une valeur chaîne à une propriété ou une sous-propriété de l'objet destinataire.
     *
     * <p>
     * La valeur peut être convertie en {@link BigDecimal} ou en <code>int</code> le cas échéant.
     * </p>
     *
     * @param recipient l'objet destinataire.
     * @param path le chemin de la propriété (e.g. 'foo.bar.here')
     * @param value la valeur sous forme de chaîne.
     * @throws InvocationTargetException when there is a problem.
     * @throws IllegalAccessException when there is a problem.
     * @throws IllegalArgumentException when there is a problem.
     */
    public static void applyValueTo(Object recipient, String path, String value) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Object _finalRecipient = recipient ;
        Model _model = modelFromClass(_finalRecipient.getClass()) ;
        for (final Scanner _pScan = new Scanner(path).useDelimiter("[.]"); _pScan.hasNext();) {
            final String _target = _pScan.next() ;
            if (!_model.getProperties().containsKey(_target)) {
                continue ;
            }
            final Property _property = _model.getProperties().get(_target) ;
            if (_pScan.hasNext()) {
                // dive into subobjects
                _finalRecipient = _property.getGetter().getValue(_finalRecipient) ;
                if (null == _finalRecipient) {
                    throw new NullPointerException(format("Cannot continue, null value for property '%s'", _target)) ;
                }
                _model = modelFromClass(_finalRecipient.getClass()) ;
            } else {
                // change value
                _property.getSetter().setValue(value, _finalRecipient) ;
            }
        }
    }

    public static Object extractValueFrom(Object source, String path) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Object _finalSource = source ;
        Model _model = modelFromClass(_finalSource.getClass()) ;
        for (final Scanner _pScan = new Scanner(path).useDelimiter("[.]"); _pScan.hasNext();) {
            final String _target = _pScan.next() ;
            if (!_model.getProperties().containsKey(_target)) {
                continue ;
            }
            final Property _property = _model.getProperties().get(_target) ;
            if (_pScan.hasNext()) {
                // dive into subobjects
                _finalSource = _property.getGetter().getValue(_finalSource) ;
                if (null == _finalSource) {
                    throw new NullPointerException(format("Cannot continue, null value for property '%s'", _target)) ;
                }
                _model = modelFromClass(_finalSource.getClass()) ;
            } else {
                // change value
                return _property.getGetter().getValue(_finalSource) ;
            }
        }
        return null ;
    }

}