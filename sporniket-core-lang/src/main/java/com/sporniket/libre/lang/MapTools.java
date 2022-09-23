/**
 * 
 */
package com.sporniket.libre.lang;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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
 * <i>The Sporniket Core Library &#8211; lang</i> is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; lang</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 * 
 * <p>
 * You should have received a copy of the GNU Lesser General Public License along with <i>The Sporniket Core Library &#8211;
 * lang</i>. If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
 * 
 * <hr>
 * 
 * @author David SPORN
 * @version 22.09.00
 * @since 22.09.01
 */
public class MapTools
{

    /**
     * Change the values of the given map using the provided map of changes ; only existing keys in the target map are changed, no
     * new entry are inserted.
     * 
     * @param target
     *            the map to modify.
     * @param changes
     *            a set of changes.
     * @return the modified map, no new entry inserted.
     */
    public static final Map<String, String> apply(Map<String, String> target, Map<String, String> changes)
    {
        changes.entrySet().stream()//
                .filter(e -> target.containsKey(e.getKey()))//
                .forEach(e -> target.put(e.getKey(), e.getValue()));
        return target;
    }

    /**
     * Create a {@link Map} using a list of {@link String}s, each string encoding a pair of key-value.
     * 
     * @param separator
     *            the separator char to use.
     * @param items
     *            each item follows the pattern : <code>key + separator + value</code>
     * @return the map.
     */
    public static final Map<String, String> asMap(char separator, String... items)
    {
        String sep = "[" + separator + "]";
        return asList(items).stream().map(a -> a.split(sep, 2))//
                .collect(Collectors.toMap(a -> a[0], a -> a[1]));
    }

    /**
     * Create a {@link Map} using a list of {@link String}s, each string encoding a pair of key-value.
     * 
     * @param items
     *            each item follows the pattern : <code>key + ':' + value</code>
     * @return the map.
     */
    public static final Map<String, String> asMap(String... items)
    {
        return asMap(':', items);
    }

    /**
     * Produce a {@link Map} from the given Map by keeping items whose key is in the given list.
     * 
     * @param source
     *            the original map.
     * @param keys
     *            the allowed keys.
     * @return the filtered map.
     */
    public static final Map<String, String> filterByKeys(Map<String, String> source, String... keys)
    {
        Set<String> keysFilter = new TreeSet<String>(asList(keys));
        return source.entrySet().stream()//
                .filter(e -> keysFilter.contains(e.getKey()))//
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
