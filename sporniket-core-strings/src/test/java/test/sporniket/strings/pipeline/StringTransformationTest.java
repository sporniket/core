/**
 * 
 */
package test.sporniket.strings.pipeline;

import static com.sporniket.strings.pipeline.StringTransformation.NULL_TO_EMPTY;
import static com.sporniket.strings.pipeline.StringTransformation.TO_HASH_MD5;
import static com.sporniket.strings.pipeline.StringTransformation.TO_LOWERCASE;
import static com.sporniket.strings.pipeline.StringTransformation.TO_UPPERCASE;
import static com.sporniket.strings.pipeline.StringTransformation.TRIM;
import static com.sporniket.strings.pipeline.StringTransformation.TRIM_END;
import static com.sporniket.strings.pipeline.StringTransformation.TRIM_START;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

/**
 * Test built-in String transformations.
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
 * @since 19.02.00
 */
public class StringTransformationTest
{
	List<String> WHITE_SPACE_COMBINATIONS = Arrays.asList(" ", "\t", " \t", "\t ");

	@TestFactory
	public Stream<DynamicTest> testNullToEmpty()
	{
		return asList(//
				dynamicTest("Should return an empty string when input is null",
						() -> then(NULL_TO_EMPTY.apply(null)).isNotNull().isEqualTo(""))//
				, dynamicTest("Should return the input string when it is not null",
						() -> then(NULL_TO_EMPTY.apply("a")).isEqualTo("a"))//
		).stream();
	}

	@TestFactory
	public Stream<DynamicTest> testToHashMd5()
	{
		return asList(//
				dynamicTest("Should compute the MD5 hash of the input string",
						() -> then(TO_HASH_MD5.apply("abcd")).isEqualTo("E2FC714C4727EE9395F324CD2E7F331F"))//
		).stream();
	}

	@TestFactory
	public Stream<DynamicTest> testToLowercase()
	{
		return asList(//
				dynamicTest("Should convert to upper case all the chars of the input string",
						() -> then(TO_LOWERCASE.apply("WhAtEvEr")).isEqualTo("whatever"))//
		).stream();
	}

	@TestFactory
	public Stream<DynamicTest> testToUppercase()
	{
		return asList(//
				dynamicTest("Should convert to upper case all the chars of the input string",
						() -> then(TO_UPPERCASE.apply("WhatEver")).isEqualTo("WHATEVER"))//
		).stream();
	}

	@TestFactory
	public Stream<DynamicTest> testTrim()
	{
		final String _expected = "a";
		return WHITE_SPACE_COMBINATIONS.parallelStream()//
				.map(t -> t + _expected)//
				.flatMap(t -> WHITE_SPACE_COMBINATIONS.parallelStream().map(suffix -> t + suffix))//
				.map(text -> dynamicTest(format("Should remove whitespaces at both ends of '%s'", text),
						() -> then(TRIM.apply(text)).isEqualTo(_expected)))//
		;
	}

	@TestFactory
	public Stream<DynamicTest> testTrim_OnBlankStrings()
	{
		final String _expected = "";
		return WHITE_SPACE_COMBINATIONS.parallelStream()//
				.map(text -> dynamicTest(format("Should remove whitespaces at both ends of '%s'", text),
						() -> then(TRIM.apply(text)).isEqualTo(_expected)))//
		;
	}

	@TestFactory
	public Stream<DynamicTest> testTrimEnd()
	{
		final String _expected = "a";
		return WHITE_SPACE_COMBINATIONS.parallelStream()//
				.map(t -> _expected + t)//
				.map(text -> dynamicTest(format("Should remove whitespaces at the end of '%s'", text),
						() -> then(TRIM_END.apply(text)).isEqualTo(_expected)));
	}

	@TestFactory
	public Stream<DynamicTest> testTrimEnd_OnBlankStrings()
	{
		final String _expected = "";
		return WHITE_SPACE_COMBINATIONS.parallelStream()//
				.map(text -> dynamicTest(format("Should remove whitespaces at the end of '%s'", text),
						() -> then(TRIM_END.apply(text)).isEqualTo(_expected)));
	}

	@TestFactory
	public Stream<DynamicTest> testTrimStart()
	{
		final String _expected = "a";
		return WHITE_SPACE_COMBINATIONS.parallelStream()//
				.map(t -> t + _expected)//
				.map(text -> dynamicTest(format("Should remove whitespaces at the start of '%s'", text),
						() -> then(TRIM_START.apply(text)).isEqualTo(_expected)));
	}

	@TestFactory
	public Stream<DynamicTest> testTrimStart_OnBlankStrings()
	{
		final String _expected = "";
		return WHITE_SPACE_COMBINATIONS.parallelStream()//
				.map(text -> dynamicTest(format("Should remove whitespaces at the start of '%s'", text),
						() -> then(TRIM_START.apply(text)).isEqualTo(_expected)));
	}
}
