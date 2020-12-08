package com.sporniket.reflection ;

import static java.lang.Boolean.parseBoolean ;
import static java.lang.Double.parseDouble ;
import static java.lang.Float.parseFloat ;
import static java.lang.Integer.parseInt ;
import static java.lang.Long.parseLong ;

import java.lang.reflect.InvocationTargetException ;
import java.lang.reflect.Method ;
import java.lang.reflect.Parameter ;
import java.math.BigDecimal ;

class SetterUsingMethod implements SetterUsingStringValue {

    private final Method myMethod ;

    public SetterUsingMethod(Method method) {
        myMethod = method ;
    }

    @Override
    public void setValue(String value, Object recipient) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        final Parameter arg = myMethod.getParameters()[0] ;
        if (String.class.equals(arg.getType())) {
            myMethod.invoke(recipient, value) ;
        } else if (BigDecimal.class.equals(arg.getType())) {
            myMethod.invoke(recipient, new BigDecimal(value)) ;
        } else if (int.class.equals(arg.getType()) || Integer.class.equals(arg.getType())) {
            myMethod.invoke(recipient, parseInt(value)) ;
        } else if (boolean.class.equals(arg.getType()) || Boolean.class.equals(arg.getType())) {
            myMethod.invoke(recipient, parseBoolean(value)) ;
        } else if (long.class.equals(arg.getType()) || Long.class.equals(arg.getType())) {
            myMethod.invoke(recipient, parseLong(value)) ;
        } else if (float.class.equals(arg.getType()) || Float.class.equals(arg.getType())) {
            myMethod.invoke(recipient, parseFloat(value)) ;
        } else if (double.class.equals(arg.getType()) || Double.class.equals(arg.getType())) {
            myMethod.invoke(recipient, parseDouble(value)) ;
        }
    }

}
