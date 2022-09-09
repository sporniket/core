/**
 * 
 */
package com.sporniket.libre.ui.swing;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Utility class for JFrames.
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
 * 
 * @version 22.09.00
 * @since 15.02.00
 */
public class JFrameUtils
{
	/**
	 * Query {@link Toolkit#getScreenInsets(GraphicsConfiguration)} and {@link GraphicsConfiguration#getBounds()} to compute the
	 * bounds of a maximized JFrame.
	 * 
	 * @return a rectangle describing the outer bounds of a maximised frame, for determining the screen size (this is not the
	 *         full-screen size).
	 */
	public static Rectangle getMaximisedFrameOuterBounds()
	{
		Toolkit _toolkit = Toolkit.getDefaultToolkit();
		GraphicsEnvironment _ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice _gd = _ge.getDefaultScreenDevice();
		GraphicsConfiguration _gc = _gd.getDefaultConfiguration();
		Insets _screenInsets = _toolkit.getScreenInsets(_gc);
		Rectangle _rectangle = _gc.getBounds();

		int _width = _rectangle.width - _screenInsets.left - _screenInsets.right;
		int _height = _rectangle.height - _screenInsets.top - _screenInsets.bottom;
		Rectangle _result = new Rectangle(_screenInsets.left, _screenInsets.top, _width, _height);
		return _result;
	}

	/**
	 * Make the given {@link JFrame} maximized.
	 * 
	 * @param frame
	 *            the frame to maximize.
	 * @return the maximized frame.
	 */
	public static JFrame maximizeFrame(JFrame frame)
	{
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		return frame;
	}
}
