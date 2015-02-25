package com.sporniket.libre.ui.action;

import java.util.Locale;

import javax.swing.Action;
import javax.swing.ImageIcon;

import com.sporniket.libre.lang.functor.Functor;
import com.sporniket.libre.lang.message.MessageProviderInterface;
import com.sporniket.libre.lang.string.StringTools;
import com.sporniket.libre.ui.KeyboardRepresentationTable;
import com.sporniket.libre.ui.icon.IconProvider;

/**
 * note : for jdk 1.6 and following
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
 * @param <IconLocationType>
 *            type that store the location of the icon.
 * 
 * @author David SPORN <david.sporn@sporniket.com>
 * @version 15.02.00
 * @since 12.06.01
 */
public class AdaptedAction<IconLocationType> extends FunctorBasedAction implements UserInterfaceAction<IconLocationType>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6677632061826632661L;

	private IconProvider<IconLocationType> myIconProvider;

	private MessageProviderInterface myMessageProvider = null;

	private UserInterfaceAction<IconLocationType> myUserInterfaceAction = new UserInterfaceActionImplementation<IconLocationType>();

	public AdaptedAction(Functor functor)
	{
		super(functor);
	}

	/**
	 * @param functor
	 * @param messageProvider
	 * @param urlProvider
	 * @param iconProvider
	 * @since 15.02.00
	 */
	public AdaptedAction(Functor functor, MessageProviderInterface messageProvider, 
			IconProvider<IconLocationType> iconProvider)
	{
		super(functor);
		myMessageProvider = messageProvider;
		myIconProvider = iconProvider;
	}

	/**
	 * @return
	 * @see com.sporniket.libre.ui.action.UserInterfaceAction#getCallback()
	 */
	public Functor getCallback()
	{
		return myUserInterfaceAction.getCallback();
	}

	/**
	 * @return
	 * @see com.sporniket.libre.ui.action.UserInterfaceAction#getIconForButton()
	 */
	public IconLocationType getIconForButton()
	{
		return myUserInterfaceAction.getIconForButton();
	}

	/**
	 * @return
	 * @see com.sporniket.libre.ui.action.UserInterfaceAction#getIconForMenu()
	 */
	public IconLocationType getIconForMenu()
	{
		return myUserInterfaceAction.getIconForMenu();
	}

	/**
	 * @return
	 * @see com.sporniket.libre.ui.action.UserInterfaceAction#getKeyboardShortcut()
	 */
	public String getKeyboardShortcut()
	{
		return myUserInterfaceAction.getKeyboardShortcut();
	}

	/**
	 * @return
	 * @see com.sporniket.libre.ui.action.UserInterfaceAction#getLabelDescription()
	 */
	public String getLabelDescription()
	{
		return myUserInterfaceAction.getLabelDescription();
	}

	/**
	 * @return
	 * @see com.sporniket.libre.ui.action.UserInterfaceAction#getLabelMessage()
	 */
	public String getLabelMessage()
	{
		return myUserInterfaceAction.getLabelMessage();
	}

	/**
	 * @return
	 * @see com.sporniket.libre.ui.action.UserInterfaceAction#getLocale()
	 */
	public Locale getLocale()
	{
		return myUserInterfaceAction.getLocale();
	}

	/**
	 * @return
	 * @see com.sporniket.libre.ui.action.UserInterfaceAction#isEnabled()
	 */
	public boolean isEnabled()
	{
		return myUserInterfaceAction.isEnabled();
	}

	/**
	 * Load the icon file.
	 * 
	 * @param location
	 *            Path to the file.
	 */
	private ImageIcon retrieveIcon(IconLocationType location)
	{
		try
		{
			return myIconProvider.retrieveIcon(location);
		}
		catch (Exception _exception)
		{
			System.err.println(_exception.getMessage());
		}
		return null;
	}

	/**
	 * @param callback
	 * @see com.sporniket.libre.ui.action.UserInterfaceAction#setCallback(com.sporniket.libre.lang.functor.Functor)
	 */
	public void setCallback(Functor callback)
	{
		myUserInterfaceAction.setCallback(callback);
		setFunctor(getCallback());
	}

	/**
	 * @param enabled
	 * @see com.sporniket.libre.ui.action.UserInterfaceAction#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled)
	{
		myUserInterfaceAction.setEnabled(enabled);
		setEnabled(enabled);
	}

	/**
	 * @param iconForButton
	 * @see com.sporniket.libre.ui.action.UserInterfaceAction#setIconForButton(java.lang.String)
	 */
	public void setIconForButton(IconLocationType iconForButton)
	{
		if (null != iconForButton)
		{
			myUserInterfaceAction.setIconForButton(iconForButton);
			ImageIcon _icon = retrieveIcon(getIconForButton());
			putValue(Action.LARGE_ICON_KEY, _icon);
		}
	}

	/**
	 * @param iconForMenu
	 * @see com.sporniket.libre.ui.action.UserInterfaceAction#setIconForMenu(java.lang.String)
	 */
	public void setIconForMenu(IconLocationType iconForMenu)
	{
		if (null != iconForMenu)
		{
			myUserInterfaceAction.setIconForMenu(iconForMenu);
			ImageIcon _icon = retrieveIcon(getIconForMenu());
			putValue(Action.SMALL_ICON, _icon);
		}
	}

	/**
	 * @param keyboardShortcut
	 * @see com.sporniket.libre.ui.action.UserInterfaceAction#setKeyboardShortcut(java.lang.String)
	 */
	public void setKeyboardShortcut(String keyboardShortcut)
	{
		if (!StringTools.isEmptyString(keyboardShortcut))
		{
			myUserInterfaceAction.setKeyboardShortcut(keyboardShortcut);
			putValue(Action.ACCELERATOR_KEY, KeyboardRepresentationTable.getKeyStroke(getKeyboardShortcut()));
		}
		else
		{
			putValue(Action.ACCELERATOR_KEY, null);
		}
	}

	/**
	 * @param labelDescription
	 * @see com.sporniket.libre.ui.action.UserInterfaceAction#setLabelDescription(java.lang.String)
	 */
	public void setLabelDescription(String labelDescription)
	{
		myUserInterfaceAction.setLabelDescription(labelDescription);
		putValue(Action.SHORT_DESCRIPTION, myMessageProvider.getMessage(getLabelDescription(), getLocale()));
	}

	/**
	 * @param labelMessage
	 * @see com.sporniket.libre.ui.action.UserInterfaceAction#setLabelMessage(java.lang.String)
	 */
	public void setLabelMessage(String labelMessage)
	{
		myUserInterfaceAction.setLabelMessage(labelMessage);
		putValue(Action.NAME, myMessageProvider.getMessage(getLabelMessage(), getLocale()));
	}

	/**
	 * @param locale
	 * @see com.sporniket.libre.ui.action.UserInterfaceAction#setLocale(java.util.Locale)
	 */
	public void setLocale(Locale locale)
	{
		myUserInterfaceAction.setLocale(locale);
		updateAction(this);
	}

	public void updateAction(Action action)
	{
		action.putValue(Action.NAME, myMessageProvider.getMessage(getLabelMessage(), getLocale()));
		action.putValue(Action.SHORT_DESCRIPTION, myMessageProvider.getMessage(getLabelDescription(), getLocale()));
		if (!StringTools.isEmptyString(getKeyboardShortcut()))
		{
			action.putValue(Action.ACCELERATOR_KEY, KeyboardRepresentationTable.getKeyStroke(getKeyboardShortcut()));
		}
		else
		{
			action.putValue(Action.ACCELERATOR_KEY, null);
		}
	}

}
