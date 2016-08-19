/**
 * 
 */
package com.sporniket.libre.ui.icon;

import java.net.URL;

import javax.swing.ImageIcon;

import com.sporniket.libre.lang.url.UrlProvider;
import com.sporniket.libre.lang.url.UrlProviderException;

/**
 * @author David SPORN 
 *
 * @version 16.08.01
 * @since 15.02.00
 */
public class IconProviderFromUrlProvider implements IconProvider<String>
{
	private final UrlProvider myUrlProvider ;
	
	private final IconProvider<URL> myIconProvider = new IconProviderFromUrl() ;
	
	

	/**
	 * Get iconProvider.
	 * @return the iconProvider.
	 */
	private IconProvider<URL> getIconProvider()
	{
		return myIconProvider;
	}



	/**
	 * Get urlProvider.
	 * @return the urlProvider.
	 */
	private UrlProvider getUrlProvider()
	{
		return myUrlProvider;
	}



	/**
	 * @param urlProvider the url provider.
	 */
	public IconProviderFromUrlProvider(UrlProvider urlProvider)
	{
		myUrlProvider = urlProvider;
	}



	/* (non-Javadoc)
	 * @see com.sporniket.libre.ui.swing.IconProvider#retrieveIcon(java.lang.Object)
	 */
	public ImageIcon retrieveIcon(String location) throws IconProviderException
	{
		try
		{
			URL _url = getUrlProvider().getUrl(location);
			return getIconProvider().retrieveIcon(_url);
		}
		catch (UrlProviderException _exception)
		{
			throw new IconProviderException(_exception);
		}
	}

}
