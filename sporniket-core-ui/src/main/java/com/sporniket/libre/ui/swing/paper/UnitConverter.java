package com.sporniket.libre.ui.swing.paper;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
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
 * @version 22.09.01
 * @since 12.06.01
 */
public class UnitConverter
{
	/**
	 * Cache for avoiding recreating a unit converter.
	 */
	private static Map<Integer, UnitConverter> theCache = new HashMap<Integer, UnitConverter>();

	/**
	 * Retrieve from the cache, or create if not cached yet, a unit converter setted for the given pixel per inch value.
	 * 
	 * @param pixelPerInch pixel per inch value.
	 * @return an instance tuned for the specified pixel density.
	 */
	public static UnitConverter getCachedInstance(int pixelPerInch)
	{
		Integer _key = new Integer(pixelPerInch);
		if (!theCache.containsKey(_key))
		{
			theCache.put(_key, new UnitConverter(pixelPerInch));
		}
		return theCache.get(_key);
	}

	/**
	 * Retrieve the Unit converter for the default pixel per inch value.
	 * 
	 * @return the default unit converter.
	 */
	public static UnitConverter getCachedInstance()
	{
		return getCachedInstance(PIXEL_PER_INCH_DEFAULT);
	}

	/**
	 * The default resolution in pixel per inch.
	 * 
	 * This value is the one <a href="http://www.w3.org/TR/SVG/coords.html#Units">cited in example</a> in the <a
	 * href="http://www.w3.org/TR/SVG/">SVG specification</a>. (By chance, it appears that my screen has an horizontal resolution of
	 * 89 dpi and a vertical resolution of 90 dpi.)
	 */
	public static final int PIXEL_PER_INCH_DEFAULT = 90;

	/**
	 * The definition of the point (unit used in typography).
	 * 
	 * It use the <a href="http://en.wikipedia.org/wiki/Point_(typography)">desktop publishing point</a> (also called the PostScript
	 * point).
	 * 
	 */
	public static final int POINT_PER_INCH = 72;

	public static final int MM_PER_CM = 100;

	/**
	 * One pica is always equal to 12 points.
	 */
	public static final int POINT_PER_PICA = 12;

	/**
	 * One isternational inch is equal to 25.4 mm.
	 */
	public static final double MM_PER_INCH = 25.4;

	private int myPixelPerInch = PIXEL_PER_INCH_DEFAULT;

	/**
	 * @param pixelPerInch pixel density.
	 */
	public UnitConverter(int pixelPerInch)
	{
		if (0 > pixelPerInch)
		{
			String _message = "pixelPerInch (={0}) must be a strictly positive value.";
			Object[] _parameters =
			{
				new Integer(pixelPerInch)
			};
			throw new IllegalArgumentException(MessageFormat.format(_message, _parameters));
		}
		myPixelPerInch = pixelPerInch;
	}

	/**
	 * 
	 */
	public UnitConverter()
	{
	}

	// =========
	// From\To in cm mm pt pc px
	// in x 0 0 x 0
	// cm x 0 x x x
	// mm 0 0 x x x
	// pt 0 x x 0 x
	// pc x x x 0 x
	// px 0 x x x x

	// from inch
	public double convertInchToCentimeter(double value)
	{
		return convertMillimeterToCentimeter(convertInchToMillimeter(value));
	}

	public double convertInchToMillimeter(double value)
	{
		return value * MM_PER_INCH;
	}

	public double convertInchToPoint(double value)
	{
		return value * POINT_PER_INCH;
	}

	public double convertInchToPica(double value)
	{
		return convertPointToPica(convertInchToPoint(value));
	}

	public double convertInchToPixel(double value)
	{
		return value * myPixelPerInch;
	}

	// from centimeter
	public double convertCentimeterToInch(double value)
	{
		return convertMillimeterToInch(convertCentimeterToMillimeter(value));
	}

	public double convertCentimeterToMillimeter(double value)
	{
		return value * MM_PER_CM;
	}

	public double convertCentimeterToPoint(double value)
	{
		return convertMillimeterToPoint(convertCentimeterToMillimeter(value));
	}

	public double convertCentimeterToPica(double value)
	{
		return convertMillimeterToPica(convertCentimeterToMillimeter(value));
	}

	public double convertCentimeterToPixel(double value)
	{
		return convertMillimeterToPixel(convertCentimeterToMillimeter(value));
	}

	// from millimeter
	public double convertMillimeterToInch(double value)
	{
		return value / MM_PER_INCH;
	}

	public double convertMillimeterToCentimeter(double value)
	{
		return value / MM_PER_CM;
	}

	public double convertMillimeterToPoint(double value)
	{
		return convertInchToPoint(convertMillimeterToInch(value));
	}

	public double convertMillimeterToPica(double value)
	{
		return convertPointToPica(convertMillimeterToPoint(value));
	}

	public double convertMillimeterToPixel(double value)
	{
		return convertInchToPixel(convertMillimeterToInch(value));
	}

	// from point
	public double convertPointToInch(double value)
	{
		return value / POINT_PER_INCH;
	}

	public double convertPointToCentimeter(double value)
	{
		return convertInchToCentimeter(convertPointToInch(value));
	}

	public double convertPointToMillimiter(double value)
	{
		return convertInchToMillimeter(convertPointToInch(value));
	}

	public double convertPointToPica(double value)
	{
		return value / POINT_PER_PICA;
	}

	public double convertPointToPixel(double value)
	{
		return convertInchToPixel(convertPointToInch(value));
	}

	// from pica
	public double convertPicaToInch(double value)
	{
		return convertPointToInch(convertPicaToPoint(value));
	}

	public double convertPicaToCentimeter(double value)
	{
		return convertPointToCentimeter(convertPicaToPoint(value));
	}

	public double convertPicaToMillimeter(double value)
	{
		return convertPointToMillimiter(convertPicaToPoint(value));
	}

	public double convertPicaToPoint(double value)
	{
		return value * POINT_PER_PICA;
	}

	public double convertPicaToPixel(double value)
	{
		return convertPointToPixel(convertPicaToPoint(value));
	}

	// from pixel
	public double convertPixelToInch(double value)
	{
		return value / myPixelPerInch;
	}

	public double convertPixelToCentimeter(double value)
	{
		return convertInchToCentimeter(convertPixelToInch(value));
	}

	public double convertPixelToMillimeter(double value)
	{
		return convertInchToMillimeter(convertPixelToInch(value));
	}

	public double convertPixelToPoint(double value)
	{
		return convertInchToPoint(convertPixelToInch(value));
	}

	public double convertPixelToPica(double value)
	{
		return convertInchToPica(convertPixelToInch(value));
	}

}
