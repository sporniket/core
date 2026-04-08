/**
 *
 */
package test.sporniket.libre.lang;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.stream.Stream;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.sporniket.libre.lang.DataTools;

/**
 * Test suite for {@link DataTools}.
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
 * GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * <p>
 * <i>The Sporniket Core Library &#8211; lang</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * <p>
 * You should have received a copy of the GNU Affero General Public License along with <i>The Sporniket Core Library &#8211;
 * lang</i>. If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
 *
 * <hr>
 *
 * @author David SPORN
 *
 * @version 22.11.00
 * @since 15.02.00
 */
public class TestDataTools
{
	@Nested
	class GetUnsignedIntValue
	{

		private static Stream<Arguments> provideUnsignedIntTests()
		{
			return Stream.of( //
					Arguments.of("Unsigned value of positive int", 282, "11a"), //
					Arguments.of("Unsigned value of negative int", -2023157196, "87691234") //
			);
		}

		@ParameterizedTest(name = "[{index}] {0}")
		@MethodSource("provideUnsignedIntTests")
		final void should_convert_int_to_unsigned_value(final String testDescription, final int value,
				final String expectedHexString)
		{
			then(Long.toHexString(DataTools.getUnsignedIntValue(value))) //
					.isEqualTo(expectedHexString);
		}

	}

	@Nested
	class GetUnsignedShortValue
	{
		private static Stream<Arguments> provideUnsignedShortTests()
		{
			return Stream.of( //
					Arguments.of("Unsigned value of positive short", (short) 282, "11a"), //
					Arguments.of("Unsigned value of negative short", (short) -30871, "8769") //
			);
		}

		@ParameterizedTest(name = "[{index}] {0}")
		@MethodSource("provideUnsignedShortTests")
		final void should_convert_int_to_unsigned_value(final String testDescription, final short value,
				final String expectedHexString)
		{
			then(Integer.toHexString(DataTools.getUnsignedShortValue(value))) //
					.isEqualTo(expectedHexString);
		}

	}

	@Nested
	class GetUnsignedByteValue
	{
		private static Stream<Arguments> provideUnsignedByteTests()
		{
			return Stream.of( //
					Arguments.of("Unsigned value of positive short", (byte) 113, "71"), //
					Arguments.of("Unsigned value of negative short", (byte) -121, "87") //
			);
		}

		@ParameterizedTest(name = "[{index}] {0}")
		@MethodSource("provideUnsignedByteTests")
		final void should_convert_int_to_unsigned_value(final String testDescription, final byte value,
				final String expectedHexString)
		{
			then(Integer.toHexString(DataTools.getUnsignedByteValue(value))) //
					.isEqualTo(expectedHexString);
		}

	}

	static Stream<Arguments> provideByteTests()
	{
		return Stream.of( //
				Arguments.of((byte) 10, "0a"), //
				Arguments.of((byte) 160, "a0") //
		);
	}

	@Nested
	class ConvertToPaddedHexString
	{

		@ParameterizedTest
		@MethodSource("test.sporniket.libre.lang.TestDataTools#provideByteTests")
		void should_convert_single_byte_to_zero_padded_hex_string(final byte value, final String expectedHexString)
		{
			then(DataTools.convertToPaddedHexString(value)).isEqualTo(expectedHexString);
		}

		@Test
		public final void should_convert_byte_array_to_zero_padded_hex_string()
		{
			then(DataTools.convertToPaddedHexString(new byte[]
			{
					10, //
					(byte) 160, //
					10
			})).isEqualTo("0aa00a");
		}
	}

	@Nested
	class AppendByteAsPaddedHexString
	{

		@ParameterizedTest
		@MethodSource("test.sporniket.libre.lang.TestDataTools#provideByteTests")
		void should_convert_single_byte_to_zero_padded_hex_string(final byte value, final String expectedHexString)
		{
			then(DataTools.appendByteAsPaddedHexString(value, new StringBuffer()).toString()).isEqualTo(expectedHexString);
		}
	}

	@Nested
	class AppendBytesAsPaddedHexString
	{
		@Test
		public final void should_convert_byte_array_to_zero_padded_hex_string()
		{
			then(DataTools.appendBytesAsPaddedHexString(new byte[]
			{
					10, //
					(byte) 160, //
					10
			}, new StringBuffer()).toString()).isEqualTo("0aa00a");
		}
	}
}
