package com.sporniket.libre.ui.swing.paper;

import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * 
 * <p>
 * &copy; Copyright 2002-2012 David Sporn
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
 * @version 15.02.00
 * @since 12.06.01
 */
public interface PaperContent
{
	// Rectangle of the full content
	// Left position in mm
	double getFullX();

	// Top position in mm
	double getFullY();

	// Width in mm
	double getFullWidth();

	// Height in mm
	double getFullHeight();

	// Rectangle of the useful zone (most of the time,
	// it will be the same as the complete content)
	// Left position in mm
	void setCoreX(double x);

	double getCoreX();

	// Top position in mm
	void setCoreY(double y);

	double getCoreY();

	// Width in mm
	void setCoreWidth(double w);

	double getCoreWidth();

	// Height in mm
	void setCoreHeight(double h);

	double getCoreHeight();

	// Physical bounds (in drawing unit)
	/**
	 * Physical bounds (in drawing unit). It changes with the PaperPane to which it is attached.
	 * 
	 * @return the physical bounds as a rectangle.
	 * @throws PaperContentIsUnattachedException if there is a problem to deal with.
	 */
	Rectangle getFullBounds() throws PaperContentIsUnattachedException;

	/**
	 * Physical bounds of the usefull zone (in drawing unit). It changes with the PaperPane to which it is attached.
	 * 
	 * @return the physical bounds as a rectangle.
	 * @throws PaperContentIsUnattachedException if there is a problem to deal with.
	 */
	Rectangle getCoreBounds() throws PaperContentIsUnattachedException;

	PaperContainer getContainer();

	/**
	 * Call this method when there has been a modification of the environnement, like the PaperPane. It is necessary to get a
	 * correct value for getFullBounds() and getCoreBounds ;
	 */
	void updateInternals();

	void attachTo(PaperContainer container);

	void unattach();

	void paint(Graphics2D g);
}
