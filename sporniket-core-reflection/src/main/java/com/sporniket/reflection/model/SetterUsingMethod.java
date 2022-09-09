package com.sporniket.reflection.model ;

import static java.lang.Boolean.parseBoolean ;
import static java.lang.Double.parseDouble ;
import static java.lang.Float.parseFloat ;
import static java.lang.Integer.parseInt ;
import static java.lang.Long.parseLong ;

import java.lang.reflect.InvocationTargetException ;
import java.lang.reflect.Method ;
import java.lang.reflect.Parameter ;
import java.math.BigDecimal ;

/**
 * Implementation of a {@link SetterUsingStringValue} that uses an accessor method to change the value.
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
public class SetterUsingMethod implements SetterUsingStringValue {

    private final Method myMethod ;

    public SetterUsingMethod(Method method) {
        myMethod = method ;
    }

    @SuppressWarnings({
            "unchecked", "rawtypes"
    })
    @Override
    public void setValue(String value, Object recipient) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        final Parameter arg = myMethod.getParameters()[0] ;
        final Class<?> argType = arg.getType() ;
        if (String.class.equals(argType)) {
            myMethod.invoke(recipient, value) ;
        } else if (BigDecimal.class.equals(argType)) {
            myMethod.invoke(recipient, new BigDecimal(value)) ;
        } else if (int.class.equals(argType) || Integer.class.equals(argType)) {
            myMethod.invoke(recipient, parseInt(value)) ;
        } else if (boolean.class.equals(argType) || Boolean.class.equals(argType)) {
            myMethod.invoke(recipient, parseBoolean(value)) ;
        } else if (long.class.equals(argType) || Long.class.equals(argType)) {
            myMethod.invoke(recipient, parseLong(value)) ;
        } else if (float.class.equals(argType) || Float.class.equals(argType)) {
            myMethod.invoke(recipient, parseFloat(value)) ;
        } else if (double.class.equals(argType) || Double.class.equals(argType)) {
            myMethod.invoke(recipient, parseDouble(value)) ;
        } else if (argType.isEnum()) {
            myMethod.invoke(recipient, Enum.valueOf((Class<Enum>) argType, value)) ;
        }
    }

}
