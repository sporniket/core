/**
 * 
 */
package com.sporniket.libre.lang ;

import static java.util.Arrays.asList ;

import java.util.Map ;
import java.util.stream.Collectors ;

/**
 * Macros for maps.
 * 
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
 * @since 22.09.01
 */
public class MapTools {

    /**
     * Update the given map using a list of {@link String}s, each string encoding a pair of key-value.
     * 
     * @param target the map to modify.
     * @param separator
     * @param items each item follows the pattern : <code>key + separator + value</code>
     * @return the updated map.
     */
    public static final Map<String, String> applyToMap(Map<String, String> target, char separator, String... items) {
        String sep = "[" + separator + "]" ;
        asList(items).stream().map(i -> i.split(sep, 2)).forEach(i -> target.put(i[0], i[1])) ;
        return target ;
    }

    /**
     * Update the given map using a list of {@link String}s, each string encoding a pair of key-value.
     * 
     * @param target the map to modify.
     * @param items each item follows the pattern : <code>key + ':' + value</code>
     * @return the updated map.
     */
    public static final Map<String, String> applyToMap(Map<String, String> target, String... items) {
        return applyToMap(target, ':', items) ;
    }

    /**
     * Create a {@link Map} using a list of {@link String}s, each string encoding a pair of key-value.
     * 
     * @param separator the separator char to use.
     * @param items each item follows the pattern : <code>key + separator + value</code>
     * @return the map.
     */
    public static final Map<String, String> asMap(char separator, String... items) {
        String sep = "[" + separator + "]" ;
        return asList(items).stream().map(a -> a.split(sep, 2))//
                .collect(Collectors.toMap(a -> a[0], a -> a[1])) ;
    }

    /**
     * Create a {@link Map} using a list of {@link String}s, each string encoding a pair of key-value.
     * 
     * @param items each item follows the pattern : <code>key + ':' + value</code>
     * @return the map.
     */
    public static final Map<String, String> asMap(String... items) {
        return asMap(':', items) ;
    }

}
