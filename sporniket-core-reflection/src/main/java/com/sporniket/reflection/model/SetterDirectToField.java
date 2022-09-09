package com.sporniket.reflection.model ;

import static java.lang.Boolean.parseBoolean ;
import static java.lang.Double.parseDouble ;
import static java.lang.Float.parseFloat ;
import static java.lang.Integer.parseInt ;
import static java.lang.Long.parseLong ;

import java.lang.reflect.Field ;
import java.math.BigDecimal ;

/**
 * <p>
 * &copy; Copyright 2002-2022 David Sporn
 * </p>
 * <hr>
 * 
 * <p>
 * This file is part of <i>The Sporniket Core Library &#8211; lang</i>.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; lang</i> is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; lang</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 * 
 * <p>
 * You should have received a copy of the GNU Lesser General Public License along with <i>The Sporniket Core Library &#8211; lang</i>. If not, see
 * <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
 * 
 * <hr>
 * 
 * @author David SPORN
 * @version 22.09.00
 * @since 22.09.00
 */
public class SetterDirectToField implements SetterUsingStringValue {

    final Field myField ;

    public SetterDirectToField(Field field) {
        myField = field ;
    }

    @SuppressWarnings({
            "unchecked", "rawtypes"
    })
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
        } else if (long.class.equals(_targetType) || Long.class.equals(_targetType)) {
            myField.set(recipient, parseLong(value)) ;
        } else if (float.class.equals(_targetType) || Float.class.equals(_targetType)) {
            myField.set(recipient, parseFloat(value)) ;
        } else if (double.class.equals(_targetType) || Double.class.equals(_targetType)) {
            myField.set(recipient, parseDouble(value)) ;
        } else if (_targetType.isEnum()) {
            myField.set(recipient, Enum.valueOf((Class<Enum>) myField.getType(), value)) ;
        }
    }

}
