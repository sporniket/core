package com.sporniket.libre.ui.swing.paper;

import java.awt.Rectangle;

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
 * @version 16.08.02
 * @since 12.06.01
 */
public abstract class PaperContentAdapter implements PaperContent
{
	private double myCoreX;

	private double myCoreY;

	private double myCoreWidth;

	private double myCoreHeight;

	private PaperContainer myContainer;

	private Rectangle myCoreBounds = new Rectangle();

	/**
	 * Read the height property.
	 * 
	 * @return the height
	 */
	public double getFullHeight()
	{
		return getCoreHeight();
	}

	/**
	 * Read the leftPosition property.
	 * 
	 * @return the leftPosition
	 */
	public double getFullX()
	{
		return getCoreX();
	}

	/**
	 * Read the topPosition property.
	 * 
	 * @return the topPosition
	 */
	public double getFullY()
	{
		return getCoreY();
	}

	/**
	 * Read the width property.
	 * 
	 * @return the width
	 */
	public double getFullWidth()
	{
		return getCoreWidth();
	}

	/**
	 * Read the container property.
	 * 
	 * @return the container
	 */
	public PaperContainer getContainer()
	{
		return myContainer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.swing.paper.PaperContent#getPhysicalBounds()
	 */
	public Rectangle getFullBounds() throws PaperContentIsUnattachedException
	{
		return getCoreBounds();
	}

	public void attachTo(PaperContainer container)
	{
		if (null == container)
		{
			throw new NullPointerException("Cannot attach to a null container");
		}
		myContainer = container;
		updateInternals();
	}

	public void unattach()
	{
		myContainer = null;
	}

	public void updateInternals()
	{
		if (null != getContainer())
		{
			myCoreBounds.setBounds((int) getContainer().convertMillimeterToUnit(getCoreX()), (int) getContainer()
					.convertMillimeterToUnit(getCoreY()), (int) getContainer().convertMillimeterToUnit(getCoreWidth()),
					(int) getContainer().convertMillimeterToUnit(getCoreHeight()));
		}
	}

	// Rectangle of the useful zone (most of the time,
	// it will be the same as the complete content)
	// Left position in mm
	public void setCoreX(double x)
	{
		myCoreX = x;
	}

	public double getCoreX()
	{
		return myCoreX;
	}

	// Top position in mm
	public void setCoreY(double y)
	{
		myCoreY = y;
	}

	public double getCoreY()
	{
		return myCoreY;
	}

	// Width in mm
	public void setCoreWidth(double w)
	{
		myCoreWidth = w;
	}

	public double getCoreWidth()
	{
		return myCoreWidth;
	}

	// Height in mm
	public void setCoreHeight(double h)
	{
		myCoreHeight = h;
	}

	public double getCoreHeight()
	{
		return myCoreHeight;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.swing.paper.PaperContent#getPhysicalBoundsUsefullZone()
	 */
	public Rectangle getCoreBounds() throws PaperContentIsUnattachedException
	{
		if (null == myContainer)
		{
			throw new PaperContentIsUnattachedException();
		}
		return myCoreBounds;
	}

}