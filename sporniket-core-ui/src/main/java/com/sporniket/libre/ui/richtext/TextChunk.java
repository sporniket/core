package com.sporniket.libre.ui.richtext;

/**
 * 
 * <p>
 * &copy; Copyright 2002-2022 David Sporn
 * </p>
 * <hr>
 * 
 * <p>
 * This file is part of <i>The Sporniket Core Library &#8211; ui</i>.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; ui</i> is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; ui</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for
 * more details.
 * 
 * <p>
 * You should have received a copy of the GNU Lesser General Public License along with <i>The Sporniket Core Library &#8211; ui</i>.
 * If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
 * 
 * <hr>
 * 
 * @author David SPORN 
 * @version 22.11.00
 * @since 12.06.01
 */
public class TextChunk implements TextContainer
{
	private static final String DEFAULT_TEXT = "";

	private String myText = DEFAULT_TEXT;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.richtext.TextContainer#getText()
	 */
	public String getText()
	{
		return myText;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.richtext.TextContainer#setText(java.lang.String)
	 */
	public void setText(String text)
	{
		myText = (null == text) ? DEFAULT_TEXT : text;
	}
}
