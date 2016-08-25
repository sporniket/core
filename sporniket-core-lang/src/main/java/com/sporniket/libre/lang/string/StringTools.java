package com.sporniket.libre.lang.string;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;

import com.sporniket.libre.lang.Constants;
import com.sporniket.libre.lang.DataTools;

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
 * @version 16.08.02
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
	 * @version 16.08.02
	 * @since 15.09.00
	 */
	public static enum SpaceRemovingMode
	{
		LEADING_SPACES,
		TRAILING_SPACES,
		TWO_ENDS_SPACES
	}

	private static final String explode__DEFAULT_PATTERN = "\\s";

	private static final String[] explode__EMPTY_ARRAY = new String[0];

	/**
	 * Letters.
	 */
	public static final String FILTER__ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	/**
	 * Lower case letters.
	 */
	public static final String FILTER__ALPHA_LOW = "abcdefghijklmnopqrstuvwxyz";

	/**
	 * Upper case letters.
	 */
	public static final String FILTER__ALPHA_UP = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * Letters and digits.
	 */
	public static final String FILTER__ALPHANUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	/**
	 * Lower case letters and digits.
	 */
	public static final String FILTER__ALPHANUM_LOW = "abcdefghijklmnopqrstuvwxyz0123456789";

	/**
	 * Upper case letter and digits.
	 */
	public static final String FILTER__ALPHANUM_UP = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	/**
	 * Digits.
	 */
	public static final String FILTER__DIGIT = "0123456789";

	/**
	 * Letter, digits and underscore.
	 */
	public static final String FILTER__FILENAME = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_.-";

	/**
	 * Letter, digits, underscore and slash.
	 */
	public static final String FILTER__FILEPATH_UNIX = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_.-/";

	/**
	 * Letter, digits, underscore and backslash.
	 */
	public static final String FILTER__FILEPATH_WIN = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_.-\\";

	/**
	 * Digits, minus, dot and comma.
	 */
	public static final String FILTER__NUMBER_FLOAT = "0123456789-.,";

	/**
	 * Digits and minus sign.
	 */
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
	 */
	public static String[] explode(final String source)
	{
		if (null == source)
		{
			return explode__EMPTY_ARRAY;
		}
		return source.split(explode__DEFAULT_PATTERN);
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
	 */
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
	 */
	public static boolean isEmptyString(String value)
	{
		return ((null == value) || (0 == value.trim().length()));
	}

	/**
	 * Compute the hex string of the MD5 hash of the given value.
	 * 
	 * @param value
	 *            the value to hash.
	 * @return the hex string of the hash.
	 * @since 15.02.00
	 */
	public static String hash(String value)
	{
		try
		{
			final MessageDigest _filenameHasher = MessageDigest.getInstance(Constants.HashAlgorithmNames.MD5);
			byte[] _sourceBytes = value.getBytes(Constants.CharsetNames.UTF8);
			_filenameHasher.update(_sourceBytes);
			byte[] _hashBytes = _filenameHasher.digest();
			return DataTools.convertToPaddedHexString(_hashBytes);
		}
		catch (NoSuchAlgorithmException _exception)
		{
			throw new RuntimeException(_exception);
		}
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
	 */
	public static String removeWhiteSpaces(String value, SpaceRemovingMode mode)
	{
		if (null == value)
		{
			throw new NullPointerException();
		}
		int _resultStart = 0;
		int _resultEnd = value.length();

		// remove leading spaces
		if (SpaceRemovingMode.LEADING_SPACES == mode || SpaceRemovingMode.TWO_ENDS_SPACES == mode)
		{
			for (int _pos = 0; _pos < _resultEnd; _pos++)
			{
				char _currentChar = value.charAt(_pos);
				if (!removeWhiteSpaces__isWhiteSpace(_currentChar))
				{
					break;
				}
				_resultStart = _pos + 1;
			}
		}

		// remove trailing spaces
		if (SpaceRemovingMode.TRAILING_SPACES == mode || SpaceRemovingMode.TWO_ENDS_SPACES == mode)
		{
			for (int _pos = _resultEnd - 1; _pos >= _resultStart; _pos--)
			{
				char _currentChar = value.charAt(_pos);
				if (!removeWhiteSpaces__isWhiteSpace(_currentChar))
				{
					break;
				}
				_resultEnd = _pos;
			}
		}

		return value.substring(_resultStart, _resultEnd);
	}

	/**
	 * @param value
	 *            char to test.
	 * @return <code>true</code> if the given char is ' '(space) or tabulation.
	 * @since 15.09.00
	 */
	private static boolean removeWhiteSpaces__isWhiteSpace(char value)
	{
		return value == ' ' || value == '\t';
	}

}
