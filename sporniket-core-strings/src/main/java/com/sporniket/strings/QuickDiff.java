/**
 * 
 */
package com.sporniket.strings;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * Rough implementation of a diff tool, for basic needs (the report does not follows a standard diff format).
 * 
 * The typical use will be through {@link #reportDiff(String[], String[], boolean, boolean)}.
 * 
 * <p>
 * &copy; Copyright 2002-2019 David Sporn
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
 * @since 12.09.01
 */
public class QuickDiff
{
	private static final String REPORT_LINE__EMPTY = "";

	/**
	 * Template for pointing different text in the text on left.
	 */
	private static final String TEMPLATE__TEXT_ON_LEFT = "<<{0,number,00000}=={1}";

	/**
	 * Template for pointing different text in the text on right.
	 */
	private static final String TEMPLATE__TEXT_ON_RIGHT = "--{0,number,00000}>>{1}";

	/**
	 * Flag to set to <code>true</code> if empty lines are meaningless.
	 */
	private boolean myIgnoreEmptyLines = false;

	/**
	 * Flag to set to <code>true</code> if lines must be compared after a {@link String#trim()}.
	 */
	private boolean myIgnoreTrailingWhiteSpaces = false;

	private MessageFormat myTemplateOnLeft = new MessageFormat(TEMPLATE__TEXT_ON_LEFT);

	private MessageFormat myTemplateOnRight = new MessageFormat(TEMPLATE__TEXT_ON_RIGHT);

	/**
	 * @return the ignoreEmptyLines
	 */
	public boolean isIgnoreEmptyLines()
	{
		return myIgnoreEmptyLines;
	}

	/**
	 * @param ignoreEmptyLines
	 *            the ignoreEmptyLines to set
	 */
	public void setIgnoreEmptyLines(boolean ignoreEmptyLines)
	{
		myIgnoreEmptyLines = ignoreEmptyLines;
	}

	/**
	 * @return the ignoreTrailingWhiteSpaces
	 */
	public boolean isIgnoreTrailingWhiteSpaces()
	{
		return myIgnoreTrailingWhiteSpaces;
	}

	/**
	 * @param ignoreTrailingWhiteSpaces
	 *            the ignoreTrailingWhiteSpaces to set
	 */
	public void setIgnoreTrailingWhiteSpaces(boolean ignoreTrailingWhiteSpaces)
	{
		myIgnoreTrailingWhiteSpaces = ignoreTrailingWhiteSpaces;
	}

	/**
	 * Compute a diff report.
	 * 
	 * @param textOnLeft
	 *            The text on left as an array of lines.
	 * @param textOnRight
	 *            The text on right as an array of lines.
	 * @return The diff report as an array of lines.
	 */
	// FIXME refactor this mammoth using submethods...
	public String[] reportDiff(String[] textOnLeft, String[] textOnRight)
	{
		List<String> _report = new LinkedList<String>();
		int _currentLeft = 0;
		int _currentRight = 0;

		while (_currentLeft < textOnLeft.length || _currentRight < textOnRight.length)
		{
			if (_currentLeft < textOnLeft.length && _currentRight < textOnRight.length)
			{
				// normal case
				String _left = getTextLine(textOnLeft, _currentLeft);
				String _right = getTextLine(textOnRight, _currentRight);
				if (isIgnoreEmptyLines())
				{
					boolean _hasSkippedLine = false;
					if (0 == _left.length())
					{
						++_currentLeft;
						_hasSkippedLine = true;
					}
					if (0 == _right.length())
					{
						++_currentRight;
						_hasSkippedLine = true;
					}
					if (_hasSkippedLine) continue;
				}
				if (_left.equals(_right))
				{
					++_currentLeft;
					++_currentRight;
					continue;
				}
				else
				{
					// find the range of the difference
					int _foundLeft = findNextMatchingLine(textOnLeft, _currentLeft, _right);

					int _foundRight = findNextMatchingLine(textOnRight, _currentRight, _left);

					if (-1 == _foundLeft && -1 == _foundRight)
					{
						// maybe only this line is different
						outputReportLine(_report, myTemplateOnLeft, textOnLeft, _currentLeft);
						_currentLeft++;

						outputReportLine(_report, myTemplateOnRight, textOnRight, _currentRight);
						_currentRight++;
					}
					else if (-1 == _foundLeft)
					{
						// added lines on right
						outputReportLine(_report, myTemplateOnLeft, textOnLeft, _currentLeft);
						_currentLeft++;

						++_foundRight;
						outputRangeOfTextInReport(_report, myTemplateOnRight, textOnRight, _currentRight, _foundRight);
						_currentRight = _foundRight;
					}
					else if (-1 == _foundRight)
					{
						// added lines on left
						++_foundLeft;
						outputRangeOfTextInReport(_report, myTemplateOnLeft, textOnLeft, _currentLeft, _foundLeft);
						_currentLeft = _foundLeft;

						outputReportLine(_report, myTemplateOnRight, textOnRight, _currentRight);
						_currentRight++;
					}
					else
					{
						// 2 possibilities, find the minimum range size
						++_foundLeft;
						int _leftSize = _foundLeft - _currentLeft;

						++_foundRight;
						int _rightSize = _foundRight - _currentRight;

						if (_leftSize < _rightSize)
						{
							// choose left edits
							outputRangeOfTextInReport(_report, myTemplateOnLeft, textOnLeft, _currentLeft, _foundLeft);
							_currentLeft = _foundLeft + 1;

							outputReportLine(_report, myTemplateOnRight, textOnRight, _currentRight);
							_currentRight++;
						}
						else
						{
							// choose right edits
							outputReportLine(_report, myTemplateOnLeft, textOnLeft, _currentLeft);
							_currentLeft++;

							outputRangeOfTextInReport(_report, myTemplateOnRight, textOnRight, _currentRight, _foundRight);
							_currentRight = _foundRight + 1;
						}
					}
					_report.add(REPORT_LINE__EMPTY);
				}
			}
			else if (_currentLeft < textOnLeft.length)
			{
				// supplemental lines on left
				String _line = getTextLine(textOnLeft, _currentLeft);
				if (!(isIgnoreEmptyLines() && 0 == _line.length()))
				{
					outputReportLine(_report, myTemplateOnLeft, textOnLeft, _currentLeft);
				}
				++_currentLeft;
			}
			else if (_currentRight < textOnRight.length)
			{
				// supplemental lines on right
				String _line = getTextLine(textOnRight, _currentRight);
				if (!(isIgnoreEmptyLines() && 0 == _line.length()))
				{
					outputReportLine(_report, myTemplateOnRight, textOnRight, _currentRight);
				}
				_currentRight++;
			}
		}

		return _report.toArray(new String[_report.size()]);
	}

	/**
	 * Add a report line the designated line.
	 * 
	 * @param report
	 *            The report buffer.
	 * @param template
	 *            The template to use (to distinguish between text on left and text on right).
	 * @param textLines
	 *            The source text as an array of lines.
	 * @param currentLine
	 *            The line to output.
	 */
	private void outputReportLine(List<String> report, MessageFormat template, String[] textLines, int currentLine)
	{
		Object[] _args =
		{
				currentLine, textLines[currentLine]
		};
		report.add(template.format(_args));
	}

	/**
	 * Add a report line for each designated line.
	 * 
	 * @param textLines
	 *            The source text as an array of lines.
	 * @param report
	 *            The report buffer.
	 * @param template
	 *            The template to use (to distinguish between text on left and text on right).
	 * @param startLine
	 *            First line to output (inclusive)
	 * @param endLine
	 *            Last line to output (exclusive)
	 */
	private void outputRangeOfTextInReport(List<String> report, MessageFormat template, String[] textLines, int startLine,
			int endLine)
	{
		for (int _temp = startLine; _temp < endLine; _temp++)
		{
			outputReportLine(report, template, textLines, _temp);
		}
	}

	/**
	 * Find the matching line, if any.
	 * 
	 * @param textLines
	 *            The source text as an array of lines.
	 * @param startingLine
	 *            The line number from which to begin looking for the <code>textToMatch</code>.
	 * @param textToMatch
	 *            The text to look for.
	 * @return the line number that is the <code>textToMatch</code>.
	 */
	private int findNextMatchingLine(String[] textLines, int startingLine, String textToMatch)
	{
		int _foundLeft = -1;
		{
			int _tempLine = startingLine + 1;
			while (_tempLine < textLines.length)
			{
				String _tempText = getTextLine(textLines, _tempLine);
				if (_tempText.equals(textToMatch))
				{
					_foundLeft = _tempLine;
					break;
				}
				++_tempLine;
			}
		}
		return _foundLeft;
	}

	/**
	 * Return the specified line from the text.
	 * 
	 * @param textLines
	 *            The source text as an array of lines.
	 * @param line
	 *            The line to return.
	 * @return the line as is, or trimed, according to {@link #isIgnoreTrailingWhiteSpaces()}.
	 */
	private String getTextLine(String[] textLines, int line)
	{
		return (isIgnoreTrailingWhiteSpaces()) ? textLines[line].trim() : textLines[line];
	}

	/**
	 * Macro to compute a diff report.
	 * 
	 * @param textOnLeft
	 *            The text on left as an array of lines.
	 * @param textOnRight
	 *            The text on right as an array of lines.
	 * @param ignoreEmptyLines
	 *            Set to <code>true</code> if you want to skip empty lines.
	 * @param ignoreTrailingWhiteSpaces
	 *            Set to <code>true</code> if lines are to be compared without leading and trailing whitespaces (will use
	 *            {@link String#trim()}).
	 * @return The diff report as an array of lines.
	 */
	public static String[] reportDiff(String[] textOnLeft, String[] textOnRight, boolean ignoreEmptyLines,
			boolean ignoreTrailingWhiteSpaces)
	{
		QuickDiff _instance = new QuickDiff();
		_instance.setIgnoreEmptyLines(ignoreEmptyLines);
		_instance.setIgnoreTrailingWhiteSpaces(ignoreTrailingWhiteSpaces);

		return _instance.reportDiff(textOnLeft, textOnRight);
	}
}
