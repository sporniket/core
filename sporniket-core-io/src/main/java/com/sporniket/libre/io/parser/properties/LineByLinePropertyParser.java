/**
 * 
 */
package com.sporniket.libre.io.parser.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.sporniket.libre.lang.string.StringTools;
import com.sporniket.libre.lang.string.StringTools.SpaceRemovingMode;

/**
 * Line oriented property parser.
 * 
 * <p>
 * This parser MUST be fed line by line, and send event to listener when it has completed the parsing of a property.
 * </p>
 * 
 * <p>
 * &copy; Copyright 2002-2016 David Sporn
 * </p>
 * <hr>
 * 
 * <p>
 * This file is part of <i>The Sporniket Core Library &#8211; io</i>.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; io</i> is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; io</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for
 * more details.
 * 
 * <p>
 * You should have received a copy of the GNU Lesser General Public License along with <i>The Sporniket Core Library &#8211; io</i>.
 * If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
 * 
 * <hr>
 * 
 * @author David SPORN
 * @version 16.08.00
 * @since 15.09.00
 */
public class LineByLinePropertyParser
{
	/**
	 * Some special character patterns for the automaton.
	 * 
	 * <p>
	 * &copy; Copyright 2002-2016 David Sporn
	 * </p>
	 * <hr>
	 * 
	 * <p>
	 * This file is part of <i>The Sporniket Core Library &#8211; io</i>.
	 * 
	 * <p>
	 * <i>The Sporniket Core Library &#8211; io</i> is free software: you can redistribute it and/or modify it under the terms of
	 * the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at
	 * your option) any later version.
	 * 
	 * <p>
	 * <i>The Sporniket Core Library &#8211; io</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
	 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
	 * License for more details.
	 * 
	 * <p>
	 * You should have received a copy of the GNU Lesser General Public License along with <i>The Sporniket Core Library &#8211;
	 * io</i>. If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
	 * 
	 * <hr>
	 * 
	 * @author David SPORN
	 * @version 16.08.00
	 * @since 15.09.00
	 */
	private static final class CharacterPattern
	{
		public static final String END_TAG = "^[-._$:0-9A-Za-z]+$";

		public static final String NAME_BODY = "[-._0-9A-Za-z]";

		public static final String NAME_START = "[A-Za-z]";

		public static final String WHITESPACE = "[ \t]";
	}

	/**
	 * Final state of the automaton.
	 * 
	 * <p>
	 * Final states MUST set the proper FinalState in the parser field instance.
	 * </p>
	 * 
	 * <p>
	 * &copy; Copyright 2002-2016 David Sporn
	 * </p>
	 * <hr>
	 * 
	 * <p>
	 * This file is part of <i>The Sporniket Core Library &#8211; io</i>.
	 * 
	 * <p>
	 * <i>The Sporniket Core Library &#8211; io</i> is free software: you can redistribute it and/or modify it under the terms of
	 * the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at
	 * your option) any later version.
	 * 
	 * <p>
	 * <i>The Sporniket Core Library &#8211; io</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
	 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
	 * License for more details.
	 * 
	 * <p>
	 * You should have received a copy of the GNU Lesser General Public License along with <i>The Sporniket Core Library &#8211;
	 * io</i>. If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
	 * 
	 * <hr>
	 * 
	 * @author David SPORN
	 * @version 16.08.00
	 * @since 15.09.00
	 */
	private static enum FinalState
	{
		IS_COMMENT_LINE,
		IS_EMPTY_LINE,
		IS_MULTIPLE_LINE,
		IS_MULTIPLE_LINE_LEFT_TRIM,
		IS_SINGLE_LINE,
		IS_SINGLE_LINE_LEFT_TRIM;
	}

	/**
	 * Automaton model : transition to a next state, only if it matches a pattern.
	 * 
	 * <p>
	 * &copy; Copyright 2002-2016 David Sporn
	 * </p>
	 * <hr>
	 * 
	 * <p>
	 * This file is part of <i>The Sporniket Core Library &#8211; io</i>.
	 * 
	 * <p>
	 * <i>The Sporniket Core Library &#8211; io</i> is free software: you can redistribute it and/or modify it under the terms of
	 * the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at
	 * your option) any later version.
	 * 
	 * <p>
	 * <i>The Sporniket Core Library &#8211; io</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
	 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
	 * License for more details.
	 * 
	 * <p>
	 * You should have received a copy of the GNU Lesser General Public License along with <i>The Sporniket Core Library &#8211;
	 * io</i>. If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
	 * 
	 * <hr>
	 * 
	 * @author David SPORN
	 * @version 16.08.00
	 * @since 15.09.00
	 */
	private static final class FollowUp
	{
		/**
		 * States will be stored in an array, so that there is no chicken and egg problem.
		 */
		private final int myNextStateIndex;

