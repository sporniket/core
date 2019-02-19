/**
 * 
 */
package test.sporniket.strings;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import test.sporniket.strings.QuickDiffTest.TestSpecStruct;

/**
 * Base class for test, when one needs some utilities.
 * 
 * @author dsporn
 *
 */
public class TestBase
{
	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * Reads a JSON resource file.
	 * 
	 * @param relativePath
	 *            the path relative to the current class
	 * @param type
	 *            the type to obtain
	 * @return the extracted object
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	protected <T> T loadJsonData(String relativePath, Class<T> type) throws JsonParseException, JsonMappingException, IOException
	{
		return mapper.readValue(getClass().getClassLoader().getResourceAsStream(relativePath), type);
	}
}
