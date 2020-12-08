package com.sporniket.reflection ;

import java.lang.reflect.InvocationTargetException ;

interface SetterUsingStringValue {

    SetterUsingStringValue DO_NOTHING = (v, r) -> {
    } ;

    void setValue(String value, Object recipient) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException ;
}
