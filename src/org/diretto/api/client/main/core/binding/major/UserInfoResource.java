package org.diretto.api.client.main.core.binding.major;

import org.diretto.api.client.main.core.binding.resources.UserResource;

/**
 * This class represents a POJO based {@code UserInfoResource}. <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class UserInfoResource
{
	private UserResource user;

	public UserResource getUser()
	{
		return user;
	}

	public void setUser(UserResource user)
	{
		this.user = user;
	}
}
