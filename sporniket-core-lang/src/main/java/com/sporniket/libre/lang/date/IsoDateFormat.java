/**
 * 
 */
package com.sporniket.libre.lang.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Ready to use date format following the ISO format.
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
 * @version 19.04.00
 * @since 12.06.01
 */
// FIXME SimpleDateFormat is not thread safe, give access to the pattern, and provide function instanciating a dateformat using the
// pattern, deprecate current dateformat instance.
public class IsoDateFormat
{
	/**
	 * Long format : date, time up to millisecond.
	 */
	public static final DateFormat LONG__MS = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

	/**
	 * Short format : date.
	 */
	public static final DateFormat SHORT__DATE = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Short format : date, time.
	 */
	public static final DateFormat SHORT__DATE_TIME = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

}
