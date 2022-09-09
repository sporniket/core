/**
 * 
 */
package test.sporniket.strings.parsers;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

import com.sporniket.strings.parsers.FormattedInputSimpleParserFactory;

/**
 * Test suite for the {@link FormattedInputSimpleParserFactory}.
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
 * @since 16.08.00
 */
public class FormattedInputSimpleParserFactoryTest
{
	@Test
	public void testTypicalInputFormat()
	{
		Pattern _parserToTest = FormattedInputSimpleParserFactory.getSimpleParser("foo:#,#,$");
		String _testInput = "foo:1,2,trois";
		Matcher _parsedResult = _parserToTest.matcher(_testInput);
		then(_parsedResult.matches()).as("syntax error in input :'%s'", _testInput).isTrue();
		then(_parsedResult.group(1)).isEqualTo("1");
		then(_parsedResult.group(2)).isEqualTo("2");
		then(_parsedResult.group(3)).isEqualTo("trois");
	}

	@Test
	public void testTypicalInputFormat_specialChars()
	{
		Pattern _parserToTest = FormattedInputSimpleParserFactory.getSimpleParser("foo:{#},[#],($)");
		String _testInput = "foo:{1},[2],(trois)";
		Matcher _parsedResult = _parserToTest.matcher(_testInput);
		then(_parsedResult.matches()).as("syntax error in input :'%s'", _testInput).isTrue();
		then(_parsedResult.group(1)).isEqualTo("1");
		then(_parsedResult.group(2)).isEqualTo("2");
		then(_parsedResult.group(3)).isEqualTo("trois");
	}

	@Test
	public void testTypicalInputFormat__blanks()
	{
		Pattern _parserToTest = FormattedInputSimpleParserFactory.getSimpleParser("foo\u00a0# , # , $");
		String _testInput = "foo 1	,2	,trois";
		String _testInputInvalid = "foo1	,2	,trois";
		then(_parserToTest.matcher(_testInputInvalid).matches()).as("MUST reject input with missing mandatory blank spaces")
				.isFalse();

		Matcher _parsedResult = _parserToTest.matcher(_testInput);
		then(_parsedResult.matches()).as("syntax error in input :'%s'", _testInput).isTrue();
		then(_parsedResult.group(1)).isEqualTo("1");
		then(_parsedResult.group(2)).isEqualTo("2");
		then(_parsedResult.group(3)).isEqualTo("trois");
	}
}
