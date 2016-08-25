package com.sporniket.libre.ui.swing;

import java.awt.Font;

import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

/**
 * Custom Look and Feel, Small, based on DefaultMetalTheme.
 * <p>
 * Typical use :
 * </p>
 * 
 * <pre>
 * try
 * {
 * 	javax.swing.plaf.metal.MetalLookAndFeel.setCurrentTheme(new LookAndFeelSmall());
 * }
 * catch (Exception e)
 * {
 * 	System.out.println(&quot;Unexpected error. \nProgram Terminated&quot;);
 * 	e.printStackTrace();
 * 	System.exit(1);
 * }
 * </pre>
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
 * @version 16.08.02
 * @since 12.06.01
 */
public class LookAndFeelSmall extends DefaultMetalTheme
{
	// ============================================================
	// Public Interface

	// Overriden methods

	public String getName()
	{
		return NAME;
	}

	public FontUIResource getControlTextFont()
	{
		return FONT__CONTROL;
	}

	// ToolTips font
	public FontUIResource getSystemTextFont()
	{
		return FONT__SYSTEM;
	}

	public FontUIResource getUserTextFont()
	{
		return FONT__USER;
	}

	public FontUIResource getMenuTextFont()
	{
		return FONT__CONTROL;
	}

	public FontUIResource getWindowTitleFont()
	{
		return FONT__WINDOW_TITLE;
	}

	// ShortCuts font
	public FontUIResource getSubTextFont()
	{
		return FONT__SMALL;
	}

	// ============================================================
	// Implementation details

	private static final FontUIResource FONT__CONTROL = new FontUIResource("Dialog", Font.PLAIN, 11);

	private static final FontUIResource FONT__SYSTEM = new FontUIResource("Dialog", Font.PLAIN, 11);

	private static final FontUIResource FONT__WINDOW_TITLE = new FontUIResource("Dialog", Font.BOLD, 10);

	private static final FontUIResource FONT__USER = new FontUIResource("DialogInput", Font.PLAIN, 13);

	private static final FontUIResource FONT__SMALL = new FontUIResource("Dialog", Font.PLAIN, 9);

	private static final String NAME = "Sporniket Theme (Small)";

}
