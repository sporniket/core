package test.sporniket.libre.lang;

import static com.sporniket.libre.lang.MapTools.asMap;
import static com.sporniket.libre.lang.MapTools.filterByKeys;
import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.sporniket.libre.lang.MapTools;

/**
 * Test suite for {@link MapTools}.
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
 * @version 22.11.00
 * @since 22.09.01
 */
public class TestMapTools
{
	@Nested
	class Apply
	{
		@Test
		public void should_change_existing_keys_only()
		{
			then(MapTools.apply(asMap("a:item a", "b:item b"), asMap("a:change a", "c:change c")))//
					.hasSize(2)//
					.containsEntry("a", "change a")//
					.containsEntry("b", "item b");
		}
	}

	@Nested
	class AsMap
	{
		@Test
		public void should_create_map_using_default_separator()
		{
			then(asMap("a:item a", "b:item b"))//
					.hasSize(2)//
					.containsEntry("a", "item a")//
					.containsEntry("b", "item b");
		}

		@Test
		public void should_create_map_using_specified_separator()
		{
			then(asMap('|', "a|item a", "b|item b"))//
					.hasSize(2)//
					.containsEntry("a", "item a")//
					.containsEntry("b", "item b");
		}
	}

	@Nested
	class FilterByKeys
	{
		@Test
		public void should_return_map_with_filtered_keys_only()
		{
			then(filterByKeys(asMap("a:item a", "b:item b"), "a", "c"))//
					.hasSize(1)//
					.containsEntry("a", "item a");
		}
	}
}
