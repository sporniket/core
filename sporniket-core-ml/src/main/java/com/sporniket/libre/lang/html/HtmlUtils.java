/**
 * 
 */
package com.sporniket.libre.lang.html;

import java.text.MessageFormat;

import com.sporniket.libre.lang.sgml.SgmlUtils;

/**
 * Utility macros to generate HTML code.
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
public class HtmlUtils
{

	/**
	 * Attribute names reference.
	 * 
	 * <hr>
	 * <p>
	 * &copy; Copyright 2002-2016 David Sporn
	 * </p>
	 * <hr>
	 * 
	 * <p>
	 * This file is part of Sporniket-core-lang
	 * </p>
	 * <p>
	 * Sporniket-core-lang is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
	 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
	 * </p>
	 * <p>
	 * Sporniket-core-lang is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
	 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
	 * </p>
	 * <p>
	 * You should have received a copy of the GNU General Public License along with Sporniket-core-lang. If not, see
	 * <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>.
	 * </p>
	 * <hr>
	 * 
	 * @author David SPORN 
	 * @version 16.08.02
	 * @since 12.06.01
	 */
	public static final class AttributeNames
	{

		/**
		 * Attributes for form tag.
		 * <hr>
		 * <p>
		 * &copy; Copyright 2002-2016 David Sporn
		 * </p>
		 * <hr>
		 * 
		 * <p>
		 * This file is part of Sporniket-core-lang
		 * </p>
		 * <p>
		 * Sporniket-core-lang is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
		 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
		 * version.
		 * </p>
		 * <p>
		 * Sporniket-core-lang is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
		 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
		 * </p>
		 * <p>
		 * You should have received a copy of the GNU General Public License along with Sporniket-core-lang. If not, see
		 * <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>.
		 * </p>
		 * <hr>
		 * 
		 * @author David SPORN 
		 * @version 16.08.02
		 * @since 12.06.01
		 */
		public static final class Form
		{
			public static final String FOR = "for";

			public static final String METHOD = "method";
		}

		public static final String CLASS = "class";

		public static final String HREF = "href";

		public static final String ID = "id";

		public static final String LANG = "lang";

		public static final String NAME = "name";

		public static final String SRC = "src";

		public static final String STYLE = "style";

		public static final String VALUE = "value";
	}

	/**
	 * Code snippet for some special attributes.
	 * 
	 * Some attribute are specially coded in HTML, like the <code>checked</code> attribute.
	 * 
	 * <hr>
	 * <p>
	 * &copy; Copyright 2002-2016 David Sporn
	 * </p>
	 * <hr>
	 * 
	 * <p>
	 * This file is part of Sporniket-core-lang
	 * </p>
	 * <p>
	 * Sporniket-core-lang is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
	 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
	 * </p>
	 * <p>
	 * Sporniket-core-lang is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
	 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
	 * </p>
	 * <p>
	 * You should have received a copy of the GNU General Public License along with Sporniket-core-lang. If not, see
	 * <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>.
	 * </p>
	 * <hr>
	 * 
	 * @author David SPORN 
	 * @version 16.08.02
	 * @since 12.06.01
	 */
	static final class SpecialAttribute
	{
		/**
		 * Snippet to use to activate multiple selection (in selector)
		 */
		static final String ALLOW_MULTIPLE_SELECTION = "multiple=\"multiple\"";

		/**
		 * Snippet to use to mark a checkbox.
		 */
		static final String CHECKED = "checked=\"checked\"";

		/**
		 * Snippet to use to disable a form control
		 */
		static final String DISABLED = "disabled=\"disabled\"";

		/**
		 * Snippet to use when a special attribute is set to false.
		 */
		static final String NONE = "";

		/**
		 * Snippet to use to mark a selection.
		 */
		static final String SELECTED = "selected=\"selected\"";

	}

	private static final MessageFormat FORMAT_PARAGRAPH = new MessageFormat("<p>{0}</p>");

	/**
	 * Create a HTML paragraph from the given text.
	 * 
	 * @param value the value to enclose in paragraph.
	 * @return a HTML paragraphe.
	 */
	public static String encloseInParagraphe(String value)
	{
		Object[] _params =
		{
			value
		};
		return FORMAT_PARAGRAPH.format(_params);
	}

	/**
	 * Generate the HTML code for an attribute.
	 * 
	 * @param attributeName the name of the attribute.
	 * @param value the value of the attribute.
	 * @return the HTML attribute.
	 */
	public static String generateAttribute(String attributeName, String value)
	{
		return SgmlUtils.generateAttribute(attributeName, value);
	}
}
