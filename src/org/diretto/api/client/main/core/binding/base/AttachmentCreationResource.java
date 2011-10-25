package org.diretto.api.client.main.core.binding.base;

import java.util.ArrayList;

import org.diretto.api.client.main.core.binding.resources.ContributorResource;
import org.diretto.api.client.main.core.binding.resources.UserHyperLinkResource;

/**
 * This {@code abstract} class serves as base class for all
 * {@code AttachmentCreationResource}s, to minimize the effort required to
 * implement a specific {@code AttachmentCreationResource}.
 * 
 * @author Tobias Schlecht
 */
public abstract class AttachmentCreationResource
{
	private String title;
	private String description;
	private String mimeType;
	private String license;
	private ArrayList<ContributorResource.ContributorEntry> contributors;
	private ArrayList<UserHyperLinkResource> creators;

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

	public String getMimeType()
	{
		return mimeType;
	}

	public void setMimeType(String mimeType)
	{
		this.mimeType = mimeType;
	}

	public String getLicense()
	{
		return license;
	}

	public void setLicense(String license)
	{
		this.license = license;
	}

	public ArrayList<ContributorResource.ContributorEntry> getContributors()
	{
		return contributors;
	}

	public void setContributors(ArrayList<ContributorResource.ContributorEntry> contributors)
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
