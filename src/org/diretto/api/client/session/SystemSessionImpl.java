package org.diretto.api.client.session;

import java.net.URL;

import org.diretto.api.client.user.User;
import org.diretto.api.client.util.URLTransformationUtils;

/**
 * This class is the implementation class of the {@link SystemSession}
 * interface.
 * 
 * @author Tobias Schlecht
 */
final class SystemSessionImpl extends AbstractSession implements SystemSession
{
	private final URL apiBaseURL;

	/**
	 * Constructs an object of the {@link SystemSession} interface.
	 * 
	 * @param apiBaseURL The API base {@code URL}
	 * @param system The corresponding system {@code User} object
	 */
	SystemSessionImpl(URL apiBaseURL, User system)
	{
		super(system);
		
		this.apiBaseURL = URLTransformationUtils.adjustAPIBaseURL(apiBaseURL);
	}

	@Override
	public URL getAPIBaseURL()
	{
		return apiBaseURL;
	}
}
