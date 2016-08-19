/**
 * 
 */
package com.sporniket.libre.lang.html;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

//FIXME support managing a list of group of option.
//create a class : 
//OptionGroup(String name, List<Option> option), comparable by name
//SortedOptionGroup(int rank) extends OptionGroup, comparable by rank and name.
//
//manage each group of option, have an anonymous group.

/**
 * Model for the <code>option</code> tag.
 * 
 * Put a list of option in a {@link List} and call {@link Select#create(String, String, List, boolean, boolean)}.
 * 
 * To generate the HTML code, simply call the {@link #toString()} method.
 * 
 * <p>
 * &copy; Copyright 2002-2016 David Sporn
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
 * @version 16.08.01
 * @since 12.06.01
 */
public class Select extends FormControl
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1471620952004489016L;

	/**
	 * Template for the code.
	 * 
	 * {0} : name {1} : id {2} : content {3} : disabled {4} : preselected option
	 */
	private static final MessageFormat FORMAT__SELECT = new MessageFormat("<select {0} {1} {3} {4}>{2}</select>");

	/**
	 * Create a fully defined selector..
	 * 
	 * @param name the name of the selector.
	 * @param idSuffix the suffix to add to compute the id attribute.
	 * @param options a list of options.
	 * @param allowMultipleSelection <code>true</code> to allow multiple selection.
	 * @param isDisabled disabled attribute.
	 * @return a fully defined {@link Select}.
	 */
	public static Select create(String name, String idSuffix, List<Option> options, boolean allowMultipleSelection,
			boolean isDisabled)
	{
		Select _select = new Select();
		_select.setName(name);
		_select.setIdSuffix(idSuffix);
		_select.setDisabled(isDisabled);
		_select.setMultiple(allowMultipleSelection);
		_select.getOptions().addAll(options);
		return _select;
	}

	/**
	 * Flag to allow multiple selection.
	 */
	private boolean myMultiple = false;

	/**
	 * OptionList
	 */
	private List<Option> myOptions = new ArrayList<Option>();

	/**
	 * @return the options
	 */
	public List<Option> getOptions()
	{
		return myOptions;
	}

	/**
	 * @return the multiple
	 */
	public boolean isMultiple()
	{
		return myMultiple;
	}

	/**
	 * @param multiple
	 *            the multiple to set
	 */
	public void setMultiple(boolean multiple)
	{
		myMultiple = multiple;
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
	 * Generate the html code.
	 * 
	 * @return
	 */
	private String getHtmlCode()
	{
		StringBuffer _body = new StringBuffer();
		for (Option _option : getOptions())
		{
			_body.append(_option.getHtmlCode());
		}
		Object[] _params =
		{
				getHtmlCodeForNameAttribute(),
				getHtmlCodeForIdAttribute(),
				_body.toString(),
				isMultiple() ? HtmlUtils.SpecialAttribute.ALLOW_MULTIPLE_SELECTION : HtmlUtils.SpecialAttribute.NONE,
				isDisabled() ? HtmlUtils.SpecialAttribute.DISABLED : HtmlUtils.SpecialAttribute.NONE
		};
		return FORMAT__SELECT.format(_params);
	}

}