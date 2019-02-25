package com.sporniket.libre.lang.html;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.sporniket.libre.lang.string.StringTools;

/**
 * Model for a group of radio button.
 * 
 * <p>
 * To generate the HTML code, simply call the {@link #toString()} method.
 * </p>
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
 * @version 19.02.00
 * @since 12.06.01
 */
public class RadioButtonGroup extends NamedElement
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1292844792597710089L;

	private static final MessageFormat FORMAT__END = new MessageFormat("</ul>");

	private static final MessageFormat FORMAT__ITEM = new MessageFormat("<li>{0}</li>");

	/**
	 * Template for starting the group.
	 * 
	 * 
	 * <ul>
	 * <li>{0} : id</li>
	 * <li>{1} : class</li> </lu>
	 */
	private static final MessageFormat FORMAT__START = new MessageFormat("<ul {0} {1}>");

	public static RadioButtonGroup create(String name, String idSuffix, List<RadioButton> options)
	{
		RadioButtonGroup _result = new RadioButtonGroup();
		_result.setName(name);
		if (!StringTools.isEmptyString(idSuffix))
		{
			_result.setIdSuffix(idSuffix);
		}
		_result.getOptions().addAll(options);
		return _result;
	}

	private List<RadioButton> myOptions = new ArrayList<RadioButton>();

	/**
	 * @return the options
	 */
	public List<RadioButton> getOptions()
	{
		return myOptions;
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
	 * @return the HTML code.
	 */
	public String getHtmlCode()
	{
		StringBuffer _buffer = new StringBuffer();

		Object[] _paramsStart =
		{
				getHtmlCodeForIdAttribute(), getHtmlCodeForNameAttribute()
		};
		_buffer.append(FORMAT__START.format(_paramsStart));

		for (RadioButton _radio : getOptions())
		{
			Object[] _paramsItem =
			{
				_radio.getHtmlCode()
			};
			_buffer.append(FORMAT__ITEM.format(_paramsItem));
		}

		Object[] _paramsEnd = {};
		_buffer.append(FORMAT__END.format(_paramsEnd));
		return _buffer.toString();
	}

}