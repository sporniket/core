/**
 * 
 */
package com.sporniket.libre.lang.message;

/**
 * How the message provider should behave when the message provider can't retrieve the wanted message is not found.
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
 * 
 * @version 16.08.00
 * @since 15.02.00
 */
public interface BehaviourOnMissingMessage
{
	/**
	 * Defines the default behaviour.
	 */
	public static final BehaviourOnMissingMessage DEFAULT_BEHAVIOUR = new ReturnKey();

	/**
	 * Implementation that return the key if the message is missing (that will be the default behaviour).
	 * <p>
	 * &copy; Copyright 2002-2015 David Sporn
	 * </p>
	 * <hr>
	 * 
	 * <p>
	 * This file is part of <i>The Sporniket Core Library &#8211; lang</i>.
	 * 
	 * <p>
	 * <i>The Sporniket Core Library &#8211; lang</i> is free software: you can redistribute it and/or modify it under the terms of
	 * the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at
	 * your option) any later version.
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
	 * 
	 * @version 16.08.00
	 * @since 15.02.00
	 */
	public static class ReturnKey implements BehaviourOnMissingMessage
	{
		/* (non-Javadoc)
		 * @see com.sporniket.libre.lang.message.BehaviourOnMissingMessage#getValueOnMissingMessage(java.lang.String)
		 */
		public String getValueOnMissingMessage(String key)
		{
			return key;
		}
	}

	/**
	 * Return <code>null</code> if the message is missing.
	 * <p>
	 * &copy; Copyright 2002-2015 David Sporn
	 * </p>
	 * <hr>
	 * 
	 * <p>
	 * This file is part of <i>The Sporniket Core Library &#8211; lang</i>.
	 * 
	 * <p>
	 * <i>The Sporniket Core Library &#8211; lang</i> is free software: you can redistribute it and/or modify it under the terms of
	 * the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at
	 * your option) any later version.
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
	 * 
	 * @version 16.08.00
	 * @since 15.02.00
	 */
	public static class ReturnNull implements BehaviourOnMissingMessage
	{
		/* (non-Javadoc)
		 * @see com.sporniket.libre.lang.message.BehaviourOnMissingMessage#getValueOnMissingMessage(java.lang.String)
		 */
		public String getValueOnMissingMessage(String key)
		{
			return null;
		}
	}

	/**
	 * What to return if the message is missing.
	 * 
	 * @param key
	 *            the message key.
	 * @return a default value or something else.
	 */
	String getValueOnMissingMessage(String key);
}
