package com.sporniket.libre.lang.layered;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Model of layered object.
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
public class LayeredObject
{
	private int myCounter = 0;

	private List<Layer> myLayers = new ArrayList<Layer>();

	private boolean myFlagIsAllowedLayerReordering = true;

	private int mySelectedLayer;

	private boolean mySingleLayerMode = false;

	/**
	 * Get a copy of the list of layers.
	 * 
	 * @return the list of layers.
	 */
	public List<Layer> getLayers()
	{
		return new ArrayList<Layer>(myLayers);
	}

	public Layer createNewLayer(Object object)
	{
		Layer _result = new Layer();
		_result.setRank(myCounter);
		myCounter++;
		_result.setObject(object);
		myLayers.add(_result);

		return _result;
	}

	/**
	 * Iterate over Layers to change the rank, so that there is no gap.
	 */
	public void fixRank()
	{
		int _fixedRank = 0;
		for (Layer _layer : myLayers)
		{
			_layer.setRank(_fixedRank);
			++_fixedRank;
		}
	}

	public void goDown(int index)
	{
		if (isAllowedLayerReordering() && index >= 0 && index < myLayers.size())
		{
			Layer _layerSource = myLayers.get(index);
			Layer _layerDest = myLayers.get(index - 1);
			int _saveRank = _layerSource.getRank().intValue();
			_layerSource.setRank(_layerDest.getRank());
			_layerDest.setRank(_saveRank);
			Collections.sort(myLayers);
		}
	}

	public void goUp(int index)
	{
		goDown(index + 1);
	}

	public boolean isAllowedLayerReordering()
	{
		return myFlagIsAllowedLayerReordering;
	}

	public void setAllowedLayerReordering(boolean isAllowedLayerReordering)
	{
		myFlagIsAllowedLayerReordering = isAllowedLayerReordering;
	}

	public int getSelectedLayer()
	{
		return mySelectedLayer;
	}

	public void setSelectedLayer(int selectedLayer)
	{
		mySelectedLayer = selectedLayer;
		if (0 > mySelectedLayer)
		{
			mySelectedLayer = 0;
		}
		if (!myLayers.isEmpty() && mySelectedLayer >= myLayers.size())
		{
			mySelectedLayer = myLayers.size() - 1;
		}
	}

	public boolean isSingleLayerMode()
	{
		return mySingleLayerMode;
	}

	public void setSingleLayerMode(boolean singleLayerMode)
	{
		mySingleLayerMode = singleLayerMode;
	}

}