		/**
		 * The pattern to match to follow this follow up.
		 */
		private final Pattern myPattern;

		public FollowUp(String pattern, int nextStateIndex)
		{
			myPattern = Pattern.compile(pattern);
			myNextStateIndex = nextStateIndex;
		}

		public int getNextStateIndex()
		{
			return myNextStateIndex;
		}

		public boolean isMatching(String value)
		{
			return getPattern().matcher(value).matches();
		}

		private Pattern getPattern()
		{
			return myPattern;
		}
	}

	/**
	 * Automaton state.
	 * 
	 * <p>
	 * &copy; Copyright 2002-2016 David Sporn
	 * </p>
	 * <hr>
	 * 
	 * <p>
	 * This file is part of <i>The Sporniket Core Library &#8211; io</i>.
	 * 
	 * <p>
	 * <i>The Sporniket Core Library &#8211; io</i> is free software: you can redistribute it and/or modify it under the terms of
	 * the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at
	 * your option) any later version.
	 * 
	 * <p>
	 * <i>The Sporniket Core Library &#8211; io</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
	 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
	 * License for more details.
	 * 
	 * <p>
	 * You should have received a copy of the GNU Lesser General Public License along with <i>The Sporniket Core Library &#8211;
	 * io</i>. If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
	 * 
	 * <hr>
	 * 
	 * @author David SPORN
	 * @version 16.08.00
	 * @since 15.09.00
	 */
	private static class State
	{
		private final boolean myFinal;

		private final FollowUp[] myFollowUps;

		private final LineByLinePropertyParser myParser;

		/**
		 * Constructor that infers the fact that the state is final.
		 * 
		 * @param parser
		 *            the parser that can be modified in {@link #execute()}.
		 * @param followUps
		 *            the list of follows up, if there is no follow up, it is a final state.
		 */
		public State(LineByLinePropertyParser parser, FollowUp[] followUps)
		{
			myParser = parser;
			myFollowUps = initFollowUps(followUps);
			myFinal = (myFollowUps.length == 0);
		}

		/**
		 * Constructor that does not infers the fact that the state is final.
		 * 
		 * @param parser
		 *            the parser that can be modified in {@link #execute()}.
		 * @param followUps
		 *            the list of follows up.
		 * @param isFinal
		 *            <code>true</code> if the state is final.
		 */
		public State(LineByLinePropertyParser parser, FollowUp[] followUps, boolean isFinal)
		{
			myFollowUps = initFollowUps(followUps);
			myParser = parser;
			myFinal = isFinal;
		}

		public int chooseNext(String charToParse) throws SyntaxErrorException
		{
			for (FollowUp _candidate : getFollowUps())
			{
				if (_candidate.isMatching(charToParse))
				{
					return _candidate.getNextStateIndex();
				}
			}
			throw new SyntaxErrorException("Error");
		}

		public void execute()
		{
			// do nothing by default.
		}

		public boolean isFinal()
		{
			return myFinal;
		}

		/**
		 * @return <code>true</code> if the state has follow ups.
		 */
		public boolean isFollowable()
		{
			return getFollowUps().length > 0;
		}

		protected LineByLinePropertyParser getParser()
		{
			return myParser;
		}

		private FollowUp[] getFollowUps()
		{
			return myFollowUps;
		}

		private FollowUp[] initFollowUps(FollowUp[] followUps)
		{
			FollowUp[] _followUp = new FollowUp[] {};
			if (null != followUps)
			{
				ArrayList<FollowUp> _checkedFollowUps = new ArrayList<LineByLinePropertyParser.FollowUp>(followUps.length);
				for (FollowUp _toCheck : followUps)
				{
					if (null != _toCheck) _checkedFollowUps.add(_toCheck);
				}
				_followUp = _checkedFollowUps.toArray(new FollowUp[_checkedFollowUps.size()]);
			}
			return _followUp;
		}
	}

	private static final int POSITION__NOT_SET = -1;

