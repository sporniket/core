package com.sporniket.libre.ui.swing;

import java.awt.Font;

import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

/**
 * Custom Look and Feel, Medium, based on DefaultMetalTheme.
 * <p>
 * Typical use :
 * </p>
 * 
 * <pre>
 * try
 * {
 * 	javax.swing.plaf.metal.MetalLookAndFeel.setCurrentTheme(new LookAndFeelMedium());
 * }
 * catch (Exception e)
 * {
 * 	System.out.println(&quot;Unexpected error. \nProgram Terminated&quot;);
 * 	e.printStackTrace();
 * 	System.exit(1);
 * }
 * </pre>
 * 
 * <hr>
 * <p>
 * &copy; Copyright David Sporn
 * </p>
 * <hr>
 * 
 * This library is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License version
 * 3 as published by the Free Software Foundation.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with this library; if not, write to the Free
 * Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 * <hr>
 * 
 * @author David Sporn
 * @version 16.08.02
 * @since 12.06.01
 */
public class LookAndFeelMedium extends DefaultMetalTheme
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

	private static final FontUIResource FONT__CONTROL = new FontUIResource("Dialog", Font.PLAIN, 13);

	private static final FontUIResource FONT__SYSTEM = new FontUIResource("Dialog", Font.PLAIN, 13);

	private static final FontUIResource FONT__WINDOW_TITLE = new FontUIResource("Dialog", Font.BOLD, 12);

	private static final FontUIResource FONT__USER = new FontUIResource("DialogInput", Font.PLAIN, 13);

	private static final FontUIResource FONT__SMALL = new FontUIResource("Dialog", Font.PLAIN, 11);

	private static final String NAME = "Sporniket Theme (Medium)";

}
