package com.sporniket.reflection ;

import static java.util.stream.Collectors.toSet ;
import static java.util.stream.Stream.of ;

import java.math.BigDecimal ;
import java.util.Set ;

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
 * @version 22.11.00
 * @since 22.09.00
 */
public class UtilsReflection {

    public static final Set<Class<?>> FINAL_TYPES = of(//
            String.class, //
            Integer.class, //
            int.class, //
            boolean.class, //
            Boolean.class, //
            float.class, //
            Float.class, //
            long.class, //
            Long.class, //
            double.class, //
            Double.class, //
            BigDecimal.class // ,
    ).collect(toSet()) ;

}
