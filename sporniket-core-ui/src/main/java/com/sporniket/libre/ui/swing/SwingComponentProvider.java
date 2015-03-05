/**
 * 
 */
package com.sporniket.libre.ui.swing;

import java.awt.Component;

import javax.swing.JApplet;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * Formal model of a functor that create a Swing component (any subclass of {@link JComponent}).
 * 
 * <p>
 * Top-level components ( {@link JFrame}, {@link JDialog}, {@link JApplet}) are not supported.
 * 
 * <p>
 * &copy; Copyright 2002-2015 David Sporn
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
 * 
 * @version 15.02.00
 * @since 15.02.00
 */
public interface SwingComponentProvider<ComponentType extends JComponent, ParameterType>
{
	/**
	 * Parameter type to use when the provider does not need any to create a component.
	 * <p>
	 * &copy; Copyright 2002-2015 David Sporn
	 * </p>
	 * <hr>
	 * 
	 * <p>
	 * This file is part of <i>The Sporniket Core Library &#8211; ui</i>.
	 * 
	 * <p>
	 * <i>The Sporniket Core Library &#8211; ui</i> is free software: you can redistribute it and/or modify it under the terms of
	 * the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at
	 * your option) any later version.
	 * 
	 * <p>
	 * <i>The Sporniket Core Library &#8211; ui</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
	 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
	 * License for more details.
	 * 
	 * <p>
	 * You should have received a copy of the GNU Lesser General Public License along with <i>The Sporniket Core Library &#8211;
	 * ui</i>. If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
	 * 
	 * <hr>
	 * 
	 * @author David SPORN 
	 * 
	 * @version 15.02.00
	 * @since 15.02.00
	 */
	public static class NoParameter
	{
	}

	/**
	 * Return the component that suits the parameters.
	 * 
	 * @param parameters
	 *            the parameters for the creation of the component.
	 * @return a Swing component.
	 * @since 15.02.00
	 */
	ComponentType create(ParameterType parameters);
}
