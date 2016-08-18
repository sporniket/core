/**
 * 
 */
package com.sporniket.libre.io.parser.properties;

/**
 * Interface defining a listener for the parsing of properties.
 * 
 * <p>
 * &copy; Copyright 2002-2016 David Sporn
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
 * @version 16.08.00
 * @since 15.09.00
 */
public interface PropertiesParsingListener
{
	/**
	 * Event handler when a multiple line property has been read.
	 * 
	 * @param event
	 *            the event containing the property definition.
	 */
	void onMultipleLinePropertyParsed(MultipleLinePropertyParsedEvent event);

	/**
	 * Event handler when a single line property has been read.
	 * 
	 * @param event
	 *            the event containing the property definition.
	 */
	void onSingleLinePropertyParsed(SingleLinePropertyParsedEvent event);
}
