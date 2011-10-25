package org.diretto.api.client.session;

import java.net.URL;

/**
 * This interface represents a {@code SystemSession}.
 * 
 * @author Tobias Schlecht
 */
public interface SystemSession extends Session
{
	/**
	 * Returns the base {@link URL} of the API.
	 * 
	 * @return The base {@code URL} of the API
	 */
	URL getAPIBaseURL();
}
