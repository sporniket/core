/**
 * 
 */
package com.sporniket.libre.lang.sgml;

import java.text.MessageFormat;

/**
 * Utility macro for sgml code generation.
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
 * @version 16.08.02
 * @since 12.06.01
 */
public class SgmlUtils
{
	private static final MessageFormat MESSAGE_FORMAT__ATTRIBUT = new MessageFormat(" {0}=\"{1}\"");

	/**
	 * Generate an attribute of the specified name and value.
	 * 
	 * @param attributeName name of the attribute.
	 * @param value value of the attribute.
	 * @return the SGML code for an attribute.
	 */
	public static String generateAttribute(String attributeName, String value)
	{
		Object[] _args =
		{
				attributeName, SgmlAttributeCodec.getInstance().encode(value)
		};
		return MESSAGE_FORMAT__ATTRIBUT.format(_args);
	}
}
