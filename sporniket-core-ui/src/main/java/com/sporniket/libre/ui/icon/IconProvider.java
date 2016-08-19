/**
 * 
 */
package com.sporniket.libre.ui.icon;

import javax.swing.ImageIcon;

/**
 * @author David SPORN 
 *
 * @version 16.08.01
 * @since 15.02.00
 */
public interface IconProvider<SourceType>
{
	ImageIcon retrieveIcon(SourceType location) throws IconProviderException;
}
