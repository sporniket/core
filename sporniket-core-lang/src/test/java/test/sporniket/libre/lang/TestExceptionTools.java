package test.sporniket.libre.lang;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import com.sporniket.libre.lang.ExceptionTools;

/**
 * Test suite for {@link ExceptionTools}.
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
 * @version 26.01.00
 * @since 26.01.00
 */
public class TestExceptionTools
{
    private static final Pattern STACK_TRACE_FRAME_PATTERN = Pattern
            .compile("\tat ([^./$]+)([./$][^./$]+)*[(]([^.]+[.]java[:])([0-9])+[)]");

    @Test
    void should_return_printed_stacktrace_in_full()
    {
        String[] _stack;

        // Execute
        try
        {
            throw new IllegalStateException("whatever");
        }
        catch (final Exception _e)
        {
            _stack = ExceptionTools.getStackTrace(_e).split("\n");
        }

        // Verify
        then(_stack).isNotNull().hasSizeGreaterThan(2);
        then(_stack[0].stripTrailing()).isEqualTo("java.lang.IllegalStateException: whatever");
        then(_stack[1].stripTrailing()).matches(Pattern.compile(
                "\tat test[.]sporniket[.]libre[.]lang[.]TestExceptionTools[.]should_return_printed_stacktrace_in_full[(]TestExceptionTools[.]java:([0-9])+[)]"));
        for (int i = 2; i < _stack.length; i++)
        {
            then(_stack[i].stripTrailing()).matches(STACK_TRACE_FRAME_PATTERN);
        }
    }

    @Test
    void should_return_limited_printed_stacktrace_as_requested()
    {
        String[] _stack;

        // Execute
        try
        {
            throw new IllegalStateException("whatever");
        }
        catch (final Exception _e)
        {
            _stack = ExceptionTools.getStackTrace(_e, 1).split("\n");
        }

        // Verify
        then(_stack).isNotNull().hasSize(3);
        then(_stack[0].stripTrailing()).isEqualTo("java.lang.IllegalStateException: whatever");
        then(_stack[1].stripTrailing()).matches(Pattern.compile(
                "\tat test[.]sporniket[.]libre[.]lang[.]TestExceptionTools[.]should_return_limited_printed_stacktrace_as_requested[(]TestExceptionTools[.]java:([0-9])+[)]"));
        then(_stack[2].stripTrailing()).isEqualTo("\t...");
    }

    @Test
    void should_return_printed_stacktrace_in_full_when_limit_is_zero()
    {
        String[] _expectedStack;
        String[] _actualStack;

        // Execute
        try
        {
            throw new IllegalStateException("whatever");
        }
        catch (final Exception _e)
        {
            _expectedStack = ExceptionTools.getStackTrace(_e).split("\n");
            _actualStack = ExceptionTools.getStackTrace(_e, 0).split("\n");
        }

        // Verify
        then(_actualStack).hasSize(_expectedStack.length);
        for (int i = 0; i < _actualStack.length; i++)
        {
            then(_actualStack[i]).isEqualTo(_expectedStack[i]);
        }

    }

    @Test
    void should_return_printed_stacktrace_in_full_when_limit_is_excessively_high()
    {
        String[] _expectedStack;
        String[] _actualStack;

        // Execute
        try
        {
            throw new IllegalStateException("whatever");
        }
        catch (final Exception _e)
        {
            _expectedStack = ExceptionTools.getStackTrace(_e).split("\n");
            _actualStack = ExceptionTools.getStackTrace(_e, 100000).split("\n");
        }

        // Verify
        then(_actualStack).hasSize(_expectedStack.length);
        for (int i = 0; i < _actualStack.length; i++)
        {
            then(_actualStack[i].stripTrailing()).isEqualTo(_expectedStack[i].stripTrailing());
        }

    }
}
