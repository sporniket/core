/**
 * 
 */
package com.sporniket.libre.ui.icon;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

/**
 * Icon provider using the classloader.
 * @author David SPORN 
 *
 * @version 16.08.02
 * @since 15.02.00
 */
public class IconProviderFromUrl implements IconProvider<URL>
{
	private final Map<URL, ImageIcon> myCache = new HashMap<URL, ImageIcon>() ;
	
	/**
	 * @return
	 */
	private Map<URL, ImageIcon> getCache()
	{
		return myCache;
	}

	/* (non-Javadoc)
	 * @see com.sporniket.libre.ui.swing.IconProvider#retrieveIcon(java.lang.Object)
	 */
	public ImageIcon retrieveIcon(URL location) throws IconProviderException
	{
		if (!getCache().containsKey(location))
		{
			getCache().put(location, new ImageIcon(location));
		}
		return getCache().get(location);
	}

}
