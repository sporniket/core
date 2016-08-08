package com.sporniket.libre.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.sporniket.libre.lang.CollectionTools;

/**
 * The Base Class for converting text files.
 * <p>
 * This class provide the ability to choose the encoding scheme used for the source file and the one for the destination file.
 * </p>
 * <p>
 * When setting an encoding, one should give the preferred MIME name as registered with the <a
 * href="http://www.iana.org/assignments/character-sets">Internet Assigned Numbers Authority.</a>
 * </p>
 * <p>
 * However an encoding might be "unsupported" if an instance cannot find out the encoding code used by Java for classes such as
 * <tt>java.io.XxxReader</tt>, <tt>java.io.XxxWriter</tt> and <tt>String</tt>.
 * <p>
 * This mapping is provided with the properties file <i>EncodingName</i>, thus one must add new supported encodings name inside this
 * file.
 * 
 * <p>
 * <i>Subclass of TextFileConverterAdapter <b>MUST</b> implement <tt>doConvertFile(...)</tt> instead of overriding
 * <code>convertFile(...)</code> </i>
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
 * @version 16.08.00
 * @since 12.06.01
 */
public abstract class AbstractTextFileConverter implements FileConverterInterface
{

	/**
	 * Error message key
	 */
	private static final String KEY_ERROR_TRANSLATING_ENCODING = "ERROR_TRANSLATING_ENCODING";

	/**
	 * Error message key
	 */
	private static final String KEY_UNSUPPORTED_ENCODING = "UNSUPPORTED_ENCODING";

	/**
	 * Name of the properties file associating iso encoding names to Java encoding names.
	 */
	private static final String RB_ENCODING = "EncodingNames";

	/**
	 * Package names for the properties files.
	 */
	private static final String RB_PREXIX = "com.sporniket.libre.io.";

	/**
	 * Human friendly error messages
	 */
	private static final String RB_TEXT_FILE_CONVERTER_ADAPTER = "TextFileConverterAdapter";

	/**
	 * Resource bundles
	 */
	private static ResourceBundle theEncodingResource = null;

	/**
	 * Error message
	 */
	private static String theMessageErrorTranslatingEncoding = null;

	/**
	 * Error message
	 */
	private static String theMessageUnsupportedEncoding = null;

	/**
	 * Resource bundles
	 */
	private static ResourceBundle theTextFileConverterAdapterResource = null;

	/**
	 * Initializes the resource storing the MIME encoding names and its mapping to classic <code>java.io</code> encoding names.
	 */
	private static void initEncodingResource()
	{
		try
		{
			theEncodingResource = ResourceBundle.getBundle(RB_PREXIX + RB_ENCODING);
		} // try
		catch (java.util.MissingResourceException _exception)
		{
			_exception.printStackTrace();
		} // catch (java.util.MissingResourceException _exception)
	}

	/**
	 * Initialize the class (resource).
	 */
	private static void initTextFileConverterAdapterResource()
	{
		try
		{
			theTextFileConverterAdapterResource = ResourceBundle.getBundle(RB_PREXIX + RB_TEXT_FILE_CONVERTER_ADAPTER);
		} // try
		catch (java.util.MissingResourceException _exception)
		{
			_exception.printStackTrace();
		} // catch (java.util.MissingResourceException _exception)
		theMessageUnsupportedEncoding = CollectionTools.getString(theTextFileConverterAdapterResource, KEY_UNSUPPORTED_ENCODING,
				KEY_UNSUPPORTED_ENCODING);
		theMessageErrorTranslatingEncoding = CollectionTools.getString(theTextFileConverterAdapterResource,
				KEY_ERROR_TRANSLATING_ENCODING, KEY_ERROR_TRANSLATING_ENCODING);
	}

	/**
	 * Get the java encoding name from a standard encoding name.
	 * 
	 * @param encodingName
	 *            the standard encoding name, e.g. <code>iso-8859-1</code>
	 * @return the java encoding name, e.g. <code>ISO_8859_1</code>
	 * @throws UnsupportedEncodingException
	 *             if a problem occurs.
	 */
	private static String translateEncoding(final String encodingName) throws UnsupportedEncodingException
	{
		try
		{
			String _encoding = encodingName.trim().toLowerCase();
			String _encodingCode = theEncodingResource.getString(_encoding);

			return _encodingCode;
		} // try
		catch (MissingResourceException _exception)
		{
			throw new java.io.UnsupportedEncodingException(theMessageUnsupportedEncoding + encodingName);
		} // catch(java.util.MissingResourceException _exception)
		catch (Exception _exception)
		{
			throw new java.io.UnsupportedEncodingException(theMessageErrorTranslatingEncoding + encodingName);
		} // catch (Exception _exception)
	}

	/**
	 * {@link Reader}.
	 */
	private BufferedReader myBufferedReader;

	/**
	 * {@link Writer}.
	 */
	private BufferedWriter myBufferedWriter;

	/**
	 * Standard name of the encoding for the input.
	 */
	private String myInputEncoding = "utf-8";

	/**
	 * Java name of the encoding for the input.
	 */
	private String myInputEncodingCode = "UTF8";

	/**
	 * input file related objects
	 */
	private File myInputFile;

	/**
	 * Internal input stream.
	 */
	private FileInputStream myInputStream;

	/**
	 * Internal {@link Reader}.
	 */
	private LineNumberReader myLineReader;

	/**
	 * Standard name of the encoding for the output
	 */
	private String myOutputEncoding = "utf-8";

	/**
	 * Java name of the encoding for the output
	 */
	private String myOutputEncodingCode = "UTF8";

