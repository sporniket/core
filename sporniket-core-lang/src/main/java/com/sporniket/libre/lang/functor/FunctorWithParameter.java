package com.sporniket.libre.lang.functor;

import java.util.Map;

/**
 * Model of a functor with parameters.
 * 
 * This model can be used to separate similar pattern in specifique framework, for instance to separate the processing of a toolkit
 * event listener.
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
public interface FunctorWithParameter
{
    /**
     * The operation to do.
     * 
     * @param parameters
     *            a map of parameters.
     * @return a result (optional)
     * @throws Exception
     *             if there is a problem to deal with.
     */
    Object process(Map<String, Object> parameters) throws Exception;
}
