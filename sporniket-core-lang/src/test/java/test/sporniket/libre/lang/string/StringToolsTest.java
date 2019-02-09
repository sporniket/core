package test.sporniket.libre.lang;

import junit.framework.TestCase;

import com.sporniket.libre.lang.string.StringTools;
import com.sporniket.libre.lang.string.StringTools.SpaceRemovingMode;

/**
 * Test suite for {@link StringTools}.
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
 * @version 16.08.02
 * @since 15.09.00
 */
public class TestStringTools extends TestCase
{
	public final void testRemoveHeadingSpaces()
	{
		String _valueToTrim = " a";
		String _valueToExpect = "a";
		String _valueGot = StringTools.removeWhiteSpaces(_valueToTrim, SpaceRemovingMode.LEADING_SPACES);
		if (!_valueToExpect.equals(_valueGot))
		{
			fail("Some whitespaces has not been detected : expected '" + _valueToExpect + "', got '" + _valueGot + "'.");
		}
		_valueToTrim = "  a";
		_valueGot = StringTools.removeWhiteSpaces(_valueToTrim, SpaceRemovingMode.LEADING_SPACES);
		if (!_valueToExpect.equals(_valueGot))
		{
			fail("Some whitespaces has not been detected : expected '" + _valueToExpect + "', got '" + _valueGot + "'.");
		}
		_valueToTrim = "   a";
		_valueGot = StringTools.removeWhiteSpaces(_valueToTrim, SpaceRemovingMode.LEADING_SPACES);
		if (!_valueToExpect.equals(_valueGot))
		{
			fail("Some whitespaces has not been detected : expected '" + _valueToExpect + "', got '" + _valueGot + "'.");
		}
		_valueToExpect = "";
		_valueToTrim = "   ";
		_valueGot = StringTools.removeWhiteSpaces(_valueToTrim, SpaceRemovingMode.LEADING_SPACES);
		if (!_valueToExpect.equals(_valueGot))
		{
			fail("Some whitespaces has not been detected : expected '" + _valueToExpect + "', got '" + _valueGot + "'.");
		}
	}

	public final void testRemoveSpacesOnBothSides()
	{
		String _valueToTrim = " a ";
		String _valueToExpect = "a";
		String _valueGot = StringTools.removeWhiteSpaces(_valueToTrim, SpaceRemovingMode.TWO_ENDS_SPACES);
		if (!_valueToExpect.equals(_valueGot))
		{
			fail("Some whitespaces has not been detected : expected '" + _valueToExpect + "', got '" + _valueGot + "'.");
		}
		_valueToTrim = "  a ";
		_valueGot = StringTools.removeWhiteSpaces(_valueToTrim, SpaceRemovingMode.TWO_ENDS_SPACES);
		if (!_valueToExpect.equals(_valueGot))
		{
			fail("Some whitespaces has not been detected : expected '" + _valueToExpect + "', got '" + _valueGot + "'.");
		}
		_valueToTrim = "   a  ";
		_valueGot = StringTools.removeWhiteSpaces(_valueToTrim, SpaceRemovingMode.TWO_ENDS_SPACES);
		if (!_valueToExpect.equals(_valueGot))
		{
			fail("Some whitespaces has not been detected : expected '" + _valueToExpect + "', got '" + _valueGot + "'.");
		}
		_valueToExpect = "";
		_valueToTrim = "   ";
		_valueGot = StringTools.removeWhiteSpaces(_valueToTrim, SpaceRemovingMode.TWO_ENDS_SPACES);
		if (!_valueToExpect.equals(_valueGot))
		{
			fail("Some whitespaces has not been detected : expected '" + _valueToExpect + "', got '" + _valueGot + "'.");
		}
	}

	public final void testRemoveTrailingSpaces()
	{
		String _valueToTrim = "a ";
		String _valueToExpect = "a";
		String _valueGot = StringTools.removeWhiteSpaces(_valueToTrim, SpaceRemovingMode.TRAILING_SPACES);
		if (!_valueToExpect.equals(_valueGot))
		{
			fail("Some whitespaces has not been detected : expected '" + _valueToExpect + "', got '" + _valueGot + "'.");
		}
		_valueToTrim = "a  ";
		_valueGot = StringTools.removeWhiteSpaces(_valueToTrim, SpaceRemovingMode.TRAILING_SPACES);
		if (!_valueToExpect.equals(_valueGot))
		{
			fail("Some whitespaces has not been detected : expected '" + _valueToExpect + "', got '" + _valueGot + "'.");
		}
		_valueToTrim = "a   ";
		_valueGot = StringTools.removeWhiteSpaces(_valueToTrim, SpaceRemovingMode.TRAILING_SPACES);
		if (!_valueToExpect.equals(_valueGot))
		{
			fail("Some whitespaces has not been detected : expected '" + _valueToExpect + "', got '" + _valueGot + "'.");
		}
		_valueToExpect = "";
		_valueToTrim = "   ";
		_valueGot = StringTools.removeWhiteSpaces(_valueToTrim, SpaceRemovingMode.TRAILING_SPACES);
		if (!_valueToExpect.equals(_valueGot))
		{
			fail("Some whitespaces has not been detected : expected '" + _valueToExpect + "', got '" + _valueGot + "'.");
		}
	}

	public final void testWhiteSpaceCharacterisation()
	{
		String _valueToTrim = " \ta";
		String _valueToExpect = "a";
		if (!_valueToExpect.equals(StringTools.removeWhiteSpaces(_valueToTrim, SpaceRemovingMode.LEADING_SPACES)))
		{
			fail("Some whitespaces has not been detected");
		}
	}

}
