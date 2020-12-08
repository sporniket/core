package com.sporniket.reflection ;

import java.lang.reflect.InvocationTargetException ;

interface Getter {

    Getter DO_NOTHING = o -> null ;

    Object getValue(Object from) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException ;
}
