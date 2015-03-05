/**
 * 
 */
package com.sporniket.libre.lang.sgml;

import com.sporniket.libre.lang.string.AbstractStringCodec;

/**
 * Codec for coding/decoding sgml attribute.
 * 
 * <p>
 * Used for Xml and Html for instance.
 * </p>
 * 
 * <p>
 * &copy; Copyright 2002-2012 David Sporn
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
 * @version 15.02.00
 * @since 12.06.01
 */
class SgmlAttributeCodec extends AbstractStringCodec
{

	/**
	 * Singleton instance.
	 */
	private static final SgmlAttributeCodec THE_INSTANCE = new SgmlAttributeCodec();

	/**
	 * Return the singleton instance.
	 * 
	 * @return
	 */
	public static SgmlAttributeCodec getInstance()
	{
		return THE_INSTANCE;
	}

	/**
	 * Special SGML chars in encoded form.
	 * 
	 * @author SPORN
	 * 
	 */
	private static class EncodedChar
	{
		private static final String AMPERSAND = "&amp;";

		private static final String GREATER_THAN = "&gt;";

		private static final String LOWER_THAN = "&lt;";

		private static final String QUOTE = "&quot;";
	}

	/**
	 * Special SGML chars in raw form.
	 * 
	 * @author SPORN
	 * 
	 */
	private static class RawChar
	{
		private static final String AMPERSAND = "&";

		private static final String GREATER_THAN = ">";

		private static final String LOWER_THAN = "<";

		private static final String QUOTE = "\"";
	}

	@Override
	protected StringBuffer doDecode(String data, StringBuffer buffer)
	{
		if (null != data)
		{
			String _result = data;
			_result = _result.replace(EncodedChar.AMPERSAND, RawChar.AMPERSAND);
			_result = _result.replace(EncodedChar.LOWER_THAN, RawChar.LOWER_THAN);
			_result = _result.replace(EncodedChar.GREATER_THAN, RawChar.GREATER_THAN);
			_result = _result.replace(EncodedChar.QUOTE, RawChar.QUOTE);
			buffer.append(_result);
		}
		return buffer;
	}

	@Override
	protected StringBuffer doEncode(String data, StringBuffer buffer)
	{
		if (null != data)
		{
			String _result = data;
			_result = _result.replace(RawChar.AMPERSAND, EncodedChar.AMPERSAND);
			_result = _result.replace(RawChar.LOWER_THAN, EncodedChar.LOWER_THAN);
			_result = _result.replace(RawChar.GREATER_THAN, EncodedChar.GREATER_THAN);
			_result = _result.replace(RawChar.QUOTE, EncodedChar.QUOTE);
			buffer.append(_result);
		}
		return buffer;
	}

}
