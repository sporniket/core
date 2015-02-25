package com.sporniket.libre.ui.action;

import java.net.URL;
import java.util.Locale;

import com.sporniket.libre.lang.functor.FunctorFactory;
import com.sporniket.libre.lang.message.MessageProviderInterface;
import com.sporniket.libre.lang.string.StringTools;
import com.sporniket.libre.lang.url.UrlProvider;
import com.sporniket.libre.lang.url.UrlProviderException;

/**
 * Utilities class.
 * 
 * <p>
 * &copy; Copyright 2002-2012 David Sporn
 * </p>
 * <hr />
 * 
 * <p>
 * This file is part of <i>The Sporniket Core Library &#8211; ui</i>.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; ui</i> is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; ui</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for
 * more details.
 * 
 * <p>
 * You should have received a copy of the GNU Lesser General Public License along with <i>The Sporniket Core Library &#8211; ui</i>.
 * If not, see <http://www.gnu.org/licenses/>. 2
 * 
 * <hr />
 * 
 * @author David SPORN <david.sporn@sporniket.com>
 * @version 15.02.00
 * @since 12.06.01
 */
public class Utils
{
	public static final String TOKEN_CALLBACK = "callback";

	public static final String TOKEN_ICON_FOR_BUTTON = "iconForButton";

	public static final String TOKEN_ICON_FOR_MENU = "iconForMenu";

	public static final String TOKEN_KEYBOARD_SHORTCUT = "keyboardShortcut";

	public static final String TOKEN_LABEL_DESCRIPTION = "labelDescription";

	public static final String TOKEN_LABEL_MESSAGE = "labelMessage";

	public static final String TOKEN_SEPARATOR = ".";

	/**
	 * Read the data of an UserInterfaceAction from a properties file.
	 * 
	 * The properties files should provide the following properties :
	 * <ul>
	 * <li>[prefix].callback</li>
	 * <li>[prefix].iconForButton</li>
	 * <li>[prefix].iconForMenu</li>
	 * <li>[prefix].keyboardShrotcut</li>
	 * <li>[prefix].labelDescription</li>
	 * <li>[prefix].labelMessage</li>
	 * </ul>
	 * 
	 * @param action
	 *            the action instance to configure, not null.
	 * @param messageProvider
	 *            the message provider that retrieve the values.
	 * @param prefix
	 *            the prefix.
	 * @param locale
	 *            the locale.
	 * @param callbackProvider
	 *            an instance that contains the method referenced as "callback"
	 * @return the given action instance, configured with data extracted from the message provider.
	 */
	public static UserInterfaceAction<URL> retrieveActionDefinitionFromMessageProvider(UserInterfaceAction<URL> action,
			MessageProviderInterface messageProvider, String prefix, Locale locale, Object callbackProvider, UrlProvider urlProvider)
	{
		// sanity check
		if (null == action)
		{
			throw new NullPointerException("action must not be null");
		}
		if (null == messageProvider)
		{
			throw new NullPointerException("messageProvider must not be null");
		}
		if (null == callbackProvider)
		{
			throw new NullPointerException("callbackProvider must not be null");
		}
		String _prefix = (null != prefix) ? prefix.trim() + TOKEN_SEPARATOR : TOKEN_SEPARATOR;

		// ok, do the processing
		try
		{
			action.setCallback(FunctorFactory.instanciateFunctorAsAnInstanceMethodWrapper(callbackProvider,
					messageProvider.getMessage(_prefix + TOKEN_CALLBACK, locale)));
		}
		catch (Exception _exception)
		{
			throw new ActionException(_exception);
		}
		try
		{
			String _location = messageProvider.getMessage(_prefix + TOKEN_ICON_FOR_BUTTON, locale);
			if (!StringTools.isEmptyString(_location))
			{
				action.setIconForButton(urlProvider.getUrl(_location));
			}
		}
		catch (UrlProviderException _exception)
		{
			// throw new ActionException(_exception);
		}
		try
		{
			String _location = messageProvider.getMessage(_prefix + TOKEN_ICON_FOR_MENU, locale);
			if (!StringTools.isEmptyString(_location))
			{
				action.setIconForMenu(urlProvider.getUrl(_location));
			}
		}
		catch (UrlProviderException _exception)
		{
			// throw new ActionException(_exception);
		}
		action.setKeyboardShortcut(messageProvider.getMessage(_prefix + TOKEN_KEYBOARD_SHORTCUT, locale));
		action.setLabelDescription(messageProvider.getMessage(_prefix + TOKEN_LABEL_DESCRIPTION, locale));
		action.setLabelMessage(messageProvider.getMessage(_prefix + TOKEN_LABEL_MESSAGE, locale));

		// done
		return action;

	}
}
