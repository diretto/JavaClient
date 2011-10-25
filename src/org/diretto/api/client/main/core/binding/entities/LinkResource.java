package org.diretto.api.client.main.core.binding.entities;

import org.diretto.api.client.main.core.binding.post.DocumentHyperLinkResource;
import org.diretto.api.client.main.core.binding.resources.HyperLinkResource;
import org.diretto.api.client.main.core.binding.resources.HyperLinkResourceWrapper;
import org.diretto.api.client.main.core.binding.resources.TagsResource;
import org.diretto.api.client.main.core.binding.resources.VotesResource;

/**
 * This class represents a POJO based {@code LinkResource}. <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class LinkResource
{
	private HyperLinkResource link;
	private String title;
	private String description;
	private String creationTime;
	private HyperLinkResourceWrapper creator;
	private DocumentHyperLinkResource source;
	private DocumentHyperLinkResource destination;
	private VotesResource votes;
	private TagsResource tags;

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

	public VotesResource getVotes()
	{
		return votes;
	}

	public void setVotes(VotesResource votes)
	{
		this.votes = votes;
	}

	public TagsResource getTags()
	{
		return tags;
	}

	public void setTags(TagsResource tags)
	{
		this.tags = tags;
	}
}
