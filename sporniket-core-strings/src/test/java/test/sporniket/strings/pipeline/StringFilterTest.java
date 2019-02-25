package test.sporniket.strings.pipeline;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

import com.sporniket.strings.pipeline.StringFilter;

/**
 * 
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
 * @version 19.02.00
 * @since 19.02.00
 */
public class StringFilterTest
{
	public final String SAMPLE_TEXT = "SELECT * from DUAL where TOTO='0023' and bar=-3;";

	public final String SAMPLE_TEXT_FILES = "/root/foo_bar--01.toto;c:\\win\\bar_foo--02.zip";

	@Test
	public void testKeepAlpha()
	{
		BDDAssertions.then(StringFilter.KEEP_ALPHA.transform(SAMPLE_TEXT)).isEqualTo("SELECTfromDUALwhereTOTOandbar");
	}

	@Test
	public void testKeepAlphaLowerCase()
	{
		BDDAssertions.then(StringFilter.KEEP_ALPHA_LOWER_CASE.transform(SAMPLE_TEXT)).isEqualTo("fromwhereandbar");
	}

	@Test
	public void testKeepAlphanum()
	{
		BDDAssertions.then(StringFilter.KEEP_ALPHANUM.transform(SAMPLE_TEXT)).isEqualTo("SELECTfromDUALwhereTOTO0023andbar3");
	}

	@Test
	public void testKeepAlphanumLowerCase()
	{
		BDDAssertions.then(StringFilter.KEEP_ALPHANUM_LOWER_CASE.transform(SAMPLE_TEXT)).isEqualTo("fromwhere0023andbar3");
	}

	@Test
	public void testKeepAlphanumUpperCase()
	{
		BDDAssertions.then(StringFilter.KEEP_ALPHANUM_UPPER_CASE.transform(SAMPLE_TEXT)).isEqualTo("SELECTDUALTOTO00233");
	}

	@Test
	public void testKeepAlphaUpperCase()
	{
		BDDAssertions.then(StringFilter.KEEP_ALPHA_UPPER_CASE.transform(SAMPLE_TEXT)).isEqualTo("SELECTDUALTOTO");
	}

	@Test
	public void testKeepDigit()
	{
		BDDAssertions.then(StringFilter.KEEP_DIGIT.transform(SAMPLE_TEXT)).isEqualTo("00233");
	}

	@Test
	public void testKeepFilenameValidChars()
	{
		BDDAssertions.then(StringFilter.KEEP_FILENAME_VALID_CHARS.transform(SAMPLE_TEXT_FILES))
				.isEqualTo("rootfoo_bar--01.totocwinbar_foo--02.zip");
	}

	@Test
	public void testKeepFilepathUnixValidChars()
	{
		BDDAssertions.then(StringFilter.KEEP_FILEPATH_UNIX_VALID_CHARS.transform(SAMPLE_TEXT_FILES))
				.isEqualTo("/root/foo_bar--01.totocwinbar_foo--02.zip");
	}

	@Test
	public void testKeepFilepathWindowsValidChars()
	{
		BDDAssertions.then(StringFilter.KEEP_FILEPATH_WINDOWS_VALID_CHARS.transform(SAMPLE_TEXT_FILES))
				.isEqualTo("rootfoo_bar--01.totoc:\\win\\bar_foo--02.zip");
	}

}
