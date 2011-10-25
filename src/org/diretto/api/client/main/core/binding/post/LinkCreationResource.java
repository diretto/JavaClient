package org.diretto.api.client.main.core.binding.post;

/**
 * This class represents a POJO based {@code LinkCreationResource}. <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class LinkCreationResource
{
	private String title;
	private String description;
	private DocumentHyperLinkResource source;
	private DocumentHyperLinkResource destination;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public DocumentHyperLinkResource getSource()
	{
		return source;
	}

	public void setSource(DocumentHyperLinkResource source)
	{
		this.source = source;
	}

	public DocumentHyperLinkResource getDestination()
	{
		return destination;
	}

	public void setDestination(DocumentHyperLinkResource destination)
	{
		this.destination = destination;
	}
}
