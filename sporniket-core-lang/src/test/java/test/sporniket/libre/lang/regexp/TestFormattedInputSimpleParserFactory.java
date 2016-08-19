/**
 * 
 */
package test.sporniket.libre.lang.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;

import com.sporniket.libre.lang.regexp.FormattedInputSimpleParserFactory;

/**
 * Test suite for the {@link FormattedInputSimpleParserFactory}.
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
 * @version 16.08.01
 * @since 16.08.00
 */
public class TestFormattedInputSimpleParserFactory extends TestCase
{
	public void testTypicalInputFormat()
	{
		Pattern _parserToTest = FormattedInputSimpleParserFactory.getSimpleParser("foo:#,#,$");
		String _testInput = "foo:1,2,trois";
		Matcher _parsedResult = _parserToTest.matcher(_testInput);
		if (_parsedResult.matches())
		{
			if (!"1".equals(_parsedResult.group(1)))
			{
				fail("did not match correctly first value");
			}
			if (!"2".equals(_parsedResult.group(2)))
			{
				fail("did not match correctly second value");
			}
			if (!"trois".equals(_parsedResult.group(3)))
			{
				fail("did not match correctly third value");
			}
		}
		else
		{
			fail("syntax error in input :'" + _testInput + "'");
		}
	}

	public void testTypicalInputFormat__blanks()
	{
		Pattern _parserToTest = FormattedInputSimpleParserFactory.getSimpleParser("foo\u00a0# , # , $");
		String _testInput = "foo 1	,2	,trois";
		String _testInputInvalid = "foo1	,2	,trois";
		if (_parserToTest.matcher(_testInputInvalid).matches())
		{
			fail("MUST reject input with missing mandatory blank spaces");
		}
		Matcher _parsedResult = _parserToTest.matcher(_testInput);
		if (_parsedResult.matches())
		{
			if (!"1".equals(_parsedResult.group(1)))
			{
				fail("did not match correctly first value");
			}
			if (!"2".equals(_parsedResult.group(2)))
			{
				fail("did not match correctly second value");
			}
			if (!"trois".equals(_parsedResult.group(3)))
			{
				fail("did not match correctly third value");
			}
		}
		else
		{
			fail("syntax error in input :'" + _testInput + "'");
		}
	}
}
