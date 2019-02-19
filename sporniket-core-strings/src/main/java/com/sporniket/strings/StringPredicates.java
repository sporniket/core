/**
 * 
 */
package com.sporniket.strings;

import java.util.function.Predicate;

/**
 * Various predicates about strings
 * 
 * @author dsporn
 *
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
