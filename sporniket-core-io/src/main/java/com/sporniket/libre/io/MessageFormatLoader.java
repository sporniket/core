/**
 * 
 */

package com.sporniket.libre.io;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;

/**
 * Utility class to load a {@link MessageFormat} from various sources.
 * 
 * <p>
 * &copy; Copyright 2002-2022 David Sporn
 * </p>
 * <hr>
 * 
 * <p>
 * This file is part of <i>The Sporniket Core Library &#8211; io</i>.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; io</i> is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; io</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for
 * more details.
 * 
 * <p>
 * You should have received a copy of the GNU Lesser General Public License along with <i>The Sporniket Core Library &#8211; io</i>.
 * If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
 * 
 * <hr>
 * 
 * @author David SPORN 
 * @version 22.11.00
 * @since 12.06.01
 */
// FIXME provide a loader for File and Reader
public final class MessageFormatLoader
{

	/**
	 * Load a {@link MessageFormat} from the specified stream.
	 * 
	 * @param source source stream.
	 * @return a {@link MessageFormat}
	 * @throws IOException if there is a problem to deal with.
	 */
	public static MessageFormat load(InputStream source) throws IOException
	{
		return load(source, null);
	}

	/**
	 * Load a {@link MessageFormat} from the specified stream encoded using the given encoding.
	 * 
	 * @param source source stream.
	 * @param encoding
	 *            the name of the charset used to decode the stream.
	 * @return a {@link MessageFormat}
	 * @throws IOException if there is a problem to deal with.
	 */
	public static MessageFormat load(InputStream source, Encoding encoding) throws IOException
	{
		MessageFormat _result = null;
		String _text = TextLoader.getInstance(encoding).load(source);
		_result = new MessageFormat(_text);
		return _result;
	}
}
