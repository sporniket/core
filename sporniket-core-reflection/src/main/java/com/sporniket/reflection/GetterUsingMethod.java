package com.sporniket.reflection ;

import java.lang.reflect.InvocationTargetException ;
import java.lang.reflect.Method ;

class GetterUsingMethod implements Getter {

    private final Method myGetter ;

    public GetterUsingMethod(Method getter) {
        myGetter = getter ;
    }

    @Override
    public Object getValue(Object from) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        return myGetter.invoke(from) ;
    }

}
