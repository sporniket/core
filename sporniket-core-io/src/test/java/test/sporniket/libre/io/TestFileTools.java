/**
 * 
 */
package test.sporniket.libre.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import junit.framework.TestCase;

import com.sporniket.libre.io.Encoding;
import com.sporniket.libre.io.FileTools;
import com.sporniket.libre.io.parser.properties.SyntaxErrorException;

/**
 * Test case for {@link FileTools}.
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
 * @version 16.08.01
 * @since 15.02.00
 */
public class TestFileTools extends TestCase
{
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
	 * @throws IOException when there is a problem.
	 * @throws SyntaxErrorException when there is a problem.
	 * @since 16.08.01
	 */
	public final void testLoadProperties() throws IOException, SyntaxErrorException
	{
		InputStream _fileLocation = getClass().getClassLoader().getResourceAsStream("test/filetools/SampleProperties.properties");
		Map<String, String> _properties = FileTools.loadProperties(_fileLocation, Encoding.ISO_8859_1, "\n");
		assertEquals("I am trimed at the begining ", _properties.get("trimed.value"));
		assertEquals(" I am untrimed at the begining ", _properties.get("untrimed.value"));
		assertEquals("bla blah \ntrimed ! ", _properties.get("trimed.multiline.value"));
		assertEquals("	bla blah \n	trimed ! ", _properties.get("untrimed.multiline.value"));
	}

}
