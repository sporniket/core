package com.sporniket.libre.lang.bean;

import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;

/**
 * Sample implementation.
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
 * @version 22.11.00
 * @since 12.06.01
 */
public class DemoConstrained implements Monitorable, Constrainable
{

    private DemoMonitored __myEncapsuledObject = new DemoMonitored();

    private VetoableChangeSupport __myVcs = new VetoableChangeSupport(this);

    public DemoMonitored __getEncapsuledObject()
    {
        return __myEncapsuledObject;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        __myEncapsuledObject.addPropertyChangeListener(listener);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
    {
        __myEncapsuledObject.addPropertyChangeListener(propertyName, listener);
    }

    public void addVetoableChangeListener(String propertyName, VetoableChangeListener listener)
    {
        __myVcs.addVetoableChangeListener(propertyName, listener);
    }

    public void addVetoableChangeListener(VetoableChangeListener listener)
    {
        __myVcs.addVetoableChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener)
    {
        __myEncapsuledObject.removePropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener)
    {
        __myEncapsuledObject.removePropertyChangeListener(propertyName, listener);
    }

    public void removeVetoableChangeListener(String propertyName, VetoableChangeListener listener)
    {
        __myVcs.addVetoableChangeListener(propertyName, listener);
    }

    public void removeVetoableChangeListener(VetoableChangeListener listener)
    {
        __myVcs.addVetoableChangeListener(listener);
    }
}
