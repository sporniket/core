package com.sporniket.libre.ui.swing;

import java.awt.FlowLayout;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.sporniket.libre.lang.url.ClassLoaderUrlProvider;
import com.sporniket.libre.lang.url.UrlProvider;
import com.sporniket.libre.lang.url.UrlProviderException;

/**
 * Macros for creating customized Swing component.
 * 
 * Macros that are not specific to jdk7 calls the corresponding jdk1.4 macros.
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
public class ComponentFactory
{
	/**
	 * Create a scrollable panel.
	 * 
	 * @param horizontalGap
	 *            the horizontal gap.
	 * @param verticalGap
	 *            the vertical gap.
	 * @return a scrollable panel.
	 * @since 15.02.00
	 */
	public static FluidFlowPanelModel createFluidFlowPanel(int horizontalGap, int verticalGap)
	{
		FlowLayout _flowLayout = new FlowLayout(FlowLayout.LEFT, horizontalGap, verticalGap);
		return FluidFlowPanelModel.createFluidFlowPanel(_flowLayout);
	}

	/**
	 * Create a panel that lays out components horizontally.
	 * 
	 * @return a panel with an horizontal box layout.
	 * @since 15.02.00
	 */
	public static JPanel createPanelWithHorizontalLayout()
	{
		JPanel _panel = new JPanel();
		_panel.setLayout(new BoxLayout(_panel, BoxLayout.X_AXIS));
		return _panel;
	}

	/**
	 * Create a panel that lays out components vertically.
	 * 
	 * @return a panel with a vertical layout.
	 * @since 15.02.00
	 */
	public static JPanel createPanelWithVerticalLayout()
	{
		JPanel _panel = new JPanel();
		_panel.setLayout(new BoxLayout(_panel, BoxLayout.Y_AXIS));
		return _panel;
	}

	/**
	 * Create an Image icon from a String.
	 * 
	 * @param objectLoader
	 *            This method use <i>objectLoader.getResource() </i> to retrieve the icon.
	 * @param fileName
	 *            the name of the file.
	 * @return an Icon.
	 * @throws UrlProviderException if there is a problem to deal with.
	 */
	public static Icon getImageIcon(ClassLoader objectLoader, String fileName) throws UrlProviderException
	{
		return getImageIcon(new ClassLoaderUrlProvider(objectLoader), fileName);
	}

	/**
	 * Create an Image based icon from a String.
	 * 
	 * @param urlProvider
	 *            The URL provider.
	 * @param fileName
	 *            the name of the file.
	 * @return an Icon.
	 * @throws UrlProviderException if there is a problem to deal with.
	 */
	public static Icon getImageIcon(UrlProvider urlProvider, String fileName) throws UrlProviderException
	{
		URL _icon = urlProvider.getUrl(fileName);
		return new ImageIcon(_icon);
	}

	/**
	 * Create a custom JToolBar.
	 * 
	 * @return a {@link com.sporniket.libre.ui.swing.JMenu}.
	 */
	public static JMenu newMenu()
	{
		return new com.sporniket.libre.ui.swing.JMenu();
	}

	/**
	 * Create a custom JToolBar.
	 * 
	 * @return a {@link com.sporniket.libre.ui.swing.JToolBar}.
	 */
	public static JToolBar newToolBar()
	{
		return new com.sporniket.libre.ui.swing.JToolBar();
	}

}