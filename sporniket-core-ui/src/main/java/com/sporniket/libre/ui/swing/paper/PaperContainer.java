package com.sporniket.libre.ui.swing.paper;

/**
 * 
 * <p>
 * &copy; Copyright 2002-2016 David Sporn
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
 * @version 16.08.00
 * @since 12.06.01
 */
public interface PaperContainer
{

	/**
	 * Read the sheetOfPaper property.
	 * 
	 * @return the sheetOfPaper
	 */
	public SheetOfPaper getSheetOfPaper();

	public double convertMillimeterToUnit(double value);

	public double convertUnitToMillimeter(double value);

	/**
	 * Read the content property.
	 * 
	 * @return the content
	 */
	public PaperContent getContent();

	/**
	 * Write the content property.
	 * 
	 * @param content
	 *            the content to set
	 */
	public void setContent(PaperContent content);

}