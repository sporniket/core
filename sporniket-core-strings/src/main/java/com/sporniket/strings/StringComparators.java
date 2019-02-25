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
 * @author dsporn
 *
 */
public final class StringComparators
{
	public static final Comparator<String> STRING_COMPARATOR_NATURAL = nullsFirst(naturalOrder());

	public static final Comparator<String> STRING_COMPARATOR_NATURAL_IGNORE_CASE = nullsFirst(CASE_INSENSITIVE_ORDER);

	public static final Comparator<String> STRING_COMPARATOR_REVERSE = nullsFirst(reverseOrder());

	public static final Comparator<String> STRING_COMPARATOR_REVERSE_IGNORE_CASE = nullsFirst(CASE_INSENSITIVE_ORDER.reversed());
}
