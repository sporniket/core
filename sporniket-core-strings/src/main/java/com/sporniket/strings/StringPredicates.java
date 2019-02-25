/**
 * 
 */
package com.sporniket.strings;

import java.util.function.Predicate;

/**
 * Various predicates about strings.
 * 
 * <p>
 * &copy; Copyright 2002-2016 David Sporn
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
 * @version 19.02.00
 * @since 19.02.00
 */
public class StringPredicates
{
	// ordered before public predicates for references.
	private static final Predicate<String> AA_IS_EMPTY = s -> null == s || 0 == s.length();

	public static final Predicate<String> IS_BLANK = AA_IS_EMPTY.or(s -> 0 == s.trim().length());

	public static final Predicate<String> IS_EMPTY = AA_IS_EMPTY;

	public static final Predicate<String> IS_NOT_BLANK = IS_BLANK.negate();

	public static final Predicate<String> IS_NOT_EMPTY = IS_EMPTY.negate();

}