	/**
	 * The automaton ; at each iteration, the current state is executed, then if it is not a final state, the next state is choosen.
	 * 
	 * <p>
	 * The goal of this automaton is to find the limit of the property name and the "rest of the line", as well as to recognize the
	 * kind of line parsed.
	 * </p>
	 */
	private final State[] myAutomaton =
	{
			// 0
			new State(this, new FollowUp[]
			{
					new FollowUp(CharacterPattern.WHITESPACE, 0),
					new FollowUp("[#]", 1),
					new FollowUp(CharacterPattern.NAME_START, 2)
			}, true)
			{
				@Override
				public void execute()
				{
					getParser().setParserOutcome(FinalState.IS_EMPTY_LINE);
					getParser().setPropertyNameStart(getParser().getCurrentChar());
				}
			},// 1
			new State(this, null)
			{
				@Override
				public void execute()
				{
					getParser().setParserOutcome(FinalState.IS_COMMENT_LINE);
				}
			},// 2
			new State(this, new FollowUp[]
			{
					new FollowUp(CharacterPattern.NAME_BODY, 2),
					new FollowUp(CharacterPattern.WHITESPACE, 3),
					new FollowUp("[=]", 4),
					new FollowUp("[<]", 7)
			})
			{
				@Override
				public void execute()
				{
					getParser().setPropertyNameEnd(getParser().getCurrentChar());
				}
			},// 3
			new State(this, new FollowUp[]
			{
					new FollowUp(CharacterPattern.WHITESPACE, 3), new FollowUp("[=]", 4), new FollowUp("[<]", 7)
			}),// 4
			new State(this, new FollowUp[]
			{
					new FollowUp("[>]", 5), new FollowUp(".", 6)
			}, true)
			{
				@Override
				public void execute()
				{
					getParser().setRestOfLineStart(getParser().getCurrentChar());
					getParser().setParserOutcome(FinalState.IS_SINGLE_LINE_LEFT_TRIM);
				}
			},// 5
			new State(this, null)
			{
				@Override
				public void execute()
				{
					getParser().setRestOfLineStart(getParser().getCurrentChar());
					getParser().setParserOutcome(FinalState.IS_SINGLE_LINE);
				}
			},// 6
			new State(this, null),// 7
			new State(this, new FollowUp[]
			{
				new FollowUp("[<]", 8)
			}),// 8
			new State(this, new FollowUp[]
			{
					new FollowUp("[-]", 9), new FollowUp(".", 10)
			}),// 9
			new State(this, null)
			{
				@Override
				public void execute()
				{
					getParser().setRestOfLineStart(getParser().getCurrentChar());
					getParser().setParserOutcome(FinalState.IS_MULTIPLE_LINE);
				}
			},// 10
			new State(this, null)
			{
				@Override
				public void execute()
				{
					getParser().setRestOfLineStart(getParser().getCurrentChar());
					getParser().setParserOutcome(FinalState.IS_MULTIPLE_LINE_LEFT_TRIM);
				}
			}
	};

	/**
	 * Position, in the current line, of the char being processed.
	 */
	private int myCurrentChar;

	/**
	 * Pattern to check the characters of the endtag.
	 */
	private final Pattern myEndTagChecker = Pattern.compile(CharacterPattern.END_TAG);

	/**
	 * Listeners registry.
	 */
	private final List<PropertiesParsingListener> myListeners = new ArrayList<PropertiesParsingListener>(10);

	private String myMultipleLineEndTag;

	private String myMultipleLinePropertyName;

	private List<String> myMultipleLinePropertyValue;

	/**
	 * What to do of the current parsed line when the automaton has reached a final state.
	 */
	private FinalState myParserOutcome;

	/**
	 * Position, in the current line, of the end of the property name.
	 */
	private int myPropertyNameEnd;

	/**
	 * Position, in the current line, of the beginning of the property name.
	 */
	private int myPropertyNameStart;

	/**
	 * Position, in the current line, of the beginning of the rest of the line (after '=', '=>', '&lt;&lt;' or '&lt;&lt;-').
	 */
	private int myRestOfLineStart;

	/**
	 * @param listener
	 *            the listener to register.
	 */
	public void addListener(PropertiesParsingListener listener)
	{
		if (!getListeners().contains(listener))
		{
			getListeners().add(listener);
		}
	}

