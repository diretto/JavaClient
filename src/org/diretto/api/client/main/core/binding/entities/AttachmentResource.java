package org.diretto.api.client.main.core.binding.entities;

import java.util.ArrayList;

import org.diretto.api.client.main.core.binding.resources.ContributorResource;
import org.diretto.api.client.main.core.binding.resources.FileResource;
import org.diretto.api.client.main.core.binding.resources.HyperLinkResource;
import org.diretto.api.client.main.core.binding.resources.HyperLinkResourceWrapper;
import org.diretto.api.client.main.core.binding.resources.UserHyperLinkResource;
import org.diretto.api.client.main.core.binding.resources.VotesResource;

/**
 * This class represents a POJO based {@code AttachmentResource}. <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class AttachmentResource
{
	private HyperLinkResource link;
	private String title;
	private String description;
	private String publishedTime;
	private HyperLinkResourceWrapper publisher;
	private String mimeType;
	private FileResource file;
	private HyperLinkResourceWrapper external;
	private VotesResource votes;
	private String license;
	private ArrayList<ContributorResource> contributors;
	private ArrayList<UserHyperLinkResource> creators;

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

	public String getPublishedTime()
	{
		return publishedTime;
	}

	public void setPublishedTime(String publishedTime)
	{
		this.publishedTime = publishedTime;
	}

	public HyperLinkResourceWrapper getPublisher()
	{
		return publisher;
	}

	public void setPublisher(HyperLinkResourceWrapper publisher)
	{
		this.publisher = publisher;
	}

	public String getMimeType()
	{
		return mimeType;
	}

	public void setMimeType(String mimeType)
	{
		this.mimeType = mimeType;
	}

	public FileResource getFile()
	{
		return file;
	}

	public void setFile(FileResource file)
	{
		this.file = file;
	}

	public HyperLinkResourceWrapper getExternal()
	{
		return external;
	}

	public void setExternal(HyperLinkResourceWrapper external)
	{
		this.external = external;
	}

	public VotesResource getVotes()
	{
		return votes;
	}

	public void setVotes(VotesResource votes)
	{
		this.votes = votes;
	}

	public String getLicense()
	{
		return license;
	}

	public void setLicense(String license)
	{
		this.license = license;
	}

	public ArrayList<ContributorResource> getContributors()
	{
		return contributors;
	}

	public void setContributors(ArrayList<ContributorResource> contributors)
	{
		this.contributors = contributors;
	}

	public ArrayList<UserHyperLinkResource> getCreators()
	{
		return creators;
	}

	public void setCreators(ArrayList<UserHyperLinkResource> creators)
	{
		this.creators = creators;
	}
}
