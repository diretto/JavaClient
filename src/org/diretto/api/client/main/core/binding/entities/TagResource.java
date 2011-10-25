package org.diretto.api.client.main.core.binding.entities;

import org.diretto.api.client.main.core.binding.resources.HyperLinkResource;
import org.diretto.api.client.main.core.binding.resources.HyperLinkResourceWrapper;
import org.diretto.api.client.main.core.binding.resources.VotesResource;

/**
 * This class represents a POJO based {@code TagResource}. <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class TagResource
{
	private HyperLinkResource link;
	private HyperLinkResourceWrapper baseTag;
	private String value;
	private String creationTime;
	private HyperLinkResourceWrapper creator;
	private VotesResource votes;

	public HyperLinkResource getLink()
	{
		return link;
	}

	public void setLink(HyperLinkResource link)
	{
		this.link = link;
	}

	public HyperLinkResourceWrapper getBaseTag()
	{
		return baseTag;
	}

	public void setBaseTag(HyperLinkResourceWrapper baseTag)
	{
		this.baseTag = baseTag;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
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

	public VotesResource getVotes()
	{
		return votes;
	}

	public void setVotes(VotesResource votes)
	{
		this.votes = votes;
	}
}
