/**
 * 
 */
package com.sporniket.libre.lang;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Predefined constants.
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
 * @version 22.11.00
 * @since 12.06.01
 */
public final class Constants
{

    /**
     * Charset names.
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
     * @version 22.11.00
     * @since 15.02.00
     * @deprecated use {@link StandardCharsets#UTF_8}
     */
    public static final class CharsetNames
    {
        public static final Charset UTF8 = Charset.forName("UTF8");
    }
}
