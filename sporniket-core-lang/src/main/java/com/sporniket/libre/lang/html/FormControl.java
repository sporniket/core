package com.sporniket.libre.lang.html;

/**
 * Base class to generate form controls.
 * 
 * <p>
 * &copy; Copyright 2002-2012 David Sporn
 * </p>
 * <hr>
 * 
 * <p>
 * This file is part of <i>The Sporniket Core Library &#8211; lang</i>.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; lang</i> is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; lang</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 * 
 * <p>
 * You should have received a copy of the GNU Lesser General Public License along with <i>The Sporniket Core Library &#8211;
 * lang</i>. If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
 * 
 * <hr>
 * 
 * @author David SPORN 
 * @version 15.02.00
 * @since 12.06.01
 */
class FormControl extends NamedElement
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8486616181041196786L;

	/**
	 * The flag to disable the control.
	 */
	private boolean myDisabled = false;

	/**
	 * Value of the radio button.
	 */
	private String myValue;

	public FormControl()
	{
		super();
	}

	/**
	 * @return the value
	 */
	public String getValue()
	{
		return myValue;
	}

	/**
	 * @return the disabled
	 */
	public boolean isDisabled()
	{
		return myDisabled;
	}

	/**
	 * @param disabled
	 *            the disabled to set
	 */
	public void setDisabled(boolean disabled)
	{
		myDisabled = disabled;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value)
	{
		myValue = value;
	}

}