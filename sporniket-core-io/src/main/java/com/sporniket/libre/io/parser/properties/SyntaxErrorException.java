/**
 * 
 */
package com.sporniket.libre.io.parser.properties;

/**
 * Exception thrown when there is an error in the parsing of the provided input.
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
 * @version 15.09.00
 * @since 15.09.00
 */
public class SyntaxErrorException extends Exception
{

	private static final long serialVersionUID = -7863054860730946641L;

	public SyntaxErrorException()
	{
	}

	public SyntaxErrorException(String s)
	{
		super(s);
	}

	public SyntaxErrorException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public SyntaxErrorException(Throwable cause)
	{
		super(cause);
	}

}