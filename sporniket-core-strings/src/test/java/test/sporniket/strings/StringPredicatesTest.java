package test.sporniket.strings;

import static com.sporniket.strings.StringPredicates.IS_BLANK;
import static com.sporniket.strings.StringPredicates.IS_EMPTY;
import static com.sporniket.strings.StringPredicates.IS_NOT_BLANK;
import static com.sporniket.strings.StringPredicates.IS_NOT_EMPTY;
import static java.util.Arrays.asList;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

/**
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
 * @version 19.02.00
 * @since 19.02.00
 */
public class StringPredicatesTest
{
	@TestFactory
	public Stream<DynamicTest> testIsBlank()
	{
		return asList(//
				dynamicTest("null is blank", () -> then(IS_BLANK.test(null)).isTrue())//
				, dynamicTest("'' is blank", () -> then(IS_BLANK.test("")).isTrue())//
				, dynamicTest("'  ' is blank", () -> then(IS_BLANK.test("  ")).isTrue())//
				, dynamicTest("'aa' is not blank", () -> then(IS_BLANK.test("aaa")).isFalse())//
				, dynamicTest("'   aa   ' is not blank", () -> then(IS_BLANK.test("  aaa  ")).isFalse())//
		).stream();
	}

	@TestFactory
	public Stream<DynamicTest> testIsEmpty()
	{
		return asList(//
				dynamicTest("null is empty", () -> then(IS_EMPTY.test(null)).isTrue())//
				, dynamicTest("'' is empty", () -> then(IS_EMPTY.test("")).isTrue())//
				, dynamicTest("'  ' is not empty", () -> then(IS_EMPTY.test("  ")).isFalse())//
				, dynamicTest("'aa' is not empty", () -> then(IS_EMPTY.test("aaa")).isFalse())//
				, dynamicTest("'   aa   ' is not empty", () -> then(IS_EMPTY.test("  aaa  ")).isFalse())//
		).stream();
	}

	@TestFactory
	public Stream<DynamicTest> testIsNotBlank()
	{
		return asList(//
				dynamicTest("null is blank", () -> then(IS_NOT_BLANK.test(null)).isFalse())//
				, dynamicTest("'' is blank", () -> then(IS_NOT_BLANK.test("")).isFalse())//
				, dynamicTest("'  ' is blank", () -> then(IS_NOT_BLANK.test("  ")).isFalse())//
				, dynamicTest("'aa' is not blank", () -> then(IS_NOT_BLANK.test("aaa")).isTrue())//
				, dynamicTest("'   aa   ' is not blank", () -> then(IS_NOT_BLANK.test("  aaa  ")).isTrue())//
		).stream();
	}

	@TestFactory
	public Stream<DynamicTest> testIsNotEmpty()
	{
		return asList(//
				dynamicTest("null is empty", () -> then(IS_NOT_EMPTY.test(null)).isFalse())//
				, dynamicTest("'' is empty", () -> then(IS_NOT_EMPTY.test("")).isFalse())//
				, dynamicTest("'  ' is not empty", () -> then(IS_NOT_EMPTY.test("  ")).isTrue())//
				, dynamicTest("'aa' is not empty", () -> then(IS_NOT_EMPTY.test("aaa")).isTrue())//
				, dynamicTest("'   aa   ' is not empty", () -> then(IS_NOT_EMPTY.test("  aaa  ")).isTrue())//
		).stream();
	}
}
