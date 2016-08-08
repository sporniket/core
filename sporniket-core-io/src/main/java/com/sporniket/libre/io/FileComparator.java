/**
 * 
 */
package com.sporniket.libre.io;

import java.io.File;
import java.util.Comparator;

/**
 * Enclosing type for various comparators.
 * @author dsporn
 *
 * @version 16.08.00
 * @since 15.02.00
 */
public class FileComparator
{
	/**
	 * @author dsporn
	 *
	 * @version 16.08.00
	 * @since 15.02.00
	 */
	public static class ByAbsolutePath implements Comparator<File> {

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
        private ByAbsolutePath() {
            super();
        }

        /**
         * @param reverse
         */
        private ByAbsolutePath(boolean reverse) {
            setReverse(reverse);
        }

        /**
         * @param o1
         * @param o2
         * @return
         */
        private int doCompare(File o1, File o2) {
            if (null == o1 && null == o2) {
                return 0;
            } else if (null == o1) {
                return -1;
            } else if (null == o2) {
                return 1;
            } else {
                return (o1.getAbsolutePath().compareTo(o2.getAbsolutePath()));
            }
        }

        /**
         * @return the reverse
         */
        private boolean isReverse() {
            return myReverse;
        }

        /**
         * @param reverse
         *            the reverse to set
         */
        private void setReverse(boolean reverse) {
            myReverse = reverse;
        }

        public int compare(File o1, File o2) {
            if (isReverse()) {
                return -doCompare(o1, o2);
            } else {
                return doCompare(o1, o2);
            }
        }
    }
}
