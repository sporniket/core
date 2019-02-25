/**
 * 
 */
package com.sporniket.strings;

import static java.lang.String.CASE_INSENSITIVE_ORDER;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsFirst;
import static java.util.Comparator.reverseOrder;

import java.util.Comparator;

/**
 * Ready to use Locale insensitive string comparators, safely handling <code>null</code> values as less than a non <code>null</code>
 * value.
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
public final class StringComparators
{
	public static final Comparator<String> STRING_COMPARATOR_NATURAL = nullsFirst(naturalOrder());

	public static final Comparator<String> STRING_COMPARATOR_NATURAL_IGNORE_CASE = nullsFirst(CASE_INSENSITIVE_ORDER);

	public static final Comparator<String> STRING_COMPARATOR_REVERSE = nullsFirst(reverseOrder());

	public static final Comparator<String> STRING_COMPARATOR_REVERSE_IGNORE_CASE = nullsFirst(CASE_INSENSITIVE_ORDER.reversed());
}
