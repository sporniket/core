package test.sporniket.libre.lang;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.sporniket.libre.lang.SystemProperties;

/**
 * Test suite for {@link SystemProperties}.
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
 *
 * @version 26.01.00
 * @since 26.01.00
 */
public class TestSystemProperties
{
    @Nested
    class Private
    {
        @Test
        void should_return_user_home()
        {
            then(SystemProperties.Private.getUserHome()).isNotEmpty();
        }
    }

    @Nested
    class Protected
    {
        @Test
        void should_not_return_user_agent()
        {
            then(SystemProperties.Protected.getUserAgent()).isNull();
        }

        @Test
        void should_return_tmp_dir()
        {
            then(SystemProperties.Protected.getTempDirectory()).isNotEmpty();
        }
    }

    @Nested
    class Public
    {
        @Test
        void should_return_line_separator()
        {
            then(SystemProperties.getLineSeparator()).isNotEmpty();
        }
    }
}
