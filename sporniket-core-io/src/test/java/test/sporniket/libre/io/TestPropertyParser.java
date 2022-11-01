/**
 * 
 */
package test.sporniket.libre.io;

import junit.framework.TestCase;

import com.sporniket.libre.io.parser.properties.LineByLinePropertyParser;
import com.sporniket.libre.io.parser.properties.MultipleLinePropertyParsedEvent;
import com.sporniket.libre.io.parser.properties.PropertiesParsingListener;
import com.sporniket.libre.io.parser.properties.SingleLinePropertyParsedEvent;
import com.sporniket.libre.io.parser.properties.SyntaxErrorException;

/**
 * Test case for {@link com.sporniket.libre.io.parser.properties.LineByLinePropertyParser}.
 * <p>
 * &copy; Copyright 2002-2022 David Sporn
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
 * @version 22.11.00
 * @since 15.09.00
 */
public class TestPropertyParser extends TestCase
{
	private static final class RememberLastMultipleLineProperty implements PropertiesParsingListener
	{
		public String myParsedName = null;

		public String[] myParsedValue = null;

		@Override
		public void onMultipleLinePropertyParsed(MultipleLinePropertyParsedEvent event)
		{
			// TODO Auto-generated method stub
			myParsedName = event.getName();
			myParsedValue = event.getValue();

		}

		@Override
		public void onSingleLinePropertyParsed(SingleLinePropertyParsedEvent event)
		{
			// TODO Auto-generated method stub
		}
	}

	private static final class RememberLastSingleLineProperty implements PropertiesParsingListener
	{
		public String myParsedName = null;

		public String myParsedValue = null;

		@Override
		public void onMultipleLinePropertyParsed(MultipleLinePropertyParsedEvent event)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void onSingleLinePropertyParsed(SingleLinePropertyParsedEvent event)
		{
			// TODO Auto-generated method stub
			myParsedName = event.getName();
			myParsedValue = event.getValue();
		}
	}

	private static final String[] TEST_DESCRIPTION__COMMENT__NO_SPACES =
	{
			"#This is a comment", null, null
	};

	private static final String[] TEST_DESCRIPTION__COMMENT__SPACES_BEFORE__1 =
	{
			" #This is a comment", null, null
	};

	private static final String[] TEST_DESCRIPTION__COMMENT__SPACES_BEFORE__2 =
	{
			"  #This is a comment", null, null
	};

	private static final String[] TEST_DESCRIPTION__EMPTY_LINE =
	{
			"    ", null, null
	};

	private static final String[] TEST_DESCRIPTION__MULTIPLE_LINE_PROPERIES__PROPERTY_VALUE__EXPECTED__LEFT_TRIM =
	{
			"line 1  ", "line 2."
	};

	private static final String[] TEST_DESCRIPTION__MULTIPLE_LINE_PROPERIES__PROPERTY_VALUE__SOURCE =
	{
			"\t line 1  ", "  line 2."
	};

	private static final String[] TEST_DESCRIPTION__MULTIPLE_LINE_PROPERTIES__LEFT_TRIM =
	{
			"name <<- endName", "endName", "name"
	};

	private static final String[] TEST_DESCRIPTION__MULTIPLE_LINE_PROPERTIES__NO_LEFT_TRIM =
	{
			"name << endName", "endName", "name"
	};

	private static final String[] TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__EMPTY_VALUE =
	{
			"name=>", "name", ""
	};

	private static final String[] TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__EMPTY_VALUE =
	{
			"name=", "name", ""
	};

	private static final String[] TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__MIXED_WHITESPACES =
	{
			"name\t = \t value", "name", "value"
	};

	private static final String[] TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__NO_SPACES =
	{
			"name=value", "name", "value"
	};

	private static final String[] TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__SPACES_AFTER__1 =
	{
			"name = value", "name", "value"
	};

	private static final String[] TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__SPACES_AFTER__2 =
	{
			"name =  value", "name", "value"
	};

	private static final String[] TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__SPACES_BEFORE__1 =
	{
			"name =value", "name", "value"
	};

	private static final String[] TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__SPACES_BEFORE__2 =
	{
			"name  =value", "name", "value"
	};

	private static final String[] TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__NO_SPACES =
	{
			"name=>value", "name", "value"
	};

	private static final String[] TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__SPACES_AFTER__1 =
	{
			"name => value", "name", " value"
	};

	private static final String[] TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__SPACES_AFTER__2 =
	{
			"name =>  value", "name", "  value"
	};

