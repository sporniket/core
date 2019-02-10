/**
 * 
 */
package test.sporniket.libre.lang.string;

import static com.sporniket.libre.lang.string.QuickDiff.reportDiff;
import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;

import com.sporniket.libre.lang.string.QuickDiff;

/**
 * Test suite for {@link QuickDiff}.
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
 * @since 12.09.01
 */
public class QuickDiffTest
{
	@Test
	public void shouldFindNoDiffComparingIdenticalInput()
	{
		String[] _left =
		{
				"line 1", "line 2"
		};
		String[] _right =
		{
				"line 1", "line 2"
		};
		String[] _result = reportDiff(_left, _right, false, false);
		then(_result).isEmpty();
	}

	@Test
	public void shouldFindDiffWhenOneLineIsDifferent()
	{
		String[] _left =
		{
				"line 1", "line 2a", "line 3"
		};
		String[] _right =
		{
				"line 1", "line 2b", "line 3"
		};
		String[] _result = QuickDiff.reportDiff(_left, _right, false, false);
		then(_result).isNotEmpty();
	}

	@Test
	public void shouldSpotSupplementalLinesOnLeft()
	{
		String[] _left =
		{
				"line 1", "line 2", "line 3"
		};
		String[] _right =
		{
				"line 1", "line 3"
		};
		String[] _result = QuickDiff.reportDiff(_left, _right, false, false);
		then(_result).isNotEmpty();
	}

	@Test
	public void shouldSpotSupplementalLinesOnRight()
	{
		String[] _left =
		{
				"line 1", "line 3"
		};
		String[] _right =
		{
				"line 1", "line 2", "line 3"
		};
		String[] _result = QuickDiff.reportDiff(_left, _right, false, false);
		then(_result).isNotEmpty();
	}

	@Test
	public void shouldSpotAppendedLinesOnLeft()
	{
		String[] _left =
		{
				"line 1", "line 2", "line 3"
		};
		String[] _right =
		{
				"line 1", "line 2"
		};
		String[] _result = QuickDiff.reportDiff(_left, _right, false, false);
		then(_result).isNotEmpty();
	}

	@Test
	public void shouldSpotAppendedLinesOnRight()
	{
		String[] _left =
		{
				"line 1", "line 2"
		};
		String[] _right =
		{
				"line 1", "line 2", "line 3"
		};
		String[] _result = QuickDiff.reportDiff(_left, _right, false, false);
		then(_result).isNotEmpty();
	}

	@Test
	public void shouldFollowIgnoreEmptyLinePolicyOnLeft()
	{
		String[] _left =
		{
				"line 1", "", "line 2"
		};
		String[] _right =
		{
				"line 1", "line 2"
		};
		String[] _result = QuickDiff.reportDiff(_left, _right, false, false);
		then(_result).isNotEmpty();

		String[] _resultIgnore = QuickDiff.reportDiff(_left, _right, true, false);
		then(_resultIgnore).isEmpty();
	}

	@Test
	public void shouldFollowIgnoreEmptyLinePolicyOnRight()
	{
		String[] _left =
		{
				"line 1", "line 2"
		};
		String[] _right =
		{
				"line 1", "", "line 2"
		};
		String[] _result = QuickDiff.reportDiff(_left, _right, false, false);
		then(_result).isNotEmpty();

		String[] _resultIgnore = QuickDiff.reportDiff(_left, _right, true, false);
		then(_resultIgnore).isEmpty();
	}

	@Test
	public void shouldFollowIgnoreEmptyTrailingWhiteSpacePolicy()
	{
		String[] _left =
		{
				"line 1 ", " line 2", "line 3\t", "\tline 4", "line 5 ", " line 6", "line 7\t", "\tline 8"
		};
		String[] _right =
		{
				"line 1", " line 2", " line 3", "line 4\t", "\tline 5", "\tline 6 ", " line 7\t", "\tline 8\t"
		};
		String[] _result = QuickDiff.reportDiff(_left, _right, false, false);
		then(_result).isNotEmpty();

		String[] _resultIgnore = QuickDiff.reportDiff(_left, _right, false, true);
		then(_resultIgnore).isEmpty();
	}
}
