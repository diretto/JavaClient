package org.diretto.api.client.session;

import org.diretto.api.client.user.User;

/**
 * This interface represents a {@code Session}.
 * 
 * @author Tobias Schlecht
 */
public interface Session
{
	/**
	 * Returns the {@link User} of this {@link Session}.
	 * 
	 * @return The {@code User} of this {@code Session}
	 */
	User getUser();
}
