package org.diretto.api.client.main.core.binding.resources;

/**
 * This class represents a POJO based {@code DeploymentResource}. <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class DeploymentResource
{
	private String title;
	private String contact;
	private HyperLinkResourceWrapper website;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContact()
	{
		return contact;
	}

	public void setContact(String contact)
	{
		this.contact = contact;
	}

	public HyperLinkResourceWrapper getWebsite()
	{
		return website;
	}

	public void setWebsite(HyperLinkResourceWrapper website)
	{
		this.website = website;
	}
}
