/**
 *
 */
package test.sporniket.libre.lang.url;

import static org.assertj.core.api.BDDAssertions.then;

import java.net.URL;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

import com.sporniket.libre.lang.url.ClasspathProtocolAwareUrlProvider;
import com.sporniket.libre.lang.url.UrlProvider;
import com.sporniket.libre.lang.url.UrlProviderException;

/**
 * Test suite for {@link ClasspathProtocolAwareUrlProvider}
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
 * @since 16.08.02
 */
public class TestClasspathProtocolAwareUrlProvider
{

    @Test
    public final void testClasspathUrl() throws UrlProviderException
    {
        final UrlProvider _provider = new ClasspathProtocolAwareUrlProvider();
        final URL _url = _provider.getUrl("classpath:com/sporniket/test/lang/TestCollectionTools.properties");
        then(_url).isNotNull();
    }

    @Test
    public final void testStandardUrl() throws UrlProviderException
    {
        final UrlProvider _provider = new ClasspathProtocolAwareUrlProvider();
        then(_provider.getUrl("http://www.google.com")).isNotNull();
    }

    @Test
    public final void testUnknownProtocolUrl()
    {
        final UrlProvider _provider = new ClasspathProtocolAwareUrlProvider();
        BDDAssertions.thenThrownBy(() -> _provider.getUrl("foo://www.google.com")).isInstanceOf(UrlProviderException.class);
    }
}
