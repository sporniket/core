package com.sporniket.libre.ui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.sporniket.libre.lang.functor.Functor;

/**
 * The <code>actionPerformed</code> calls a Functor.
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
public class FunctorBasedAction extends AbstractAction
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6950421022057840561L;

	Functor myFunctor = null;

	/**
	 * @param functor the callback functor.
	 */
	public FunctorBasedAction(Functor functor)
	{
		myFunctor = functor;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			myFunctor.process();
		}
		catch (Exception _exception)
		{
			_exception.printStackTrace(System.err);
		}
	}

	/**
	 * Read the functor property.
	 * 
	 * @return the functor
	 */
	public Functor getFunctor()
	{
		return myFunctor;
	}

	/**
	 * Write the functor property.
	 * 
	 * @param functor
	 *            the functor to set
	 */
	public void setFunctor(Functor functor)
	{
		myFunctor = functor;
	}

}
