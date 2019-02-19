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
 * @author dsporn
 *
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
