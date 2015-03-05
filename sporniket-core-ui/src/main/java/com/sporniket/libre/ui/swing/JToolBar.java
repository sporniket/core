package com.sporniket.libre.ui.swing;

import java.lang.reflect.Method;

import javax.swing.Action;
import javax.swing.JButton;

import com.sporniket.libre.ui.action.AdaptedAction;
import com.sporniket.libre.ui.action.FunctorBasedAction;
import com.sporniket.libre.ui.action.UserInterfaceAction;

/**
 * Custom JToolBar that supports some implementation of UserInterfaceActionTrait.
 * 
 * <p>
 * &copy; Copyright 2002-2012 David Sporn
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
 * @version 15.02.00
 * @since 12.06.01
 */
public class JToolBar extends javax.swing.JToolBar
{
	/**
	 * Name of the function to find by using java introspection.
	 */
	private static final String FUNCTION_NAME_ADD = "add";

	/**
	 * Needed by Serializable.
	 */
	private static final long serialVersionUID = 6988230740726958410L;

	/**
	 * Add the action and setup the obtained button to behave correctly and further settings (icon, tooltip, focus).
	 * 
	 * @see javax.swing.JToolBar#add(javax.swing.Action)
	 */
	public JButton add(Action act)
	{
		JButton _button;
		_button = super.add(act);
		_button.setRequestFocusEnabled(false);
		if (null != act.getValue(Action.SMALL_ICON))
		{
			_button.setToolTipText((String) act.getValue(Action.NAME));
			_button.setText(null);
		}
		return _button;
	}

	public JButton add(AdaptedAction action)
	{
		return add((Action) action);
	}

	public JButton add(FunctorBasedAction action)
	{
		return add((Action) action);
	}

	/**
	 * Polymorphic add, it uses introspection to call the <code>add</code> function that match the class of the provided action.
	 * 
	 * @param action the action to link to the new button.
	 * @return the new button added to this toolbar.
	 */
	public JButton add(UserInterfaceAction action)
	{
		try
		{
			Method _specializedMethod = getClass().getMethod(FUNCTION_NAME_ADD, new Class[]
			{
				action.getClass()
			});
			return (JButton) _specializedMethod.invoke(this, new Object[]
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
