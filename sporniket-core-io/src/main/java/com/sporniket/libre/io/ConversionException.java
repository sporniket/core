package com.sporniket.libre.io;

/**
 * Exception class when a problem related with the conversion process occurs.
 * 
 * <p>
 * &copy; Copyright 2002-2015 David Sporn
 * </p>
 * <hr>
 * 
 * <p>
 * This file is part of <i>The Sporniket Core Library &#8211; io</i>.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; io</i> is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; io</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for
 * more details.
 * 
 * <p>
 * You should have received a copy of the GNU Lesser General Public License along with <i>The Sporniket Core Library &#8211; io</i>.
 * If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
 * 
 * <hr>
 * 
 * @author David SPORN 
 * @version 15.02.00
 * @since 12.06.01
 */
public class ConversionException extends RuntimeException
{

	/**
	 * Serialization ID
	 * @since 12.06.01
	 */
	private static final long serialVersionUID = -5369859601325858923L;

	public ConversionException()
	{
		super();
	}

	public ConversionException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ConversionException(String message)
	{
		super(message);
	}

	public ConversionException(Throwable cause)
	{
		super(cause);
	}

}
