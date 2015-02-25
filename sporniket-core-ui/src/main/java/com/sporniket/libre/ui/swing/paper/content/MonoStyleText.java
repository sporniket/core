package com.sporniket.libre.ui.swing.paper.content;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sporniket.libre.ui.swing.paper.PaperContentAdapter;
import com.sporniket.libre.ui.swing.paper.PaperContentIsUnattachedException;
import com.sporniket.libre.ui.swing.paper.UnitConverter;

/**
 * Text rendered using a single font.
 * 
 * The rendered text will fit the specified width, and it will takes all the space needed vertically to display the text.
 * 
 * <p>
 * &copy; Copyright 2002-2012 David Sporn
 * </p>
 * <hr />
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
 * If not, see <http://www.gnu.org/licenses/>. 2
 * 
 * <hr />
 * 
 * @author David SPORN <david.sporn@sporniket.com>
 * @version 15.02.00
 * @since 12.06.01
 */
public class MonoStyleText extends PaperContentAdapter
{
	private static final Color DEFAULT_COLOR = Color.BLACK;

	private String myText;

	private Font myFont;

	private Color myColor = DEFAULT_COLOR;

	/**
	 * Font that will be derived from the user specified font so that it matches wisywig.
	 */
	private Font myInternalFont;

	private Map<TextAttribute, Object> myTextFormat = new HashMap<TextAttribute, Object>(1);

	private LineBreakMeasurer myLineBreakMeasurer;

	private List<TextLayout> myTextLayouts = new ArrayList<TextLayout>(10);

	private TextLayout myTextLayout = null;

	/**
	 * Vertical offset to the text baseline, relative to the top of a rendered line.
	 */
	private double myBaseLineOffset = 0;

	private double myLineHeight = 0;

	private double myTextHeight = 0;

	public void paint(Graphics2D g)
	{
		g.setColor(getColor());
		try
		{
			float _xPos = (float) getFullBounds().getX();
			double _yPos = getFullBounds().getY() + myBaseLineOffset;

			for (TextLayout _layout : myTextLayouts)
			{
				_layout.draw(g, _xPos, (float) _yPos);
				_yPos += myLineHeight;
			}
		}
		catch (PaperContentIsUnattachedException _exception)
		{
			// Should not arrive
			throw new RuntimeException("Impossible error");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.swing.paper.PaperContentAdapter#updateInternals()
	 */
	public void updateInternals()
	{
		if (null == getContainer())
		{
			return;
		}
		super.updateInternals();

		UnitConverter _uc = UnitConverter.getCachedInstance(getContainer().getSheetOfPaper().getPixelPerInch());
		double _zoom = getContainer().convertMillimeterToUnit(_uc.convertPointToMillimiter(1));

		myInternalFont = getFont().deriveFont(AffineTransform.getScaleInstance(_zoom, _zoom));

		// update textformat
		myTextFormat.clear();
		myTextFormat.put(TextAttribute.FONT, myInternalFont);

		myLineBreakMeasurer = new LineBreakMeasurer(new AttributedString(getText(), myTextFormat).getIterator(),
				new FontRenderContext(null, true, true));

		try
		{
			float _wrappingWidth = (float) getFullBounds().getWidth();
			myTextLayout = myLineBreakMeasurer.nextLayout(_wrappingWidth);
			myTextLayouts.clear();
			myTextLayouts.add(myTextLayout);
			while (myLineBreakMeasurer.getPosition() > 0 && myLineBreakMeasurer.getPosition() < getText().length())
			{
				myTextLayouts.add(myLineBreakMeasurer.nextLayout(_wrappingWidth));
			}
		}
		catch (PaperContentIsUnattachedException _exception)
		{
			// Should not arrive
			throw new RuntimeException("Impossible error");
		}
		myBaseLineOffset = myTextLayout.getAscent();
		myTextHeight = myBaseLineOffset + myTextLayout.getDescent();
		myLineHeight = myTextHeight + myTextLayout.getLeading();

		// adjust the height
		double _realHeight = myTextHeight; // at least one line
		if (myTextLayouts.size() > 1)
		{
			_realHeight += ((myTextLayouts.size() - 1) * myLineHeight);
		}
		setCoreHeight(getContainer().convertUnitToMillimeter(_realHeight));
		super.updateInternals();
	}

	/**
	 * Read the font property.
	 * 
	 * @return the font
	 */
	public Font getFont()
	{
		return myFont;
	}

	/**
	 * Write the font property.
	 * 
	 * @param font
	 *            the font to set
	 */
	public void setFont(Font font)
	{
		myFont = font;
	}

	/**
	 * Read the text property.
	 * 
	 * @return the text
	 */
	public String getText()
	{
		return myText;
	}

	/**
	 * Write the text property.
	 * 
	 * @param text
	 *            the text to set
	 */
	public void setText(String text)
	{
		myText = text;
	}

	/**
	 * Read the color property.
	 * 
	 * @return the color
	 */
	public Color getColor()
	{
		return myColor;
	}

	/**
	 * Write the color property.
	 * 
	 * @param color
	 *            the color to set
	 */
	public void setColor(Color color)
	{
		myColor = color;
		if (null == myColor)
		{
			myColor = DEFAULT_COLOR;
		}
	}

}
