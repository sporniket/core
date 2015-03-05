/**
 * 
 */
package test.sporniket.libre.io;

import java.io.File;

import com.sporniket.libre.io.FileTools;

import junit.framework.TestCase;

/**
 * Test case for {@link FileTools}.
 * <p>
 * &copy; Copyright 2002-2015 David Sporn
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
 * @version 15.02.00
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
			File _subDir = FileTools.createFileBalancingDirectoryDescriptor(_startingPoint, _filename, 4, 4);
			fail("Exception not thrown on short file name.");
		} catch (IllegalArgumentException _exception)
		{
			//ok
		}
	}

}
