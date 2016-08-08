package com.sporniket.libre.lang.xml;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Usual string operations when generating XML.
 * 
 * <p>
 * &copy; Copyright 2002-2015 David Sporn
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
 * @version 16.08.00
 * @since 12.06.01
 */
public class XmlStringTools
{
	/**
	 * This class encapsulate the serialization process of a {@link Node} into an XML snippet.
	 * 
	 * @author David SPORN 
	 * 
	 * @version 16.08.00
	 * @since 12.06.01
	 */
	public static class NodeSerializer
	{
		/**
		 * Convert the specified node as a Cdata section.
		 * 
		 * @param source
		 * @param buffer
		 *            buffer to fill.
		 * @return the specified buffer, or a new one if it is <code>null</code>, containing the xml code.
		 */
		private static StringBuffer appendCdataNodeAsXmlStream(Node source, StringBuffer buffer)
		{
			return XmlStringTools.appendCdataSection(buffer, source.getNodeValue());
		}

		/**
		 * Convert the specified node as an xml comment.
		 * 
		 * @param source
		 * @param buffer
		 *            buffer to fill.
		 * @return the specified buffer, or a new one if it is <code>null</code>, containing the xml code.
		 */
		private static StringBuffer appendCommentNodeAsXmlStream(Node source, StringBuffer buffer)
		{
			return XmlStringTools.appendComment(buffer, source.getNodeValue());
		}

		/**
		 * Convert the specified node into the corresponding xml stream.
		 * 
		 * @param source the node to convert.
		 * @param buffer
		 *            buffer to fill.
		 * @return the specified buffer, or a new one if it is <code>null</code>, containing the xml code.
		 */
		public static StringBuffer appendNodeAsXmlStream(Node source, StringBuffer buffer)
		{
			StringBuffer _buffer = initStringBufferIfNecessary(buffer);
			return doAppendNodeAsXmlStream(source, _buffer);
		}

		/**
		 * Convert the children nodes of the specified node into the corresponding xml stream.
		 * 
		 * @param source the parent node of the nodes to convert.
		 * @param buffer
		 *            buffer to fill.
		 * @return the specified buffer, or a new one if it is <code>null</code>, containing the xml code.
		 */
		public static StringBuffer appendNodeValueAsXmlStream(Node source, StringBuffer buffer)
		{
			StringBuffer _buffer = initStringBufferIfNecessary(buffer);
			NodeList _children = source.getChildNodes();
			if (_children != null)
			{
				for (int _i = 0; _i < _children.getLength(); _i++)
				{
					Node _childNode = _children.item(_i);
					doAppendNodeAsXmlStream(_childNode, _buffer);
				}
			}

			return _buffer;
		}

		/**
		 * Convert the specified node into the corresponding xml stream with the node name as the xml tag.
		 * 
		 * @param source
		 * @param buffer
		 *            buffer to fill.
		 * @return the specified buffer, or a new one if it is <code>null</code>, containing the xml code.
		 */
		private static StringBuffer appendRegularNodeAsXmlStream(Node source, StringBuffer buffer)
		{
			StringBuffer _buffer = initStringBufferIfNecessary(buffer);
			NamedNodeMap _attributes = source.getAttributes();
			Map<String, String> _attributesMap = null;
			if (_attributes != null)
			{
				_attributesMap = new HashMap<String, String>(_attributes.getLength());
				for (int _i = 0; _i < _attributes.getLength(); _i++)
				{
					Node _attributeNode = _attributes.item(_i);
					_attributesMap.put(_attributeNode.getNodeName(), _attributeNode.getNodeValue());
				}
			}
			XmlStringTools.appendOpeningTag(_buffer, source.getNodeName(), _attributesMap);
			appendNodeValueAsXmlStream(source, _buffer);
			XmlStringTools.appendClosingTag(_buffer, source.getNodeName());

			return _buffer;
		}

		/**
		 * Convert the specified node as an xml text.
		 * 
		 * @param source
		 * @param buffer
		 *            buffer to fill.
		 * @return the specified buffer, or a new one if it is <code>null</code>, containing the xml code.
		 */
		private static StringBuffer appendTextNodeAsXmlStream(Node source, StringBuffer buffer)
		{
			StringBuffer _buffer = initStringBufferIfNecessary(buffer);
			_buffer.append(source.getTextContent());
			return _buffer;
		}

