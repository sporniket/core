/**
 * 
 */
package com.sporniket.libre.lang.message;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import com.sporniket.libre.lang.CollectionTools;

/**
 * ResourceBundle based message provider.
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
public class ResourceBundleMessageProvider implements MessageProviderInterface
{
	/**
	 * How to deal with missing messages.
	 * @since 15.02.00
	 */
	private BehaviourOnMissingMessage myBehaviourOnMissingMessage = BehaviourOnMissingMessage.DEFAULT_BEHAVIOUR;

	private Map<Locale, ResourceBundle> myResourceBundleMap = new HashMap<Locale, ResourceBundle>();
	
	private String myResourceBundleName = null;

	/**
	 * Provider using the default behaviour for missing message.
	 * @param resourceBundleName name of the resource bundle
	 * @since 12.06.01
	 */
	public ResourceBundleMessageProvider(String resourceBundleName)
	{
		myResourceBundleName = resourceBundleName;
	}

	/**
	 * @param resourceBundleName name of the resource bundle
	 * @param behaviourOnMissingMessage behaviour to use when the resource message is missing.
	 * @since 15.02.00
	 */
	public ResourceBundleMessageProvider(String resourceBundleName, BehaviourOnMissingMessage behaviourOnMissingMessage)
	{
		this(resourceBundleName);
		setBehaviourOnMissingMessage(behaviourOnMissingMessage);
	}

	/**
	 * Get behaviourOnMissingMessage.
	 * @return the behaviourOnMissingMessage
	 * @since 15.02.00
	 */
	public BehaviourOnMissingMessage getBehaviourOnMissingMessage()
	{
		return myBehaviourOnMissingMessage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.lang.MessageProviderTrait#getMessage(java.lang.String)
	 */
	public String getMessage(String key)
	{
		return getMessage(key, Locale.getDefault());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.lang.MessageProviderTrait#getMessage(java.lang.String, java.util.Locale)
	 */
	public String getMessage(String key, Locale locale)
	{
		if (null == locale)
		{
			return getMessage(key);
		}
		return CollectionTools.getString(getResourceBundle(locale), key, getBehaviourOnMissingMessage().getValueOnMissingMessage(key));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.lang.MessageProviderTrait#getMessage(java.lang.String, java.util.Locale, java.lang.Object[])
	 */
	public String getMessage(String key, Locale locale, Object[] parameters)
	{
		if (null == locale)
		{
			return getMessage(key, parameters);
		}
		if (null == parameters)
		{
			return getMessage(key, locale);
		}
		if (null != key)
		{
			MessageFormat _messageFormat = new MessageFormat(getMessage(key, locale));
			return _messageFormat.format(parameters);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.lang.MessageProviderTrait#getMessage(java.lang.String, java.lang.Object[])
	 */
	public String getMessage(String key, Object[] parameters)
	{
		return getMessage(key, Locale.getDefault(), parameters);
	}

	private ResourceBundle getResourceBundle(Locale locale)
	{
		if (myResourceBundleMap.containsKey(locale))
		{
			ResourceBundle _resourceBundle = myResourceBundleMap.get(locale);
			return _resourceBundle;
		}
		else
		{
			try
			{
				ResourceBundle _resourceBundle = ResourceBundle.getBundle(getResourceBundleName(), locale);
				myResourceBundleMap.put(locale, _resourceBundle);
				return _resourceBundle;
			}
			catch (Exception _exception)
			{
				// silently fails...
			}
		}
		return null;

	}

	/**
	 * Read the resourceBundleName property.
	 * 
	 * @return the resourceBundleName
	 */
	public String getResourceBundleName()
	{
		return myResourceBundleName;
	}

	/**
	 * Change behaviourOnMissingMessage.
	 * @param behaviourOnMissingMessage the new value of behaviourOnMissingMessage.
	 * @since 15.02.00
	 */
	public void setBehaviourOnMissingMessage(BehaviourOnMissingMessage behaviourOnMissingMessage)
	{
		myBehaviourOnMissingMessage = behaviourOnMissingMessage;
	}

	/**
	 * Write the resourceBundleName property.
	 * 
	 * @param resourceBundleName
	 *            the resourceBundleName to set
	 */
	public void setResourceBundleName(String resourceBundleName)
	{
		myResourceBundleName = resourceBundleName;
		myResourceBundleMap.clear();
	}

}
