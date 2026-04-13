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

    @Nested
    class ConvertToPaddedHexString
    {

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

        @ParameterizedTest
        @MethodSource("test.sporniket.libre.lang.TestDataTools#provideByteTests")
        void should_convert_single_byte_to_zero_padded_hex_string(final byte value, final String expectedHexString)
        {
            then(DataTools.convertToPaddedHexString(value)).isEqualTo(expectedHexString);
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
    class MatchSequence
    {
        private static final byte[] BUFFER = new byte[]
        {
                5, 6, 7, 8, 1, 2, 3, 4
        };

        private static final byte[] BUFFER_SHORTER_THAN_EXPECTED = new byte[]
        {
                5, 6, 7, 8, 1, 2, 3
        };

        private static final byte[] TO_FIND = new byte[]
        {
                1, 2, 3, 4
        };

        private static final byte[] TO_FIND_AND_MORE = new byte[]
        {
                1, 2, 3, 4, 5, 6, 7, 8
        };

        private static final byte[] TO_FIND_BUT_ONE_EDIT = new byte[]
        {
                1, 2, 0, 4
        };

        private static final byte[] TO_FIND_BUT_SMALLER = new byte[]
        {
                1, 2, 3
        };

        static Stream<Arguments> provideBufferEndsBeforeSequenceTests()
        {
            return Stream.of( //
                    Arguments.of("Should not match when sequence is longer than buffer", TO_FIND, TO_FIND_BUT_SMALLER, 0), //
                    Arguments.of("Should not match when buffer ends before sequence", TO_FIND, BUFFER_SHORTER_THAN_EXPECTED, 4) //
            );
        }

        static Stream<Arguments> provideDefaultOffsetTests()
        {
            return Stream.of( //
                    Arguments.of("Should match itself", TO_FIND, TO_FIND, true), //
                    Arguments.of("Should match when buffer starts with sequence", TO_FIND_BUT_SMALLER, TO_FIND, true), //
                    Arguments.of("Should not match when buffer does not start with sequence", TO_FIND, BUFFER, false), //
                    Arguments.of("Should not match when buffer is not the same as sequence", TO_FIND, TO_FIND_BUT_ONE_EDIT, false) //
            );
        }

        static Stream<Arguments> provideMatchingTests()
        {
            return Stream.of( //
                    Arguments.of("Should match itself", TO_FIND, TO_FIND, 0), //
                    Arguments.of("Should match when sequence is found inside at offset", TO_FIND_BUT_SMALLER, BUFFER, 4), //
                    Arguments.of("Should match when buffer ends with sequence", TO_FIND, BUFFER, 4), //
                    Arguments.of("Should match when buffer starts with sequence", TO_FIND_BUT_SMALLER, TO_FIND, 0) //
            );
        }

        @ParameterizedTest(name = "[{index}] {0}")
        @MethodSource("provideDefaultOffsetTests")
        void should_compare_with_default_offset_of_zero(final String testDescription, final byte[] givenSequence,
                final byte[] givenBuffer, final boolean expectedResult)
        {
            then(DataTools.matchSequence(givenSequence, givenBuffer)).isEqualTo(expectedResult);
        }

        @Test
        void should_match_buffer_longer_than_given_sequence_when_the_later_is_found()
        {
            then(DataTools.matchSequence(TO_FIND, TO_FIND_AND_MORE)).isTrue();
        }

        @Test
        void should_match_sequence_starting_from_offset()
        {
            then(DataTools.matchSequence(TO_FIND, BUFFER, 4)).isTrue();
        }

        @ParameterizedTest(name = "[{index}] {0}")
        @MethodSource("provideMatchingTests")
        void should_match_when_sequence_is_at_given_position(final String testDescription, final byte[] givenSequence,
                final byte[] givenBuffer, final int givenOffset)
        {
            then(DataTools.matchSequence(givenSequence, givenBuffer, givenOffset)).isTrue();
        }

        @Test
        void should_not_match_buffer_as_soon_as_there_is_a_difference()
        {
            then(DataTools.matchSequence(TO_FIND, TO_FIND_BUT_ONE_EDIT)).isFalse();
        }

        @Test
        void should_not_match_buffer_shorter_than_sequence()
        {
            then(DataTools.matchSequence(TO_FIND, TO_FIND_BUT_SMALLER)).isFalse();
        }

        @Test
        void should_not_match_when_both_sequence_and_buffer_are_null()
        {
            then(DataTools.matchSequence(null, null)).isFalse();
        }

        @ParameterizedTest(name = "[{index}] {0}")
        @MethodSource("provideBufferEndsBeforeSequenceTests")
        void should_not_match_when_buffers_ends_before_sequence(final String testDescription, final byte[] givenSequence,
                final byte[] givenBuffer, final int givenOffset)
        {
            then(DataTools.matchSequence(givenSequence, givenBuffer, givenOffset)).isFalse();
        }

        @Test
        void should_not_match_when_offset_is_negative()
        {
            then(DataTools.matchSequence(TO_FIND, TO_FIND_AND_MORE, -1)).isFalse();
        }

        @Test
        void should_not_match_when_only_buffer_is_null()
        {
            then(DataTools.matchSequence(TO_FIND, null)).isFalse();
        }

        @Test
        void should_not_match_when_only_sequence_is_null()
        {
            then(DataTools.matchSequence(null, BUFFER)).isFalse();
        }
    }

    static Stream<Arguments> provideByteTests()
    {
        return Stream.of( //
                Arguments.of((byte) 10, "0a"), //
                Arguments.of((byte) 160, "a0") //
        );
    }
}
