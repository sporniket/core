package test.sporniket.libre.lang ;

import static com.sporniket.libre.lang.MapTools.applyToMap ;
import static com.sporniket.libre.lang.MapTools.asMap ;
import static org.assertj.core.api.BDDAssertions.then ;

import java.util.Map ;

import org.junit.jupiter.api.Test ;

import com.sporniket.libre.lang.MapTools ;

/**
 * Test suite for {@link MapTools}.
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
 * 
 * @version 22.09.00
 * @since 22.09.01
 */
public class TestMapTools {

    @Test
    public void applyMapWithoutSeparatorShouldModifyMap() {
        Map<String, String> target = asMap('|', "a|item a", "b|item b") ;
        then(applyToMap(target, "a:apply a", "c:apply c"))//
                .hasSize(3)//
                .containsEntry("a", "apply a")//
                .containsEntry("b", "item b")//
                .containsEntry("c", "apply c") ;
    }

    @Test
    public void applyMapWithSeparatorShouldModifyMap() {
        Map<String, String> target = asMap("a:item a", "b:item b") ;
        then(applyToMap(target, '|', "a|apply a", "c|apply c"))//
                .hasSize(3)//
                .containsEntry("a", "apply a")//
                .containsEntry("b", "item b")//
                .containsEntry("c", "apply c") ;
    }

    @Test
    public void asMapWithoutSeparatorShouldCreateMap() {
        then(asMap("a:item a", "b:item b"))//
                .hasSize(2)//
                .containsEntry("a", "item a")//
                .containsEntry("b", "item b") ;
    }

    @Test
    public void asMapWithSeparatorShouldCreateMap() {
        then(asMap('|', "a|item a", "b|item b"))//
                .hasSize(2)//
                .containsEntry("a", "item a")//
                .containsEntry("b", "item b") ;
    }
}
