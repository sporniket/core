package com.sporniket.libre.lang;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * Macros for collections and resources.
 * <p>
 * These macros are meant to be used when retrieving a value from a <code>ResourceBundle</code> or a <code>Collection</code> and
 * wanting a default value if it fails.
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
public class CollectionTools
{
	/**
	 * Return an object from a ResourceBundle.
	 * 
	 * @param source
	 *            ResourceBundle from which extract the value
	 * @param key
	 *            The key to retrieve the value
	 * @param defaultValue
	 *            When the wanted value doesn't exist, return this one
	 * @return The wanted object or defaulValue
	 */
	public static Object getObject(final ResourceBundle source, final String key, final Object defaultValue)
	{
		try
		{
			return source.getObject(key);
		}
		catch (Exception _exception)
		{
			return defaultValue;
		}
	}

	/**
	 * Return an object from a Map.
	 * 
	 * @param source
	 *            Map from which extract the value
	 * @param key
	 *            The key to retrieve the value
	 * @param defaultValue
	 *            When the wanted value doesn't exist, return this one
	 * @return The wanted object or defaulValue
	 */
	public static Object getObject(final Map<String, Object> source, final String key, final Object defaultValue)
	{
		try
		{
			return source.get(key);
		}
		catch (Exception _exception)
		{
			return defaultValue;
		}
	}

	/**
	 * Return an string from a ResourceBundle.
	 * 
	 * @param source
	 *            ResourceBundle from which extract the value
	 * @param key
	 *            The key to retrieve the value
	 * @param defaultValue
	 *            When the wanted value doesn't exist, return this one
	 * @return The wanted object or defaulValue
	 */
	public static String getString(final ResourceBundle source, final String key, final String defaultValue)
	{
		try
		{
			return source.getString(key);
		}
		catch (Exception _exception)
		{
			return defaultValue;
		}
	}

}
