package org.diretto.api.client;

import java.net.URL;

import org.diretto.api.client.main.core.CoreService;
import org.diretto.api.client.service.Service;
import org.diretto.api.client.service.ServicePluginID;
import org.diretto.api.client.session.SystemSession;

/**
 * This interface represents a {@code JavaClient}. <br/><br/>
 * 
 * A {@code JavaClient} is particularly responsible for the administration of
 * the platform {@link Service}s.
 * 
 * @author Tobias Schlecht
 */
public interface JavaClient
{
	/**
	 * Returns the base {@link URL} of the API.
	 * 
	 * @return The base {@code URL} of the API
	 */
	URL getAPIBaseURL();

	/**
	 * Returns the corresponding {@link SystemSession}.
	 * 
	 * @return The corresponding {@code SystemSession}
	 */
	SystemSession getSystemSession();

	/**
	 * Returns the corresponding {@link CoreService}.
	 * 
	 * @return The corresponding {@code CoreService}
	 */
	CoreService getCoreService();

	/**
	 * Returns the corresponding {@link Service} for the given
	 * {@link ServicePluginID}.
	 * 
	 * @param servicePluginID A {@code ServicePluginID}
	 * @return The corresponding {@code Service}
	 */
	Service getService(ServicePluginID servicePluginID);
}
