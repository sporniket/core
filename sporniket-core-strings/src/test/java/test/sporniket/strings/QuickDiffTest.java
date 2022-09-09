/**
 * 
 */
package test.sporniket.strings;

import static com.sporniket.strings.QuickDiff.reportDiff;
import static java.lang.String.format;
import static java.lang.String.join;
import static java.util.Arrays.asList;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sporniket.strings.QuickDiff;

/**
 * Test suite for {@link QuickDiff}.
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
 * @version 22.09.00
 * @since 12.09.01
 */
public class QuickDiffTest extends TestBase
{
	static class TestSpecStruct
	{
		public String label;

		public boolean ignoreEmptyLines;

		public boolean ignoreTrailingWhiteSpaces;

		public String[] left;

		public String[] right;

		public String[] reportLtr;

		public String[] reportRtl;
	}

	@TestFactory
	public Stream<DynamicTest> shouldPassSpecifiedScenario()
			throws URISyntaxException, JsonParseException, JsonMappingException, IOException
	{
		String resourceName = format("%s_data/specs.json", getClass().getName().replace('.', File.separatorChar));
		return asList(loadJsonData(resourceName, TestSpecStruct[].class))//
				.stream()//
				.flatMap(s -> asList(//
						dynamicTest(format("%s -- ltr", s.label),
								() -> then(join("\n", reportDiff(s.left, s.right, s.ignoreEmptyLines, s.ignoreTrailingWhiteSpaces)))
										.isEqualTo(join("\n", s.reportLtr)))//
						,
						dynamicTest(format("%s -- rtl", s.label),
								() -> then(join("\n", reportDiff(s.right, s.left, s.ignoreEmptyLines, s.ignoreTrailingWhiteSpaces)))
										.isEqualTo(join("\n", s.reportRtl)))//
				).stream());
	}
}
