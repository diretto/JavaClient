package org.diretto.api.client.user;

import org.diretto.api.client.base.entities.Entity;
import org.diretto.api.client.session.UserSession;

/**
 * This interface represents a {@code User}.
 * 
 * @author Tobias Schlecht
 */
public interface User extends Entity<UserID>
{
	/**
	 * Returns the corresponding community information.
	 * 
	 * @return The corresponding community information
	 */
	UserInfo getUserInfo();

	/**
	 * Returns the corresponding Email address.
	 * 
	 * @return The corresponding Email address
	 */
	String getEmailAddress();

	/**
	 * Returns the corresponding authentication ID. <br/><br/>
	 * 
	 * <i>Annotation:</i> The authentication ID is a MD5 hash of the Email
	 * address.
	 * 
	 * @return The corresponding authentication ID
	 */
	String getAuthID();

	/**
	 * Changes the name of the {@link User}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param userName The new name of the {@code User}
	 * @return {@code true} if the change was successful; otherwise
	 *         {@code false}
	 */
	boolean changeUserName(UserSession userSession, String userName);

	/**
	 * Changes the password of the {@link User}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param password The new password of the {@code User}
	 * @return {@code true} if the change was successful; otherwise
	 *         {@code false}
	 */
	boolean changePassword(UserSession userSession, String password);

	/**
	 * Determines if the given password represents the same sequence of
	 * characters as the password of this {@link User}.
	 * 
	 * @param password The password to compare
	 * @return {@code true} if the given password represents the same sequence
	 *         of characters as the password of this {@code User}; otherwise
	 *         {@code false}
	 */
	boolean verifyPassword(String password);
}
