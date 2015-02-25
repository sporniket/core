/**
 * 
 */
package com.sporniket.libre.ui.icon;

import javax.swing.ImageIcon;

/**
 * @author David SPORN <david.sporn@sporniket.com>
 *
 * @version 15.02.00
 * @since 15.02.00
 */
public interface IconProvider<SourceType>
{
	ImageIcon retrieveIcon(SourceType location) throws IconProviderException;
}
