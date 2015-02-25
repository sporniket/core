package com.sporniket.libre.ui.swing;

import java.lang.reflect.Method;

import javax.swing.Action;
import javax.swing.JMenuItem;

import com.sporniket.libre.ui.action.AdaptedAction;
import com.sporniket.libre.ui.action.FunctorBasedAction;
import com.sporniket.libre.ui.action.UserInterfaceAction;

/**
 * Custom JMenu that supports some implementation of UserInterfaceActionTrait.
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
public class JMenu extends javax.swing.JMenu
{
	/**
	 * Name of the function to find by using java introspection.
	 */
	private static final String FUNCTION_NAME_ADD = "add";

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -272211675840992550L;

	public JMenuItem add(AdaptedAction action)
	{
		return add((Action) action);
	}

	public JMenuItem add(FunctorBasedAction action)
	{
		return add((Action) action);
	}

	/**
	 * Polymorphic add, it uses introspection to call the <code>add</code> function that match the class of the provided action.
	 * 
	 * @param action
	 * @return
	 */
	public JMenuItem add(UserInterfaceAction action)
	{
		try
		{
			Method _specializedMethod = getClass().getMethod(FUNCTION_NAME_ADD, new Class[]
			{
				action.getClass()
			});
			return (JMenuItem) _specializedMethod.invoke(this, new Object[]
			{
				action
			});
		}
		catch (Exception _exception)
		{
			_exception.printStackTrace(System.err);
			return null;
		}
	}
}
