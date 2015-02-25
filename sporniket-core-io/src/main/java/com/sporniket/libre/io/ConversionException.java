package com.sporniket.libre.io;

/**
 * Exception class when a problem related with the conversion process occurs.
 * 
 * <p>
 * &copy; Copyright 2002-2012 David Sporn
 * </p>
 * <hr />
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
 * If not, see <http://www.gnu.org/licenses/>. 2
 * 
 * <hr />
 * 
 * @author David SPORN <david.sporn@sporniket.com>
 * @version 15.02.00
 * @since 12.06.01
 */
public class ConversionException extends RuntimeException
{

	/**
	 * Serializable support.
	 * @since 12.06.01
	 */
	private static final long serialVersionUID = -5369859601325858923L;

	/*
	 * @see Exception
	 */
	public ConversionException()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * @param message
	 * 
	 * @param cause
	 * 
	 * @see Exception
	 */
	public ConversionException(String message, Throwable cause)
	{
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/*
	 * @param message
	 * 
	 * @see Exception
	 */
	public ConversionException(String message)
	{
		super(message);
		// TODO Auto-generated constructor stub
	}

	/*
	 * @param cause
	 * 
	 * @see Exception
	 */
	public ConversionException(Throwable cause)
	{
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
