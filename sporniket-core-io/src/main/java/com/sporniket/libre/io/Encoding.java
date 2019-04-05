/**
 * 
 */
package com.sporniket.libre.io;

/**
 * Encapsulate standard naming and sun naming, and can be used instead of "magic strings".
 * 
 * <p>
 * &copy; Copyright 2002-2019 David Sporn
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
 * @version 19.04.00
 * @since 12.06.01
 */
public enum Encoding
{
	// FIXME complete the list
	// FIXME build mapping and parser to get the enum from iso/sun old io/sun new io name.

	/**
	 * ISO Japan.
	 */
	ISO_2022_JP("iso-2022-jp", "ISO2022JP", "ISO-2022-JP"),
	/**
	 * ISO latin.
	 */
	ISO_8859_1("iso-8859-1", "ISO8859_1", "ISO-8859-1"),
	/**
	 * ISO latin.
	 */
	ISO_8859_13("iso-8859-13", "ISO8859_13", "ISO-8859-13"),
	/**
	 * ISO latin.
	 */
	ISO_8859_15("iso-8859-15", "ISO8859_15", "ISO-8859-15"),
	/**
	 * ISO latin.
	 */
	ISO_8859_2("iso-8859-2", "ISO8859_2", "ISO-8859-2"),
	/**
	 * ISO latin.
	 */
	ISO_8859_3("iso-8859-3", "ISO8859_3", "ISO-8859-3"),
	/**
	 * ISO latin.
	 */
	ISO_8859_4("iso-8859-4", "ISO8859_4", "ISO-8859-4"),
	/**
	 * ISO latin.
	 */
	ISO_8859_5("iso-8859-5", "ISO8859_5", "ISO-8859-5"),
	/**
	 * ISO latin.
	 */
	ISO_8859_6("iso-8859-6", "ISO8859_6", "ISO-8859-6"),
	/**
	 * ISO latin.
	 */
	ISO_8859_7("iso-8859-7", "ISO8859_7", "ISO-8859-7"),
	/**
	 * ISO latin.
	 */
	ISO_8859_8("iso-8859-8", "ISO8859_8", "ISO-8859-8"),
	/**
	 * ISO latin.
	 */
	ISO_8859_9("iso-8859-9", "ISO8859_9", "ISO-8859-9"),
	/**
	 * US ASCII
	 */
	US_ASCII("us-ascii", "ASCII", "US-ASCII");
	/**
	 * Encoding designation in ISO naming convention.
	 */
	private String myIsoName;

	/**
	 * Encoding designation in Sun java.lang.nio package.
	 */
	private String mySunNewIoName;

	/**
	 * Encoding designation in Sun java.lang.io package.
	 */
	private String mySunOldIoName;

	/**
	 * @param isoName
	 * @param sunOldIoName
	 * @param sunNewIoName
	 */
	private Encoding(String isoName, String sunOldIoName, String sunNewIoName)
	{
		myIsoName = isoName;
		mySunOldIoName = sunOldIoName;
		mySunNewIoName = sunNewIoName;
	}

	/**
	 * @return the isoName
	 */
	public String getIsoName()
	{
		return myIsoName;
	}

	/**
	 * @return the sunNewIoName
	 */
	public String getSunNewIoName()
	{
		return mySunNewIoName;
	}

	/**
	 * @return the sunOldIoName
	 */
	public String getSunOldIoName()
	{
		return mySunOldIoName;
	}

}
