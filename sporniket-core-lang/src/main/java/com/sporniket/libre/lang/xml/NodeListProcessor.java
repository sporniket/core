package com.sporniket.libre.lang.xml;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sporniket.libre.lang.string.StringTools;

/**
 * A class that iterates over a {@link NodeList} and dispatch processing according to the node name of the current {@link Node}.
 * 
 * <p>
 * &copy; Copyright 2002-2012 David Sporn
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
 * @version 15.02.00
 * @since 12.06.01
 */
public class NodeListProcessor
{
	/**
	 * Node name for the default action.
	 */
	private static final String NODE_NAME__DEFAULT = "";

	/**
	 * The start position of the processor to scan all the node.
	 */
	private static final int POSITION__START_OF_LIST = 0;

	private Map<String, NodeProcessor> myActionPool = new HashMap<String, NodeProcessor>();

	/**
	 * The position of the finder in the list, this is the last found node.
	 */
	private int myPosition = POSITION__START_OF_LIST;

	private NodeList mySource = null;

	/**
	 * Default constructor, one need a call to {@link #setup(NodeList)} or {@link #setup(NodeList, int)}.
	 */
	public NodeListProcessor()
	{
		super();
	}

	/**
	 * Usual constructor, just provide the NodeList to work with.
	 * 
	 * @param source the node list to work with.
	 */
	public NodeListProcessor(NodeList source)
	{
		super();
		setup(source);
	}

	/**
	 * A constructor where one specify the starting position.
	 * 
	 * @param source the node list to work with.
	 * @param position
	 *            the index in the node list of the first node to scan.
	 */
	public NodeListProcessor(NodeList source, int position)
	{
		super();
		setup(source, position);
	}

	/**
	 * Add a default processing that will be applied when no specific processor is found.
	 * @param processor the default processor.
	 */
	public void addDefaultProcessor(NodeProcessor processor)
	{
		if (null == processor)
		{
			throw new IllegalArgumentException("Processor should not be null.");
		}
		getActionPool().put(NODE_NAME__DEFAULT, processor);
	}

	/**
	 * Add a specific processing that will be applied to nodes having the matching name.
	 * @param nodeName the name of nodes that will be processed.
	 * @param processor the processor.
	 */
	public void addProcessor(String nodeName, NodeProcessor processor)
	{
		if (null == processor)
		{
			throw new IllegalArgumentException("Processor should not be null.");
		}
		if (StringTools.isEmptyString(nodeName))
		{
			throw new IllegalArgumentException("The node name should not be empty.");
		}
		getActionPool().put(nodeName, processor);
	}

	/**
	 * Perform the processing on the next available node.
	 */
	private void doProcessNext()
	{
		int _position = getPosition();
		Node _current = getSource().item(_position);

		if (getActionPool().containsKey(_current.getNodeName()))
		{
			getActionPool().get(_current.getNodeName()).execute(_current, _position);
		}
		else if (getActionPool().containsKey(NODE_NAME__DEFAULT))
		{
			getActionPool().get(NODE_NAME__DEFAULT).execute(_current, _position);
		}
		// else do nothing...

		// done
		_position++;
		setPosition(_position);
	}

	/**
	 * Read the actionPool property.
	 * 
	 * @return the actionPool
	 */
	private Map<String, NodeProcessor> getActionPool()
	{
		return myActionPool;
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
	 * @return <code>true</code> if there are still a Node to scan.
	 */
	public boolean hasMoreAvailableElement()
	{
		return getPosition() < getSource().getLength();
	}

	/**
	 * Perform the processing on the next available node.
	 */
	public void processNext()
	{
		if (hasMoreAvailableElement())
		{
			doProcessNext();
		}
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
	 * Specify the NodeList to work with, starting from the beginning.
	 * 
	 * @param source the node list to work with.
	 */
	public void setup(NodeList source)
	{
		setup(source, POSITION__START_OF_LIST);
	}

	/**
	 * Specify the NodeList to work with, and the starting position.
	 * 
	 * @param source the node list to work with.
	 * @param position
	 *            the index in the node list of the first node to scan.
	 */
	public void setup(NodeList source, int position)
	{
		setSource(source);
		setPosition(position);
	}

}
