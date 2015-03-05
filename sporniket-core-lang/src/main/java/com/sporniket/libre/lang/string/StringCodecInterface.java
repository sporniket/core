/**
 * 
 */
package com.sporniket.libre.lang.string;

/**
 * Interface for coding/decoding strings.
 * 
 * <p>
 * Some String manipulation for exchanging data involves transcoding string data to address some data format restriction or
 * implement said data format (e.g. encoding xml attribute value, uuencoding, etc...)
 * </p>
 * 
 * <p>
 * &copy; Copyright 2002-2015 David Sporn
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
public interface StringCodecInterface
{
	/**
	 * Encode the specified data and return a StringBuffer containing the encoded data.
	 * 
	 * @param data data to encode.
	 * @return a buffer containing the encoded data.
	 */
	StringBuffer encode(String data);

	/**
	 * Encode the specified data and return the StringBuffer provided, where the encoded data has been stored.
	 * 
	 * @param data data to encode.
	 * @param buffer the buffer to which the encoded data will be appended.
	 * @return the updated buffer.
	 */
	StringBuffer encode(String data, StringBuffer buffer);

	/**
	 * Decode the specified data and return a StringBuffer containing the decoded data.
	 * 
	 * @param data data to decode.
	 * @return a buffer containing the decoded data.
	 */
	StringBuffer decode(String data);

	/**
	 * Decode the specified data and return the StringBuffer provided, where the decoded data has been stored.
	 * 
	 * @param data data to decode.
	 * @param buffer the buffer to which the decoded data will be appended.
	 * @return the updated buffer.
	 */
	StringBuffer decode(String data, StringBuffer buffer);
}
