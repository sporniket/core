package test.sporniket.libre.lang;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.Map;
import java.util.ResourceBundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.sporniket.libre.lang.ResourceBundleTools;

/**
 * Test suite for {@link ResourceBundleTools}.
 *
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
 * GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * <p>
 * <i>The Sporniket Core Library &#8211; lang</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * <p>
 * You should have received a copy of the GNU Affero General Public License along with <i>The Sporniket Core Library &#8211;
 * lang</i>. If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
 *
 * <hr>
 *
 * @author David SPORN
 * @version 22.11.00
 * @since 12.06.01
 */
public class TestResourceBundleTools
{

	private static final String VALUE__BUNDLE_TEST = "Property 1";

	private static final String KEY__BUNDLE_TEST = "prop1";

	private static final String KEY__BUNDLE_TEST__NOT_EXISTING = "foo";

	private static final String VALUE__WHEN_NOT_FOUND = "NULL";

	private static final String RESOURCE_BUNDLE__NAME = "TestCollectionTools";

	private ResourceBundle myTestBundle;

	private final Map<String, Object> myTestMap = Map.of("1", (Integer) 1, "2", (Integer) 2);

	@BeforeEach
	public void setup()
	{
		myTestBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE__NAME);
	}

	@Nested
	class GetString
	{
		@Test
		public final void should_return_existing_values()
		{
			then(ResourceBundleTools.getString(myTestBundle, KEY__BUNDLE_TEST, VALUE__WHEN_NOT_FOUND)) //
					.isEqualTo(VALUE__BUNDLE_TEST);
		}

		@Test
		public final void should_return_default_value_when_requested_value_does_not_exists()
		{
			then(ResourceBundleTools.getString(myTestBundle, KEY__BUNDLE_TEST__NOT_EXISTING, VALUE__WHEN_NOT_FOUND)) //
					.isEqualTo(VALUE__WHEN_NOT_FOUND);
		}
	}

	@Nested
	class GetObject
	{
		@Test
		public final void should_return_existing_values()
		{
			then(ResourceBundleTools.getObject(myTestBundle, KEY__BUNDLE_TEST, VALUE__WHEN_NOT_FOUND)) //
					.isEqualTo(VALUE__BUNDLE_TEST);
		}

		@Test
		public final void should_return_default_value_when_requested_value_does_not_exists()
		{
			then(ResourceBundleTools.getObject(myTestBundle, KEY__BUNDLE_TEST__NOT_EXISTING, VALUE__WHEN_NOT_FOUND)) //
					.isEqualTo(VALUE__WHEN_NOT_FOUND);
		}
	}
}
