/**
 * 
 */
package com.sporniket.strings.pipeline;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

/**
 * Model of an elementary String transformation, and builtin usual transformations .
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
public interface StringTransformation
{
	// FIXME to sha-1, sha-256, url encode/decode

	public static final StringTransformation NULL_TO_EMPTY = t -> null == t ? "" : t;

	public static final StringTransformation TO_HASH_MD5 = t -> {
		try
		{
			final MessageDigest _hasher = MessageDigest.getInstance("MD5");
			byte[] _sourceBytes = t.getBytes(StandardCharsets.UTF_8);
			_hasher.update(_sourceBytes);
			byte[] _hashBytes = _hasher.digest();
			return DatatypeConverter.printHexBinary(_hashBytes);
		}
		catch (NoSuchAlgorithmException _exception)
		{
			throw new RuntimeException(_exception);
		}
	};

	public static final StringTransformation TO_LOWERCASE = t -> t.toLowerCase();

	public static final StringTransformation TO_UPPERCASE = t -> t.toUpperCase();

	public static final StringTransformation TRIM = t -> t.trim();

	public static final StringTransformation TRIM_START = t -> {
		String trimmed = t.trim();
		return 0 == trimmed.length() //
				? trimmed
				: t.substring(t.indexOf(trimmed.charAt(0)));
	};

	public static final StringTransformation TRIM_END = t -> {
		String trimmed = t.trim();
		return 0 == trimmed.length() //
				? trimmed
				: t.substring(0, trimmed.length() + t.indexOf(trimmed.charAt(0)));
	};

	String transform(String input);
}
