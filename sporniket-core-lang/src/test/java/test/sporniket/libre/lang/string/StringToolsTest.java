package test.sporniket.libre.lang.string;

import static com.sporniket.libre.lang.string.StringTools.removeWhiteSpaces;
import static com.sporniket.libre.lang.string.StringTools.SpaceRemovingMode.LEADING_SPACES;
import static com.sporniket.libre.lang.string.StringTools.SpaceRemovingMode.TRAILING_SPACES;
import static com.sporniket.libre.lang.string.StringTools.SpaceRemovingMode.TWO_ENDS_SPACES;
import static java.lang.String.format;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import com.sporniket.libre.lang.string.StringTools;
import com.sporniket.libre.lang.string.StringTools.SpaceRemovingMode;

/**
 * Test suite for {@link StringTools}.
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
 * @version 16.08.02
 * @since 15.09.00
 */
public class StringToolsTest
{
	List<String> WHITE_SPACE_COMBINATIONS = Arrays.asList(" ", "\t", " \t", "\t ");

	@TestFactory
	public Stream<DynamicTest> testRemovingHeadingSpaces()
	{
		final String _expected = "a";
		return WHITE_SPACE_COMBINATIONS.parallelStream()//
				.map(t -> t + _expected)//
				.map(text -> dynamicTest(format("Should remove leading whitespaces in '%s'", text),
						() -> then(removeWhiteSpaces(text, LEADING_SPACES)).isEqualTo(_expected)));
	}

	@TestFactory
	public Stream<DynamicTest> testRemovingTrailingSpaces()
	{
		final String _expected = "a";
		return WHITE_SPACE_COMBINATIONS.parallelStream()//
				.map(t -> _expected + t)//
				.map(text -> dynamicTest(format("Should remove trailing whitespaces in '%s'", text),
						() -> then(removeWhiteSpaces(text, TRAILING_SPACES)).isEqualTo(_expected)));
	}

	@TestFactory
    public Stream<DynamicTest> testRemovingSpacesOnBothEnds() {
		final String _expected = "a";
        return WHITE_SPACE_COMBINATIONS.parallelStream()//
        		.map(t -> t+_expected)//
        		.flatMap(t -> WHITE_SPACE_COMBINATIONS.parallelStream().map(suffix -> t+suffix))//
        		.map(text -> dynamicTest(format("Should remove leading and trailing whitespaces in '%s'", text),
						() -> then(removeWhiteSpaces(text, TWO_ENDS_SPACES)).isEqualTo(_expected)))//
        		;
    }
}
