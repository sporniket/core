package com.sporniket.libre.ui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.swing.KeyStroke;

/**
 * Create a KeyEvent or a KeyStroke from a codified String.
 * <p>
 * The purpose of this class is to allow us to store keyboard shortcuts inside a property file, for instance. Then it is easy to
 * localize thoose shortcuts
 * </p>
 * 
 * <p>
 * How to represent a stroke :
 * </p>
 * <ul>
 * <li>KeyEvent : see the table below</li>
 * <li>KeyStroke : "Mask/KeyEvent", Mask is a 3-binary digit representing the state of Control, Shift and Alternate key,
 * respectively</li>
 * </ul>
 * 
 * <table border="1" cellspacing="0">
 * <caption>Keyboard mapping</caption>
 * <tr>
 * <th>Key</th>
 * <th>Code</th>
 * </tr>
 * <tr>
 * <td>0</td>
 * <td>0</td>
 * </tr>
 * <tr>
 * <td>1</td>
 * <td>1</td>
 * </tr>
 * <tr>
 * <td>2</td>
 * <td>2</td>
 * </tr>
 * <tr>
 * <td>3</td>
 * <td>3</td>
 * </tr>
 * <tr>
 * <td>4</td>
 * <td>4</td>
 * </tr>
 * <tr>
 * <td>5</td>
 * <td>5</td>
 * </tr>
 * <tr>
 * <td>6</td>
 * <td>6</td>
 * </tr>
 * <tr>
 * <td>7</td>
 * <td>7</td>
 * </tr>
 * <tr>
 * <td>8</td>
 * <td>8</td>
 * </tr>
 * <tr>
 * <td>9</td>
 * <td>9</td>
 * </tr>
 * <tr>
 * <td>A</td>
 * <td>A</td>
 * </tr>
 * <tr>
 * <td>B</td>
 * <td>B</td>
 * </tr>
 * <tr>
 * <td>C</td>
 * <td>C</td>
 * </tr>
 * <tr>
 * <td>D</td>
 * <td>D</td>
 * </tr>
 * <tr>
 * <td>E</td>
 * <td>E</td>
 * </tr>
 * <tr>
 * <td>F</td>
 * <td>F</td>
 * </tr>
 * <tr>
 * <td>G</td>
 * <td>G</td>
 * </tr>
 * <tr>
 * <td>H</td>
 * <td>H</td>
 * </tr>
 * <tr>
 * <td>I</td>
 * <td>I</td>
 * </tr>
 * <tr>
 * <td>J</td>
 * <td>J</td>
 * </tr>
 * <tr>
 * <td>K</td>
 * <td>K</td>
 * </tr>
 * <tr>
 * <td>L</td>
 * <td>L</td>
 * </tr>
 * <tr>
 * <td>M</td>
 * <td>M</td>
 * </tr>
 * <tr>
 * <td>N</td>
 * <td>N</td>
 * </tr>
 * <tr>
 * <td>O</td>
 * <td>O</td>
 * </tr>
 * <tr>
 * <td>P</td>
 * <td>P</td>
 * </tr>
 * <tr>
 * <td>Q</td>
 * <td>Q</td>
 * </tr>
 * <tr>
 * <td>R</td>
 * <td>R</td>
 * </tr>
 * <tr>
 * <td>S</td>
 * <td>S</td>
 * </tr>
 * <tr>
 * <td>T</td>
 * <td>T</td>
 * </tr>
 * <tr>
 * <td>U</td>
 * <td>U</td>
 * </tr>
 * <tr>
 * <td>V</td>
 * <td>V</td>
 * </tr>
 * <tr>
 * <td>W</td>
 * <td>W</td>
 * </tr>
 * <tr>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td>Y</td>
 * <td>Y</td>
 * </tr>
 * <tr>
 * <td>Z</td>
 * <td>Z</td>
 * </tr>
 * <tr>
 * <td>F1</td>
 * <td>$1</td>
 * </tr>
 * <tr>
 * <td>F2</td>
 * <td>$2</td>
 * </tr>
 * <tr>
 * <td>F3</td>
 * <td>$3</td>
 * </tr>
 * <tr>
 * <td>F4</td>
 * <td>$4</td>
 * </tr>
 * <tr>
 * <td>F5</td>
 * <td>$5</td>
 * </tr>
 * <tr>
 * <td>F6</td>
 * <td>$6</td>
 * </tr>
 * <tr>
 * <td>F7</td>
 * <td>$7</td>
 * </tr>
 * <tr>
 * <td>F8</td>
 * <td>$8</td>
 * </tr>
 * <tr>
 * <td>F9</td>
 * <td>$9</td>
 * </tr>
 * <tr>
 * <td>F10</td>
 * <td>$10</td>
 * </tr>
 * <tr>
 * <td>F11</td>
 * <td>$11</td>
 * </tr>
 * <tr>
 * <td>F12</td>
 * <td>$12</td>
 * </tr>
 * <tr>
 * <td>SPACE</td>
 * <td>$SPACE</td>
 * </tr>
 * <tr>
 * <td>ENTER</td>
 * <td>$ENTER</td>
 * </tr>
 * <tr>
 * <td>ESCAPE</td>
 * <td>$ESCAPE</td>
 * </tr>
 * <tr>
 * <td>BACKSPACE</td>
 * <td>$BACKSPACE</td>
 * </tr>
 * <tr>
 * <td>INSERT</td>
 * <td>$INSERT</td>
 * </tr>
 * <tr>
 * <td>DELETE</td>
 * <td>$DELETE</td>
 * </tr>
 * <tr>
 * <td>HOME</td>
 * <td>$HOME</td>
 * </tr>
 * <tr>
 * <td>END</td>
 * <td>$END</td>
 * </tr>
 * <tr>
 * <td>PAGE UP</td>
 * <td>$PAGEUP</td>
 * </tr>
 * <tr>
 * <td>PAGE DOWN</td>
 * <td>$PAGEDOWN</td>
 * </tr>
 * <tr>
 * <td>TAB</td>
 * <td>$TAB</td>
 * </tr>
 * <tr>
 * <td>CURSOR UP</td>
 * <td>$ARROWUP</td>
 * </tr>
 * <tr>
 * <td>CURSOR DOWN</td>
 * <td>$ARROWDOWN</td>
 * </tr>
 * <tr>
 * <td>CURSOR LEFT</td>
 * <td>$ARROWLEFT</td>
 * </tr>
 * <tr>
 * <td>CURSOR RIGHT</td>
 * <td>$ARROWRIGHT</td>
 * </tr>
 * </table>
 * 
 * <p>
 * You should use the mask "000" only for functions key.
 * 
 * <p>
 * Examples :
 * </p>
 * 
 * <ul>
 * <li><b>F12</b> : <tt>000/$12</tt></li>
 * <li><b>Control+Shift+Backpace</b> : <tt>110/$BACKSPACE</tt></li>
 * </ul>
 * 
 * <p>
 * &copy; Copyright 2002-2016 David Sporn
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
 * @version 16.08.01
 * @since 12.06.01
 */

