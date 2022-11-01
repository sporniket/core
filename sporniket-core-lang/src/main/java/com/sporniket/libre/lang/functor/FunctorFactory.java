package com.sporniket.libre.lang.functor;

import java.lang.reflect.Method;
import java.security.InvalidParameterException;
import java.util.Map;

/**
 * Factory to constructs some implementation of Functor.
 * 
 * <p>
 * &copy; Copyright 2002-2022 David Sporn
 * </p>
 * <hr>
 * 
 * <p>
 * This file is part of <i>The Sporniket Core Library &#8211; lang</i>.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; lang</i> is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; lang</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 * 
 * <p>
 * You should have received a copy of the GNU Lesser General Public License along with <i>The Sporniket Core Library &#8211;
 * lang</i>. If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
 * 
 * <hr>
 * 
 * @author David SPORN
 * @version 22.11.00
 * @since 12.06.01
 */
public class FunctorFactory
{
    /**
     * Create a functor without parameter, wrapping a call to another method.
     * 
     * @param instanceClass
     *            class containing the method.
     * @param methodName
     *            Name of the method, it must exist.
     * @return a Functor that call the specified method on the specified instance.
     * @throws Exception
     *             if there is a problem to deal with.
     */
    public static Functor instanciateFunctorAsAClassMethodWrapper(Class<?> instanceClass, String methodName) throws Exception
    {
        if (null == instanceClass)
        {
            throw new NullPointerException("instanceClass is null");
        }
        Method _method = instanceClass.getMethod(methodName, (Class<?>[]) null);
        return instanciateFunctorAsAMethodWrapper(null, _method);
    }

    /**
     * Create a functor without parameter, wrapping a call to another method.
     * 
     * @param instance
     *            null is allowed, for wrapping static method calls
     * @param method
     *            the method to call.
     * @return a Functor that call the specified method on the specified instance.
     * @throws Exception
     *             if there is a problem to deal with.
     */
    public static Functor instanciateFunctorAsAMethodWrapper(final Object instance, final Method method) throws Exception
    {
        if (null == method)
        {
            throw new NullPointerException("Method is null");
        }
        if (method.getParameterTypes().length > 0)
        {
            throw new InvalidParameterException("Method should not accept any parameter");
        }
        return new Functor()
        {
            public Object process() throws Exception
            {
                return method.invoke(instance, (Object[]) null);
            }
        };
    }

    /**
     * Create a functor without parameter, wrapping a call to another method.
     * 
     * @param instance
     *            instance to call the method upon. Should not be null.
     * @param methodName
     *            Name of the method, it must exist.
     * @return a Functor that call the specified method on the specified instance.
     * @throws Exception
     *             if there is a problem to deal with.
     */
    public static Functor instanciateFunctorAsAnInstanceMethodWrapper(final Object instance, String methodName) throws Exception
    {
        if (null == instance)
        {
            throw new NullPointerException("Instance is null");
        }
        Method _method = instance.getClass().getMethod(methodName, (Class<?>[]) null);
        return instanciateFunctorAsAMethodWrapper(instance, _method);
    }

    /**
     * Create a functor with parameter, wrapping a call to another method.
     * 
     * @param instanceClass
     *            class containing the method.
     * @param methodName
     *            Name of the method, it must exist.
     * @return a Functor with parameter that call the specified method on the specified instance.
     * @throws Exception
     *             if there is a problem to deal with.
     */
    public static FunctorWithParameter instanciateFunctorWithParameterAsAClassMethodWrapper(Class<?> instanceClass,
            String methodName) throws Exception
    {
        if (null == instanceClass)
        {
            throw new NullPointerException("instanceClass is null");
        }
        Method _method = instanceClass.getMethod(methodName, (Class<?>[]) null);
        return instanciateFunctorWithParameterAsAMethodWrapper(null, _method);
    }

    /**
     * Create a functor with parameter, wrapping a call to another method.
     * 
     * @param instance
     *            null is allowed, for wrapping static method calls
     * @param method
     *            the method to call.
     * @return a Functor with parameter that call the specified method on the specified instance.
     * @throws Exception
     *             if there is a problem to deal with.
     */
    public static FunctorWithParameter instanciateFunctorWithParameterAsAMethodWrapper(final Object instance, final Method method)
            throws Exception
    {
        if (null == method)
        {
            throw new NullPointerException("Method is null");
        }
        if (method.getParameterTypes().length != 1 || !Map.class.equals(method.getParameterTypes()[0]))
        {
            throw new InvalidParameterException("Method must accept a Map");
        }

        return new FunctorWithParameter()
        {
            public Object process(Map<String, Object> parameters) throws Exception
            {
                Object[] _paramList =
                {
                        parameters
                };
                return method.invoke(instance, _paramList);
            }
        };
    }

    /**
     * Create a functor with parameter, wrapping a call to another method.
     * 
     * @param instance
     *            instance to call the method upon. Should not be null.
     * @param methodName
     *            Name of the method, it must exist.
     * @return a Functor with parameter that call the specified method on the specified instance.
     * @throws Exception
     *             if there is a problem to deal with.
     */
    public static FunctorWithParameter instanciateFunctorWithParameterAsAnInstanceMethodWrapper(final Object instance,
            String methodName) throws Exception
    {
        if (null == instance)
        {
            throw new NullPointerException("Instance is null");
        }
        Method _method = instance.getClass().getMethod(methodName, (Class<?>[]) null);
        return instanciateFunctorWithParameterAsAMethodWrapper(instance, _method);
    }
}
