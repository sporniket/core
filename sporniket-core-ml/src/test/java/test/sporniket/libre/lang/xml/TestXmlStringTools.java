/**
 * 
 */
package test.sporniket.libre.lang.xml ;

import static com.sporniket.libre.lang.MapTools.asMap ;
import static com.sporniket.libre.lang.xml.XmlStringTools.appendEmptyTag ;
import static com.sporniket.libre.lang.xml.XmlStringTools.appendOpeningTag ;
import static com.sporniket.libre.lang.xml.XmlStringTools.getEmptyTag ;
import static com.sporniket.libre.lang.xml.XmlStringTools.getOpeningTag ;
import static org.assertj.core.api.BDDAssertions.then ;

import org.junit.jupiter.api.Test ;

import com.sporniket.libre.lang.xml.XmlStringTools ;

/**
 * Test suite for {@link XmlStringTools}.
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
 * <i>The Sporniket Core Library &#8211; lang</i> is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; lang</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 * 
 * <p>
 * You should have received a copy of the GNU Lesser General Public License along with <i>The Sporniket Core Library &#8211; lang</i>. If not, see
 * <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
 * 
 * <hr>
 * 
 * @author David SPORN
 * @version 22.09.00
 * @since 22.09.01
 */
public class TestXmlStringTools {

    @Test
    public void appendEmptyTagShouldUpdateBufferAsExpected() {
        then(appendEmptyTag(new StringBuffer(), "a", asMap("attrb:b", "attrc:c")).toString())//
                .startsWith("<a ")//
                .endsWith("\"/>")//
                .contains(//
                        " attrb=\"b\"", //
                        " attrc=\"c\"") ;
    }

    @Test
    public void appendEmptyTagWithoutAttributesShouldUpdateBufferAsExpected() {
        then(appendEmptyTag(new StringBuffer(), "a").toString()).isEqualTo("<a/>") ;
    }

    @Test
    public void appendOpeningTagShouldUpdateBufferAsExpected() {
        then(appendOpeningTag(new StringBuffer(), "a", asMap("attrb:b", "attrc:c")).toString())//
                .startsWith("<a ")//
                .endsWith("\">")//
                .contains(//
                        " attrb=\"b\"", //
                        " attrc=\"c\"") ;
    }

    @Test
    public void appendOpeningTagWithoutAttributesShouldUpdateBufferAsExpected() {
        then(appendOpeningTag(new StringBuffer(), "a").toString()).isEqualTo("<a>") ;
    }

    @Test
    public void getEmptyTagShouldReturnExpectedXmlSnippet() {
        then(getEmptyTag("a", asMap("attrb:b", "attrc:c")))//
                .startsWith("<a ")//
                .endsWith("\"/>")//
                .contains(//
                        " attrb=\"b\"", //
                        " attrc=\"c\"") ;
    }

    @Test
    public void getEmptyTagWithoutAttributesShouldReturnExpectedXmlSnippet() {
        then(getEmptyTag("a")).isEqualTo("<a/>") ;
    }

    @Test
    public void getOpeningTagShouldReturnExpectedXmlSnippet() {
        then(getOpeningTag("a", asMap("attrb:b", "attrc:c")))//
                .startsWith("<a ")//
                .endsWith("\">")//
                .contains(//
                        " attrb=\"b\"", //
                        " attrc=\"c\"") ;
    }

    @Test
    public void getOpeningTagWithoutAttributeShouldReturnExpectedXmlSnippet() {
        then(getOpeningTag("a")).isEqualTo("<a>") ;
    }
}
