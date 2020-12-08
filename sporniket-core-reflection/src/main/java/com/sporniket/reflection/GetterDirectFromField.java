package com.sporniket.reflection ;

import java.lang.reflect.Field ;

class GetterDirectFromField implements Getter {

    private final Field myField ;

    public GetterDirectFromField(Field field) {
        myField = field ;
    }

    @Override
    public Object getValue(Object from) throws IllegalArgumentException, IllegalAccessException {
        return myField.get(from) ;
    }

}
