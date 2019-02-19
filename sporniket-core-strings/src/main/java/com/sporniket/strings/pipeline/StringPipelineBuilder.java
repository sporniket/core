/**
 * 
 */
package com.sporniket.strings.pipeline;

import java.util.LinkedList;
import java.util.List;

/**
 * Fluent builder of {@link StringPipeline}.
 * 
 * @author dsporn
 *
 */
public class StringPipelineBuilder
{
	private List<StringTransformation> myTransformers = new LinkedList<>();

	public StringPipelineBuilder pipeThrough(StringTransformation transformer)
	{
		if (null == transformer)
		{
			throw new IllegalArgumentException("transformer MUST be defined");
		}
		myTransformers.add(transformer);
		return this;
	}

	public StringPipeline done()
	{
		return new StringPipeline(myTransformers);
	}
}
