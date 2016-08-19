/**
 * 
 */
package com.sporniket.libre.io;

import java.io.File;

/**
 * Define a common interface for a File generator.
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
 * @since 12.09.01
 */
public interface FileGenerator
{
	/**
	 * The directory where the generator will create files.
	 * 
	 * @return a directory.
	 */
	File getWorkingDirectory();

	/**
	 * Set the directory where the generator will create files.
	 * 
	 * @param workingDirectory the working directory.
	 * @throws IllegalArgumentException
	 *             if the file is not a directory or does not exists.
	 */
	void setWorkingDirectory(File workingDirectory) throws IllegalArgumentException;
}
