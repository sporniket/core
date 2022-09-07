/**
 * 
 */
package com.sporniket.libre.lang.xml;

import static com.sporniket.strings.StringPredicates.IS_EMPTY;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class that will iterate over a {@link NodeList} to find the next specified node.
 * 
 * When it has been instanciated, it starts from the beginning of the list, and each new request starts on the Node following the
 * previously found Node.
 * 
 * <p>
 * &copy; Copyright 2002-2022 David Sporn
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
 * @version 19.04.00
 * @since 12.06.01
 */
public class NodeFinder
{
	/**
	 * The start position of the {@link NodeFinder} to scan all the node.
	 */
	private static final int POSITION__START_OF_LIST = 0;

	/**
	 * The position of the finder in the list, this is the last found node.
	 */
	private int myPosition = POSITION__START_OF_LIST;

	private NodeList mySource = null;

	/**
	 * Usual constructor, just provide the NodeList to work with.
	 * 
	 * @param source
	 *            node list to scan.
	 */
	public NodeFinder(NodeList source)
	{
		super();
		mySource = source;
	}

	/**
	 * A constructor where one specify the starting position.
	 * 
	 * @param source
	 *            node list to scan.
	 * @param position
	 *            the index in the node list of the first node to scan.
	 */
	public NodeFinder(NodeList source, int position)
	{
		super();
		setSource(source);
		setPosition(position);
	}

	/**
	 * Read the position property.
	 * 
	 * @return the position
	 */
	public int getPosition()
	{
		return myPosition;
	}

	/**
	 * Read the source property.
	 * 
	 * @return the source
	 */
	private NodeList getSource()
	{
		if (null == mySource)
		{
			throw new IllegalStateException("Internal node list should not be null");
		}
		return mySource;
	}

	/**
	 * Test whether there are still Node to scan.
	 * 
	 * @return <code>true</code> if there are still a node to scan.
	 */
	public boolean hasMoreAvailableElement()
	{
		return getPosition() < getSource().getLength();
	}

	/**
	 * Write the position property.
	 * 
	 * @param position
	 *            the position to set
	 */
	private void setPosition(int position)
	{
		myPosition = (position >= POSITION__START_OF_LIST) ? position : POSITION__START_OF_LIST;
	}

	/**
	 * Write the source property.
	 * 
	 * @param source
	 *            the source to set
	 */
	private void setSource(NodeList source)
	{
		if (null == source)
		{
			throw new IllegalArgumentException("The node list to search within must not be null.");
		}
		mySource = source;
	}

	/**
	 * Find the next node having the specified node name.
	 * 
	 * @param nodeName
	 *            name to look for.
	 * @return <code>null</code> if there are no more node to scan and there was no matching node.
	 */
	public Node find(String nodeName)
	{
		// Sanity check
		if (IS_EMPTY.test(nodeName))
		{
			throw new IllegalArgumentException("The node name should not be empty");
		}
		Node _result = null;
		while (hasMoreAvailableElement() && (null == _result))
		{
			int _position = getPosition();
			Node _current = getSource().item(_position);
			_position++;
			setPosition(_position);

			if (nodeName.equals(_current.getNodeName()))
			{
				_result = _current;
			}
		}
		return _result;
	}

}
