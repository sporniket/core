package com.sporniket.libre.ui.swing;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;

/**
 * Provides shortcut for setting window Location.
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
 * @version 16.08.00
 * @since 12.06.01
 */
public class WindowLocation extends Object
{
	/** Predefined position */
	public final static int TOP_LEFT = 0;

	/** Predefined position */
	public final static int TOP_CENTER = 1;

	/** Predefined position */
	public final static int TOP_RIGHT = 2;

	/** Predefined position */
	public final static int MIDDLE_LEFT = 3;

	/** Predefined position */
	public final static int MIDDLE_CENTER = 4;

	/** Predefined position */
	public final static int MIDDLE_RIGHT = 5;

	/** Predefined position */
	public final static int BOTTOM_LEFT = 6;

	/** Predefined position */
	public final static int BOTTOM_CENTER = 7;

	/** Predefined position */
	public final static int BOTTOM_RIGHT = 8;

	/**
	 * Snap the Window Position to a special location.
	 * 
	 * @param win
	 *            The Window to move
	 * @param location
	 *            A value among the allowed predefined positions
	 */
	public static void snapWindowTo(Window win, int location)
	{
		Dimension _screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension _window = win.getSize();
		int _wx = 0;
		int _wy = 0;
		switch (location)
		{
			case TOP_LEFT:
				break;
			case TOP_CENTER:
				_wx = (_screen.width - _window.width) / 2;
				break;
			case TOP_RIGHT:
				_wx = _screen.width - _window.width;
				break;
			case MIDDLE_LEFT:
				_wy = (_screen.height - _window.height) / 2;
				break;
			case MIDDLE_CENTER:
				_wx = (_screen.width - _window.width) / 2;
				_wy = (_screen.height - _window.height) / 2;
				break;
			case MIDDLE_RIGHT:
				_wx = _screen.width - _window.width;
				_wy = (_screen.height - _window.height) / 2;
				break;
			case BOTTOM_LEFT:
				_wy = _screen.height - _window.height;
				break;
			case BOTTOM_CENTER:
				_wx = (_screen.width - _window.width) / 2;
				_wy = _screen.height - _window.height;
				break;
			case BOTTOM_RIGHT:
				_wx = _screen.width - _window.width;
				_wy = _screen.height - _window.height;
				break;
		}
		win.setLocation(new Point(_wx, _wy));
	}
}