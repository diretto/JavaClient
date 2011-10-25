package org.diretto.api.client.main.core.binding.resources;

import org.diretto.api.client.main.core.binding.entities.LinkResource;

/**
 * This class represents a POJO based {@code DocumentLinkResource}. <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class DocumentLinkResource
{
	private LinkResource documentLink;

	public LinkResource getDocumentLink()
	{
		return documentLink;
	}

	public void setDocumentLink(LinkResource documentLink)
	{
		this.documentLink = documentLink;
	}
}
