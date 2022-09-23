package com.sporniket.libre.lang.date;

import java.text.MessageFormat;
import java.util.Date;

/**
 * Format date elements to obtain an ISO compliant date string.
 * 
 * To be used when one doesn't have a {@link Date} instance to work with.
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
 * @version 22.09.01
 * @since 12.06.01
 * 
 * @see IsoDateFormat
 */
public class IsoParsableDateFormatter
{
    /**
     * Defines parts of format.
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
     * @version 22.09.01
     * @since 12.06.01
     */
    private static class FormatFragments
    {
        /**
         * Date part.
         */
        public static final String DATE = "{0,number,0000}-{1,number,00}-{2,number,00}";

        /**
         * Time part.
         */
        public static final String TIME = "T{3,number,00}:{4,number,00}:{5,number,00}";

        /**
         * Millisecond part.
         */
        public static final String TIME_MS = ".{6,number,000}";
    }

    /**
     * Format for year-month-day'T'hour:minute:second.
     */
    private static final MessageFormat LONG__MS = new MessageFormat(
            FormatFragments.DATE + FormatFragments.TIME + FormatFragments.TIME_MS);

    /**
     * Format for year-month-day.
     */
    private static final MessageFormat SHORT__DATE = new MessageFormat(FormatFragments.DATE);

    /**
     * Format for year-month-day'T'hour:minute:second.
     */
    private static final MessageFormat SHORT__DATE_TIME = new MessageFormat(FormatFragments.DATE + FormatFragments.TIME);

    /**
     * Formatter for a date.
     * 
     * @param year
     *            [0..9999].
     * @param month
     *            [1..12].
     * @param day
     *            day of month [1..31].
     * @return the ISO formatted date.
     */
    public static String format(Integer year, Integer month, Integer day)
    {
        Object[] _args =
        {
                year, month, day
        };
        return SHORT__DATE.format(_args);
    }

    /**
     * Formatter for a date and time.
     * 
     * @param year
     *            [0..9999].
     * @param month
     *            [1..12].
     * @param day
     *            day of month [1..31].
     * @param hour
     *            [0..23].
     * @param minute
     *            [0..59].
     * @param second
     *            [0..59].
     * @return the ISO formatted date and time.
     */
    public static String format(Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second)
    {
        Object[] _args =
        {
                year, month, day, hour, minute, second
        };
        return SHORT__DATE_TIME.format(_args);
    }

    /**
     * Formatter for a date and time up to milliseconds.
     * 
     * @param year
     *            [0..9999].
     * @param month
     *            [1..12].
     * @param day
     *            day of month [1..31].
     * @param hour
     *            [0..23].
     * @param minute
     *            [0..59].
     * @param second
     *            [0..59].
     * @param millisecond
     *            [0..999].
     * @return the ISO formatted date and time.
     */
    public static String format(Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second,
            Integer millisecond)
    {
        Object[] _args =
        {
                year, month, day, hour, minute, second, millisecond
        };
        return LONG__MS.format(_args);
    }
}
