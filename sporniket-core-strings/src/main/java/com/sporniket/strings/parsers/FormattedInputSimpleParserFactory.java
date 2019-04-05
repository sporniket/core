/**
 * 
 */
package com.sporniket.strings.parsers;

import static com.sporniket.strings.StringPredicates.IS_NOT_EMPTY;
import static com.sporniket.strings.StringPredicates.IS_EMPTY;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Regular expression factory based on very simple description of the input format.
 * 
 * <p>
 * typical input format will look like : <code>bla blah : # , # , $;</code> meaning there is some literal display
 * <code>bla blah :</code> followed by 2 numerical values (<code>#</code> placeholders) and an alphanumerical value (<code>$</code>
 * placeholder) and a literal display <code>;</code>.
 * 
 * <p>
 * The Regular expression will match each one char placeholder found in the input format. Obviously, there MUST be some separation
 * between placeholders to work as intended.
 * 
 * <p>
 * Spaces and tabulations are converted as optionnal blank spaces <code>[ \t]*</code> ; Non breaking spaces (<code>\u00a0</code>)
 * are converted as mandatory blank spaces <code>[ \t]+</code>.
 * 
 * <p>
 * Thus, if one want to use blank spaces to separate values, one MUST use non breaking spaces in the input format, e.g.
 * <code>blah\u00a0#\u00a0#</code>.
 * 
 * <p>
 * The input format is trimed before processing, thus one MUST trim the input to parse.
 * 
 * 
 * <p>
 * By default, this factory use the following placeholders :
 * <dl>
 * <dt>#
 * <dd>integers matching <code>[0-9]+</code>
 * <dt>$
 * <dd>alphanumeric words matching <code>[0-9A-Za-z-_.]+</code>
 * </dl>
 * 
 * <p>
 * The list of placeholders and their matching expression can be provided as an array of <code>String[]</code>, each array
 * containing at least 2 non-empty Strings. The first item is the placeholder, only the first char is taken into account, the second
 * item is the regular expression to match, it will be enclosed inside <code>()</code> in the final pattern.
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
 * @since 16.08.00
 */
public class FormattedInputSimpleParserFactory
{
	private static final String[][] DEFAULT_PLACEHOLDERS_DEFINITION =
	{
			{
					"#", "[0-9]+"
			},
			{
					"$", "[0-9A-Za-z-_.]+"
			}
	};

	private static final FormattedInputSimpleParserFactory INSTANCE = new FormattedInputSimpleParserFactory();

	/**
	 * Shortcut for <code>new FormattedInputSimpleParserFactory().getParser(inputFormat)</code>.
	 * 
	 * @param inputFormat
	 *            the input format. the input format to match.
	 * @return a regular expression matching the specified input format.
	 */
	public static Pattern getSimpleParser(String inputFormat)
	{
		return INSTANCE.getParser(inputFormat);
	}

	private static final String MATCHER_BLANK__MANDATORY = "[ \t]+";

	private static final String MATCHER_BLANK__OPTIONAL = "[ \t]*";

	/**
	 * Characters that need to be escaped, if they are not a placeholder.
	 */
	private static final String SPECIAL_CHARS = "\\(){}[]<>^$.*+?|-&";

	private static final String SPECIAL_CHARS__BLANK = " \t";

	private static final String SPECIAL_CHARS__NBSP = "\u00a0";

	private final Map<Character, String> myPlaceHolderSpecs = new TreeMap<Character, String>();

	public FormattedInputSimpleParserFactory()
	{
		this(DEFAULT_PLACEHOLDERS_DEFINITION);
	}

	/**
	 * Create a factory using specified placeholders.
	 * 
	 * @param placeHolderSpecs
	 *            invalid specification are silently ignored.
	 */
	public FormattedInputSimpleParserFactory(String[][] placeHolderSpecs)
	{
		buildPlaceHolderSpecs(placeHolderSpecs);
	}

	/**
	 * Get a parser suitable to matche multiple values.
	 * 
	 * @param inputFormat
	 *            the input format to match.
	 * @return a regular expression matching the specified input format.
	 */
	public Pattern getParser(String inputFormat)
	{
		return Pattern.compile(computePattern(inputFormat.trim()));
	}

	private void buildPlaceHolderSpecs(String[][] specs)
	{
		for (String[] _spec : specs)
		{
			// silently ignore bad specs
			if (_spec.length < 2)
			{
				continue;
			}
			if (IS_EMPTY.test(_spec[0]))
			{
				continue;
			}
			if (IS_EMPTY.test(_spec[1]))
			{
				continue;
			}
			getPlaceHolderSpecs().put(_spec[0].charAt(0), _spec[1]);

		}
	}

	private String computePattern(String inputFormat)
	{
		StringBuilder _result = new StringBuilder();
		boolean _isInBlankSpaces = false;
		for (char _char : inputFormat.toCharArray())
		{
			if (isMandatoryBlank(_char) && !_isInBlankSpaces)
			{
				_result.append(MATCHER_BLANK__MANDATORY);
				_isInBlankSpaces = true;
			}
			else if (isBlank(_char) && !_isInBlankSpaces)
			{
				_result.append(MATCHER_BLANK__OPTIONAL);
				_isInBlankSpaces = true;
			}
			else if (isPlaceHolder(_char))
			{
				_result.append("(").append(getPlaceHolderSpecs().get(_char)).append(")");
				_isInBlankSpaces = false;
			}
			else if (isSpecialChar(_char))
			{
				_result.append("\\").append(_char);
				_isInBlankSpaces = false;
			}
			else
			{
				_result.append(_char);
				_isInBlankSpaces = false;
			}
		}
		return _result.toString();
	}

	private Map<Character, String> getPlaceHolderSpecs()
	{
		return myPlaceHolderSpecs;
	}

	private boolean isBlank(char character)
	{
		return SPECIAL_CHARS__BLANK.indexOf(character) > -1;
	}

	private boolean isMandatoryBlank(char character)
	{
		return SPECIAL_CHARS__NBSP.indexOf(character) > -1;
	}

	private boolean isPlaceHolder(char character)
	{
		return getPlaceHolderSpecs().containsKey(character);
	}

	private boolean isSpecialChar(char character)
	{
		return SPECIAL_CHARS.indexOf(character) > -1;
	}
}
