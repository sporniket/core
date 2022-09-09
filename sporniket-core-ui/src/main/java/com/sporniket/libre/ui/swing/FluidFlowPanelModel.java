package com.sporniket.libre.ui.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Utilities and model to create a scrollable panel that will contains several items of the same size, and that rearrange them to
 * avoid horizontal scrolling.
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
 * 
 * @version 22.09.00
 * @since 15.02.00
 */
public class FluidFlowPanelModel
{
	/**
	 * Listener for updating the panel size when the viewport is resized.
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
	 * <i>The Sporniket Core Library &#8211; ui</i> is free software: you can redistribute it and/or modify it under the terms of
	 * the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at
	 * your option) any later version.
	 * 
	 * <p>
	 * <i>The Sporniket Core Library &#8211; ui</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
	 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
	 * License for more details.
	 * 
	 * <p>
	 * You should have received a copy of the GNU Lesser General Public License along with <i>The Sporniket Core Library &#8211;
	 * ui</i>. If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
	 * 
	 * <hr>
	 * 
	 * @author David SPORN
	 * 
	 * @version 22.09.00
	 * @since 15.02.00
	 */
	private static class PanelSizeUpdaterOnViewPortResize implements ComponentListener, ContainerListener
	{
		private final JPanel myPanel;

		/**
		 * @param panel
		 */
		public PanelSizeUpdaterOnViewPortResize(JPanel panel)
		{
			super();
			myPanel = panel;
		}

		public void componentAdded(ContainerEvent e)
		{
			handleEvent(e);
		}

		public void componentHidden(ComponentEvent e)
		{
			// do nothing special
		}

		public void componentMoved(ComponentEvent e)
		{
			// do nothing special
		}

		public void componentRemoved(ContainerEvent e)
		{
			handleEvent(e);
		}

		public void componentResized(ComponentEvent e)
		{
			handleEvent(e);
		}

		public void componentShown(ComponentEvent e)
		{
			// do nothing special
		}

		/**
		 * Get panel.
		 * 
		 * @return the panel
		 */
		private JPanel getPanel()
		{
			return myPanel;
		}

		/**
		 * @param e
		 */
		private void handleEvent(ComponentEvent e)
		{
			updatePanelSizes();
			e.getComponent().revalidate();
		}

		/**
		 * Compute the minimum size, the size and the preferred size of the panel to fit its parent.
		 */
		private void updatePanelSizes()
		{
			JPanel _panel = getPanel();
			FlowLayout _layout = (FlowLayout) _panel.getLayout();
			int _hgap = _layout.getHgap();
			int _vgap = _layout.getVgap();
			int _max = _panel.getComponentCount();
			Dimension _old = _panel.getPreferredSize();
			Container _parent = _panel.getParent();
			if (0 < _max)
			{
				Component _reference = _panel.getComponent(0);
				int _baseVariableWidth = _reference.getWidth() + _hgap;
				int _baseVariableHeight = _reference.getHeight() + _vgap;
				int _minWidth = _hgap + _baseVariableWidth;
				int _minHeight = _vgap + _baseVariableHeight;

				// set minimum size to fit the first component
				Dimension _newMinimumSize = new Dimension(_minWidth, _minHeight);
				_panel.setMinimumSize(_newMinimumSize);
				_panel.setSize(_newMinimumSize);

				// setup preferred size
				int _width = _parent.getWidth();
				if (_width < _minWidth)
				{
					_width = _minWidth;
				}
				int _colsCount = _width / _baseVariableWidth;
				System.out.println("width = " + _width + " ; colsCount = " + _colsCount);
				int _rowCount = (_max + _colsCount - 1) / _colsCount;
				Dimension _newPreferredSize = new Dimension(_hgap + _baseVariableWidth * _colsCount, _vgap + _baseVariableHeight
						* _rowCount);
				if (_newPreferredSize.width != _old.width || _newPreferredSize.height != _old.height)
				{
					_panel.setPreferredSize(_newPreferredSize);
				}
			}
			else
			{
				Dimension _newSize = new Dimension(_parent.getWidth(), _parent.getHeight());
				_panel.setMinimumSize(_newSize);
				_panel.setSize(_newSize);
				_panel.setPreferredSize(_newSize);
			}
			_panel.revalidate();
		}
	}

	/**
	 * The macro that create a scrollable panel with a fluid flow layout.
	 * 
	 * @param flowLayout
	 *            the flow layout to use by this panel.
	 * @return the component.
	 */
	public static final FluidFlowPanelModel createFluidFlowPanel(FlowLayout flowLayout)
	{
		JPanel _panel = new JPanel(flowLayout);
		PanelSizeUpdaterOnViewPortResize _updater = new PanelSizeUpdaterOnViewPortResize(_panel);
		_panel.addContainerListener(_updater);
		JScrollPane _scroller = new JScrollPane(_panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		_scroller.addComponentListener(_updater);
		return new FluidFlowPanelModel(_panel, _scroller);
	}

	/**
	 * The panel that will contain the items.
	 */
	public final JPanel myPanel;

	/**
	 * The {@link ScrollPane} that will allow to view all the items.
	 */
	public final JScrollPane myScrollPane;

	/**
	 * @param panel
	 * @param scrollPane
	 */
	private FluidFlowPanelModel(JPanel panel, JScrollPane scrollPane)
	{
		myPanel = panel;
		myScrollPane = scrollPane;
	}

	/**
	 * Get panel.
	 * 
	 * @return the panel
	 */
	public JPanel getPanel()
	{
		return myPanel;
	}

	/**
	 * Get scrollPane.
	 * 
	 * @return the scrollPane
	 */
	public JScrollPane getScrollPane()
	{
		return myScrollPane;
	}

	/**
	 * Revalidate the components.
	 */
	public void revalidate()
	{
		getPanel().revalidate();
		getScrollPane().revalidate();
	}

}
