package test.sporniket.strings.pipeline;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

import com.sporniket.strings.pipeline.StringFilter;

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
		BDDAssertions.then(StringFilter.KEEP_FILENAME_VALID_CHARS.transform(SAMPLE_TEXT_FILES)).isEqualTo("rootfoo_bar--01.totocwinbar_foo--02.zip");
	}

	@Test
	public void testKeepFilepathUnixValidChars()
	{
		BDDAssertions.then(StringFilter.KEEP_FILEPATH_UNIX_VALID_CHARS.transform(SAMPLE_TEXT_FILES)).isEqualTo("/root/foo_bar--01.totocwinbar_foo--02.zip");
	}

	@Test
	public void testKeepFilepathWindowsValidChars()
	{
		BDDAssertions.then(StringFilter.KEEP_FILEPATH_WINDOWS_VALID_CHARS.transform(SAMPLE_TEXT_FILES)).isEqualTo("rootfoo_bar--01.totoc:\\win\\bar_foo--02.zip");
	}

}
