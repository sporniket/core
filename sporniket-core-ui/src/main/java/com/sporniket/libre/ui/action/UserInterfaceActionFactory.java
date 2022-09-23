package com.sporniket.libre.ui.action;

import java.util.Locale;

import com.sporniket.libre.lang.FactoryNotReadyException;
import com.sporniket.libre.lang.functor.Functor;
import com.sporniket.libre.lang.message.MessageProviderInterface;
import com.sporniket.libre.ui.icon.IconProvider;

/**
 * Interface to implement in order to create a Factory that will be able to instanciate a fully defined {@link UserInterfaceAction}.
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
 * @version 22.09.01
 * @since 12.06.01
 */
public interface UserInterfaceActionFactory<IconLocationType>
{
	/**
	 * Instanciate and configure an UserInterfaceAction
	 * 
	 * @param label label for a menu or button.
	 * @param description tooltip text.
	 * @param keyboardShortcut keyboard shortcut.
	 * @param iconForMenu icon to use in a menu.
	 * @param iconForButton icon to in a button.
	 * @param locale locale.
	 * @param callback function to call when the user click on the menu or button linked to the action.
	 * @return a {@link UserInterfaceAction}.
	 * @throws FactoryNotReadyException if there is a problem to deal with.
	 */
	UserInterfaceAction<IconLocationType> create(String label, String description, String keyboardShortcut,
			IconLocationType iconForMenu, IconLocationType iconForButton, Locale locale, Functor callback)
			throws FactoryNotReadyException;

	/**
	 * Icon provider to use.
	 * 
	 * @param iconProvider the icon provider.
	 * @since 15.02.00
	 */
	void setIconProvider(IconProvider<IconLocationType> iconProvider);

	/**
	 * Message provider to use.
	 * 
	 * @param messageProvider the message provider.
	 */
	void setMessageProvider(MessageProviderInterface messageProvider);
}
