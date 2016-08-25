/**
 * 
 */
package com.sporniket.libre.io;

import java.io.File;
import java.util.Comparator;

/**
 * Enclosing type for various comparators.
 * 
 * <p>
 * &copy; Copyright 2002-2016 David Sporn
 * </p>
 * <hr>
 * 
 * <p>
 * This file is part of <i>The Sporniket Core Library &#8211; io</i>.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; io</i> is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; io</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for
 * more details.
 * 
 * <p>
 * You should have received a copy of the GNU Lesser General Public License along with <i>The Sporniket Core Library &#8211; io</i>.
 * If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
 * 
 * <hr>
 * 
 * @author David SPORN
 * @version 16.08.02
 * @since 15.02.00
 */
public class FileComparator
{
	/**
	 * Comparator by absolute path.
	 * 
	 * <p>
	 * &copy; Copyright 2002-2016 David Sporn
	 * </p>
	 * <hr>
	 * 
	 * <p>
	 * This file is part of <i>The Sporniket Core Library &#8211; io</i>.
	 * 
	 * <p>
	 * <i>The Sporniket Core Library &#8211; io</i> is free software: you can redistribute it and/or modify it under the terms of
	 * the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at
	 * your option) any later version.
	 * 
	 * <p>
	 * <i>The Sporniket Core Library &#8211; io</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
	 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
	 * License for more details.
	 * 
	 * <p>
	 * You should have received a copy of the GNU Lesser General Public License along with <i>The Sporniket Core Library &#8211;
	 * io</i>. If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
	 * 
	 * <hr>
	 * 
	 * @author David SPORN
	 * @version 16.08.02
	 * @since 15.02.00
	 */
	public static class ByAbsolutePath implements Comparator<File>
	{

		/**
		 * Comparator to sort by ascending order.
		 */
		public static final ByAbsolutePath ASCENDING = new ByAbsolutePath();

		/**
		 * Comparator to sort by descending order.
		 */
		public static final ByAbsolutePath DESCENDING = new ByAbsolutePath(true);

		/**
		 * If <code>true</code>, reverse the order of the comparator.
		 */
		private boolean myReverse = false;

		/**
		 * @param reverse
		 */
		private ByAbsolutePath()
		{
			super();
		}

		/**
		 * @param reverse
		 */
		private ByAbsolutePath(boolean reverse)
		{
			setReverse(reverse);
		}

		public int compare(File o1, File o2)
		{
			if (isReverse())
			{
				return -doCompare(o1, o2);
			}
			else
			{
				return doCompare(o1, o2);
			}
		}

		/**
		 * @param o1
		 * @param o2
		 * @return
		 */
		private int doCompare(File o1, File o2)
		{
			if (null == o1 && null == o2)
			{
				return 0;
			}
			else if (null == o1)
			{
				return -1;
			}
			else if (null == o2)
			{
				return 1;
			}
			else
			{
				return (o1.getAbsolutePath().compareTo(o2.getAbsolutePath()));
			}
		}

		/**
		 * @return the reverse
		 */
		private boolean isReverse()
		{
			return myReverse;
		}

		/**
		 * @param reverse
		 *            the reverse to set
		 */
		private void setReverse(boolean reverse)
		{
			myReverse = reverse;
		}
	}
}
