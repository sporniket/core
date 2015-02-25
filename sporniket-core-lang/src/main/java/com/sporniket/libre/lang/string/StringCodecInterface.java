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
 * &copy; Copyright 2002-2012 David Sporn
 * </p>
 * <hr />
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
 * lang</i>. If not, see <http://www.gnu.org/licenses/>. 2
 * 
 * <hr />
 * 
 * @author David SPORN <david.sporn@sporniket.com>
 * @version 15.02.00
 * @since 12.06.01
 */
public interface StringCodecInterface
{
	/**
	 * Encode the specified data and return a StringBuffer containing the encoded data.
	 * 
	 * @param data
	 * @return
	 */
	StringBuffer encode(String data);

	/**
	 * Encode the specified data and return the StringBuffer provided, where the encoded data has been stored.
	 * 
	 * @param data
	 * @return
	 */
	StringBuffer encode(String data, StringBuffer buffer);

	/**
	 * Decode the specified data and return a StringBuffer containing the decoded data.
	 * 
	 * @param data
	 * @return
	 */
	StringBuffer decode(String data);

	/**
	 * Decode the specified data and return the StringBuffer provided, where the decoded data has been stored.
	 * 
	 * @param data
	 * @return
	 */
	StringBuffer decode(String data, StringBuffer buffer);
}