	/**
	 * Parse the given line and update the internal state of the parser.
	 * 
	 * @param line
	 *            the line to parse.
	 * @throws SyntaxErrorException
	 *             if there is a syntax problem in the line.
	 */
	public synchronized void parseLine(String line) throws SyntaxErrorException
	{
		if (null != getParserOutcome() && isParserOutcomeMultipleLine())
		{
			parseLine__multipleLineMode(line);
		}
		else
		{
			parseLine__singleLineMode(line);
		}
	}

	/**
	 * @param listener
	 *            the listener to unregister.
	 */
	public void removeListener(PropertiesParsingListener listener)
	{
		if (getListeners().contains(listener))
		{
			getListeners().remove(listener);
		}
	}

	/**
	 * Reset the state of the parser, so that it is possible to reuse the parser.
	 */
	public void resetState()
	{
		setParserOutcome(null);
	}

	/**
	 * Notify listeners that a multiple line property has been parsed.
	 * 
	 * @param name
	 *            property name.
	 * @param value
	 *            property value.
	 */
	private void fireMultipleLinePropertyParsedEvent(String name, String[] value)
	{
		MultipleLinePropertyParsedEvent _event = new MultipleLinePropertyParsedEvent(name, value);
		for (PropertiesParsingListener _listener : getListeners())
		{
			_listener.onMultipleLinePropertyParsed(_event);
		}
	}

	/**
	 * Notify listeners that a single line property has been parsed.
	 * 
	 * @param name
	 *            property name.
	 * @param value
	 *            property value.
	 */
	private void fireSingleLinePropertyParsedEvent(String name, String value)
	{
		SingleLinePropertyParsedEvent _event = new SingleLinePropertyParsedEvent(name, value);
		for (PropertiesParsingListener _listener : getListeners())
		{
			_listener.onSingleLinePropertyParsed(_event);
		}
	}

	private State[] getAutomaton()
	{
		return myAutomaton;
	}

	private int getCurrentChar()
	{
		return myCurrentChar;
	}

	private Pattern getEndTagChecker()
	{
		return myEndTagChecker;
	}

	private List<PropertiesParsingListener> getListeners()
	{
		return myListeners;
	}

	private String getMultipleLineEndTag()
	{
		return myMultipleLineEndTag;
	}

	private String getMultipleLinePropertyName()
	{
		return myMultipleLinePropertyName;
	}

	private List<String> getMultipleLinePropertyValue()
	{
		return myMultipleLinePropertyValue;
	}

	private FinalState getParserOutcome()
	{
		return myParserOutcome;
	}

	private int getPropertyNameEnd()
	{
		return myPropertyNameEnd;
	}

	private int getPropertyNameStart()
	{
		return myPropertyNameStart;
	}

	private int getRestOfLineStart()
	{
		return myRestOfLineStart;
	}

	/**
	 * @return true if the parser outcome is a multiple line property (with or without leading spaces).
	 */
	private boolean isParserOutcomeMultipleLine()
	{
		return FinalState.IS_MULTIPLE_LINE == getParserOutcome() || FinalState.IS_MULTIPLE_LINE_LEFT_TRIM == getParserOutcome();
	}

	/**
	 * @return true if the parser outcome is a single line property (with or without leading spaces).
	 */
	private boolean isParserOutcomeSingleLine()
	{
		return FinalState.IS_SINGLE_LINE == getParserOutcome() || FinalState.IS_SINGLE_LINE_LEFT_TRIM == getParserOutcome();
	}

	private void parseLine__extractSingleLineProperty(String line, boolean removeLeadingSpaces)
	{
		String _propertyName = line.substring(getPropertyNameStart(), getPropertyNameEnd());
		String _propertyValue = line.substring(getRestOfLineStart());
		if (removeLeadingSpaces)
		{
			_propertyValue = removeLeadingSpaces(_propertyValue);
		}
		fireSingleLinePropertyParsedEvent(_propertyName, _propertyValue);
	}

	/**
	 * Line parsing when inside a multiple line property definition.
	 * 
	 * @param line
	 */
	private void parseLine__multipleLineMode(String line)
	{
		boolean _leftTrim = (FinalState.IS_MULTIPLE_LINE_LEFT_TRIM == getParserOutcome());
		parseLine__multipleLineMode__appendLineUnlessEndTag(line, _leftTrim);
	}

