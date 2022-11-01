package test.sporniket.strings.pipeline ;

import static com.sporniket.strings.pipeline.StringTransformation.NULL_TO_EMPTY ;
import static com.sporniket.strings.pipeline.StringTransformation.TO_LOWERCASE ;
import static com.sporniket.strings.pipeline.StringTransformation.TRIM ;
import static java.util.Arrays.asList ;
import static org.assertj.core.api.BDDAssertions.then ;
import static org.junit.jupiter.api.Assertions.assertThrows ;
import static org.junit.jupiter.api.DynamicTest.dynamicTest ;

import java.util.stream.Stream ;

import org.junit.jupiter.api.DynamicTest ;
import org.junit.jupiter.api.Test ;
import org.junit.jupiter.api.TestFactory ;

import com.sporniket.strings.pipeline.StringPipeline ;
import com.sporniket.strings.pipeline.StringPipelineBuilder ;

/**
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
 * @version 22.11.00
 * @since 19.02.00
 */
public class StringPipelineTest {

    @Test
    public void cannotCreatePipelineWithEmptyTransformerList() {
        assertThrows(IllegalStateException.class, () -> new StringPipelineBuilder().done()) ;
    }

    @Test
    public void cannotCreatePipelineWithoutTransformerList() {
        assertThrows(IllegalStateException.class, () -> new StringPipeline(null)) ;
    }

    @Test
    public void cannotPipeThroughNull() {
        assertThrows(IllegalArgumentException.class, () -> new StringPipelineBuilder().pipeThrough(null)) ;
    }

    @TestFactory
    public Stream<DynamicTest> shouldTransformStringAsDesigned() {
        StringPipeline pipeline = new StringPipelineBuilder()//
                .pipeThrough(NULL_TO_EMPTY)//
                .pipeThrough(TO_LOWERCASE)//
                .pipeThrough(TRIM).done() ;
        return asList(//
                dynamicTest("null give empty string", () -> then(pipeline.apply(null)).isEqualTo(""))//
                , dynamicTest("test on '   WhatEVER R you DOING ?  '", () -> then(pipeline.apply("   WhatEVER R you DOING ?  ")).isEqualTo("whatever r you doing ?"))//
        ).stream() ;
    }
}
