package org.diretto.api.client.service;

/**
 * This interface is for the identification of a {@link Service}.
 * 
 * @author Tobias Schlecht
 */
public interface ServiceID
{
	/**
	 * Returns the unique name of the {@link Service}.
	 * 
	 * @return The unique name of the {@code Service}
	 */
	String getName();

	/**
	 * Returns the API version of the {@link Service}.
	 * 
	 * @return The API version of the {@code Service}
	 */
	String getAPIVersion();
}