	/**
	 * @param line
	 * @param removeLeadingSpaces
	 */
	private void parseLine__multipleLineMode__appendLineUnlessEndTag(String line, boolean removeLeadingSpaces)
	{
		String _line = line;
		if (removeLeadingSpaces)
		{
			_line = removeLeadingSpaces(_line);
		}
		if (!StringTools.isEmptyString(getMultipleLineEndTag()) && _line.trim().equals(getMultipleLineEndTag()))
		{
			// exit multiple line mode
			setParserOutcome(null);

			// notify listeners.
			final String[] _valueAsArray = getMultipleLinePropertyValue()
					.toArray(new String[getMultipleLinePropertyValue().size()]);
			fireMultipleLinePropertyParsedEvent(getMultipleLinePropertyName(), _valueAsArray);
		}
		else
		{
			getMultipleLinePropertyValue().add(_line);
		}
	}

	/**
	 * Default line parsing, if a heredoc syntax is detected, next lines will be parsed in multiple line mode.
	 * 
	 * @param line
	 *            the line to parse.
	 * @throws SyntaxErrorException
	 *             when the parsing fails.
	 */
	private void parseLine__singleLineMode(String line) throws SyntaxErrorException
	{
		// reset
		setPropertyNameStart(POSITION__NOT_SET);
		setPropertyNameEnd(POSITION__NOT_SET);
		setRestOfLineStart(POSITION__NOT_SET);

		int _nextStateIndex = 0;
		State _currentState = null;
		char[] _nextChar = new char[1];

		for (int _i = 0; _i <= line.length(); _i++)
		{
			setCurrentChar(_i);
			_currentState = getAutomaton()[_nextStateIndex];
			_currentState.execute();
			if (line.length() == _i || !_currentState.isFollowable()) break;
			_nextChar[0] = line.charAt(_i);
			String _charToParse = new String(_nextChar);
			try
			{
				_nextStateIndex = _currentState.chooseNext(_charToParse);
			}
			catch (SyntaxErrorException _exception)
			{
				throw new SyntaxErrorException("Error at pos " + _i + " in line :'" + line + "'");
			}
		}
		// sanity check : final state and not error
		if (null == _currentState || !_currentState.isFinal())
		{
			throw new SyntaxErrorException("Undefined error at pos in line :'" + line + "'");
		}

		// do the thing.
		if (null != getParserOutcome())
		{
			if (isParserOutcomeSingleLine())
			{
				boolean _leftTrim = (FinalState.IS_SINGLE_LINE_LEFT_TRIM == getParserOutcome());
				parseLine__extractSingleLineProperty(line, _leftTrim);
			}
			else if (isParserOutcomeMultipleLine())
			{
				String _propertyName = line.substring(getPropertyNameStart(), getPropertyNameEnd());
				setMultipleLinePropertyName(_propertyName);
				setMultipleLinePropertyValue(new ArrayList<String>());
				String _endTag = line.substring(getRestOfLineStart()).trim();
				if (!getEndTagChecker().matcher(_endTag).matches())
				{
					throw new SyntaxErrorException("End tag MUST match the rule : '" + CharacterPattern.END_TAG + "', got '"
							+ _endTag + "'");
				}
				setMultipleLineEndTag(_endTag);
			}
		}
	}

	/**
	 * Macro to remove whitespaces.
	 * 
	 * @param value
	 *            the value to trim.
	 * @return the trimed value.
	 */
	private String removeLeadingSpaces(String value)
	{
		return StringTools.removeWhiteSpaces(value, SpaceRemovingMode.LEADING_SPACES);
	}

	private void setCurrentChar(int currentChar)
	{
		myCurrentChar = currentChar;
	}

	private void setMultipleLineEndTag(String multipleLineEndTag)
	{
		myMultipleLineEndTag = multipleLineEndTag;
	}

	private void setMultipleLinePropertyName(String multipleLinePropertyName)
	{
		myMultipleLinePropertyName = multipleLinePropertyName;
	}

	private void setMultipleLinePropertyValue(List<String> multipleLinePropertyValue)
	{
		myMultipleLinePropertyValue = multipleLinePropertyValue;
	}

	private void setParserOutcome(FinalState parserOutcome)
	{
		myParserOutcome = parserOutcome;
	}

	private void setPropertyNameEnd(int propertyNameEnd)
	{
		myPropertyNameEnd = propertyNameEnd;
	}

	private void setPropertyNameStart(int propertyNameStart)
	{
		myPropertyNameStart = propertyNameStart;
	}

	private void setRestOfLineStart(int restOfLineStart)
	{
		myRestOfLineStart = restOfLineStart;
	}
}
