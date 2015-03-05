/**
 * 
 */
package com.sporniket.libre.ui.icon;

/**
 * Exception thrown when an Icon provider has a problem.
 * @author David SPORN 
 *
 * @version 15.02.00
 * @since 15.02.00
 */
public class IconProviderException extends Exception
{
	/**
	 * Serialization ID
	 * @since 15.03.00
	 */
	private static final long serialVersionUID = -3556988726228258984L;

	public IconProviderException()
	{
		super();
	}

	public IconProviderException(String message)
	{
		super(message);
	}

	public IconProviderException(Throwable cause)
	{
		super(cause);
	}

	public IconProviderException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
