package org.diretto.api.client.service;

import java.net.URL;

/**
 * This interface represents a {@code Service}.
 * 
 * @author Tobias Schlecht
 */
public interface Service
{
	/**
	 * Returns the corresponding {@link ServiceID}.
	 * 
	 * @return The corresponding {@code ServiceID}
	 */
	ServiceID getServiceID();

	/**
	 * Returns the service {@link URL}.
	 * 
	 * @return The service {@code URL}
	 */
	URL getServiceURL();
}
