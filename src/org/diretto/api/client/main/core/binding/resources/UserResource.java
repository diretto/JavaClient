package org.diretto.api.client.main.core.binding.resources;

/**
 * This class represents a POJO based {@code UserResource}. <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class UserResource
{
	private HyperLinkResource link;
	private String username;
	private String email;

	public HyperLinkResource getLink()
	{
		return link;
	}

	public void setLink(HyperLinkResource link)
	{
		this.link = link;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
}
