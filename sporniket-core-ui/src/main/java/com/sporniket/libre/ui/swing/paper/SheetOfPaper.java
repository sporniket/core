package com.sporniket.libre.ui.swing.paper;

import java.awt.Color;

/**
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
public interface SheetOfPaper
{
	/**
	 * The default pixel per inch value. DSP: I choose 90 for two reason : it is <a
	 * href="http://www.w3.org/TR/SVG/coords.html#Units">the sample value used in SVG specification</a> and it appears to be the
	 * actual resolution of my screen (19" CRT Iiyama HM903DT).
	 */
	public static int PIXEL_PER_INCH__DEFAULT = 90;

	public static int PIXEL_PER_INCH__WINDOWS = 96;

	public static int PIXEL_PER_INCH__ADOBE = 72;

	public static int PIXEL_PER_INCH__APPLE = 72;

	public static int HEIGHT__DEFAULT = 297;

	public static int HEIGHT__A4 = 297;

	public static int WIDTH__DEFAULT = 210;

	public static int WIDTH__A4 = 210;

	public static int OUTER_MARGIN__DEFAULT = 50;

	/**
	 * The default model precision. DSP: I wanted to use 10 (i.e. one unit is a tenth of a millimeter) but Java was displaying
	 * nothing, then. Maybe it needed too much memory ?
	 */
	public static final int UNIT_PER_MM__DEFAULT = 5;

	/**
	 * Read the color property.
	 * 
	 * Color of the paper.
	 * 
	 * @return the color
	 */
	public Color getColor();

	/**
	 * Write the color property.
	 * 
	 * Color of the paper.
	 * 
	 * @param color
	 *            the color to set
	 */
	public void setColor(Color color);

	/**
	 * Read the PixelPerInch property.
	 * 
	 * How much pixels represents one inch (graphical zoom).
	 * 
	 * @return the PixelPerInch
	 */
	public int getPixelPerInch();

	/**
	 * Write the PixelPerInch property.
	 * 
	 * How much pixels represents one inch (graphical zoom).
	 * 
	 * @param pixelPerInch
	 *            the PixelPerInch to set
	 */
	public void setPixelPerInch(int pixelPerInch);

	/**
	 * Read the height property.
	 * 
	 * Height, in millimeter.
	 * 
	 * @return the height
	 */
	public int getHeight();

	/**
	 * Write the height property.
	 * 
	 * Height, in millimeter.
	 * 
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height);

	/**
	 * Read the outerMargin property.
	 * 
	 * When drawing the sheet of paper and the outside, the margin outside, in millimeter.
	 * 
	 * @return the outerMargin
	 */
	public int getOuterMargin();

	/**
	 * Write the outerMargin property.
	 * 
	 * When drawing the sheet of paper and the outside, the margin outside, in millimeter.
	 * 
	 * @param outerMargin
	 *            the outerMargin to set
	 */
	public void setOuterMargin(int outerMargin);

	/**
	 * Read the unitPerMillimeter property.
	 * 
	 * How much unit in one millimeter (Model precision).
	 * 
	 * @return the unitPerMillimeter
	 */
	public int getUnitPerMillimeter();

	/**
	 * Write the unitPerMillimeter property.
	 * 
	 * How much unit in one millimeter (Model precision).
	 * 
	 * @param unitPerMillimeter
	 *            the unitPerMillimeter to set
	 */
	public void setUnitPerMillimeter(int unitPerMillimeter);

	/**
	 * Read the width property.
	 * 
	 * Width, in millimeter.
	 * 
	 * @return the width
	 */
	public int getWidth();

	/**
	 * Write the width property.
	 * 
	 * Width, in millimeter.
	 * 
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width);

}