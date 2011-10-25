package org.diretto.api.client.user;

import org.diretto.api.client.base.entities.Entity;

/**
 * This interface represents the community information of a {@link User}.
 * 
 * @author Tobias Schlecht
 */
public interface UserInfo extends Entity<UserID>
{
	/**
	 * Returns the name of the {@link User}.
	 * 
	 * @return The name of the {@code User}
	 */
	String getUserName();
}
