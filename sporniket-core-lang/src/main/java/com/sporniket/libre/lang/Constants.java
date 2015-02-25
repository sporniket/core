/**
 * 
 */
package com.sporniket.libre.lang;

import java.nio.charset.Charset;

/**
 * Predefined constants.
 * 
 * <p>
 * &copy; Copyright 2002-2012 David Sporn
 * </p>
 * <hr />
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
 * lang</i>. If not, see <http://www.gnu.org/licenses/>. 2
 * 
 * <hr />
 * 
 * @author David SPORN <david.sporn@sporniket.com>
 * @version 15.02.00
 * @since 12.06.01
 */
public final class Constants
{

	/**
	 * Constants defining what <em>empty</em> means, according to a data type.
	 * 
	 * <p>
	 * &copy; Copyright 2002-2012 David Sporn
	 * </p>
	 * <hr />
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
	 * lang</i>. If not, see <http://www.gnu.org/licenses/>. 2
	 * 
	 * <hr />
	 * 
	 * @author David SPORN <david.sporn@sporniket.com>
	 * @version 15.02.00
	 * @since 12.06.01
	 */
	public static final class Empty
	{
		/**
		 * Empty object.
		 */
		public static final Object OBJECT = null;

		/**
		 * Empty string.
		 */
		public static final String STRING = "";
	}

	/**
	 * Constants defining byte values for common technical us-ascii7 characters.
	 * 
	 * <p>
	 * &copy; Copyright 2002-2012 David Sporn
	 * </p>
	 * <hr />
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
	 * lang</i>. If not, see <http://www.gnu.org/licenses/>. 2
	 * 
	 * <hr />
	 * 
	 * @author David SPORN <david.sporn@sporniket.com>
	 * @version 15.02.00
	 * @since 12.06.01
	 */
	public static final class CharacterCodePoints
	{
		public static final Byte HTAB = 9;

		public static final Byte LF = 10;

		public static final Byte CR = 13;

		public static final Byte DQUOTE = 22;

		public static final Byte SPACE = 32;

		public static final Byte PLUS_SIGN = 43;

		public static final Byte COMMA = 44;

		public static final Byte HYPHEN_MINUS = 45;

		public static final Byte PERIOD = 46;

		public static final Byte SOLIDUS = 47;

		public static final Byte COLON = 58;

		public static final Byte SEMICOLON = 59;

		public static final Byte LATIN_CAPITAL_LETTER_N = 78;

		public static final Byte LATIN_CAPITAL_LETTER_T = 84;

		public static final Byte LATIN_CAPITAL_LETTER_X = 88;

		public static final Byte LATIN_CAPITAL_LETTER_Z = 90;

		public static final Byte BACKSLASH = 92;

		public static final Byte LATIN_SMALL_LETTER_N = 110;
	}

	/**
	 * Charset names.
	 * 
	 * <p>
	 * &copy; Copyright 2002-2012 David Sporn
	 * </p>
	 * <hr />
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
	 * lang</i>. If not, see <http://www.gnu.org/licenses/>. 2
	 * 
	 * <hr />
	 * 
	 * @author David SPORN <david.sporn@sporniket.com>
	 * @version 15.02.00
	 * @since 15.02.00
	 */
	public static final class CharsetNames
	{
		public static final Charset UTF8 = Charset.forName("UTF8");
	}

	/**
	 * Hash algorithm names.
	 * <p>
	 * &copy; Copyright 2002-2012 David Sporn
	 * </p>
	 * <hr />
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
	 * lang</i>. If not, see <http://www.gnu.org/licenses/>. 2
	 * 
	 * <hr />
	 * 
	 * @author David SPORN <david.sporn@sporniket.com>
	 * @version 15.02.00
	 * @since 15.02.00
	 */
	public static final class HashAlgorithmNames
	{
		public static final String MD5 = "MD5";
	}
}
