package com.sporniket.libre.ui.action;

import java.util.Locale;

import com.sporniket.libre.lang.functor.Functor;

/**
 * Basic implementation.
 * 
 * <p>
 * &copy; Copyright 2002-2016 David Sporn
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
 * @version 16.08.01
 * @since 12.06.01
 */
public class UserInterfaceActionImplementation<IconLocationType> implements UserInterfaceAction<IconLocationType>
{
	/**
	 * Default locale to use when undefined.
	 */
	private static final Locale DEFAULT__LOCALE = Locale.getDefault();

	private Functor myCallback = null;

	private boolean myEnabled = true;

	private IconLocationType myIconForButton = null;

	private IconLocationType myIconForMenu = null;

	private String myKeyboardShortcut = null;

	private String myLabelDescription = "labelDescription";

	private String myLabelMessage = "labelMessage";

	private Locale myLocale = DEFAULT__LOCALE;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.UserInterfaceActionTrait#getCallback()
	 */
	public Functor getCallback()
	{
		return myCallback;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.UserInterfaceActionTrait#getIconForButton()
	 */
	public IconLocationType getIconForButton()
	{
		return myIconForButton;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.UserInterfaceActionTrait#getIconForMenu()
	 */
	public IconLocationType getIconForMenu()
	{
		return myIconForMenu;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.UserInterfaceActionTrait#getKeyboardShortcut()
	 */
	public String getKeyboardShortcut()
	{
		return myKeyboardShortcut;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.UserInterfaceActionTrait#getLabelDescription()
	 */
	public String getLabelDescription()
	{
		return myLabelDescription;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.UserInterfaceActionTrait#getLabelMessage()
	 */
	public String getLabelMessage()
	{
		return myLabelMessage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.UserInterfaceActionTrait#getLocale()
	 */
	public Locale getLocale()
	{
		return myLocale;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.UserInterfaceActionTrait#isEnabled()
	 */
	public boolean isEnabled()
	{
		return myEnabled;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.UserInterfaceActionTrait#setCallback(com.sporniket.libre.lang.functor.FunctorInterface)
	 */
	public void setCallback(Functor callback)
	{
		myCallback = callback;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.UserInterfaceActionTrait#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled)
	{
		myEnabled = enabled;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.UserInterfaceActionTrait#setIconForButton(java.lang.String)
	 */
	public void setIconForButton(IconLocationType iconForButton)
	{
		myIconForButton = iconForButton;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.UserInterfaceActionTrait#setIconForMenu(java.lang.String)
	 */
	public void setIconForMenu(IconLocationType iconForMenu)
	{
		myIconForMenu = iconForMenu;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.UserInterfaceActionTrait#setKeyboardShortcut(java.lang.String)
	 */
	public void setKeyboardShortcut(String keyboardShortcut)
	{
		myKeyboardShortcut = keyboardShortcut;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.UserInterfaceActionTrait#setLabelDescription(java.lang.String)
	 */
	public void setLabelDescription(String labelDescription)
	{
		myLabelDescription = labelDescription;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.UserInterfaceActionTrait#setLabelMessage(java.lang.String)
	 */
	public void setLabelMessage(String labelMessage)
	{
		myLabelMessage = labelMessage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.UserInterfaceActionTrait#setLocale(Locale)
	 */
	public void setLocale(Locale locale)
	{
		myLocale = (null != locale) ? locale : DEFAULT__LOCALE;
	}

}
