package com.sporniket.libre.lang.html;

import java.io.Serializable;

import com.sporniket.libre.lang.Constants;

/**
 * Base class for an element having an name, and an id (the name and a suffix).
 * 
 * <p>
 * &copy; Copyright 2002-2015 David Sporn
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
 * @version 16.08.00
 * @since 12.06.01
 */
class NamedElement implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4132163276775904654L;

	/**
	 * Cache for the id value
	 */
	private String myId = null;

	/**
	 * Suffix to add to the name to get an Id for the control.
	 */
	private String myIdSuffix = Constants.Empty.STRING;

	/**
	 * Name of the field.
	 */
	private String myName = null;

	public NamedElement()
	{
		super();
	}

	/**
	 * Generate the id attribute html code.
	 * 
	 * @return the HTML coding the id attribute.
	 */
	public String getHtmlCodeForIdAttribute()
	{
		return HtmlUtils.generateAttribute(HtmlUtils.AttributeNames.ID, getId());
	}

	/**
	 * Generate the name attribute html code.
	 * 
	 * @return the HTML coding the name attribute.
	 */
	public String getHtmlCodeForNameAttribute()
	{
		return HtmlUtils.generateAttribute(HtmlUtils.AttributeNames.NAME, getName());
	}

	/**
	 * Read the id property.
	 * 
	 * @return the id
	 */
	public String getId()
	{
		return myId;
	}

	/**
	 * @return the idSuffix
	 */
	public String getIdSuffix()
	{
		return myIdSuffix;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return myName;
	}

	/**
	 * Re-compute the id of the element.
	 */
	private void refreshId()
	{
		if (null == getName())
		{
			setId(null);
		}
		else
		{
			if (null == getIdSuffix())
			{
				setId(getName());
			}
			else
			{
				setId(getName() + getIdSuffix());
			}
		}
	}

	/**
	 * Write the id property.
	 * 
	 * @param id
	 *            the id to set
	 */
	private void setId(String id)
	{
		myId = id;
	}

	/**
	 * @param idSuffix
	 *            the idSuffix to set
	 */
	public void setIdSuffix(String idSuffix)
	{
		myIdSuffix = idSuffix;
		refreshId();
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name)
	{
		myName = name;
		refreshId();
	}

}