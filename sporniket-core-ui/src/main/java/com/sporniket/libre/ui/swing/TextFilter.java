package com.sporniket.libre.ui.swing;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import com.sporniket.libre.lang.string.StringTools;

/**
 * A filter for AbstractDocument instances.
 * <p>
 * This filter has a property <i>filter</i>. This is a string that gathers all accepted character. When inserting a String into a
 * document, any character that is not found in the filtering String is removed.
 * <p>
 * If the filter is empty, there is no filtering.
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
 * @version 16.08.01
 * @since 12.06.01
 * @see javax.swing.text.AbstractDocument
 * @see javax.swing.text.DocumentFilter
 * 
 */
public class TextFilter extends DocumentFilter
{
	// ============================================================
	// Public Interface

	// Overriden methods
	/**
	 * @see javax.swing.text.DocumentFilter#insertString(FilterBypass, int, String, AttributeSet)
	 */
	public void insertString(FilterBypass arg0, int arg1, String arg2, AttributeSet arg3) throws BadLocationException
	{
		String _filteredData = filterString(arg2);
		if (0 != _filteredData.length()) super.insertString(arg0, arg1, _filteredData, arg3);
	}

	/**
	 * @see javax.swing.text.DocumentFilter#replace(FilterBypass, int, int, String, AttributeSet)
	 */
	public void replace(FilterBypass arg0, int arg1, int arg2, String arg3, AttributeSet arg4) throws BadLocationException
	{
		String _filteredData = filterString(arg3);
		if (0 != _filteredData.length()) super.replace(arg0, arg1, arg2, _filteredData, arg4);
	}

	// Accessors

	/**
	 * Returns the filter.
	 * 
	 * @return the filter.
	 */
	public String getFilter()
	{
		return myFilter;
	}

	/**
	 * Sets the filter.
	 * 
	 * @param filter
	 *            The new filter.
	 */
	public void setFilter(String filter)
	{
		myFilter = filter;
	}

	// ============================================================
	// Implementation details

	/**
	 * The filtering String.
	 */
	private String myFilter = null;

	/**
	 * The filtering process.
	 * 
	 * @param source
	 *            the String to filter
	 * @return the filtered String
	 */
	private String filterString(String source)
	{
		return StringTools.filterString(source, myFilter);
	}

}
