/**
 * 
 */
package test.sporniket.libre.lang ;

import static org.junit.jupiter.api.Assertions.fail ;

import org.junit.jupiter.api.Test ;

import com.sporniket.libre.lang.url.ClasspathProtocolAwareUrlProvider ;
import com.sporniket.libre.lang.url.UrlProvider ;
import com.sporniket.libre.lang.url.UrlProviderException ;

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
 * <i>The Sporniket Core Library &#8211; lang</i> is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; lang</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 * 
 * <p>
 * You should have received a copy of the GNU Lesser General Public License along with <i>The Sporniket Core Library &#8211; lang</i>. If not, see
 * <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
 * 
 * <hr>
 * 
 * @author David SPORN
 * @version 22.09.00
 * @since 16.08.02
 */
public class TestClasspathProtocolAwareUrlProvider {

    @Test
    public final void testClasspathUrl() throws UrlProviderException {
        UrlProvider _provider = new ClasspathProtocolAwareUrlProvider() ;
        _provider.getUrl("classpath:com/sporniket/test/lang/TestCollectionTools.properties") ;
        // should not throw any exception.
    }

    @Test
    public final void testStandardUrl() throws UrlProviderException {
        UrlProvider _provider = new ClasspathProtocolAwareUrlProvider() ;
        _provider.getUrl("http://www.google.com") ;
        // should not throw any exception.
    }

    @Test
    public final void testUnknownProtocolUrl() {
        UrlProvider _provider = new ClasspathProtocolAwareUrlProvider() ;
        try {
            _provider.getUrl("foo://www.google.com") ;
            fail() ;
        } catch (UrlProviderException _exception) {
            // expected
        }
    }
}