	private static final String[] TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__SPACES_BEFORE__1 =
	{
			"name =>value", "name", "value"
	};

	private static final String[] TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__SPACES_BEFORE__2 =
	{
			"name  =>value", "name", "value"
	};

	private static final String[] TEST_DESCRIPTION__SYNTAX_ERROR =
	{
			"This is madness", null, null
	};

	private static final String[] TEST_DESCRIPTION__SYNTAX_ERROR__MULTIPLE_LINE_PROPERTY_START__BAD_CHARACTER_AT_ENDTAG_BEGINNING =
	{
			"This << #is madness", null, null
	};

	private static final String[] TEST_DESCRIPTION__SYNTAX_ERROR__MULTIPLE_LINE_PROPERTY_START__BAD_CHARACTER_INSIDE_ENDTAG =
	{
			"This << is madness", null, null
	};

	private static final String[] TEST_DESCRIPTION__SYNTAX_ERROR__UNFINISHED_MULTIPLE_LINE_PROPERTY_START__INCOMPLETE_OPERATOR =
	{
			"This is < madness", null, null
	};

	private static final String[] TEST_DESCRIPTION__SYNTAX_ERROR__UNFINISHED_MULTIPLE_LINE_PROPERTY_START__NO_ENDTAG =
	{
			"This is madness <<", null, null
	};

	private static final String[] TEST_DESCRIPTION__SYNTAX_ERROR__UNFINISHED_MULTIPLE_LINE_PROPERTY_START__NO_ENDTAG__LEFT_TRIM =
	{
			"This is madness <<-", null, null
	};

	public final void testComment__noSpaces()
	{
		doTestSingleLine(TEST_DESCRIPTION__COMMENT__NO_SPACES[0], TEST_DESCRIPTION__COMMENT__NO_SPACES[1],
				TEST_DESCRIPTION__COMMENT__NO_SPACES[2]);
	}

	public final void testComment__spaces_before__1()
	{
		doTestSingleLine(TEST_DESCRIPTION__COMMENT__SPACES_BEFORE__1[0], TEST_DESCRIPTION__COMMENT__SPACES_BEFORE__1[1],
				TEST_DESCRIPTION__COMMENT__SPACES_BEFORE__1[2]);
	}

	public final void testComment__spaces_before__2()
	{
		doTestSingleLine(TEST_DESCRIPTION__COMMENT__SPACES_BEFORE__2[0], TEST_DESCRIPTION__COMMENT__SPACES_BEFORE__2[1],
				TEST_DESCRIPTION__COMMENT__SPACES_BEFORE__2[2]);
	}

	public final void testEmptyLine()
	{
		doTestSingleLine(TEST_DESCRIPTION__EMPTY_LINE[0], TEST_DESCRIPTION__EMPTY_LINE[1], TEST_DESCRIPTION__EMPTY_LINE[2]);
	}

	public final void testErrorManagement()
	{
		final LineByLinePropertyParser _parser = new LineByLinePropertyParser();

		final String[] _correctLines =
		{
				TEST_DESCRIPTION__EMPTY_LINE[0],
				TEST_DESCRIPTION__COMMENT__NO_SPACES[0],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__EMPTY_VALUE[0],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__EMPTY_VALUE[0]
		};
		for (String _correctLine : _correctLines)
		{
			doTestCorrectLine(_parser, _correctLine);
		}

		final String[] _incorrectLines =
		{
				TEST_DESCRIPTION__SYNTAX_ERROR[0],
				TEST_DESCRIPTION__SYNTAX_ERROR__UNFINISHED_MULTIPLE_LINE_PROPERTY_START__INCOMPLETE_OPERATOR[0],
				TEST_DESCRIPTION__SYNTAX_ERROR__UNFINISHED_MULTIPLE_LINE_PROPERTY_START__NO_ENDTAG[0],
				TEST_DESCRIPTION__SYNTAX_ERROR__UNFINISHED_MULTIPLE_LINE_PROPERTY_START__NO_ENDTAG__LEFT_TRIM[0],
				TEST_DESCRIPTION__SYNTAX_ERROR__MULTIPLE_LINE_PROPERTY_START__BAD_CHARACTER_AT_ENDTAG_BEGINNING[0],
				TEST_DESCRIPTION__SYNTAX_ERROR__MULTIPLE_LINE_PROPERTY_START__BAD_CHARACTER_INSIDE_ENDTAG[0]
		};
		for (String _lineToTest : _incorrectLines)
		{
			doTestIncorrectLine(_parser, _lineToTest);
		}

	}