public class KeyboardRepresentationTable
{
	/**
	 * Return an Integer representing a KeyEvent.
	 * 
	 * @param key
	 *            key code
	 * @return Integer corresponding to the KeyEvent
	 * @throws NoSuchElementException
	 *             if key does not exists
	 * @see KeyEvent
	 */
	public static Integer getKeyEvent(String key) throws NoSuchElementException
	{
		key = key.toUpperCase();
		Integer _result = theKeyTable.get(key);
		if (null == _result)
		{
			throw new NoSuchElementException(key);
		}
		return (_result);
	}

	/**
	 * Return a KeyStroke corresponding to the given code.
	 * 
	 * @param key
	 *            key code
	 * @return KeyStroke corresponding to the key code
	 * @throws NoSuchElementException
	 *             if there is no corresponding KeyStroke
	 * @see KeyEvent
	 */
	public static KeyStroke getKeyStroke(String key) throws NoSuchElementException
	{
		key = key.toUpperCase();
		int _sep = key.indexOf("/");
		String _mask = key.substring(0, _sep);
		String _keyCode = key.substring(_sep + 1);
		Integer _m = theMaskTable.get(_mask);
		Integer _t = theKeyTable.get(_keyCode);
		if (null == _m || null == _t)
		{
			throw new NoSuchElementException(key);
		}
		return (KeyStroke.getKeyStroke(_t.intValue(), _m.intValue()));
	}

	// ============================================================
	// Implementation details

	// ** Storage of the KeyEvent codes.
	private static Map<String, Integer> theKeyTable = new HashMap<String, Integer>(62);

	// ** Storage of the Mask codes.
	private static Map<String, Integer> theMaskTable = new HashMap<String, Integer>(8);

