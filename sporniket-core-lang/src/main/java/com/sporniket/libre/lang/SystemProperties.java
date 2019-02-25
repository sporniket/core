/**
 * 
 */
package com.sporniket.libre.lang;

/**
 * Macro to access system properties.
 * 
 * <p>
 * System properties are referenced by official names, but one might want to avoid using those litteral all around the code.
 * 
 * <p>
 * Public properties are directly accessible, properties requiring a signed applet are in {@link SystemProperties.Protected},
 * properties that cannot be accessed by any applet are in {@link SystemProperties.Private}
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
public class SystemProperties
{
	/**
	 * Access to properties forbidden in applets.
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
	 * <i>The Sporniket Core Library &#8211; lang</i> is free software: you can redistribute it and/or modify it under the terms of
	 * the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at
	 * your option) any later version.
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
	 */
	public static class Private
	{
		public static String getUserHome()
		{
			return System.getProperty("user.home");
		}
	}

	/**
	 * Access to properties requiring a signed applet.
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
	 * <i>The Sporniket Core Library &#8211; lang</i> is free software: you can redistribute it and/or modify it under the terms of
	 * the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at
	 * your option) any later version.
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
	 */
	public static class Protected
	{
		public static String getUserAgent()
		{
			return System.getProperty("http.agent");
		}

		public static String getTempDirectory()
		{
			return System.getProperty("java.io.tmpdir");
		}
	}

	public static String getLineSeparator()
	{
		return System.getProperty("line.separator");
	}
}
