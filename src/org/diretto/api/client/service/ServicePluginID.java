package org.diretto.api.client.service;

/**
 * This interface is for the identification of a {@link Service} Plugin and
 * extends the interface {@link ServiceID}.
 * 
 * @author Tobias Schlecht
 */
public interface ServicePluginID extends ServiceID
{
	/**
	 * Returns the {@link Class} object representing the implementation class of
	 * the {@link Service}.
	 * 
	 * @return The {@code Class} object
	 */
	Class<Service> getServiceClass();

	/**
	 * Returns the {@link ServiceID} view of this {@link ServicePluginID}.
	 * 
	 * @return The {@code ServiceID} view of this {@code ServicePluginID}.
	 */
	ServiceID asServiceID();
}
