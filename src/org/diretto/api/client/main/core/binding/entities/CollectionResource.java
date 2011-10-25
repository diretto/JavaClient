package org.diretto.api.client.main.core.binding.entities;

import org.diretto.api.client.main.core.binding.resources.HyperLinkResource;
import org.diretto.api.client.main.core.binding.resources.HyperLinkResourceWrapper;

/**
 * This class represents a POJO based {@code CollectionResource}. <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class CollectionResource
{
	private HyperLinkResource link;
	private String title;
	private String description;
	private String creationTime;
	private HyperLinkResourceWrapper creator;
	private boolean nonpublic;
	private HyperLinkResourceWrapper documents;

	public HyperLinkResource getLink()
	{
		return link;
	}

	public void setLink(HyperLinkResource link)
	{
		this.link = link;
	}

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

	public String getCreationTime()
	{
		return creationTime;
	}

	public void setCreationTime(String creationTime)
	{
		this.creationTime = creationTime;
	}

	public HyperLinkResourceWrapper getCreator()
	{
		return creator;
	}

	public void setCreator(HyperLinkResourceWrapper creator)
	{
		this.creator = creator;
	}

	public boolean getNonpublic()
	{
		return nonpublic;
	}

	public void setNonpublic(boolean nonpublic)
	{
		this.nonpublic = nonpublic;
	}

	public HyperLinkResourceWrapper getDocuments()
	{
		return documents;
	}

	public void setDocuments(HyperLinkResourceWrapper documents)
	{
		this.documents = documents;
	}
}
