/**
 * 
 */
package com.sporniket.libre.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import com.sporniket.libre.lang.string.StringTools;

/**
 * Utility class for file processing.
 * 
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
 * @since 12.06.01
 */
public class FileTools
{

	/**
	 * Instanciate a Reader for a file using the given encoding.
	 * 
	 * @param source source file.
	 * @param encoding
	 *            can be <code>null</code>
	 * @return the reader.
	 * @throws FileNotFoundException if there is a problem to deal with.
	 * @throws UnsupportedEncodingException if there is a problem to deal with.
	 * @since 12.06.01
	 */
	public static Reader createReaderForFile(File source, Encoding encoding) throws FileNotFoundException,
			UnsupportedEncodingException
	{
		return createReaderForInputStream(new FileInputStream(source), encoding);
	}

	/**
	 * Instanciate a Reader for a file using the given encoding.
	 * 
	 * @param source source file.
	 * @param encoding
	 *            null or empty for using system encoding.
	 * @return the reader.
	 * @throws FileNotFoundException if there is a problem to deal with.
	 * @throws UnsupportedEncodingException if there is a problem to deal with.
	 * @since 12.06.01
	 */
	public static Reader createReaderForFile(File source, String encoding) throws FileNotFoundException,
			UnsupportedEncodingException
	{
		return createReaderForInputStream(new FileInputStream(source), encoding);
	}

	/**
	 * Instanciate a Reader for a InputStream using the given encoding.
	 * 
	 * @param source source stream.
	 * @param encoding
	 *            can be <code>null</code>
	 * @return the reader.
	 * @throws FileNotFoundException if there is a problem to deal with.
	 * @throws UnsupportedEncodingException if there is a problem to deal with.
	 * @since 12.06.01
	 */
	public static Reader createReaderForInputStream(InputStream source, Encoding encoding) throws FileNotFoundException,
			UnsupportedEncodingException
	{
		return (null == encoding) ? new InputStreamReader(source) : new InputStreamReader(source, encoding.getSunOldIoName());
	}

	/**
	 * Instanciate a Reader for a InputStream using the given encoding.
	 * 
	 * @param source source stream.
	 * @param encoding
	 *            <code>null</code> or empty for using system encoding.
	 * @return the reader.
	 * @throws FileNotFoundException if there is a problem to deal with.
	 * @throws UnsupportedEncodingException if there is a problem to deal with.
	 * @since 12.06.01
	 */
	public static Reader createReaderForInputStream(InputStream source, String encoding) throws FileNotFoundException,
			UnsupportedEncodingException
	{
		return (StringTools.isEmptyString(encoding)) ? new InputStreamReader(source) : new InputStreamReader(source, encoding);
	}

	/**
	 * Create a descriptor of a subdirectory to be created in the <em>workingDirectory</em>, named from the name of the file to
	 * store.
	 * 
	 * <p>
	 * The name of the subdirectories are created as follow :
	 * <ul>
	 * <li>The first level is a substring of the filename of the first <em>cellWidth</em>-th characters of the file name
	 * <li>If the <em>depth</em> is not reached yet, the next level is a substring that has <em>cellWidth</em> more caracters than
	 * its parent.
	 * </ul>
	 * 
	 * @param workingDirectory
	 *            The working directory into which the subdirectories will be created.
	 * @param fileName
	 *            Name of the file to store in the created directories.
	 * @param depth
	 *            MUST be at least 0, SHOULD be at least 1
	 * @param cellWidth
	 *            MUST be at least 1
	 * @return a {@link File} describing the deepest directory to create, then you should use {@link File#mkdirs()} on it. If depth
	 *         was 0, return the working directory.
	 * @since 15.02.00
	 */
	//TODO tests
	public static File createFileBalancingDirectoryDescriptor(File workingDirectory, String fileName, int depth, int cellWidth)
	{
		if (depth < 0)
		{
			throw new IllegalArgumentException("depth must be greater or equal to 0");
		}
		if (cellWidth < 1)
		{
			throw new IllegalArgumentException("cellWidth must be greater or equal to 1");
		}
		if (fileName.length() < (depth*cellWidth))
		{
			throw new IllegalArgumentException("fileName must be at least "+(depth*cellWidth)+" chars, has only "+fileName.length()+" ["+fileName+"].");
		}
		int _subNameLength = cellWidth;
		File _targetFile = workingDirectory;
		for (int _level = 0; _level < depth; _level++)
		{
			_targetFile = new File(_targetFile, fileName.substring(0, _subNameLength));
			_subNameLength += cellWidth;
		}
		return _targetFile;
	}
}
