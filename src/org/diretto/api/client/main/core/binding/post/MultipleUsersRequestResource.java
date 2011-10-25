package org.diretto.api.client.main.core.binding.post;

import java.util.ArrayList;

/**
 * This class represents a POJO based {@code MultipleUsersRequestResource}.
 * <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class MultipleUsersRequestResource
{
	private ArrayList<String> users;

	public ArrayList<String> getUsers()
	{
		return users;
	}

	public void setUsers(ArrayList<String> users)
	{
		this.users = users;
	}
}
