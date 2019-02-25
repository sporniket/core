package com.sporniket.libre.lang.string;

import static com.sporniket.libre.lang.Empty.EMPTY_STRING_ARRAY;
import static com.sporniket.strings.StringPredicates.IS_EMPTY;
import static com.sporniket.strings.pipeline.StringTransformation.TO_HASH_MD5;
import static com.sporniket.strings.pipeline.StringTransformation.TRIM_START;

import com.sporniket.libre.lang.Empty;
import com.sporniket.strings.pipeline.StringTransformation;

/**
 * String utilities.
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
 * @since 12.06.01
 */
public class StringTools
{
	/**
	 * Mode for {@link StringTools#removeWhiteSpaces(String, SpaceRemovingMode)}.
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
	 * <i>The Sporniket Core Library &#8211; lang</i> is free software: you can redistribute it and/or modify it under the terms of
	 * the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at
	 * your option) any later version.
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
	 * @since 15.09.00
	 * @deprecated useless with the deprecation of {@link StringTools#removeWhiteSpaces(String, SpaceRemovingMode)}
	 */
	@Deprecated
	public static enum SpaceRemovingMode
	{
		LEADING_SPACES,
		TRAILING_SPACES,
		TWO_ENDS_SPACES
	}

	/**
	 * Letters.
	 * 
	 * @deprecated see the builtins StringFilter in sporniket-core-strings, in package com.sporniket.strings.pipeline
	 */
	@Deprecated
	public static final String FILTER__ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	/**
	 * Lower case letters.
	 * 
	 * @deprecated see the builtins StringFilter in sporniket-core-strings, in package com.sporniket.strings.pipeline
	 */
	@Deprecated
	public static final String FILTER__ALPHA_LOW = "abcdefghijklmnopqrstuvwxyz";

	/**
	 * Upper case letters.
	 * 
	 * @deprecated see the builtins StringFilter in sporniket-core-strings, in package com.sporniket.strings.pipeline
	 */
	@Deprecated
	public static final String FILTER__ALPHA_UP = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * Letters and digits.
	 * 
	 * @deprecated see the builtins StringFilter in sporniket-core-strings, in package com.sporniket.strings.pipeline
	 */
	@Deprecated
	public static final String FILTER__ALPHANUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	/**
	 * Lower case letters and digits.
	 * 
	 * @deprecated see the builtins StringFilter in sporniket-core-strings, in package com.sporniket.strings.pipeline
	 */
	@Deprecated
	public static final String FILTER__ALPHANUM_LOW = "abcdefghijklmnopqrstuvwxyz0123456789";

	/**
	 * Upper case letter and digits.
	 * 
	 * @deprecated see the builtins StringFilter in sporniket-core-strings, in package com.sporniket.strings.pipeline
	 */
	@Deprecated
	public static final String FILTER__ALPHANUM_UP = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	/**
	 * Digits.
	 * 
	 * @deprecated see the builtins StringFilter in sporniket-core-strings, in package com.sporniket.strings.pipeline
	 */
	@Deprecated
	public static final String FILTER__DIGIT = "0123456789";

	/**
	 * Letter, digits and underscore.
	 * 
	 * @deprecated see the builtins StringFilter in sporniket-core-strings, in package com.sporniket.strings.pipeline
	 */
	@Deprecated
	public static final String FILTER__FILENAME = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_.-";

	/**
	 * Letter, digits, underscore and slash.
	 * 
	 * @deprecated see the builtins StringFilter in sporniket-core-strings, in package com.sporniket.strings.pipeline
	 */
	@Deprecated
	public static final String FILTER__FILEPATH_UNIX = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_.-/";

	/**
	 * Letter, digits, underscore and backslash.
	 * 
	 * @deprecated see the builtins StringFilter in sporniket-core-strings, in package com.sporniket.strings.pipeline
	 */
	@Deprecated
	public static final String FILTER__FILEPATH_WIN = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_.-\\";

