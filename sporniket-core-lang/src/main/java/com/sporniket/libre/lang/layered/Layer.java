package com.sporniket.libre.lang.layered;

/**
 * Container for a layer in a layered object.
 * 
 * <p>
 * &copy; Copyright 2002-2016 David Sporn
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
 * @version 16.08.02
 * @since 12.06.01
 */
public class Layer implements Comparable<Layer>
{

	private boolean myVisible = true;

	/**
	 * Rank in LayeredObject.
	 * 
	 * It is used by the LayeredObject to sort the layers. It is guaranteed to be non null.
	 */
	private Integer myRank = new Integer(0);

	/**
	 * Underlying model that constitute the Layer.
	 */
	private Object myObject = null;

	public int compareTo(Layer l)
	{
		return (getRank()).compareTo(l.getRank());
	}

	public Object getObject()
	{
		return myObject;
	}

	public void setObject(Object object)
	{
		myObject = object;
	}

	/**
	 * @return an integer, guaranteed to be non null.
	 */
	public Integer getRank()
	{
		if (null == myRank)
		{
			myRank = new Integer(0);
		}
		return myRank;
	}

	public void setRank(Integer rank)
	{
		myRank = rank;
	}

	public void setRank(int rank)
	{
		myRank = new Integer(rank);
	}

	/**
	 * Read the visible property.
	 * 
	 * @return the visible
	 */
	public boolean isVisible()
	{
		return myVisible;
	}

	/**
	 * Write the visible property.
	 * 
	 * @param visible
	 *            the visible to set
	 */
	public void setVisible(boolean visible)
	{
		myVisible = visible;
	}

}
