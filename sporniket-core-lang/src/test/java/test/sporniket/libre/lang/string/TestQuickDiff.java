/**
 * 
 */
package test.sporniket.libre.lang.string;

import junit.framework.TestCase;

import com.sporniket.libre.lang.string.QuickDiff;

/**
 * Test suite for {@link QuickDiff}.
 * <p>
 * &copy; Copyright 2002-2012 David Sporn
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
 * @version 15.02.00
 * @since 12.09.01
 */
public class TestQuickDiff extends TestCase
{
	public void testNoDiff()
	{
		String[] _left =
		{
				"line 1", "line 2"
		};
		String[] _right =
		{
				"line 1", "line 2"
		};
		String[] _result = QuickDiff.reportDiff(_left, _right, false, false);
		if (0 != _result.length)
		{
			String _message = buildErrorMessage("Found differences that should not exist :\n", _result);
			fail(_message);
		}
	}

	public void testFindDiff()
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
		if (0 == _result.length)
		{
			fail("Did not found difference caused by different line");
		}
	}

	public void testFindSupplementalLinesOnLeft()
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
		if (0 == _result.length)
		{
			fail("Did not found difference caused by supplemental line");
		}
	}

	public void testFindSupplementalLinesOnRight()
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
		if (0 == _result.length)
		{
			fail("Did not found difference caused by supplemental line");
		}
	}

	public void testAppendedLinesOnLeft()
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
		if (0 == _result.length)
		{
			fail("Did not found difference caused by appended line");
		}
	}

	public void testAppendedLinesOnRight()
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
		if (0 == _result.length)
		{
			fail("Did not found difference caused by appended line");
		}
	}

	public void testIgnoreEmptyLineOnLeft()
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
		if (0 == _result.length)
		{
			fail("Did not found difference caused by empty line");
		}

		String[] _resultIgnore = QuickDiff.reportDiff(_left, _right, true, false);
		if (0 != _resultIgnore.length)
		{
			String _message = buildErrorMessage("Found differences that should not exist :\n", _resultIgnore);
			fail(_message);
		}
	}

	public void testIgnoreEmptyLineOnRight()
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
		if (0 == _result.length)
		{
			fail("Did not found difference caused by empty line");
		}

		String[] _resultIgnore = QuickDiff.reportDiff(_left, _right, true, false);
		if (0 != _resultIgnore.length)
		{
			String _message = buildErrorMessage("Found differences that should not exist :\n", _resultIgnore);
			fail(_message);
		}
	}

	public void testIgnoreEmptyTrailingWhiteSpace()
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
		if (0 == _result.length)
		{
			fail("Did not found difference caused by empty line");
		}

		String[] _resultIgnore = QuickDiff.reportDiff(_left, _right, false, true);
		if (0 != _resultIgnore.length)
		{
			String _message = buildErrorMessage("Found differences that should not exist :\n", _resultIgnore);
			fail(_message);
		}
	}

	/**
	 * @param errorMessage
	 * @param diffReport
	 * @return
	 */
	private String buildErrorMessage(String errorMessage, String[] diffReport)
	{
		StringBuilder _messageBuffer = new StringBuilder(errorMessage);
		for (String _line : diffReport)
		{
			_messageBuffer.append(_line).append("\n");
		}
		String _message = _messageBuffer.toString();
		return _message;
	}
}
