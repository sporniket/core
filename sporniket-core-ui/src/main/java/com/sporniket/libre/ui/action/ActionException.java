/**
 * 
 */
package com.sporniket.libre.ui.action;

/**
 * Exception thrown when a processing fails.
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
 * @version 19.02.00
 * @since 12.06.01
 */
public class ActionException extends RuntimeException
{
	private static final long serialVersionUID = 5822926965438887642L;

	/**
	 * Serialization ID
	 */
	public ActionException()
	{
		super();
	}

	public ActionException(String message)
	{
		super(message);
	}

	public ActionException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ActionException(Throwable cause)
	{
		super(cause);
	}

}
