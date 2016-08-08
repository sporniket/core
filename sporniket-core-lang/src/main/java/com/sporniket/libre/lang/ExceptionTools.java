/*
 * Created on 10 sept. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.sporniket.libre.lang;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Exception utilities, like extracting only a few lines of the StackTrace.
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
public class ExceptionTools
{
	/**
	 * Return a string containing the result of <code>printStackTrace()</code>.
	 * <p>
	 * This method is not limited to exceptions but is applicable on any throwable objects.
	 * </p>
	 * 
	 * @param t
	 *            the throwable to trace.
	 * @return the String containing the trace
	 */
	public static String getStackTrace(final Throwable t)
	{
		StringWriter _writer = new StringWriter();
		PrintWriter _print = new PrintWriter(_writer);
		t.printStackTrace(_print);
		return _writer.toString();
	}

	/**
	 * Return a string containing the result of <code>printStackTrace()</code>.
	 * <p>
	 * This method is not limited to exceptions but is applicable on any throwable objects.
	 * </p>
	 * 
	 * @param t
	 *            the throwable to trace.
	 * @param limit
	 *            limit the number of trace to display. 0 means all the stack.
	 * @return the String containing the trace
	 */
	public static String getStackTrace(final Throwable t, final int limit)
	{
		if (0 >= limit)
		{
			return getStackTrace(t);
		}
		StringBuffer _resultBuffer = new StringBuffer();
		_resultBuffer.append(t.getClass().getName()).append(" : ").append(t.getLocalizedMessage());
		StackTraceElement[] _stackTrace = t.getStackTrace();
		for (int _index = 0; _index < _stackTrace.length && _index < limit; _index++)
		{
			StackTraceElement _traceElement = _stackTrace[_index];
			_resultBuffer.append("\n\tat ").append(_traceElement);
		}
		if (_stackTrace.length > limit)
		{
			_resultBuffer.append("\n\t...");
		}
		return _resultBuffer.toString();
	}
}
