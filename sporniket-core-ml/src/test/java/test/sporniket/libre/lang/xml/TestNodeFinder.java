/**
 * 
 */
package test.sporniket.libre.lang.xml;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import junit.framework.TestCase;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.sporniket.libre.lang.xml.NodeFinder;

/**
 * Test suite for {@link NodeFinder}.
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
 * @version 22.09.00
 * @since 12.06.01
 */
public class TestNodeFinder extends TestCase
{
	private static final String TAG__A = "a";

	private static final String TAG__ROOT = "root";

	private static final String EXPECTED__FROM_FIRST_NODE = "123";

	private static final String EXPECTED__FROM_SECOND_NODE = "23";

	private static final String MARKER__KO = "Ko";

	private static final String XML_TEST_DOC = "<?xml version=\"1.0\"?><root><a>1</a><A>Ko</A><a>2</a><ab>Ko</ab><a>3</a></root>";

	private Document myTestDocument;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception
	{
		super.setUp();
		// TODO
		DocumentBuilderFactory _factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder _builder = _factory.newDocumentBuilder();
		myTestDocument = _builder.parse(new InputSource(new StringReader(XML_TEST_DOC)));
		// document builder
	}

	/**
	 * Test method for {@link com.sporniket.libre.lang.xml.NodeFinder#NodeFinder(org.w3c.dom.NodeList)}.
	 */
	public final void testNodeFinderNodeList()
	{
		// find root node
		NodeFinder _rootFinder = new NodeFinder(myTestDocument.getChildNodes());
		Node _rootNode = _rootFinder.find(TAG__ROOT);
		if (null == _rootNode)
		{
			fail("Root not found");
		}
		else if (!_rootNode.getNodeName().equals(TAG__ROOT))
		{
			fail("Found node is not root [" + _rootNode.getNodeName() + "]");
		}
		// si nodename != "root"

		// find any "a" node, append text to buffer
		StringBuffer _buffer = new StringBuffer();
		for (NodeFinder _aFinder = new NodeFinder(_rootNode.getChildNodes()); _aFinder.hasMoreAvailableElement();)
		{
			Node _aNode = _aFinder.find(TAG__A);
			if (null != _aNode)
			{
				_buffer.append(_aNode.getTextContent());
			}
		}
		String _result = _buffer.toString();
		// si on trouve un "Ko"
		if (_result.indexOf(MARKER__KO) != -1)
		{
			fail("Did not filter out non 'a' tags [" + _result + "]");
		}
		// si buffer != "123"
		if (!_result.equals(EXPECTED__FROM_FIRST_NODE))
		{
			fail("Did not found all 'a' tags [" + _result + "] instead of [" + EXPECTED__FROM_FIRST_NODE + "]");
		}
	}

	/**
	 * Test method for {@link com.sporniket.libre.lang.xml.NodeFinder#NodeFinder(org.w3c.dom.NodeList, int)}.
	 */
	public final void testNodeFinderNodeListInt()
	{
		// find root node
		NodeFinder _rootFinder = new NodeFinder(myTestDocument.getChildNodes());
		Node _rootNode = _rootFinder.find(TAG__ROOT);
		if (null == _rootNode)
		{
			fail("Root not found");
		}
		else if (!_rootNode.getNodeName().equals(TAG__ROOT))
		{
			fail("Found node is not root [" + _rootNode.getNodeName() + "]");
		}

		// find any "a" node from the second, append text to buffer
		StringBuffer _buffer = new StringBuffer("");
		for (NodeFinder _aFinder = new NodeFinder(_rootNode.getChildNodes(), 1); _aFinder.hasMoreAvailableElement();)
		{
			Node _aNode = _aFinder.find(TAG__A);
			if (null != _aNode)
			{
				_buffer.append(_aNode.getTextContent());
			}
		}
		String _result = _buffer.toString();
		// si on trouve un "Ko"
		if (_result.indexOf(MARKER__KO) != -1)
		{
			fail("Did not filter out non 'a' tags [" + _result + "]");
		}
		// si buffer != "23"
		if (!_result.equals(EXPECTED__FROM_SECOND_NODE))
		{
			fail("Did not found all expected 'a' tags [" + _result + "] instead of [" + EXPECTED__FROM_SECOND_NODE + "]");
		}
	}

}