		/**
		 * Convert the specified node into the corresponding xml stream.
		 * 
		 * There are no sanity checks
		 * 
		 * @param source
		 * @param buffer
		 *            buffer to fill.
		 * @return the specified buffer containing the xml code.
		 */
		private static StringBuffer doAppendNodeAsXmlStream(Node source, StringBuffer buffer)
		{
			if (Node.CDATA_SECTION_NODE == source.getNodeType())
			{
				appendCdataNodeAsXmlStream(source, buffer);
			}
			else if (Node.COMMENT_NODE == source.getNodeType())
			{
				appendCommentNodeAsXmlStream(source, buffer);
			}
			else if (Node.TEXT_NODE == source.getNodeType())
			{
				appendTextNodeAsXmlStream(source, buffer);
			}
			else
			{
				appendRegularNodeAsXmlStream(source, buffer);
			}

			return buffer;
		}

	}

	/**
	 * Empty Map to use it as a parameter.
	 */
	private static final Map<String, String> EMPTY_MAP = new HashMap<String, String>();

	/**
	 * Pattern to recognize special character.
	 */
	public static final String PATTERN__CHAR_AMPERSAND = "[&]";

	/**
	 * Pattern to recognize special character.
	 */
	public static final String PATTERN__CHAR_QUOTE = "[\"]";

	/**
	 * Pattern to recognize an escaped special character.
	 */
	public static final String PATTERN__ENTITY_AMPERSAND = "[&]amp;";

	/**
	 * Pattern to recognize an escaped special character.
	 */
	public static final String PATTERN__ENTITY_QUOTE = "[&]amp;";

	/**
	 * Beginning of an attribute sequence.
	 */
	private static final String SEQUENCE__ATTRIBUTE__BEGIN = " ";

	/**
	 * Ending of an attribute sequence.
	 */
	private static final String SEQUENCE__ATTRIBUTE__END = "\"";

	/**
	 * Separates an attribute and its value in an attribute sequence.
	 */
	private static final String SEQUENCE__ATTRIBUTE__EQUALS = "=\"";

	/**
	 * Mark the end of a cdata section
	 */
	private static final String SEQUENCE__CDATA__CLOSE = "]]>";

	/**
	 * Mark the beginning of a cdata section
	 */
	private static final String SEQUENCE__CDATA__OPEN = "<![CDATA[";

	/**
	 * Mark the end of a comment
	 */
	private static final String SEQUENCE__COMMENT__CLOSE = "-->";

	/**
	 * Mark the beginning of a comment
	 */
	private static final String SEQUENCE__COMMENT__OPEN = "<!--";

	/**
	 * Marks the beginning of a closing tag.
	 */
	private static final String SEQUENCE__TAG__BEGIN_CLOSING_TAG = "</";

	/**
	 * Marks the beginning of an opening tag.
	 */
	private static final String SEQUENCE__TAG__BEGIN_OPENING_TAG = "<";

	/**
	 * Marks the end of a tag.
	 */
	private static final String SEQUENCE__TAG__END_OF_TAG = ">";

	/**
	 * Special character.
	 */
	public static final String VALUE__CHAR_AMPERSAND = "&";

	/**
	 * Special character.
	 */
	public static final String VALUE__CHAR_QUOTE = SEQUENCE__ATTRIBUTE__END;

	/**
	 * Escaped special character
	 */
	public static final String VALUE__ENTITY_AMPERSAND = "&amp;";

	/**
	 * Escaped special character
	 */
	public static final String VALUE__ENTITY_QUOTE = "&amp;";

	/**
	 * Add a cdata section to a StringBuffer.
	 * 
	 * If the buffer is null, a new one is created.
	 * 
	 * @param buffer
	 *            StringBuffer to fill
	 * @param cdataContent
	 *            the cdata content
	 * @return the buffer
	 */
	public static StringBuffer appendCdataSection(StringBuffer buffer, String cdataContent)
	{
		StringBuffer _buffer = initStringBufferIfNecessary(buffer);
		return doAppendCdataSection(_buffer, cdataContent);
	}

	/**
	 * Add a closing tag to a StringBuffer.
	 * 
	 * If the buffer is null, a new one is created.
	 * 
	 * @param buffer
	 *            StringBuffer to fill
	 * @param tag
	 *            the tag to close
	 * @return the buffer
	 */
	public static StringBuffer appendClosingTag(StringBuffer buffer, String tag)
	{
		StringBuffer _buffer = initStringBufferIfNecessary(buffer);
		return doAppendClosingTag(_buffer, tag);
	}

	/**
	 * Add a comment to a StringBuffer.
	 * 
	 * If the buffer is null, a new one is created.
	 * 
	 * @param buffer
	 *            StringBuffer to fill
	 * @param comment
	 *            the comment
	 * @return the buffer
	 */
	public static StringBuffer appendComment(StringBuffer buffer, String comment)
	{
		StringBuffer _buffer = initStringBufferIfNecessary(buffer);
		return doAppendComment(_buffer, comment);
	}