	public final void testMultipleLineProperties__leftTrim()
	{
		doTestMultipleLine(TEST_DESCRIPTION__MULTIPLE_LINE_PROPERTIES__LEFT_TRIM[0],
				TEST_DESCRIPTION__MULTIPLE_LINE_PROPERIES__PROPERTY_VALUE__SOURCE,
				TEST_DESCRIPTION__MULTIPLE_LINE_PROPERTIES__LEFT_TRIM[1], TEST_DESCRIPTION__MULTIPLE_LINE_PROPERTIES__LEFT_TRIM[2],
				TEST_DESCRIPTION__MULTIPLE_LINE_PROPERIES__PROPERTY_VALUE__EXPECTED__LEFT_TRIM);
	}

	public final void testMultipleLineProperties__noLeftTrim()
	{
		doTestMultipleLine(TEST_DESCRIPTION__MULTIPLE_LINE_PROPERTIES__NO_LEFT_TRIM[0],
				TEST_DESCRIPTION__MULTIPLE_LINE_PROPERIES__PROPERTY_VALUE__SOURCE,
				TEST_DESCRIPTION__MULTIPLE_LINE_PROPERTIES__NO_LEFT_TRIM[1],
				TEST_DESCRIPTION__MULTIPLE_LINE_PROPERTIES__NO_LEFT_TRIM[2],
				TEST_DESCRIPTION__MULTIPLE_LINE_PROPERIES__PROPERTY_VALUE__SOURCE);
	}

	public final void testSingleLineProperties__emptyValue()
	{
		doTestSingleLine(TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__EMPTY_VALUE[0],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__EMPTY_VALUE[1], TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__EMPTY_VALUE[2]);
	}

	public final void testSingleLineProperties__leftTrim__emptyValue()
	{
		doTestSingleLine(TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__EMPTY_VALUE[0],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__EMPTY_VALUE[1],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__EMPTY_VALUE[2]);
	}

	public final void testSingleLineProperties__leftTrim__mixedWhitespaces()
	{
		doTestSingleLine(TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__MIXED_WHITESPACES[0],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__MIXED_WHITESPACES[1],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__MIXED_WHITESPACES[2]);
	}

	public final void testSingleLineProperties__leftTrim__noSpaces()
	{
		doTestSingleLine(TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__NO_SPACES[0],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__NO_SPACES[1],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__NO_SPACES[2]);
	}

	public final void testSingleLineProperties__leftTrim__spacesAfter__1()
	{
		doTestSingleLine(TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__SPACES_AFTER__1[0],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__SPACES_AFTER__1[1],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__SPACES_AFTER__1[2]);
	}

	public final void testSingleLineProperties__leftTrim__spacesAfter__2()
	{
		doTestSingleLine(TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__SPACES_AFTER__2[0],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__SPACES_AFTER__2[1],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__SPACES_AFTER__2[2]);
	}

	public final void testSingleLineProperties__leftTrim__spacesBefore__1()
	{
		doTestSingleLine(TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__SPACES_BEFORE__1[0],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__SPACES_BEFORE__1[1],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__SPACES_BEFORE__1[2]);
	}

	public final void testSingleLineProperties__leftTrim__spacesBefore__2()
	{
		doTestSingleLine(TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__SPACES_BEFORE__2[0],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__SPACES_BEFORE__2[1],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__LEFT_TRIM__SPACES_BEFORE__2[2]);
	}

	public final void testSingleLineProperties__noSpaces()
	{
		doTestSingleLine(TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__NO_SPACES[0],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__NO_SPACES[1], TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__NO_SPACES[2]);
	}

	public final void testSingleLineProperties__spacesAfter__1()
	{
		doTestSingleLine(TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__SPACES_AFTER__1[0],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__SPACES_AFTER__1[1],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__SPACES_AFTER__1[2]);
	}

	public final void testSingleLineProperties__spacesAfter__2()
	{
		doTestSingleLine(TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__SPACES_AFTER__2[0],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__SPACES_AFTER__2[1],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__SPACES_AFTER__2[2]);
	}

	public final void testSingleLineProperties__spacesBefore__1()
	{
		doTestSingleLine(TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__SPACES_BEFORE__1[0],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__SPACES_BEFORE__1[1],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__SPACES_BEFORE__1[2]);
	}

