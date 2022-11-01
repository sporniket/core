package com.sporniket.libre.ui.swing.paper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;
import javax.swing.Scrollable;

//TODO: listen les changements sur l'instance SheetOfPaper
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
public class PaperPane extends JPanel implements Scrollable, PaperContainer
{
	// public static final int UNIT_PER_MM__DEFAULT = 5;

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5254572778036860245L;

	public static final int PREFERRED_SCROLLABLE_VIEWPORT_SIZE__HORIZONTAL_RESERVE = 48;

	public static final int PREFERRED_SCROLLABLE_VIEWPORT_SIZE__VERTICAL_RESERVE = 240;

	private SheetOfPaper mySheetOfPaper = new SheetOfPaperRaw();

	private UnitConverter myUnitConverter = UnitConverter.getCachedInstance();

	private PaperContent myContent = null;

	private Dimension myPreferredScrollableViewportSize = null;

	/**
	 * 
	 */
	public PaperPane()
	{
		updateViewingDimensions();
	}

	public Dimension getPreferredScrollableViewportSize()
	{
		return myPreferredScrollableViewportSize;
	}

	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean getScrollableTracksViewportHeight()
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getScrollableTracksViewportWidth()
	{
		// TODO Auto-generated method stub
		return false;
	}

	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.swing.paper.PaperContainer#getSheetOfPaper()
	 */
	public SheetOfPaper getSheetOfPaper()
	{
		return mySheetOfPaper;
	}

	private void updateViewingDimensions()
	{
		int _width = mySheetOfPaper.getWidth() + 2 * mySheetOfPaper.getOuterMargin();
		_width = (int) myUnitConverter.convertMillimeterToPixel(_width);
		int _height = mySheetOfPaper.getHeight() + 2 * mySheetOfPaper.getOuterMargin();
		_height = (int) myUnitConverter.convertMillimeterToPixel(_height);
		setPreferredSize(new Dimension(_width, _height));

		Dimension _screen = Toolkit.getDefaultToolkit().getScreenSize();
		int _viewportWidth;
		if ((_width + PREFERRED_SCROLLABLE_VIEWPORT_SIZE__HORIZONTAL_RESERVE) <= _screen.width)
		{
			_viewportWidth = _width;
		}
		else
		{
			_viewportWidth = _screen.width - PREFERRED_SCROLLABLE_VIEWPORT_SIZE__HORIZONTAL_RESERVE;
		}
		int _viewportHeight;
		if ((_height + PREFERRED_SCROLLABLE_VIEWPORT_SIZE__VERTICAL_RESERVE) <= _screen.height)
		{
			_viewportHeight = _height;
		}
		else
		{
			_viewportHeight = _screen.height - PREFERRED_SCROLLABLE_VIEWPORT_SIZE__VERTICAL_RESERVE;
		}
		myPreferredScrollableViewportSize = new Dimension(_viewportWidth, _viewportHeight);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.swing.paper.PaperContainer#convertMillimeterToUnit(double)
	 */
	public double convertMillimeterToUnit(double value)
	{
		return value * mySheetOfPaper.getUnitPerMillimeter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.swing.paper.PaperContainer#convertUnitToMillimeter(double)
	 */
	public double convertUnitToMillimeter(double value)
	{
		return value / mySheetOfPaper.getUnitPerMillimeter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.swing.paper.PaperContainer#getContent()
	 */
	public PaperContent getContent()
	{
		return myContent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sporniket.libre.ui.swing.paper.PaperContainer#setContent(com.sporniket.libre.ui.swing.paper.PaperContent)
	 */
	public void setContent(PaperContent content)
	{
		if (myContent != content)
		{
			if (null != myContent)
			{
				myContent.unattach();
			}
			myContent = content;
		}
		if (null != myContent)
		{
			myContent.attachTo(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		// setup the graphics
		Graphics2D _g2 = (Graphics2D) (g.create());
		_g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		AffineTransform _at = computeTransformation(true);
		_g2.transform(_at);

		// do the drawing
		paintPaper(_g2);

		if (null != getContent())
		{
			getContent().paint(_g2);
		}
	}

	private void paintPaper(Graphics2D g)
	{
		int _width = (int) convertMillimeterToUnit(getSheetOfPaper().getWidth());
		int _height = (int) convertMillimeterToUnit(getSheetOfPaper().getHeight());

		g.setColor(getSheetOfPaper().getColor());
		g.fillRect(0, 0, _width, _height);
		g.setColor(Color.black);
		g.drawRect(-1, -1, _width + 1, _height + 1);

	}

	public AffineTransform computeTransformation(boolean withMargin)
	{
		double _zoomFactor = myUnitConverter.convertMillimeterToPixel(convertUnitToMillimeter(1.0));
		AffineTransform _at = AffineTransform.getScaleInstance(_zoomFactor, _zoomFactor);
		if (withMargin)
		{
			double _margin = convertMillimeterToUnit(getSheetOfPaper().getOuterMargin());
			_at.concatenate(AffineTransform.getTranslateInstance(_margin, _margin));
		}
		return _at;
	}

}
