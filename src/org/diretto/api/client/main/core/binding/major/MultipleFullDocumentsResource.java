package org.diretto.api.client.main.core.binding.major;

import java.util.LinkedHashMap;

/**
 * This class represents a POJO based {@code MultipleFullDocumentsResource}.
 * <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class MultipleFullDocumentsResource
{
	private LinkedHashMap<String, DocumentFullResource> results;

	public LinkedHashMap<String, DocumentFullResource> getResults()
	{
		return results;
	}

	public void setResults(LinkedHashMap<String, DocumentFullResource> results)
	{
		this.results = results;
	}
}
