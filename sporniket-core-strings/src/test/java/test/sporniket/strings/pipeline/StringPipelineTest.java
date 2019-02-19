package test.sporniket.strings.pipeline;

import static com.sporniket.strings.pipeline.StringTransformation.NULL_TO_EMPTY;
import static com.sporniket.strings.pipeline.StringTransformation.TO_LOWERCASE;
import static com.sporniket.strings.pipeline.StringTransformation.TRIM;
import static java.util.Arrays.asList;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import com.sporniket.strings.pipeline.StringPipeline;
import com.sporniket.strings.pipeline.StringPipelineBuilder;

public class StringPipelineTest
{
	@Test
	public void cannotCreatePipelineWithEmptyTransformerList()
	{
		assertThrows(IllegalStateException.class, () -> new StringPipelineBuilder().done());
	}

	@Test
	public void cannotCreatePipelineWithoutTransformerList()
	{
		assertThrows(IllegalStateException.class, () -> new StringPipeline(null));
	}

	@Test
	public void cannotPipeThroughNull()
	{
		assertThrows(IllegalStateException.class, () -> new StringPipelineBuilder().pipeThrough(null));
	}

	@TestFactory
	public Stream<DynamicTest> shouldTransformStringAsDesigned()
	{
		StringPipeline pipeline = new StringPipelineBuilder()//
				.pipeThrough(NULL_TO_EMPTY)//
				.pipeThrough(TO_LOWERCASE)//
				.pipeThrough(TRIM).done();
		return asList(//
				dynamicTest("null give empty string", () -> then(pipeline.transform(null)).isEqualTo(""))//
				, dynamicTest("test on '   WhatEVER R you DOING ?  '",
						() -> then(pipeline.transform("   WhatEVER R you DOING ?  ")).isEqualTo("whatever r you doing ?"))//
		).stream();
	}
}
