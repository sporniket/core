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
 * @version 19.04.00
 * @since 22.09.00
 */
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
