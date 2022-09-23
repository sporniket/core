package com.sporniket.libre.lang.url;

import java.net.URL;

/**
 * Url provider that use a ClassLoader.
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
public class ClassLoaderUrlProvider implements UrlProvider
{
    private ClassLoader myClassLoader = null;

    /**
     * @param classInstance
     *            class used to get the class loader to use.
     */
    public ClassLoaderUrlProvider(Class<?> classInstance)
    {
        myClassLoader = classInstance.getClassLoader();
    }

    /**
     * @param classLoader
     *            class loader to use.
     */
    public ClassLoaderUrlProvider(ClassLoader classLoader)
    {
        myClassLoader = classLoader;
    }

    /**
     * @param instance
     *            instance of an object to get the class loader to use.
     */
    public ClassLoaderUrlProvider(Object instance)
    {
        myClassLoader = instance.getClass().getClassLoader();
    }

    /**
     * Read the classLoader property.
     * 
     * @return the classLoader
     */
    public ClassLoader getClassLoader()
    {
        return myClassLoader;
    }

    public URL getUrl(String path) throws UrlProviderException
    {
        return myClassLoader.getResource(path);
    }

    /**
     * Write the classLoader property.
     * 
     * @param classLoader
     *            the classLoader to set
     */
    public void setClassLoader(ClassLoader classLoader)
    {
        myClassLoader = classLoader;
    }

}
