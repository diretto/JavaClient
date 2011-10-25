package org.diretto.api.client.session;

import org.diretto.api.client.user.User;

/**
 * This class is the implementation class of the {@link UserSession} interface.
 * 
 * @author Tobias Schlecht
 */
final class UserSessionImpl extends AbstractSession implements UserSession
{
	/**
	 * Constructs an object of the {@link UserSession} interface.
	 * 
	 * @param user The corresponding {@code User}
	 */
	UserSessionImpl(User user)
	{
		super(user);
	}
}
