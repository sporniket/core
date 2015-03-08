/**
 * 
 */
package test.sporniket.libre.ui;

import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

import junit.framework.TestCase;

import com.sporniket.libre.ui.KeyboardRepresentationTable;

/**
 * Test suite for {@link KeyboardRepresentationTable}.
 * 
 * <p>
 * &copy; Copyright 2002-2015 David Sporn
 * </p>
 * <hr>
 * 
 * <p>
 * This file is part of <i>The Sporniket Core Library &#8211; ui</i>.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; ui</i> is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; ui</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for
 * more details.
 * 
 * <p>
 * You should have received a copy of the GNU Lesser General Public License along with <i>The Sporniket Core Library &#8211; ui</i>.
 * If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
 * 
 * <hr>
 * 
 * @author David SPORN 
 * @version 15.02.00
 */
public class TestKeyboardRepresentationTable extends TestCase
{
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
	}

	/**
	 * Test the KeyEvent representations for letters and digits.
	 */
	public final void testAlphaNumKeyEventValues()
	{
		String _valuesToTest = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int _i = 0; _i < _valuesToTest.length(); _i++)
		{
			String _value = _valuesToTest.substring(_i, _i + 1);
			doTestKeyEvent(_value, "", "");
		}
	}

	/**
	 * Test the KeyEvent representations for function keys.
	 */
	public final void testFunctionKeysKeyEventValues()
	{
		for (int _i = 1; _i <= 12; _i++)
		{
			String _value = Integer.toString(_i);
			doTestKeyEvent(_value, "$", "F");
		}
	}

	/**
	 * Test the KeyEvent representations for special keys.
	 */
	public final void testSpecialKeysKeyEventValues()
	{
		String[] _tests =
		{
				"$SPACE;SPACE",
				"$ENTER;ENTER",
				"$ESCAPE;ESCAPE",
				"$BACKSPACE;BACK_SPACE",
				"$INSERT;INSERT",
				"$DELETE;DELETE",
				"$HOME;HOME",
				"$END;END",
				"$PAGEUP;PAGE_UP",
				"$PAGEDOWN;PAGE_DOWN",
				"$TAB;TAB",
				"$ARROWUP;UP",
				"$ARROWLEFT;LEFT",
				"$ARROWDOWN;DOWN",
				"$ARROWRIGHT;RIGHT"
		};
		for (String _test : _tests)
		{
			String[] _values = _test.split(";");
			doTestKeyEventRepresentationAgainstReference(_values[0], _values[1]);
		}
	}

	/**
	 * Test that the specified representation is the same as the value from KeyEvent.
	 * 
	 * @param value
	 *            value to test
	 * @param prefixRepresentation
	 *            prefix to add to <code>value</code> to obtain the name to use with
	 *            {@link KeyboardRepresentationTable#getKeyEvent(String)}.
	 * @param prefixReflection
	 *            prefix to add to <code>value</code> to obtain the name of the constant in {@link KeyEvent} without
	 *            <code>VK_</code>.
	 */
	private void doTestKeyEvent(String value, String prefixRepresentation, String prefixReflection)
	{
		String _representationName = prefixRepresentation + value;
		String _referenceName = prefixReflection + value;
		doTestKeyEventRepresentationAgainstReference(_representationName, _referenceName);
	}

	/**
	 * Test that the specified representation is the same as the value from KeyEvent.
	 * 
	 * @param representationName
	 *            value to use with {@link KeyboardRepresentationTable#getKeyEvent(String)}.
	 * @param referenceName
	 *            name of the constant in {@link KeyEvent} without <code>VK_</code>.
	 */
	private void doTestKeyEventRepresentationAgainstReference(String representationName, String referenceName)
	{
		try
		{
			int _keyToTest = KeyboardRepresentationTable.getKeyEvent(representationName);
			int _keyReference = retrieveKeyEventConstant(referenceName);
			if (_keyToTest != _keyReference)
			{
				fail("KeyEvent values for '" + representationName + "' do not match... expected:" + _keyReference + " "
						+ _keyToTest);
			}
		}
		catch (Exception _exception)
		{
			fail(_exception.getLocalizedMessage());
		}
	}

	/**
	 * Retrieve the value KeyEvent.VK_[name] value.
	 * 
	 * @param name
	 *            value to retrieve
	 * @return the matching KeyEvent value.
	 * @throws NoSuchFieldException if there is a problem to deal with.
	 * @throws SecurityException if there is a problem to deal with.
	 * @throws IllegalAccessException if there is a problem to deal with.
	 * @throws IllegalArgumentException if there is a problem to deal with.
	 */
	public final int retrieveKeyEventConstant(String name) throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException
	{
		String _fieldName = "VK_" + name;
		Field _field = KeyEvent.class.getField(_fieldName);

		return _field.getInt(null);
	}
}
