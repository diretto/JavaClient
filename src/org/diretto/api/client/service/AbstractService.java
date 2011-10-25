package org.diretto.api.client.service;

import java.net.URL;

import org.diretto.api.client.JavaClient;

/**
 * This {@code abstract} class provides a skeletal implementation of the
 * {@link Service} interface, to minimize the effort required to implement this
 * interface.
 * 
 * @author Tobias Schlecht
 */
public abstract class AbstractService implements Service
{
	protected final JavaClient javaClient;
	protected final ServiceID serviceID;
	protected final URL serviceURL;

	/**
	 * Provides base implementation to construct a {@link Service}.
	 * 
	 * @param serviceID The corresponding {@code ServiceID}
	 * @param serviceURL The corresponding service {@code URL}
	 * @param javaClient The corresponding {@code JavaClient}
	 */
	protected AbstractService(ServiceID serviceID, URL serviceURL, JavaClient javaClient)
	{
		if(serviceID == null || serviceURL == null || javaClient == null)
		{
			throw new NullPointerException();
		}

		this.serviceID = serviceID;
		this.serviceURL = serviceURL;
		this.javaClient = javaClient;
	}

	@Override
	public ServiceID getServiceID()
	{
		return serviceID;
	}

	@Override
	public URL getServiceURL()
	{
		return serviceURL;
	}
}
