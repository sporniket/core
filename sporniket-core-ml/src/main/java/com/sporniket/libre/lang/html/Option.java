package com.sporniket.libre.lang.html;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

import com.sporniket.libre.lang.sgml.SgmlUtils;

/**
 * Model for the <code>option</code> tag.
 * 
 * <p>
 * Put a list of options in a {@link List} and call {@link Select#create(String, String, List, boolean, boolean)}.
 * </p>
 * 
 * <p>
 * To generate the HTML code, simply call the {@link #toString()} method.
 * </p>
 * 
 * <p>
 * &copy; Copyright 2002-2022 David Sporn
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
 * @version 22.09.00
 * @since 12.06.01
 */
public class Option implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3579474854116226569L;

	private static final MessageFormat FORMAT__NOT_SELECTED = new MessageFormat("<option {0}>{1}</option>");

	private static final MessageFormat FORMAT__SELECTED = new MessageFormat("<option {0} " + HtmlUtils.SpecialAttribute.SELECTED
			+ ">{1}</option>");

	/**
	 * Create a fully specified option.
	 * 
	 * @param value value attribute.
	 * @param label label attribute.
	 * @param isSelected selected attribute.
	 * @return a fully defined {@link Option}.
	 */
	public static Option create(String value, String label, boolean isSelected)
	{
		Option _result = new Option();
		_result.setValue(value);
		_result.setLabel(label);
		_result.setSelected(isSelected);
		return _result;
	}

	/**
	 * The option label.
	 */
	private String myLabel;

	/**
	 * The flag to generate, if true, a selected option.
	 */
	private boolean mySelected;

	/**
	 * The option value.
	 */
	private String myValue;

	/**
	 * @return the label
	 */
	public String getLabel()
	{
		return myLabel;
	}

	/**
	 * @return the value
	 */
	public String getValue()
	{
		return myValue;
	}

	/**
	 * @return the selected
	 */
	public boolean isSelected()
	{
		return mySelected;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label)
	{
		myLabel = label;
	}

	/**
	 * @param selected
	 *            the selected to set
	 */
	public void setSelected(boolean selected)
	{
		mySelected = selected;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value)
	{
		myValue = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return getHtmlCode();
	}

	/**
	 * Generate the html code for the option.
	 * 
	 * @return the HTML code of the option.
	 */
	public String getHtmlCode()
	{
		Object[] _params =
		{
			SgmlUtils.generateAttribute(HtmlUtils.AttributeNames.VALUE, getValue())
		};
		MessageFormat _format = (isSelected()) ? FORMAT__SELECTED : FORMAT__NOT_SELECTED;
		return _format.format(_params);
	}

}