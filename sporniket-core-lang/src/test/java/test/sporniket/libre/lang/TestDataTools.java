/**
 * 
 */
package test.sporniket.libre.lang;

import junit.framework.TestCase;

import com.sporniket.libre.lang.DataTools;

/**
 * Test suite for {@link DataTools}.
 * 
 * <p>
 * &copy; Copyright 2002-2016 David Sporn
 * </p>
 * <hr>
 * 
 * <p>
 * This file is part of <i>The Sporniket Core Library &#8211; lang</i>.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; lang</i> is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; lang</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 * 
 * <p>
 * You should have received a copy of the GNU Lesser General Public License along with <i>The Sporniket Core Library &#8211;
 * lang</i>. If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
 * 
 * <hr>
 * 
 * @author David SPORN 
 * 
 * @version 16.08.01
 * @since 15.02.00
 */
public class TestDataTools extends TestCase
{
	public final void testUnsignedPositiveInt()
	{
		int _a = 0x11a;
		long _b = DataTools.getUnsignedIntValue(_a);
		String _aHex = Integer.toHexString(_a);
		String _bHex = Long.toHexString(_b);
		if (!_bHex.equals(_aHex))
		{
			fail("Unsigned value of positive int must match signed value : " + _aHex + " -> " + _bHex);
		}
	}

	public final void testUnsignedNegativeInt()
	{
		int _a = 0x87691234;
		long _b = DataTools.getUnsignedIntValue(_a);
		String _aHex = Integer.toHexString(_a);
		String _bHex = Long.toHexString(_b);
		if (!_bHex.equals(_aHex))
		{
			fail("Unsigned value of negative int does not match : " + _aHex + " -> " + _bHex);
		}
	}

	public final void testUnsignedPositiveShort()
	{
		short _a = 0x11a;
		int _b = DataTools.getUnsignedShortValue(_a);
		String _aHex = Integer.toHexString(_a);
		String _bHex = Integer.toHexString(_b);
		if (!_bHex.equals(_aHex))
		{
			fail("Unsigned value of positive short must match signed value : " + _aHex + " -> " + _bHex);
		}
	}

	public final void testUnsignedNegativeShort()
	{
		short _a = (short) 0x8769;
		int _b = DataTools.getUnsignedShortValue(_a);
		String _aHex = Integer.toHexString(_a).substring(4);
		String _bHex = Integer.toHexString(_b);
		if (!_bHex.equals(_aHex))
		{
			fail("Unsigned value of negative short does not match : " + _aHex + " -> " + _bHex);
		}
	}

	public final void testUnsignedPositiveByte()
	{
		byte _a = 0x71;
		int _b = DataTools.getUnsignedByteValue(_a);
		String _aHex = Integer.toHexString(_a);
		String _bHex = Integer.toHexString(_b);
		if (!_bHex.equals(_aHex))
		{
			fail("Unsigned value of positive byte must match signed value : " + _aHex + " -> " + _bHex);
		}
	}

	public final void testUnsignedNegativeByte()
	{
		byte _a = (byte) 0x87;
		int _b = DataTools.getUnsignedByteValue(_a);
		String _aHex = Integer.toHexString(_a).substring(6);
		String _bHex = Integer.toHexString(_b);
		if (!_bHex.equals(_aHex))
		{
			fail("Unsigned value of negative byte does not match : " + _aHex + " -> " + _bHex);
		}
	}
}
