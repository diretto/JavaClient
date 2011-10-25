package org.diretto.api.client.user;

import java.net.URL;

import org.diretto.api.client.base.entities.AbstractEntityID;

/**
 * This class is the implementation class of the {@link UserID} interface.
 * 
 * @author Tobias Schlecht
 */
final class UserIDImpl extends AbstractEntityID implements UserID
{
	/**
	 * Constructs an object of the {@link UserID} interface.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 */
	UserIDImpl(URL uniqueResourceURL)
	{
		super(uniqueResourceURL);
	}
}
