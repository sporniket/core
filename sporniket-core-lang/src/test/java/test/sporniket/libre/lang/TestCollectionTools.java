package test.sporniket.libre.lang;

import java.util.ResourceBundle;

import com.sporniket.libre.lang.CollectionTools;

import junit.framework.TestCase;

/**
 * Test suite for {@link CollectionTools}.
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
 * @version 16.08.00
 * @since 12.06.01
 */
public class TestCollectionTools extends TestCase
{
	private static final String VALUE__BUNDLE_TEST = "Property 1";

	private static final String KEY__BUNDLE_TEST = "prop1";

	private static final String KEY__BUNDLE_TEST__NOT_EXISTING = "foo";

	private static final String VALUE__WHEN_NOT_FOUND = "NULL";

	private static final String RESOURCE_BUNDLE__NAME = "com.sporniket.test.lang.TestCollectionTools";

	private ResourceBundle myTestBundle;

	@Override
	protected void setUp() throws Exception
	{
		super.setUp();

		myTestBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE__NAME);
	}

	public final void testGetStringForExistingValue()
	{
		String _value = CollectionTools.getString(myTestBundle, KEY__BUNDLE_TEST, VALUE__WHEN_NOT_FOUND);
		if (!VALUE__BUNDLE_TEST.equals(_value))
		{
			fail("Value does not match for key [" + KEY__BUNDLE_TEST + "] ! [" + _value + "] instead of [" + VALUE__BUNDLE_TEST
					+ "]. Check also the test properties file.");
		}
	}

	public final void testGetStringForNonExistingValue()
	{
		String _value = CollectionTools.getString(myTestBundle, KEY__BUNDLE_TEST__NOT_EXISTING, VALUE__WHEN_NOT_FOUND);
		if (!VALUE__WHEN_NOT_FOUND.equals(_value))
		{
			fail("Not default value for key [" + KEY__BUNDLE_TEST__NOT_EXISTING + "] ! [" + _value + "] instead of ["
					+ VALUE__WHEN_NOT_FOUND + "]. Check also the test properties file.");
		}
	}

}
