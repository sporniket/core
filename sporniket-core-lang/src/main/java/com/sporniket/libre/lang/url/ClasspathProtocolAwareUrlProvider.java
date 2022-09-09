/**
 * 
 */
package com.sporniket.libre.lang.url;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Url provider that recognize urls <code>classpath:url/to/file</code>, url using other protocols are directly created by the
 * standard <code>URL</code> class.
 * 
 * <p>
 * &copy; Copyright 2002-2022 David Sporn
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
 * @version 19.04.00
 * @since 16.08.02
 */
public class ClasspathProtocolAwareUrlProvider implements UrlProvider
{
	private static final String PROTOCOL = "classpath:";

	@Override
	public URL getUrl(String path) throws UrlProviderException
	{
		URL _result;
		if (path.startsWith(PROTOCOL))
		{
			String _path = path.substring(PROTOCOL.length());
			_result = getClass().getClassLoader().getResource(_path);
		}
		else
		{
			try
			{
				_result = new URL(path);
			}
			catch (MalformedURLException _exception)
			{
				throw new UrlProviderException(_exception);
			}
		}

		return _result;
	}
}