	/**
	 * Add an opening tag to a StringBuffer.
	 * 
	 * If the buffer is null, a new one is created.
	 * 
	 * @param buffer
	 *            StringBuffer to fill
	 * @param tag
	 *            the tag to open
	 * @return the buffer
	 */
	public static StringBuffer appendOpeningTag(StringBuffer buffer, String tag)
	{
		return appendOpeningTag(buffer, tag, EMPTY_MAP);
	}

	/**
	 * Add an opening tag with attributes to a StringBuffer.
	 * 
	 * @param buffer
	 *            StringBuffer to fill
	 * @param tag
	 *            the tag to open
	 * @param attributes
	 *            the attribute map
	 * @return the buffer
	 */
	public static StringBuffer appendOpeningTag(StringBuffer buffer, String tag, Map<String, String> attributes)
	{
		StringBuffer _buffer = initStringBufferIfNecessary(buffer);
		Map<String, String> _attributes = (null != attributes) ? attributes : EMPTY_MAP;
		return doAppendOpeningTag(_buffer, tag, _attributes);
	}

	/**
	 * Wrap a text inside a tag.
	 * 
	 * @param buffer
	 *            StringBuffer to fill
	 * @param text
	 *            the text to wrap
	 * @param tag
	 *            the tag to use
	 * @return the buffer
	 */
	public static StringBuffer appendTextInsideTag(StringBuffer buffer, String text, String tag)
	{
		return appendTextInsideTag(buffer, text, tag, EMPTY_MAP);
	}

	/**
	 * Wrap a text inside a tag with attributes.
	 * 
	 * @param buffer
	 *            StringBuffer to fill
	 * @param text
	 *            the text to wrap
	 * @param tag
	 *            the tag to use
	 * @param attributes
	 *            the attribute map
	 * @return the buffer
	 */
	public static StringBuffer appendTextInsideTag(StringBuffer buffer, String text, String tag, Map<String, String> attributes)
	{
		StringBuffer _buffer = initStringBufferIfNecessary(buffer);
		Map<String, String> _attributes = (null != attributes) ? attributes : EMPTY_MAP;
		return doAppendTextInsideTag(_buffer, text, tag, _attributes);
	}

	/**
	 * Reverse the escaping of special chars from a value of an attribute.
	 * 
	 * @param value
	 *            the value to decode
	 * @return the decoded value
	 */
	public static String decodeAttributeValue(String value)
	{
		String _result = value.replaceAll(PATTERN__ENTITY_QUOTE, VALUE__CHAR_QUOTE);
		_result = _result.replaceAll(PATTERN__ENTITY_AMPERSAND, VALUE__CHAR_AMPERSAND);
		return _result;
	}

	/**
	 * Add a cdata section to a StringBuffer.
	 * 
	 * If the buffer is null, a new one is created.
	 * 
	 * @param buffer
	 *            StringBuffer to fill
	 * @param cdataContent
	 *            the cdata content
	 * @return the buffer
	 */
	private static StringBuffer doAppendCdataSection(StringBuffer buffer, String cdataContent)
	{
		buffer.append(SEQUENCE__CDATA__OPEN).append(cdataContent).append(SEQUENCE__CDATA__CLOSE);
		return buffer;
	}

	/**
	 * Add a closing tag to a StringBuffer.
	 * 
	 * If the buffer is null, a new one is created.
	 * 
	 * @param buffer
	 *            StringBuffer to fill
	 * @param tag
	 *            the tag to close
	 * @return the buffer
	 */
	private static StringBuffer doAppendClosingTag(StringBuffer buffer, String tag)
	{
		buffer.append(SEQUENCE__TAG__BEGIN_CLOSING_TAG).append(tag).append(SEQUENCE__TAG__END_OF_TAG);
		return buffer;
	}

	/**
	 * Add a comment to a StringBuffer.
	 * 
	 * If the buffer is null, a new one is created.
	 * 
	 * @param buffer
	 *            StringBuffer to fill
	 * @param comment
	 *            the comment
	 * @return the buffer
	 */
	private static StringBuffer doAppendComment(StringBuffer buffer, String comment)
	{
		buffer.append(SEQUENCE__COMMENT__OPEN).append(comment).append(SEQUENCE__COMMENT__CLOSE);
		return buffer;
	}