	static
	{
		theKeyTable.put("0", new Integer(KeyEvent.VK_0));
		theKeyTable.put("1", new Integer(KeyEvent.VK_1));
		theKeyTable.put("2", new Integer(KeyEvent.VK_2));
		theKeyTable.put("3", new Integer(KeyEvent.VK_3));
		theKeyTable.put("4", new Integer(KeyEvent.VK_4));
		theKeyTable.put("5", new Integer(KeyEvent.VK_5));
		theKeyTable.put("6", new Integer(KeyEvent.VK_6));
		theKeyTable.put("7", new Integer(KeyEvent.VK_7));
		theKeyTable.put("8", new Integer(KeyEvent.VK_8));
		theKeyTable.put("9", new Integer(KeyEvent.VK_9));
		theKeyTable.put("A", new Integer(KeyEvent.VK_A));
		theKeyTable.put("B", new Integer(KeyEvent.VK_B));
		theKeyTable.put("C", new Integer(KeyEvent.VK_C));
		theKeyTable.put("D", new Integer(KeyEvent.VK_D));
		theKeyTable.put("E", new Integer(KeyEvent.VK_E));
		theKeyTable.put("F", new Integer(KeyEvent.VK_F));
		theKeyTable.put("G", new Integer(KeyEvent.VK_G));
		theKeyTable.put("H", new Integer(KeyEvent.VK_H));
		theKeyTable.put("I", new Integer(KeyEvent.VK_I));
		theKeyTable.put("J", new Integer(KeyEvent.VK_J));
		theKeyTable.put("K", new Integer(KeyEvent.VK_K));
		theKeyTable.put("L", new Integer(KeyEvent.VK_L));
		theKeyTable.put("M", new Integer(KeyEvent.VK_M));
		theKeyTable.put("N", new Integer(KeyEvent.VK_N));
		theKeyTable.put("O", new Integer(KeyEvent.VK_O));
		theKeyTable.put("P", new Integer(KeyEvent.VK_P));
		theKeyTable.put("Q", new Integer(KeyEvent.VK_Q));
		theKeyTable.put("R", new Integer(KeyEvent.VK_R));
		theKeyTable.put("S", new Integer(KeyEvent.VK_S));
		theKeyTable.put("T", new Integer(KeyEvent.VK_T));
		theKeyTable.put("U", new Integer(KeyEvent.VK_U));
		theKeyTable.put("V", new Integer(KeyEvent.VK_V));
		theKeyTable.put("W", new Integer(KeyEvent.VK_W));
		theKeyTable.put("X", new Integer(KeyEvent.VK_X));
		theKeyTable.put("Y", new Integer(KeyEvent.VK_Y));
		theKeyTable.put("Z", new Integer(KeyEvent.VK_Z));
		theKeyTable.put("$1", new Integer(KeyEvent.VK_F1));
		theKeyTable.put("$2", new Integer(KeyEvent.VK_F2));
		theKeyTable.put("$3", new Integer(KeyEvent.VK_F3));
		theKeyTable.put("$4", new Integer(KeyEvent.VK_F4));
		theKeyTable.put("$5", new Integer(KeyEvent.VK_F5));
		theKeyTable.put("$6", new Integer(KeyEvent.VK_F6));
		theKeyTable.put("$7", new Integer(KeyEvent.VK_F7));
		theKeyTable.put("$8", new Integer(KeyEvent.VK_F8));
		theKeyTable.put("$9", new Integer(KeyEvent.VK_F9));
		theKeyTable.put("$10", new Integer(KeyEvent.VK_F10));
		theKeyTable.put("$11", new Integer(KeyEvent.VK_F11));
		theKeyTable.put("$12", new Integer(KeyEvent.VK_F12));
		theKeyTable.put("$SPACE", new Integer(KeyEvent.VK_SPACE));
		theKeyTable.put("$ENTER", new Integer(KeyEvent.VK_ENTER));
		theKeyTable.put("$ESCAPE", new Integer(KeyEvent.VK_ESCAPE));
		theKeyTable.put("$BACKSPACE", new Integer(KeyEvent.VK_BACK_SPACE));
		theKeyTable.put("$INSERT", new Integer(KeyEvent.VK_INSERT));
		theKeyTable.put("$DELETE", new Integer(KeyEvent.VK_DELETE));
		theKeyTable.put("$HOME", new Integer(KeyEvent.VK_HOME));
		theKeyTable.put("$END", new Integer(KeyEvent.VK_END));
		theKeyTable.put("$PAGEUP", new Integer(KeyEvent.VK_PAGE_UP));
		theKeyTable.put("$PAGEDOWN", new Integer(KeyEvent.VK_PAGE_DOWN));
		theKeyTable.put("$TAB", new Integer(KeyEvent.VK_TAB));
		theKeyTable.put("$ARROWUP", new Integer(KeyEvent.VK_UP));
		theKeyTable.put("$ARROWLEFT", new Integer(KeyEvent.VK_LEFT));
		theKeyTable.put("$ARROWDOWN", new Integer(KeyEvent.VK_DOWN));
		theKeyTable.put("$ARROWRIGHT", new Integer(KeyEvent.VK_RIGHT));

		theMaskTable.put("000", new Integer(0));
		theMaskTable.put("001", new Integer(ActionEvent.ALT_MASK));
		theMaskTable.put("010", new Integer(ActionEvent.SHIFT_MASK));
		theMaskTable.put("100", new Integer(ActionEvent.CTRL_MASK));
		theMaskTable.put("011", new Integer(ActionEvent.SHIFT_MASK | ActionEvent.ALT_MASK));
		theMaskTable.put("101", new Integer(ActionEvent.CTRL_MASK | ActionEvent.ALT_MASK));
		theMaskTable.put("110", new Integer(ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
		theMaskTable.put("111", new Integer(ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK | ActionEvent.ALT_MASK));
	}

}