	/**
	 * Digits, minus, dot and comma.
	 * 
	 * @deprecated see the builtins StringFilter in sporniket-core-strings, in package com.sporniket.strings.pipeline
	 */
	@Deprecated
	public static final String FILTER__NUMBER_FLOAT = "0123456789-.,";

	/**
	 * Digits and minus sign.
	 * 
	 * @deprecated see the builtins StringFilter in sporniket-core-strings, in package com.sporniket.strings.pipeline
	 */
	@Deprecated
	public static final String FILTER__NUMBER_INT = "0123456789-";

	/**
	 * Tokenize a string and put the tokens in an Array.
	 * <p>
	 * This macro use the default separator of StringTokenizer.
	 * </p>
	 * 
	 * @param source
	 *            String to Tokenize
	 * @return the array of token (might be empty)
	 * @see java.util.StringTokenizer
	 * @see String#split(String)
	 * @deprecated use <code>String[] result = (null == source)?Empty.EMPTY_STRING_ARRAY:source.split("\\s") ;</code>
	 */
	public static String[] explode(final String source)
	{
		return (null == source) ? EMPTY_STRING_ARRAY : source.split("\\s");
	}

	/**
	 * A String filter.
	 * <p>
	 * A String filter keeps only autorised characters. The autorised character set is defined by a String containing any valid
	 * character. This string is called a filter.
	 * </p>
	 * <p>
	 * The <code>StringTools</code> class defines a few common filters.
	 * </p>
	 * TODO : use regular expressions
	 * 
	 * @param source
	 *            the String to filter
	 * @param filter
	 *            the filtering String
	 * @return the filtered String. If filter is empty, it returns the source.
	 * 
	 * @deprecated see the builtins StringFilter in sporniket-core-strings, in package com.sporniket.strings.pipeline
	 */
	@Deprecated
	public static String filterString(final String source, final String filter)
	{
		if ((null != filter) && (0 != filter.length()))
		{
			StringBuffer _acceptedBuffer = new StringBuffer();
			for (int _index = 0; _index < source.length(); _index++)
			{
				char _currentChar = source.charAt(_index);
				if (-1 < filter.indexOf((int) _currentChar))
				{
					_acceptedBuffer.append(_currentChar);
				}
			}
			return _acceptedBuffer.toString();
		} // if ((null != filter) && (0 != filter.length()))
		else
		{
			return source;
		} // else
	}

	/**
	 * Test if the string is null or "empty" (only white characters like space).
	 * 
	 * @param value
	 *            The String to test
	 * @return true if the string is null or "empty"
	 * 
	 * @deprecated use StringPredicates.IS_EMPTY.test(value).
	 */
	@Deprecated
	public static boolean isEmptyString(String value)
	{
		return IS_EMPTY.test(value);
	}

	/**
	 * Compute the hex string of the MD5 hash of the given value.
	 * 
	 * @param value
	 *            the value to hash.
	 * @return the hex string of the hash.
	 * @since 15.02.00
	 * @deprecated use StringTransformation.TO_HASH_MD5.transform(value).
	 */
	@Deprecated
	public static String hash(String value)
	{
		return TO_HASH_MD5.transform(value);
	}

	/**
	 * Remove whitespaces at the beginning and/or at the end of the given String.
	 * 
	 * @param value
	 *            the String to trim.
	 * @param mode
	 *            tells where to remove whitespaces.
	 * @return a trimmed String
	 * @since 15.09.00
	 * @deprecated use StringTransformation.TRIM_START, StringTransformation.TRIM_END and StringTransformation.TRIM
	 */
	@Deprecated
	public static String removeWhiteSpaces(String value, SpaceRemovingMode mode)
	{
		if (null == value)
		{
			throw new NullPointerException();
		}

		switch (mode)
		{
			case LEADING_SPACES:
				return TRIM_START.transform(value);
			case TRAILING_SPACES:
				return StringTransformation.TRIM_END.transform(value);
			default:
				return StringTransformation.TRIM.transform(value);
		}
	}
}
