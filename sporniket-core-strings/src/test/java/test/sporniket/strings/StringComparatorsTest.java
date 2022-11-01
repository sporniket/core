package test.sporniket.strings;

import static com.sporniket.strings.StringComparators.STRING_COMPARATOR_NATURAL;
import static com.sporniket.strings.StringComparators.STRING_COMPARATOR_NATURAL_IGNORE_CASE;
import static com.sporniket.strings.StringComparators.STRING_COMPARATOR_REVERSE;
import static com.sporniket.strings.StringComparators.STRING_COMPARATOR_REVERSE_IGNORE_CASE;
import static java.lang.Integer.signum;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
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
 * @since 19.02.00
 */
public class StringComparatorsTest extends TestBase
{
	static class TestSpecStruct
	{
		public int compareLtr;

		public int compareRtl;

		public String left;

		public String right;
	}

	private static String formatOperand(String operand)
	{
		return null == operand ? "<null>" : format("'%s'", operand);
	}

	private static String formatSign(int sign)
	{
		return sign < 0 //
				? "< 0"//
				: sign > 0 //
						? "> 0"//
						: "= 0";
	}

	private Stream<DynamicTest> createTestSet(Comparator<String> comparator, String comparatorName, String specsFilename)
			throws JsonParseException, JsonMappingException, IOException
	{
		String resourceName = format("%s_data/%s.json", getClass().getName().replace('.', File.separatorChar), specsFilename);
		return asList(loadJsonData(resourceName, TestSpecStruct[].class))//
				.stream()//
				.flatMap(s -> asList(//
						dynamicTest(
								format("%s( %s, %s) %s", comparatorName, formatOperand(s.left), formatOperand(s.right),
										formatSign(s.compareLtr)),
								() -> then(signum(comparator.compare(s.left, s.right))).isEqualTo(s.compareLtr))//
						,
						dynamicTest(
								format("%s( %s, %s) %s", comparatorName, formatOperand(s.right), formatOperand(s.left),
										formatSign(s.compareRtl)),
								() -> then(signum(comparator.compare(s.right, s.left))).isEqualTo(s.compareRtl))//
				).stream());
	}

	@TestFactory
	public Stream<DynamicTest> testStringComparatorNatural()
			throws URISyntaxException, JsonParseException, JsonMappingException, IOException
	{
		return createTestSet(//
				STRING_COMPARATOR_NATURAL, //
				"STRING_COMPARATOR_NATURAL", //
				"specsNatural");
	}

	@TestFactory
	public Stream<DynamicTest> testStringComparatorNaturalIgnoreCase()
			throws URISyntaxException, JsonParseException, JsonMappingException, IOException
	{
		return createTestSet(STRING_COMPARATOR_NATURAL_IGNORE_CASE, //
				"STRING_COMPARATOR_NATURAL_IGNORE_CASE", //
				"specsNaturalIgnoreCase");
	}

	@TestFactory
	public Stream<DynamicTest> testStringComparatorReverse()
			throws URISyntaxException, JsonParseException, JsonMappingException, IOException
	{
		return createTestSet(//
				STRING_COMPARATOR_REVERSE, //
				"STRING_COMPARATOR_REVERSE", //
				"specsReverse");
	}

	@TestFactory
	public Stream<DynamicTest> testStringComparatorReverseIgnoreCase()
			throws URISyntaxException, JsonParseException, JsonMappingException, IOException
	{
		return createTestSet(STRING_COMPARATOR_REVERSE_IGNORE_CASE, //
				"STRING_COMPARATOR_REVERSE_IGNORE_CASE", //
				"specsReverseIgnoreCase");
	}

}
