package com.sporniket.libre.lang;

import static javax.xml.bind.DatatypeConverter.printHexBinary;

import javax.xml.bind.DatatypeConverter;

/**
 * Data manipulation utilities.
 * <p>
 * &copy; Copyright 2002-2022 David Sporn
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
 * @version 22.11.00
 * @since 15.02.00
 */
public class DataTools
{

    /**
     * Verify that each byte of a buffer match the corresponding byte of a given sequence, starting from an offset.
     * 
     * @param sequence
     *            the sequence of bytes to match.
     * @param buffer
     *            the buffer to test.
     * @param offset
     *            the offset.
     * @return <code>true</code> if the first bytes of buffer starting from offset are the same as the provided sequence.
     */
    public static final boolean matchSequence(byte[] sequence, byte[] buffer, int offset)
    {
        boolean _result = (sequence.length < (buffer.length - offset));
        if (_result)
        {
            for (int _index = 0; _index < sequence.length; _index++)
            {
                if (buffer[_index] != sequence[_index])
                {
                    _result = false;
                    break;
                }
            }
        }
        return _result;
    }

    /**
     * Verify that a buffer start by a sequence of bytes.
     * 
     * @param sequence
     *            the sequence to match.
     * @param buffer
     *            the buffer to test.
     * @return <code>true</code> if the first bytes of buffer the same as the provided sequence.
     */
    public static final boolean matchSequence(byte[] sequence, byte[] buffer)
    {
        return matchSequence(sequence, buffer, 0);
    }

    /**
     * Convert a signed int value into the corresponding unsigned int.
     * 
     * @param signedValue
     *            the signed value to convert.
     * @return the converted unsigned value.
     */
    public static long getUnsignedIntValue(int signedValue)
    {
        return signedValue & 0xffffffffL;
    }

    /**
     * Convert a signed short value into the corresponding unsigned short.
     * 
     * @param signedValue
     *            the signed value to convert.
     * @return the converted unsigned value.
     */
    public static int getUnsignedShortValue(short signedValue)
    {
        return signedValue & 0xffff;
    }

    /**
     * Convert a signed byte value into the corresponding unsigned byte.
     * 
     * @param signedValue
     *            the signed value to convert.
     * @return the converted unsigned value.
     */
    public static int getUnsignedByteValue(byte signedValue)
    {
        return signedValue & 0xff;
    }

    /**
     * Convert a byte into a padded hexadecimal string.
     * 
     * @param value
     *            the value to convert.
     * @param buffer
     *            the buffer to append the result into.
     * @return the updated buffer.
     * @deprecated use <code>buffer.append(javax.xml.bind.DatatypeConverter.printHexBinary(new byte[] {value}).toLowerCase())</code>
     */
    @Deprecated
    public static StringBuffer appendByteAsPaddedHexString(byte value, StringBuffer buffer)
    {
        return buffer.append(printHexBinary(new byte[]
        {
                value
        }).toLowerCase());
    }

    /**
     * Convert a byte into a padded hexadecimal String.
     * 
     * @param value
     *            the value to convert.
     * @return a padded hexadecimal string.
     * @deprecated use <code>javax.xml.bind.DatatypeConverter.printHexBinary(new byte[] {value}).toLowerCase()</code>
     */
    @Deprecated
    public static String convertToPaddedHexString(byte value)
    {
        return printHexBinary(new byte[]
        {
                value
        }).toLowerCase();
    }

    /**
     * Convert a sequence of bytes into a padded hexadecimal string.
     * 
     * @param values
     *            the sequence to convert.
     * @param buffer
     *            the buffer to append the result into.
     * @return the updated buffer.
     * @deprecated use <code>buffer.append(javax.xml.bind.DatatypeConverter.printHexBinary(value).toLowerCase())</code>
     */
    @Deprecated
    public static StringBuffer appendBytesAsPaddedHexString(byte[] values, StringBuffer buffer)
    {
        return buffer.append(printHexBinary(values).toLowerCase());
    }

    /**
     * Convert a byte into a padded hexadecimal String.
     * 
     * @param values
     *            the value to convert.
     * @return a padded hexadecimal string.
     * @deprecated use <code>javax.xml.bind.DatatypeConverter.printHexBinary(value).toLowerCase()</code>
     */
    @Deprecated
    public static String convertToPaddedHexString(byte[] values)
    {
        return printHexBinary(values).toLowerCase();
    }

}
