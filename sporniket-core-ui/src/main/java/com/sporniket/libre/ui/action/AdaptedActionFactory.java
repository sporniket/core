package com.sporniket.libre.ui.action;

import java.util.Locale;

import com.sporniket.libre.lang.FactoryNotReadyException;
import com.sporniket.libre.lang.functor.Functor;
import com.sporniket.libre.lang.message.MessageProviderInterface;
import com.sporniket.libre.ui.icon.IconProvider;

/**
 * 
 * <p>
 * &copy; Copyright 2002-2022 David Sporn
 * </p>
 * <hr>
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
 * If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
 * 
 * <hr>
 * 
 * @author David SPORN 
 * @version 19.04.00
 * @since 12.06.01
 */
public class AdaptedActionFactory<IconLocationType> implements UserInterfaceActionFactory<IconLocationType>
{

	private static final String COMMA = ", ";

	private static final String DOT = ". ";

	private static final String NO_ICON_PROVIDER = "no icon provider";

	private static final String NO_MESSAGE_PROVIDER = "no message provider";

	private IconProvider<IconLocationType> myIconProvider;

	/**
	 * self diagnostic flag, <code>true</code> when the factory is ready to use.
	 */
	private boolean myIsReady = false;

	private MessageProviderInterface myMessageProvider = null;

	/**
	 * human friendly self diagnostic state message.
	 */
	private String myReasonWhyNotReady = NO_MESSAGE_PROVIDER + COMMA + NO_ICON_PROVIDER + DOT;

	/**
	 * @param messageProvider message provider.
	 * @param iconProvider icon provider.
	 */
	public AdaptedActionFactory(MessageProviderInterface messageProvider,
			IconProvider<IconLocationType> iconProvider)
	{
		myMessageProvider = messageProvider;
		myIconProvider = iconProvider;
		updateReadyness();
	}

	private void checkIfReady() throws FactoryNotReadyException
	{
		if (!myIsReady)
		{
			throw new FactoryNotReadyException(myReasonWhyNotReady);
		}
	}

	/* (non-Javadoc)
	 * @see com.sporniket.libre.ui.action.UserInterfaceActionFactory#create(java.lang.String, java.lang.String, java.lang.String, java.lang.Object, java.lang.Object, java.util.Locale, com.sporniket.libre.lang.functor.Functor)
	 */
	public UserInterfaceAction<IconLocationType> create(String label, String description, String keyboardShortcut,
			IconLocationType iconForMenu, IconLocationType iconForButton, Locale locale, Functor callback)
			throws FactoryNotReadyException
	{
		checkIfReady();

		UserInterfaceAction<IconLocationType> _action = new AdaptedAction<IconLocationType>(callback, myMessageProvider, 
				myIconProvider);
		_action.setLabelMessage(label);
		_action.setLabelDescription(description);
		_action.setKeyboardShortcut(keyboardShortcut);
		_action.setIconForMenu(iconForMenu);
		_action.setIconForButton(iconForButton);
		_action.setLocale(locale);

		return _action;
	}

	/* (non-Javadoc)
	 * @see com.sporniket.libre.ui.action.UserInterfaceActionFactory#setIconProvider(com.sporniket.libre.ui.icon.IconProvider)
	 */
	public void setIconProvider(IconProvider<IconLocationType> iconProvider)
	{
		myIconProvider = iconProvider;
		updateReadyness();
	}

	/* (non-Javadoc)
	 * @see com.sporniket.libre.ui.action.UserInterfaceActionFactory#setMessageProvider(com.sporniket.libre.lang.message.MessageProviderInterface)
	 */
	public void setMessageProvider(MessageProviderInterface messageProvider)
	{
		myMessageProvider = messageProvider;
		updateReadyness();
	}

	/**
	 * Do the self diagnostic, and update the status.
	 */
	private void updateReadyness()
	{
		StringBuffer _buffer = new StringBuffer();
		if (null == myMessageProvider)
		{
			_buffer.append(NO_MESSAGE_PROVIDER);
		}
		if (null == myIconProvider)
		{
			if (_buffer.length() > 0)
			{
				_buffer.append(COMMA);
			}
			_buffer.append(NO_ICON_PROVIDER);
		}
		if (_buffer.length() > 0)
		{
			_buffer.append(DOT);
		}
		myReasonWhyNotReady = _buffer.toString();
		myIsReady = (_buffer.length() == 0);

	}

}
