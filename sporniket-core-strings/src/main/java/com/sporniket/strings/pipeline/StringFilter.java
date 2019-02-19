/**
 * 
 */
package com.sporniket.strings.pipeline;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String transformation that only keep specified chars.
 * 
 * @author dsporn
 *
 */
public class StringFilter implements StringTransformation
{
	public static final StringFilter KEEP_ALPHA = new StringFilter("A-Za-z");

	public static final StringFilter KEEP_ALPHA_LOWER_CASE = new StringFilter("a-z");

	public static final StringFilter KEEP_ALPHA_UPPER_CASE = new StringFilter("A-Z");

	public static final StringFilter KEEP_ALPHANUM = new StringFilter("0-9A-Za-z");

	public static final StringFilter KEEP_ALPHANUM_LOWER_CASE = new StringFilter("0-9a-z");

	public static final StringFilter KEEP_ALPHANUM_UPPER_CASE = new StringFilter("0-9A-Z");

	public static final StringFilter KEEP_DIGIT = new StringFilter("0-9");

	public static final StringFilter KEEP_FILENAME_VALID_CHARS = new StringFilter("-_.0-9A-Za-z");

	public static final StringFilter KEEP_FILEPATH_UNIX_VALID_CHARS = new StringFilter("-_./0-9A-Za-z");

	public static final StringFilter KEEP_FILEPATH_WINDOWS_VALID_CHARS = new StringFilter("-_.:\\\\0-9A-Za-z");

	final Pattern myFilter;

	public StringFilter(String charactersToKeep)
	{
		super();
		myFilter = Pattern.compile(String.format("[%s]+", charactersToKeep));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.strings.pipeline.StringTransformation#transform(java.lang.String)
	 */
	@Override
	public String transform(String input)
	{
		StringBuilder result = new StringBuilder();
		int scanFrom = 0;
		for (Matcher matcher = myFilter.matcher(input); matcher.find(scanFrom); scanFrom = matcher.end())
		{
			result.append(input.substring(matcher.start(), matcher.end()));
		}
		return result.toString();
	}

}