	public final void testSingleLineProperties__spacesBefore__2()
	{
		doTestSingleLine(TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__SPACES_BEFORE__2[0],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__SPACES_BEFORE__2[1],
				TEST_DESCRIPTION__SINGLE_LINE_PROPERTIES__SPACES_BEFORE__2[2]);
	}

	public final void testSyntaxError()
	{
		doTestSingleLine(TEST_DESCRIPTION__SYNTAX_ERROR[0], TEST_DESCRIPTION__SYNTAX_ERROR[1], TEST_DESCRIPTION__SYNTAX_ERROR[2]);
	}

	private final void doTestCorrectLine(final LineByLinePropertyParser parser, final String lineToTest)
	{
		try
		{
			parser.resetState();
			parser.parseLine(lineToTest);
		}
		catch (SyntaxErrorException _exception)
		{
			fail("Should not throw Exception on correct line '" + lineToTest + "'");
		}
	}

	private final void doTestIncorrectLine(final LineByLinePropertyParser parser, final String lineToTest)
	{
		try
		{
			parser.resetState();
			parser.parseLine(lineToTest);
			fail("Must throw Exception on incorrect line '" + lineToTest + "'");
		}
		catch (SyntaxErrorException _exception)
		{
		}
	}

	private final void doTestMultipleLine(String firstLineToParse, String[] linesToParse, String lastLineToParse,
			String expectedName, String[] expectedValue)
	{

		final LineByLinePropertyParser _parser = new LineByLinePropertyParser();
		final RememberLastMultipleLineProperty _listener = new RememberLastMultipleLineProperty();
		_parser.addListener(_listener);
		try
		{
			_parser.parseLine(firstLineToParse);
			for (String _lineToParse : linesToParse)
			{
				_parser.parseLine(_lineToParse);
			}
			_parser.parseLine(lastLineToParse);
		}
		catch (SyntaxErrorException _exception)
		{
			// do nothing, it may be on purpose.
		}
		if (null == expectedName && _listener.myParsedName != null)
		{
			fail("Property name not null : \ngot='" + _listener.myParsedName + "'");
		}
		else if (null != expectedName && !expectedName.equals(_listener.myParsedName))
		{
			fail("Property name does not match : \nexpected='" + expectedName + "'\ngot='" + _listener.myParsedName + "'");
		}
		if (null == expectedValue && _listener.myParsedValue != null)
		{
			fail("Property value not null : \ngot='" + _listener.myParsedValue + "'");
		}
		else if (null != expectedValue && _listener.myParsedValue.length != expectedValue.length)
		{
			String _expected = dumpStringArray(expectedValue);
			String _got = dumpStringArray(_listener.myParsedValue);
			fail("Property value does not match : \nexpected=\n" + _expected + "\ngot=\n" + _got + "\n");
		}
		String _expected = String.join("\n", expectedValue);
		String _got = String.join("\n", _listener.myParsedValue);
		assertEquals(_expected, _got);

	}

	private final void doTestSingleLine(String lineToParse, String expectedName, String expectedValue)
	{

		final LineByLinePropertyParser _parser = new LineByLinePropertyParser();
		final RememberLastSingleLineProperty _listener = new RememberLastSingleLineProperty();
		_parser.addListener(_listener);
		try
		{
			_parser.parseLine(lineToParse);
		}
		catch (SyntaxErrorException _exception)
		{
			// do nothing, it may be on purpose.
		}
		if (null == expectedName && _listener.myParsedName != null)
		{
			fail("Property name not null : \ngot='" + _listener.myParsedName + "'");
		}
		else if (null != expectedName && !expectedName.equals(_listener.myParsedName))
		{
			fail("Property name does not match : \nexpected='" + expectedName + "'\ngot='" + _listener.myParsedName + "'");
		}
		if (null == expectedValue && _listener.myParsedValue != null)
		{
			fail("Property value not null : \ngot='" + _listener.myParsedValue + "'");
		}
		else if (null != expectedValue && !expectedValue.equals(_listener.myParsedValue))
		{
			fail("Property value does not match : \nexpected='" + expectedValue + "'\ngot='" + _listener.myParsedValue + "'");
		}
	}

	private String dumpStringArray(String[] lines)
	{
		StringBuilder _buffer = new StringBuilder();
		for (String _line : lines)
		{
			_buffer.append("'").append(_line).append("'\n");
		}
		return _buffer.toString();
	}
}
