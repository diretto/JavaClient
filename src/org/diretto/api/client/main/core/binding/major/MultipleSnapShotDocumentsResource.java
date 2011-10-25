package org.diretto.api.client.main.core.binding.major;

import java.util.LinkedHashMap;

/**
 * This class represents a POJO based {@code MultipleSnapShotDocumentsResource}.
 * <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(SnapShot) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class MultipleSnapShotDocumentsResource
{
	private LinkedHashMap<String, DocumentSnapShotResource> results;

	public LinkedHashMap<String, DocumentSnapShotResource> getResults()
	{
		return results;
	}

	public void setResults(LinkedHashMap<String, DocumentSnapShotResource> results)
	{
		this.results = results;
	}
}
