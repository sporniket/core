package com.sporniket.libre.lang;

/**
 * Interface for an object that can populate a container.
 * 
 * For instance, it might add buttons to a toolbar, and menu items in a menu, as well as a few Objects in a Collection.
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
public interface ContainerFeederInterface
{
	int DEFAULT_PROFIL = 0;

	/**
	 * Add some component to the given container.
	 * 
	 * @param container
	 *            The container to fill.
	 * @param profil
	 *            an optional value to fill the container differently than usual (usual = 0).
	 * @throws Exception
	 *             if something bad occurs.
	 */
	void feedContainer(Object container, int profil) throws Exception;
}
