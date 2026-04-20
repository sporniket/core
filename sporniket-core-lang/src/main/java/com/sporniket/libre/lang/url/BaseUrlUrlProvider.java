package com.sporniket.libre.lang.url;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Implementation that prefixes the path with a base URL.
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
public class BaseUrlUrlProvider implements UrlProvider
{

    private URI myBaseUrl = null;

    /**
     * @param baseUrl
     *            base url.
     * @throws UrlProviderException
     */
    public BaseUrlUrlProvider(final URI baseUrl) throws UrlProviderException
    {
        myBaseUrl = baseUrl;
    }

    /**
     * @param baseUrl
     *            base url.
     * @throws UrlProviderException
     */
    public BaseUrlUrlProvider(final URL baseUrl) throws UrlProviderException
    {
        try
        {
            myBaseUrl = (baseUrl != null) ? baseUrl.toURI() : null;
        }
        catch (final URISyntaxException _exception)
        {
            throw new UrlProviderException(_exception);
        }
    }

    /**
     * Read the baseUrl property.
     *
     * @return the baseUrl
     * @throws UrlProviderException
     */
    public URL getBaseUrl() throws UrlProviderException
    {
        try
        {
            return myBaseUrl.toURL();
        }
        catch (final MalformedURLException _exception)
        {
            throw new UrlProviderException(_exception);
        }
    }

    @Override
    public URL getUrl(final String path) throws UrlProviderException
    {
        try
        {
            return (myBaseUrl != null) ? myBaseUrl.resolve(path).toURL() : new URI(path).toURL();
        }
        catch (final MalformedURLException | URISyntaxException _exception)
        {
            throw new UrlProviderException("invalid.url;" + myBaseUrl.toASCIIString() + ";" + path, _exception);
        }
    }

    /**
     * Write the baseUrl property.
     *
     * @param baseUrl
     *            the baseUrl to set
     * @throws UrlProviderException
     */
    public void setBaseUrl(final String baseUrl) throws UrlProviderException
    {
        try
        {
            myBaseUrl = new URI(baseUrl);
        }
        catch (final URISyntaxException _exception)
        {
            throw new UrlProviderException(_exception);
        }
    }

    public void setBaseUrl(final URI baseUrl)
    {
        myBaseUrl = baseUrl;
    }

    /**
     * Write the baseUrl property.
     *
     * @param baseUrl
     *            the baseUrl to set
     * @throws UrlProviderException
     */
    public void setBaseUrl(final URL baseUrl) throws UrlProviderException
    {
        try
        {
            myBaseUrl = (baseUrl != null) ? baseUrl.toURI() : null;
        }
        catch (final URISyntaxException _exception)
        {
            throw new UrlProviderException(_exception);
        }
    }

}
