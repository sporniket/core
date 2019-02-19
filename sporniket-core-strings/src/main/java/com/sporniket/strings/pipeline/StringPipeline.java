/**
 * 
 */
package com.sporniket.strings.pipeline;

import java.util.ArrayList;
import java.util.List;

/**
 * Model of compounded string transformation.
 * @author dsporn
 *
 */
public class StringPipeline implements StringTransformation
{

	final List<StringTransformation> myTransformers;

	public StringPipeline(List<StringTransformation> transformers)
	{
		if (null == transformers || transformers.isEmpty())
		{
			throw new IllegalStateException("A pipeline MUST contains at least one transformer.");
		}
		myTransformers = new ArrayList<>(transformers);
	}

	/* (non-Javadoc)
	 * @see com.sporniket.strings.pipeline.StringTransformation#transform(java.lang.String)
	 */
	@Override
	public String transform(String input)
	{
		String result = input;
		for (StringTransformation transformer : myTransformers)
		{
			result = transformer.transform(result);
		}
		return result;
	}
}
