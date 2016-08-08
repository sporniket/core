/**
 * 
 */
package com.sporniket.libre.lang.xml;

/**
 * Interface for a class that gives the node name assigned to a functionnal data.
 * 
 * This class is for writing a parser of xml files using either an application specific schema or a common schema (e.g. xHTML) but
 * with a structure that can be interpreted functionnally.
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
public interface NodeNameProvider
{
	/**
	 * Return the node name according to the nature of data looked for.
	 * 
	 * @param dataClass
	 *            the "class" of data, meaningful only for an application.
	 * @return the suitable node name.
	 */
	String getNodeName(String dataClass);
}
