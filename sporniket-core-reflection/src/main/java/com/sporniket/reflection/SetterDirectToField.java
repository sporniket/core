package com.sporniket.reflection ;

import static java.lang.Boolean.parseBoolean ;
import static java.lang.Integer.parseInt ;

import java.lang.reflect.Field ;
import java.math.BigDecimal ;

class SetterDirectToField implements SetterUsingStringValue {

    final Field myField ;

    public SetterDirectToField(Field field) {
        myField = field ;
    }

    @Override
    public void setValue(String value, Object recipient) throws IllegalArgumentException, IllegalAccessException {
        final Class<?> _targetType = myField.getType() ;
        if (String.class.equals(_targetType)) {
            myField.set(recipient, value) ;
        } else if (BigDecimal.class.equals(_targetType)) {
            myField.set(recipient, new BigDecimal(value)) ;
        } else if (int.class.equals(_targetType) || Integer.class.equals(_targetType)) {
            myField.set(recipient, parseInt(value)) ;
        } else if (boolean.class.equals(_targetType) || Boolean.class.equals(_targetType)) {
            myField.set(recipient, parseBoolean(value)) ;
        }
    }

}
