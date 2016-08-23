/**
 * 
 */
package com.sporniket.libre.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.sporniket.libre.io.parser.properties.LineByLinePropertyParser;
import com.sporniket.libre.io.parser.properties.MultipleLinePropertyParsedEvent;
import com.sporniket.libre.io.parser.properties.PropertiesParsingListener;
import com.sporniket.libre.io.parser.properties.SingleLinePropertyParsedEvent;
import com.sporniket.libre.io.parser.properties.SyntaxErrorException;
import com.sporniket.libre.lang.string.StringTools;

/**
 * Utility class for file processing.
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
 * @version 16.08.01
 * @since 12.06.01
 */
public class FileTools
{

	private static final String FILE_EXTENSION__PROPERTIES = ".properties";

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
	 */
	// TODO tests
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
		if (fileName.length() < (depth * cellWidth))
		{
			throw new IllegalArgumentException("fileName must be at least " + (depth * cellWidth) + " chars, has only "
					+ fileName.length() + " [" + fileName + "].");
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

	/**
	 * Instanciate a Reader for a file using the given encoding.
	 * 
	 * @param source
	 *            source file.
	 * @param encoding
	 *            can be <code>null</code>
	 * @return the reader.
	 * @throws FileNotFoundException
	 *             if there is a problem to deal with.
	 * @throws UnsupportedEncodingException
	 *             if there is a problem to deal with.
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
	 * @param source
	 *            source file.
	 * @param encoding
	 *            null or empty for using system encoding.
	 * @return the reader.
	 * @throws FileNotFoundException
	 *             if there is a problem to deal with.
	 * @throws UnsupportedEncodingException
	 *             if there is a problem to deal with.
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
	 * @param source
	 *            source stream.
	 * @param encoding
	 *            can be <code>null</code>
	 * @return the reader.
	 * @throws FileNotFoundException
	 *             if there is a problem to deal with.
	 * @throws UnsupportedEncodingException
	 *             if there is a problem to deal with.
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
	 * @param source
	 *            source stream.
	 * @param encoding
	 *            <code>null</code> or empty for using system encoding.
	 * @return the reader.
	 * @throws FileNotFoundException
	 *             if there is a problem to deal with.
	 * @throws UnsupportedEncodingException
	 *             if there is a problem to deal with.
	 * @since 12.06.01
	 */
	public static Reader createReaderForInputStream(InputStream source, String encoding) throws FileNotFoundException,
			UnsupportedEncodingException
	{
		return (StringTools.isEmptyString(encoding)) ? new InputStreamReader(source) : new InputStreamReader(source, encoding);
	}

	/**
	 * Load a collection of properties files into the same Map, supports multiple lines values.
	 * 
	 * @param sources
	 *            the list of properties files to load.
	 * @param encoding
	 *            the encoding of the files.
	 * @param newline
	 *            the sequence to use as a line separator for multiple line values.
	 * @return the properties, merged following the rules "the last speaker is right".
	 * @throws IOException
	 *             if there is a problem to deal with.
	 * @throws SyntaxErrorException
	 *             if there is a problem to deal with.
	 * @see LineByLinePropertyParser
	 * @since 16.08.02
	 */
	public static Map<String, String> loadBundle(List<URL> sources, Encoding encoding, String newline) throws IOException,
			SyntaxErrorException
	{
		Map<String, String> _result = new HashMap<String, String>();
		for (URL _source : sources)
		{
			_result.putAll(loadProperties(_source.openStream(), encoding, newline));
		}
		return _result;
	}

	/**
	 * Implement the loading of properties files using a locale like ResourceBundle, supporting multiple line values.
	 * 
	 * @param bundleName
	 *            the bundle name, like <code>com.foo.MyBundle</code>.
	 * @param encoding
	 *            the encoding of the bundle files.
	 * @param newline
	 *            the sequence to use as a line separator for multiple line values.
	 * @param locale
	 *            the locale to use to compute the name of the localized resources.
	 * @return the properties, merged like a Java ResourceBundle.
	 * @throws IOException
	 *             if there is a problem to deal with.
	 * @throws SyntaxErrorException
	 *             if there is a problem to deal with.
	 * @see LineByLinePropertyParser
	 * @since 16.08.02
	 */
	public static Map<String, String> loadJavaBundle(String bundleName, Encoding encoding, String newline, Locale locale)
			throws IOException, SyntaxErrorException
	{
		String _baseName = bundleName.replace(".", "/");
		List<URL> _bundle = new ArrayList<URL>(3);
		_bundle.add(FileTools.class.getClassLoader().getResource(_baseName + FILE_EXTENSION__PROPERTIES));
		if (!StringTools.isEmptyString(locale.getCountry()))
		{
			_bundle.add(FileTools.class.getClassLoader().getResource(
					_baseName + "_" + locale.getCountry() + FILE_EXTENSION__PROPERTIES));
			if (!StringTools.isEmptyString(locale.getLanguage()))
			{
				_bundle.add(FileTools.class.getClassLoader().getResource(
						_baseName + "_" + locale.getCountry() + "_" + locale.getLanguage() + FILE_EXTENSION__PROPERTIES));
			}
		}
		return loadBundle(_bundle, encoding, newline);
	}

	/**
	 * Load a properties file from the specified source supporting multiple line values, as specified by
	 * {@link LineByLinePropertyParser}.
	 * 
	 * @param source
	 *            the source input stream.
	 * @param encoding
	 *            the encoding of the file.
	 * @param newline
	 *            the sequence to use as a line separator for multiple line values.
	 * @return the properties.
	 * @throws IOException
	 *             if there is a problem to deal with.
	 * @throws SyntaxErrorException
	 *             if there is a problem to deal with.
	 * @see LineByLinePropertyParser
	 * @since 16.08.01
	 */
	public static Map<String, String> loadProperties(InputStream source, Encoding encoding, String newline) throws IOException,
			SyntaxErrorException
	{
		final Map<String, String> _result = new HashMap<String, String>();
		PropertiesParsingListener _listener = new PropertiesParsingListener()
		{
			@Override
			public void onMultipleLinePropertyParsed(MultipleLinePropertyParsedEvent event)
			{
				_result.put(event.getName(), String.join(newline, event.getValue()));
			}

			@Override
			public void onSingleLinePropertyParsed(SingleLinePropertyParsedEvent event)
			{
				_result.put(event.getName(), event.getValue());
			}
		};

		BufferedReader _reader = new BufferedReader(createReaderForInputStream(source, encoding));
		LineByLinePropertyParser _parser = new LineByLinePropertyParser();
		_parser.addListener(_listener);

		for (String _line = _reader.readLine(); _line != null;)
		{
			_parser.parseLine(_line);
			_line = _reader.readLine();
		}
		_reader.close();

		return _result;
	}
}
