package org.diretto.api.client.main.core.binding.major;

import org.diretto.api.client.main.core.binding.base.QueryResultPageResource;
import org.diretto.api.client.main.core.binding.resources.HyperLinkResourceWrapper;
import org.diretto.api.client.main.core.binding.resources.DocumentResultsResource;

/**
 * This class represents a POJO based {@code DocumentQueryResultPageResource}.
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
public final class DocumentQueryResultPageResource implements QueryResultPageResource
{
	private HyperLinkResourceWrapper query;
	private DocumentResultsResource results;

	public HyperLinkResourceWrapper getQuery()
	{
		return query;
	}

	public void setQuery(HyperLinkResourceWrapper query)
	{
		this.query = query;
	}

	public DocumentResultsResource getResults()
	{
		return results;
	}

	public void setResults(DocumentResultsResource results)
	{
		this.results = results;
	}
}