	/**
	 * Output file.
	 */
	private File myOutputFile;

	/**
	 * File output stream.
	 */
	private FileOutputStream myOutputStream;

	/**
	 * Internal {@link Reader}.
	 */
	private InputStreamReader myStreamReader;

	/**
	 * Internal {@link Writer}.
	 */
	private OutputStreamWriter myStreamWriter;

	/**
	 * Creates new TextFileConverter.
	 */
	public AbstractTextFileConverter()
	{
		if (null == theTextFileConverterAdapterResource)
		{
			initTextFileConverterAdapterResource();
		} // if (null == globalTextFileConverterAdapterResource)
		if (null == theEncodingResource)
		{
			initEncodingResource();
		} // if (null == globalEncodingResource)
	}

	/**
	 * Close the input stream.
	 * 
	 * @throws IOException
	 *             if a problem occurs.
	 */
	private void closeInputFile() throws IOException
	{
		myLineReader.close();
		myBufferedReader.close();
		myStreamReader.close();
		myInputStream.close();
	}

	/**
	 * Close the outpuStream.
	 * 
	 * @throws IOException
	 *             if a problem occurs.
	 */
	private void closeOutputFile() throws IOException
	{
		myBufferedWriter.close();
		myStreamWriter.close();
		myOutputStream.close();
	}

	/**
	 * The file conversion process.
	 * 
	 * @param inputFileName
	 *            Name of the file to convert
	 * @param outputFileName
	 *            Name of the converted file
	 * @throws IOException
	 *             IO Exceptions are not processed. It is up to the calling process to decide what to do, typically retry, abort or
	 *             ignore.
	 * @throws ConversionException
	 *             if a conversion problem occurs.
	 */
	public final void convertFile(final String inputFileName, final String outputFileName) throws IOException, ConversionException
	{
		openInputFile(inputFileName);
		openOutputFile(outputFileName);
		doConvertFile(myLineReader, myBufferedWriter);
		closeOutputFile();
		closeInputFile();
	}

	/**
	 * The real processing.
	 * <p>
	 * Subclass of this class <b>must</b> implement this method instead of <tt>convertFile()</tt>.
	 * 
	 * @param reader
	 *            the input stream.
	 * @param writer
	 *            the output stream.
	 * @throws IOException
	 *             if a I/O problem occurs.
	 * @throws ConversionException
	 *             if a conversion problem occurs.
	 */
	protected abstract void doConvertFile(LineNumberReader reader, Writer writer) throws IOException, ConversionException;

	/**
	 * Return the encoding code for the input file.
	 * 
	 * @return the encoding code for the input file.
	 */
	public final String getInputEncoding()
	{
		return myInputEncoding;
	}

	/**
	 * Return the encoding code used by Java for the input file.
	 * 
	 * @return the encoding code used by Java for the input file as a String.
	 */
	public final String getInputEncodingCode()
	{
		return myInputEncodingCode;
	}

	/**
	 * Return the encoding code for the output file.
	 * 
	 * @return the encoding code for the output file.
	 */
	public final String getOutputEncoding()
	{
		return myOutputEncoding;
	}

	/**
	 * Return the encoding code used by Java for the output file.
	 * 
	 * @return the encoding code used by Java for the output file as a String.
	 */
	public final String getOutputEncodingCode()
	{
		return myOutputEncodingCode;
	}

	/**
	 * Prepare the input stream.
	 * 
	 * @param inputFileName
	 *            the file to read from.
	 * @throws IOException
	 *             if a problem occurs.
	 */
	private void openInputFile(final String inputFileName) throws IOException
	{
		myInputFile = new java.io.File(inputFileName);
		myInputStream = new java.io.FileInputStream(myInputFile);
		myStreamReader = new java.io.InputStreamReader(myInputStream, getInputEncodingCode());
		myBufferedReader = new java.io.BufferedReader(myStreamReader);
		myLineReader = new java.io.LineNumberReader(myBufferedReader);
	}

	/**
	 * Prepare the output stream.
	 * 
	 * @param outputFileName
	 *            the file to write into.
	 * @throws IOException
	 *             if a problem occurs.
	 */
	private void openOutputFile(final String outputFileName) throws IOException
	{
		myOutputFile = new java.io.File(outputFileName);
		myOutputStream = new java.io.FileOutputStream(myOutputFile);
		myStreamWriter = new java.io.OutputStreamWriter(myOutputStream, getOutputEncodingCode());
		myBufferedWriter = new java.io.BufferedWriter(myStreamWriter);
	}

	/**
	 * Set the encoding code for the input file.
	 * <p>
	 * This should be the preferred MIME name of the encoding.
	 * 
	 * @param encodingName
	 *            The new encoding scheme.
	 * @throws UnsupportedEncodingException
	 *             This exception is thrown if the encoding is not registered in the properties file <i>EncodingNames</i>.
	 */
	public final void setInputEncoding(final String encodingName) throws UnsupportedEncodingException
	{
		myInputEncodingCode = translateEncoding(encodingName);
		myInputEncoding = encodingName;
	}

	/**
	 * Set the encoding code for the output file.
	 * <p>
	 * This should be the preferred MIME name of the encoding.
	 * 
	 * @param encodingName
	 *            The new encoding scheme.
	 * @throws UnsupportedEncodingException
	 *             This exception is thrown if the encoding is not registered in the properties file <i>EncodingNames</i>.
	 */
	public final void setOutputEncoding(final String encodingName) throws UnsupportedEncodingException
	{
		myOutputEncodingCode = translateEncoding(encodingName);
		myOutputEncoding = encodingName;
	}
}
