package org.diretto.api.client.main.core.binding.resources;

/**
 * This class represents a POJO based {@code UserHyperLinkResource}. <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class UserHyperLinkResource
{
	private HyperLinkResourceWrapper user;

	public HyperLinkResourceWrapper getUser()
	{
		return user;
	}

	public void setUser(HyperLinkResourceWrapper user)
	{
		this.user = user;
	}
}
