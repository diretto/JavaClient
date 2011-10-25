package org.diretto.api.client.session;

import org.diretto.api.client.user.User;

/**
 * This {@code abstract} class provides a skeletal implementation of the
 * {@link Session} interface, to minimize the effort required to implement this
 * interface.
 * 
 * @author Tobias Schlecht
 */
abstract class AbstractSession implements Session
{
	private final User user;

	/**
	 * Provides base implementation to construct a {@link Session}.
	 * 
	 * @param user The {@code User} of this {@code Session}
	 */
	AbstractSession(User user)
	{
		if(user == null)
		{
			throw new NullPointerException();
		}

		this.user = user;
	}

	@Override
	public User getUser()
	{
		return user;
	}
}
