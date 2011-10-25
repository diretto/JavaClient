package org.diretto.api.client.main.core.binding.post;

import org.diretto.api.client.main.core.binding.resources.DocumentQueryResource;

/**
 * This class represents a POJO based {@code DispatchDocumentQueryResource}.
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
public final class DispatchDocumentQueryResource
{
	private DocumentQueryResource query;

	public DocumentQueryResource getQuery()
	{
		return query;
	}

	public void setQuery(DocumentQueryResource query)
	{
		this.query = query;
	}
}
