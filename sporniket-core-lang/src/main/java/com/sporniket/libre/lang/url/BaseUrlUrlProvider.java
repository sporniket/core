package com.sporniket.libre.lang.url;

import java.net.MalformedURLException;
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
 * @version 22.09.01
 * @since 12.06.01
 */
public class BaseUrlUrlProvider implements UrlProvider
{

    private URL myBaseUrl = null;

    /**
     * @param baseUrl
     *            base url.
     */
    public BaseUrlUrlProvider(URL baseUrl)
    {
        myBaseUrl = baseUrl;
    }

    /**
     * Read the baseUrl property.
     * 
     * @return the baseUrl
     */
    public URL getBaseUrl()
    {
        return myBaseUrl;
    }

    public URL getUrl(String path) throws UrlProviderException
    {
        try
        {
            return new URL(myBaseUrl, path);
        }
        catch (MalformedURLException _exception)
        {
            throw new UrlProviderException("Error getting url [" + path + "] from url base [" + myBaseUrl.toString() + "]",
                    _exception);
        }
    }

    /**
     * Write the baseUrl property.
     * 
     * @param baseUrl
     *            the baseUrl to set
     */
    public void setBaseUrl(URL baseUrl)
    {
        myBaseUrl = baseUrl;
    }

    /**
     * Write the baseUrl property.
     * 
     * @param baseUrl
     *            the baseUrl to set
     * @throws MalformedURLException
     *             if the url is malformed.
     */
    public void setBaseUrl(String baseUrl) throws MalformedURLException
    {
        myBaseUrl = new URL(baseUrl);
    }

}