	/**
	 * Add an opening tag with attributes to a StringBuffer.
	 * 
	 * @param buffer
	 *            StringBuffer to fill
	 * @param tag
	 *            the tag to open
	 * @param attributes
	 *            the attribute map
	 * @return the buffer
	 */
	private static StringBuffer doAppendOpeningTag(StringBuffer buffer, String tag, Map<String, String> attributes)
	{
		buffer.append(SEQUENCE__TAG__BEGIN_OPENING_TAG).append(tag);
		for (String _attributeName : attributes.keySet())
		{
			String _attributeValue = attributes.get(_attributeName);
			_attributeValue = encodeAttributeValue(_attributeValue);
			buffer.append(SEQUENCE__ATTRIBUTE__BEGIN).append(_attributeName).append(SEQUENCE__ATTRIBUTE__EQUALS)
					.append(_attributeValue).append(SEQUENCE__ATTRIBUTE__END);
		}
		buffer.append(SEQUENCE__TAG__END_OF_TAG);
		return buffer;
	}

	/**
	 * Wrap a text inside a tag with attributes.
	 * 
	 * @param buffer
	 *            StringBuffer to fill
	 * @param text
	 *            the text to wrap
	 * @param tag
	 *            the tag to use
	 * @param attributes
	 *            the attribute map
	 * @return the buffer
	 */
	private static StringBuffer doAppendTextInsideTag(StringBuffer buffer, String text, String tag, Map<String, String> attributes)
	{
		return appendClosingTag(appendOpeningTag(buffer, tag, attributes).append(text), tag);
	}

	/**
	 * Convert special chars so that it is legal as an attribute value.
	 * 
	 * @param value
	 *            the value to convert.
	 * @return the coded value.
	 */
	public static String encodeAttributeValue(String value)
	{
		String _result = value.replaceAll(PATTERN__CHAR_AMPERSAND, VALUE__ENTITY_AMPERSAND);
		_result = _result.replaceAll(PATTERN__CHAR_QUOTE, VALUE__ENTITY_QUOTE);
		return _result;
	}

	/**
	 * Create a string containing a Cdata section.
	 * 
	 * @param cdataContent
	 *            the cdata content.
	 * @return the closing tag.
	 */
	public static String getCdataSection(String cdataContent)
	{
		StringBuffer _result = new StringBuffer();
		return doAppendCdataSection(_result, cdataContent).toString();
	}

	/**
	 * Create a string containing a closing tag.
	 * 
	 * @param tag
	 *            the closing tag to generate.
	 * @return the closing tag.
	 */
	public static String getClosingTag(String tag)
	{
		StringBuffer _result = new StringBuffer();
		return doAppendClosingTag(_result, tag).toString();
	}

	/**
	 * Create a string containing a comment.
	 * 
	 * @param comment
	 *            the comment to generate.
	 * @return the closing tag.
	 */
	public static String getComment(String comment)
	{
		StringBuffer _result = new StringBuffer();
		return doAppendComment(_result, comment).toString();
	}

	/**
	 * Create a string containing a closing tag.
	 * 
	 * @param tag
	 *            the closing tag to generate.
	 * @return the opening tag.
	 */
	public static String getOpeningTag(String tag)
	{
		return getOpeningTag(tag, EMPTY_MAP);
	}

	/**
	 * Create a string containing a closing tag.
	 * 
	 * @param tag
	 *            the closing tag to generate.
	 * @param attributes
	 *            the map of attributes
	 * @return the opening tag.
	 */
	public static String getOpeningTag(String tag, Map<String, String> attributes)
	{
		StringBuffer _result = new StringBuffer();
		Map<String, String> _attributes = (null != attributes) ? attributes : EMPTY_MAP;
		return doAppendOpeningTag(_result, tag, _attributes).toString();
	}

	/**
	 * Wrap a text inside a tag with attributes.
	 * 
	 * @param text
	 *            the text to wrap
	 * @param tag
	 *            the tag to use
	 * @return the xml code
	 */
	public static String getTextInsideTag(String text, String tag)
	{
		return getTextInsideTag(text, tag, EMPTY_MAP);
	}

	/**
	 * Wrap a text inside a tag with attributes.
	 * 
	 * @param text
	 *            the text to wrap
	 * @param tag
	 *            the tag to use
	 * @param attributes
	 *            the attribute map
	 * @return the xml code
	 */
	public static String getTextInsideTag(String text, String tag, Map<String, String> attributes)
	{
		StringBuffer _buffer = new StringBuffer();
		Map<String, String> _attributes = (null != attributes) ? attributes : EMPTY_MAP;
		return doAppendTextInsideTag(_buffer, text, tag, _attributes).toString();
	}

	private static StringBuffer initStringBufferIfNecessary(StringBuffer buffer)
	{
		return (null != buffer) ? buffer : new StringBuffer();
	}
}
