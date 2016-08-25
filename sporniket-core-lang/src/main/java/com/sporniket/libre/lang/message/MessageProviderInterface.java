package com.sporniket.libre.lang.message;

import java.util.Locale;

/**
 * Models a service that returns a message from a given key.
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
public interface MessageProviderInterface
{
	/**
	 * Return a message.
	 * 
	 * Minimal version.
	 * 
	 * @param key
	 *            the key for retrieving the message.
	 * @return the message
	 */
	public String getMessage(String key);

	/**
	 * Return a message.
	 * 
	 * light version : specify which locale.
	 * 
	 * @param key
	 *            the key for retrieving the message.
	 * @param locale
	 *            the locale to get the appropriate translation of the message.
	 * @return the message
	 */
	public String getMessage(String key, Locale locale);

	/**
	 * Return a message.
	 * 
	 * Full version : specify which locale, and the parameter list.
	 * 
	 * @param key
	 *            the key for retrieving the message.
	 * @param locale
	 *            the locale to get the appropriate translation of the message.
	 * @param parameters
	 *            optionnal parameter to embed in the message.
	 * @return the message
	 */
	public String getMessage(String key, Locale locale, Object[] parameters);

	/**
	 * Return a message.
	 * 
	 * light version : specify the parameter list.
	 * 
	 * @param key
	 *            the key for retrieving the message.
	 * @param parameters
	 *            optionnal parameter to embed in the message.
	 * @return the message
	 */
	public String getMessage(String key, Object[] parameters);

}
