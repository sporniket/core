package com.sporniket.libre.io;

import java.io.IOException;

/**
 * Define a common interface for a File Conversion process.
 * 
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
 * @version 22.11.00
 * @since 12.06.01
 */
public interface FileConverterInterface
{

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
	void convertFile(String inputFileName, String outputFileName) throws ConversionException, IOException;

}
