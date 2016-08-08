package com.sporniket.libre.ui.swing.paper;

import java.awt.Color;
import java.text.MessageFormat;

//unit is millimeter
/**
 * Basic implementation of the SheetOfPaper interface.
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
public class SheetOfPaperRaw implements SheetOfPaper
{
	/**
	 * Width, in millimeter.
	 */
	int myWidth = SheetOfPaper.WIDTH__DEFAULT;

	/**
	 * Height, in millimeter.
	 */
	int myHeight = SheetOfPaper.HEIGHT__DEFAULT;

	/**
	 * When drawing the sheet of paper and the outside, the margin outside, in millimeter.
	 */
	int myOuterMargin = SheetOfPaper.OUTER_MARGIN__DEFAULT;

	/**
	 * How much pixels represents one inch (graphical zoom).
	 */
	int myPixelPerInch = SheetOfPaper.PIXEL_PER_INCH__DEFAULT;

	/**
	 * How much unit in one millimeter (Model precision).
	 */
	int myUnitPerMillimeter = SheetOfPaper.UNIT_PER_MM__DEFAULT;

	/**
	 * Color of the paper.
	 */
	Color myColor = Color.WHITE;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.swing.paper.SheetOfPaper#getColor()
	 */
	public Color getColor()
	{
		return myColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.swing.paper.SheetOfPaper#setColor(java.awt.Color)
	 */
	public void setColor(Color color)
	{
		if (null == color)
		{
			String _message = "The color must be non null.";
			Object[] _parameters = {};
			throw new IllegalArgumentException(MessageFormat.format(_message, _parameters));
		}
		myColor = color;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.swing.paper.SheetOfPaper#getPixelPerInch()
	 */
	public int getPixelPerInch()
	{
		return myPixelPerInch;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.swing.paper.SheetOfPaper#setPixelPerInch(int)
	 */
	public void setPixelPerInch(int pixelPerInch)
	{
		if (1 > pixelPerInch)
		{
			String _message = "The specified pixelPerInch ({0}) is incorrect, it must be greater or equal than 1.";
			Object[] _parameters =
			{
				new Integer(pixelPerInch)
			};
			throw new IllegalArgumentException(MessageFormat.format(_message, _parameters));
		}
		myPixelPerInch = pixelPerInch;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.swing.paper.SheetOfPaper#getHeight()
	 */
	public int getHeight()
	{
		return myHeight;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.swing.paper.SheetOfPaper#setHeight(int)
	 */
	public void setHeight(int height)
	{
		if (1 > height)
		{
			String _message = "The specified height ({0}) is incorrect, it must be greater or equal than 1.";
			Object[] _parameters =
			{
				new Integer(height)
			};
			throw new IllegalArgumentException(MessageFormat.format(_message, _parameters));
		}
		myHeight = height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.swing.paper.SheetOfPaper#getOuterMargin()
	 */
	public int getOuterMargin()
	{
		return myOuterMargin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.swing.paper.SheetOfPaper#setOuterMargin(int)
	 */
	public void setOuterMargin(int outerMargin)
	{
		if (1 > outerMargin)
		{
			String _message = "The specified outerMargin ({0}) is incorrect, it must be greater or equal than 1.";
			Object[] _parameters =
			{
				new Integer(outerMargin)
			};
			throw new IllegalArgumentException(MessageFormat.format(_message, _parameters));
		}
		myOuterMargin = outerMargin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.swing.paper.SheetOfPaper#getUnitPerMillimeter()
	 */
	public int getUnitPerMillimeter()
	{
		return myUnitPerMillimeter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.swing.paper.SheetOfPaper#setUnitPerMillimeter(int)
	 */
	public void setUnitPerMillimeter(int unitPerMillimeter)
	{
		if (1 > unitPerMillimeter)
		{
			String _message = "The specified unitPerMillimetre ({0}) is incorrect, it must be greater or equal than 1.";
			Object[] _parameters =
			{
				new Integer(unitPerMillimeter)
			};
			throw new IllegalArgumentException(MessageFormat.format(_message, _parameters));
		}
		myUnitPerMillimeter = unitPerMillimeter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.swing.paper.SheetOfPaper#getWidth()
	 */
	public int getWidth()
	{
		return myWidth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.swing.paper.SheetOfPaper#setWidth(int)
	 */
	public void setWidth(int width)
	{
		if (1 > width)
		{
			String _message = "The specified width ({0}) is incorrect, it must be greater or equal than 1.";
			Object[] _parameters =
			{
				new Integer(width)
			};
			throw new IllegalArgumentException(MessageFormat.format(_message, _parameters));
		}
		myWidth = width;
	}
}
