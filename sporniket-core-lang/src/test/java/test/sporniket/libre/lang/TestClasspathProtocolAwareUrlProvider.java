/**
 * 
 */
package test.sporniket.libre.lang;

import java.net.URL;
import java.util.Map;
import java.util.Properties;

import com.sporniket.libre.lang.url.ClasspathProtocolAwareUrlProvider;
import com.sporniket.libre.lang.url.UrlProvider;
import com.sporniket.libre.lang.url.UrlProviderException;

import junit.framework.TestCase;

/**
 * Test suite for {@link ClasspathProtocolAwareUrlProvider}
 * 
 * <p>
 * &copy; Copyright 2002-2019 David Sporn
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
 * @version 19.04.00
 * @since 16.08.02
 */
public class TestClasspathProtocolAwareUrlProvider extends TestCase
{
	public final void testClasspathUrl() throws UrlProviderException
	{
		UrlProvider _provider = new ClasspathProtocolAwareUrlProvider();
		URL _file = _provider.getUrl("classpath:com/sporniket/test/lang/TestCollectionTools.properties");
		// should not throw any exception.
	}

	public final void testStandardUrl() throws UrlProviderException
	{
		UrlProvider _provider = new ClasspathProtocolAwareUrlProvider();
		URL _file = _provider.getUrl("http://www.google.com");
		// should not throw any exception.
	}

	public final void testUnknownProtocolUrl()
	{
		UrlProvider _provider = new ClasspathProtocolAwareUrlProvider();
		try
		{
			URL _file = _provider.getUrl("foo://www.google.com");
			fail();
		}
		catch (UrlProviderException _exception)
		{
			// expected
		}
	}
}
