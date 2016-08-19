/**
 * 
 */
package com.sporniket.libre.lang.string;

/**
 * String codec base implementation.
 * 
 * <p>
 * This class provide some implementation details that will be common to almost all implementation. In effect, the only methods left
 * to implement will be the methods where the StringBuffer must be provided.
 * </p>
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
 * @version 16.08.01
 * @since 12.06.01
 */
public abstract class AbstractStringCodec implements StringCodecInterface
{

	public StringBuffer decode(String data)
	{
		return doDecode(data, new StringBuffer());
	}

	public StringBuffer decode(String data, StringBuffer buffer)
	{
		if (null == buffer)
		{
			throw new IllegalArgumentException("Buffer should not be null");
		}
		return doDecode(data, buffer);
	}

	/**
	 * Decode the data into the buffer.
	 * 
	 * @param data
	 *            data to decode
	 * @param buffer
	 *            MUST be non null.
	 * @return the updated buffer.
	 */
	protected abstract StringBuffer doDecode(String data, StringBuffer buffer);

	/**
	 * Encode the data into the buffer.
	 * 
	 * @param data data to encode.
	 * @param buffer
	 *            MUST be non null.
	 * @return the updated buffer.
	 */
	protected abstract StringBuffer doEncode(String data, StringBuffer buffer);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.lang.string.StringCodecInterface#encode(java.lang.String)
	 */
	public StringBuffer encode(String data)
	{
		return doEncode(data, new StringBuffer());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.lang.string.StringCodecInterface#encode(java.lang.String, java.lang.StringBuffer)
	 */
	public StringBuffer encode(String data, StringBuffer buffer)
	{
		if (null == buffer)
		{
			throw new IllegalArgumentException("Buffer should not be null");
		}
		return doEncode(data, buffer);
	}
}
