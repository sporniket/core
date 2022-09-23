/**
 * 
 */
package test.sporniket.libre.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import junit.framework.TestCase;

import com.sporniket.libre.io.Encoding;
import com.sporniket.libre.io.FileTools;
import com.sporniket.libre.io.parser.properties.SyntaxErrorException;

/**
 * Test case for {@link FileTools}.
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
 * @version 22.09.01
 * @since 15.02.00
 */
public class TestFileTools extends TestCase
{
	private static final String NEWLINE = "\n";

	/**
	 * Test normal behaviour of {@link FileTools#createFileBalancingDirectoryDescriptor(java.io.File, String, int, int)}.
	 */
	public final void testCreateFileBalancingDirectoryDescriptor()
	{
		File _startingPoint = new File(".");
		String _filename = "TheTestFile";
		File _subDir = FileTools.createFileBalancingDirectoryDescriptor(_startingPoint, _filename, 2, 2);
		File _expectedSubDir = new File(new File(_startingPoint, "Th"), "TheT");
		if (!_subDir.getAbsolutePath().equals(_expectedSubDir.getAbsolutePath()))
		{
			fail("Values does not match : \nexpected=" + _expectedSubDir.getAbsolutePath() + "\ngot=" + _subDir.getAbsolutePath());
		}
	}

	/**
	 * {@link FileTools#createFileBalancingDirectoryDescriptor(java.io.File, String, int, int)} must fail if the file name is not
	 * long enough to create the subdirectories.
	 */
	public final void testCreateFileBalancingDirectoryDescriptor__fileNameTooShort()
	{
		File _startingPoint = new File(".");
		String _filename = "TheTestFile";
		try
		{
			@SuppressWarnings("unused")
			File _subDir = FileTools.createFileBalancingDirectoryDescriptor(_startingPoint, _filename, 4, 4);
			fail("Exception not thrown on short file name.");
		}
		catch (IllegalArgumentException _exception)
		{
			// ok
		}
	}

	/**
	 * Test the loading of several properties files into one single map.
	 * 
	 * @throws IOException
	 *             when there is a problem.
	 * @throws SyntaxErrorException
	 *             when there is a problem.
	 * @since 16.08.02
	 */
	public final void testLoadBundle() throws IOException, SyntaxErrorException
	{
		List<URL> _bundle = new ArrayList<URL>(2);
		_bundle.add(getClass().getClassLoader().getResource("test/filetools/bundle.properties"));
		_bundle.add(getClass().getClassLoader().getResource("test/filetools/bundle2.properties"));
		Map<String, String> _properties = FileTools.loadBundle(_bundle, Encoding.ISO_8859_1, NEWLINE);
		assertEquals("foo", _properties.get("foo"));
		assertEquals("bar", _properties.get("bar"));
		assertEquals("bundle2.properties", _properties.get("overridden"));
	}

	/**
	 * Test the loading of a properties files.
	 * 
	 * @throws IOException
	 *             when there is a problem.
	 * @throws SyntaxErrorException
	 *             when there is a problem.
	 * @since 16.08.01
	 */
	public final void testLoadProperties() throws IOException, SyntaxErrorException
	{
		InputStream _fileLocation = getClass().getClassLoader().getResourceAsStream("test/filetools/SampleProperties.properties");
		Map<String, String> _properties = FileTools.loadProperties(_fileLocation, Encoding.ISO_8859_1, NEWLINE);
		assertEquals("I am trimed at the begining ", _properties.get("trimed.value"));
		assertEquals(" I am untrimed at the begining ", _properties.get("untrimed.value"));
		assertEquals("bla blah \ntrimed ! ", _properties.get("trimed.multiline.value"));
		assertEquals("	bla blah \n	trimed ! ", _properties.get("untrimed.multiline.value"));
	}

	/**
	 * Test the loading of a properties file looking for localized variant, like ResourceBundle.
	 * 
	 * @throws IOException
	 *             when there is a problem.
	 * @throws SyntaxErrorException
	 *             when there is a problem.
	 * @since 16.08.02
	 */
	public final void testLoadResourceBundle() throws IOException, SyntaxErrorException
	{
		Map<String, String> _properties = FileTools.loadResourceBundle("test.filetools.bundle", Encoding.ISO_8859_1, NEWLINE,
				Locale.FRENCH);
		assertEquals("foo", _properties.get("foo"));
		assertEquals("bundle_fr.properties", _properties.get("overridden"));
		_properties = FileTools.loadResourceBundle("test.filetools.bundle", Encoding.ISO_8859_1, NEWLINE, Locale.FRANCE);
		assertEquals("foo", _properties.get("foo"));
		assertEquals("bundle_fr_FR.properties", _properties.get("overridden"));
		_properties = FileTools.loadResourceBundle("test.filetools.bundle2", Encoding.ISO_8859_1, NEWLINE);
		assertEquals("bar", _properties.get("bar"));
		assertEquals("bundle2.properties", _properties.get("overridden"));
	}

}
