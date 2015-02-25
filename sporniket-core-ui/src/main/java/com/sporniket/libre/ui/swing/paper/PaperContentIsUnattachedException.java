package com.sporniket.libre.ui.swing.paper;

/**
 * 
 * <p>
 * &copy; Copyright 2002-2012 David Sporn
 * </p>
 * <hr />
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
 * If not, see <http://www.gnu.org/licenses/>. 2
 * 
 * <hr />
 * 
 * @author David SPORN <david.sporn@sporniket.com>
 * @version 15.02.00
 * @since 12.06.01
 */
public class PaperContentIsUnattachedException extends Exception
{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5115328250983496636L;

	public PaperContentIsUnattachedException()
	{
	}

	public PaperContentIsUnattachedException(String message)
	{
		super(message);
	}

	public PaperContentIsUnattachedException(Throwable cause)
	{
		super(cause);
	}

	public PaperContentIsUnattachedException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
