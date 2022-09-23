/**
 * 
 */
package com.sporniket.libre.lang.sgml;

import java.text.MessageFormat;

import com.sporniket.strings.pipeline.StringPipelineBuilder;
import com.sporniket.strings.pipeline.StringTransformation;

/**
 * Utility macro for sgml code generation.
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
public class SgmlUtils
{
    private static final MessageFormat MESSAGE_FORMAT__ATTRIBUT = new MessageFormat(" {0}=\"{1}\"");

    /**
     * since 19.02.00
     */
    public static final StringTransformation SGML_VALUE_DECODER = new StringPipelineBuilder()//
            .pipeThrough(StringTransformation.NULL_TO_EMPTY)//
            .pipeThrough(t -> t.replace(EncodedChar.QUOTE, RawChar.QUOTE))//
            .pipeThrough(t -> t.replace(EncodedChar.GREATER_THAN, RawChar.GREATER_THAN))//
            .pipeThrough(t -> t.replace(EncodedChar.LOWER_THAN, RawChar.LOWER_THAN))//
            .pipeThrough(t -> t.replace(EncodedChar.AMPERSAND, RawChar.AMPERSAND))//
            .done();

    /**
     * since 19.02.00
     */
    public static final StringTransformation SGML_VALUE_ENCODER = new StringPipelineBuilder()//
            .pipeThrough(StringTransformation.NULL_TO_EMPTY)//
            .pipeThrough(t -> t.replace(RawChar.AMPERSAND, EncodedChar.AMPERSAND))//
            .pipeThrough(t -> t.replace(RawChar.LOWER_THAN, EncodedChar.LOWER_THAN))//
            .pipeThrough(t -> t.replace(RawChar.GREATER_THAN, EncodedChar.GREATER_THAN))//
            .pipeThrough(t -> t.replace(RawChar.QUOTE, EncodedChar.QUOTE))//
            .done();

    /**
     * Generate an attribute of the specified name and value.
     * 
     * @param attributeName
     *            name of the attribute.
     * @param value
     *            value of the attribute.
     * @return the SGML code for an attribute.
     */
    public static String generateAttribute(String attributeName, String value)
    {
        Object[] _args =
        {
                attributeName, SGML_VALUE_ENCODER.apply(value)
        };
        return MESSAGE_FORMAT__ATTRIBUT.format(_args);
    }
}
