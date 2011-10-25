package org.diretto.api.client.session;

import java.net.URL;

import org.diretto.api.client.user.User;

/**
 * The {@code SessionFactory} is a noninstantiable factory class and is
 * responsible for creating instances of the {@link Session} interface.
 * 
 * @author Tobias Schlecht
 */
public final class SessionFactory
{
	/**
	 * The constructor is {@code private} to suppress the default constructor
	 * for noninstantiability.
	 */
	private SessionFactory()
	{
		throw new AssertionError();
	}

	/**
	 * Returns an instance of the requested {@link SystemSession}.
	 * 
	 * @param apiBaseURL The API base {@code URL}
	 * @param system The corresponding system {@code User} object
	 * @return An instance of the requested {@code SystemSession}
	 */
	public static SystemSession getSystemSessionInstance(URL apiBaseURL, User system)
	{
		return new SystemSessionImpl(apiBaseURL, system);
	}

	/**
	 * Returns an instance of the requested {@link UserSession}.
	 * 
	 * @param user The corresponding {@code User}
	 * @return An instance of the requested {@code UserSession}
	 */
	public static UserSession getUserSessionInstance(User user)
	{
		return new UserSessionImpl(user);
	}
}
