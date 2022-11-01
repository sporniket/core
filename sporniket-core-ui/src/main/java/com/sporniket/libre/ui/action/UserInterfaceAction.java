package com.sporniket.libre.ui.action;

import java.util.Locale;

import com.sporniket.libre.lang.functor.Functor;

/**
 * Model of an action to create instances of buttons and menu items.
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
 * @version 22.11.00
 * @since 12.06.01
 */
public interface UserInterfaceAction<IconLocationType>
{

	/**
	 * Read the callback property.
	 * 
	 * When using the Swing toolkit, a callback is wrapped inside an Action
	 * 
	 * @return the callback
	 */
	Functor getCallback();

	/**
	 * Read the location of the icon to use for creating a button.
	 * 
	 * @return the iconForButton
	 */
	IconLocationType getIconForButton();

	/**
	 * Read the location of the icon to use for creating a menu.
	 * 
	 * @return the iconForMenu
	 */
	IconLocationType getIconForMenu();

	/**
	 * Read the keyboardShortcut property.
	 * 
	 * The String is a representation of a keyboard representation.
	 * 
	 * @return the keyboardShortcut
	 */
	String getKeyboardShortcut();

	/**
	 * Read the labelDescription property.
	 * 
	 * @return the labelDescription
	 */
	String getLabelDescription();

	/**
	 * Read the labelMessage property.
	 * 
	 * @return the labelMessage
	 */
	String getLabelMessage();

	/**
	 * Read the locale property.
	 * 
	 * @return the locale.
	 */
	Locale getLocale();

	/**
	 * Read the enabled property.
	 * 
	 * @return the enabled
	 */
	boolean isEnabled();

	/**
	 * Write the callback property.
	 * 
	 * @param callback
	 *            the callback to set
	 */
	void setCallback(Functor callback);

	/**
	 * Write the enabled property.
	 * 
	 * @param enabled
	 *            the enabled to set
	 */
	void setEnabled(boolean enabled);

	/**
	 * Change the location of the icon to use for creating a button.
	 * 
	 * @param iconForButton
	 *            the iconForButton to set
	 */
	void setIconForButton(IconLocationType iconForButton);

	/**
	 * Change the location of the icon to use for creating a menu.
	 * 
	 * @param iconForMenu
	 *            the iconForMenu to set
	 */
	void setIconForMenu(IconLocationType iconForMenu);

	/**
	 * Write the keyboardShortcut property.
	 * 
	 * @param keyboardShortcut
	 *            the keyboardShortcut to set
	 */
	void setKeyboardShortcut(String keyboardShortcut);

	/**
	 * Write the labelDescription property.
	 * 
	 * @param labelDescription
	 *            the labelDescription to set
	 */
	void setLabelDescription(String labelDescription);

	/**
	 * Write the labelMessage property.
	 * 
	 * @param labelMessage
	 *            the labelMessage to set
	 */
	void setLabelMessage(String labelMessage);

	/**
	 * Write the locale property.
	 * 
	 * @param locale
	 *            the locale
	 */
	void setLocale(Locale locale);

}