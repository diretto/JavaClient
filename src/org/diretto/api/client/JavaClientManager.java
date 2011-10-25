package org.diretto.api.client;

import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.diretto.api.client.session.SessionFactory;
import org.diretto.api.client.session.SystemSession;
import org.diretto.api.client.user.UserFactory;

/**
 * The {@code JavaClientManager} is responsible for the administration of the
 * {@link JavaClient}s and serves as the first contact point in respect of the
 * interaction with the client applications. <br/><br/>
 * 
 * <i>Annotation:</i> <u>Singleton Pattern</u> (Enum Singleton)
 * 
 * @author Tobias Schlecht
 */
public enum JavaClientManager
{
	INSTANCE;

	private final Map<String, JavaClient> javaClients = new ConcurrentHashMap<String, JavaClient>();

	/**
	 * Constructs the sole instance of the {@link JavaClientManager}. <br/><br/>
	 * 
	 * <i>Annotation:</i> <u>Singleton Pattern</u> (Enum Singleton)
	 */
	JavaClientManager()
	{

	}

	/**
	 * Returns a {@link JavaClient} for the given {@link SystemSession}.
	 * 
	 * @param systemSession The corresponding {@code SystemSession}
	 * @return The {@code JavaClient}
	 */
	public synchronized JavaClient getJavaClient(SystemSession systemSession)
	{
		URL apiBaseURL = systemSession.getAPIBaseURL();

		JavaClient javaClient = javaClients.get(apiBaseURL.toExternalForm());

		if(javaClient == null)
		{
			javaClient = JavaClientImpl.getInstance(systemSession);
			javaClients.put(apiBaseURL.toExternalForm(), javaClient);
		}

		return javaClient;
	}

	/**
	 * Returns the {@link SystemSession} for the given data.
	 * 
	 * @param apiBaseURL The API base {@code URL}
	 * @param emailAddress The Email address of the system {@code User} account
	 * @param password The password of the system {@code User} account
	 * @return The {@code SystemSession}
	 */
	public SystemSession getSystemSession(URL apiBaseURL, String emailAddress, String password)
	{
		return SessionFactory.getSystemSessionInstance(apiBaseURL, UserFactory.getUserInstance(null, null, apiBaseURL, emailAddress, password));
	}
}
