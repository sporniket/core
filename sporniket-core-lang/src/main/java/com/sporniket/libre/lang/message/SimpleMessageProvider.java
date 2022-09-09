package com.sporniket.libre.lang.message;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * Simple implementation : the key is the message.
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
 * @since 12.06.01
 */
public class SimpleMessageProvider implements MessageProviderInterface
{

	public String getMessage(String key)
	{
		return key;
	}

	public String getMessage(String key, Locale locale)
	{
		return getMessage(key);
	}

	public String getMessage(String key, Locale locale, Object[] parameters)
	{
		return getMessage(key, parameters);
	}

	public String getMessage(String key, Object[] parameters)
	{
		if (null == parameters)
		{
			return getMessage(key);
		}
		if (null != key)
		{
			MessageFormat _messageFormat = new MessageFormat(key);
			return _messageFormat.format(parameters);
		}
		return null;
	}

}
