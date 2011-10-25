package org.diretto.api.client.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.diretto.api.client.base.entities.Entity;
import org.diretto.api.client.base.entities.SubEntity;
import org.diretto.api.client.main.core.CoreServiceID;

/**
 * {@code URLTransformationUtils} is a noninstantiable utility class and
 * provides miscellaneous methods for transforming the structure of API, service
 * and resource {@code URL}s.
 * 
 * @author Tobias Schlecht
 */
public final class URLTransformationUtils
{
	/**
	 * The constructor is {@code private} to suppress the default constructor
	 * for noninstantiability.
	 */
	private URLTransformationUtils()
	{
		throw new AssertionError();
	}

	/**
	 * Returns a new corrected {@link URL} instance of the given API base
	 * {@code URL}. The delivered {@code URL} will not be modified. <br/><br/>
	 * 
	 * <i>Annotation:</i> An example for the {@code String} representation of a
	 * corrected API base {@code URL} is {@code http://api.diretto.org/v2}
	 * 
	 * @param apiBaseURL The API base {@code URL}
	 * @return A new corrected {@code URL} instance
	 */
	public static URL adjustAPIBaseURL(URL apiBaseURL)
	{
		URL resultAPIBaseURL = null;

		try
		{
			resultAPIBaseURL = new URL(adjustAPIBaseURL(apiBaseURL.toExternalForm()));
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}

		return resultAPIBaseURL;
	}

	/**
	 * Returns a new corrected {@code String} representation from the given
	 * {@code String} representation of the API base {@code URL}. <br/><br/>
	 * 
	 * <i>Annotation:</i> An example for the {@code String} representation of a
	 * corrected API base {@code URL} is {@code http://api.diretto.org/v2}
	 * 
	 * @param apiBaseURL The {@code String} representation of the API base
	 *        {@code URL}
	 * @return A new corrected {@code String} representation
	 */
	public static String adjustAPIBaseURL(String apiBaseURL)
	{
		apiBaseURL = apiBaseURL.trim();

		if(!(apiBaseURL.startsWith("http://") || apiBaseURL.startsWith("https://")))
		{
			apiBaseURL = "http://" + apiBaseURL;
		}

		String apiVersion = CoreServiceID.INSTANCE.getAPIVersion();

		if(!apiBaseURL.endsWith("/" + apiVersion))
		{
			if(apiBaseURL.endsWith("/" + apiVersion + "/"))
			{
				apiBaseURL = apiBaseURL.substring(0, apiBaseURL.length() - 1);
			}
			else if(apiBaseURL.endsWith("/"))
			{
				apiBaseURL = apiBaseURL + apiVersion;
			}
			else
			{
				apiBaseURL = apiBaseURL + "/" + apiVersion;
			}
		}

		return apiBaseURL;
	}

	/**
	 * Returns a new corrected {@link URL} instance of the given service
	 * {@code URL}. The delivered {@code URL} will not be modified. <br/><br/>
	 * 
	 * <i>Annotation:</i> An example for the {@code String} representation of a
	 * corrected service {@code URL} is {@code http://task.diretto.org/v2} or
	 * {@code http://media.diretto.org}.
	 * 
	 * @param serviceURL The service {@code URL}
	 * @return A new corrected {@code URL} instance
	 */
	public static URL adjustServiceURL(URL serviceURL)
	{
		URL resultServiceURL = null;

		try
		{
			resultServiceURL = new URL(adjustServiceURL(serviceURL.toExternalForm()));
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}

		return resultServiceURL;
	}

	/**
	 * Returns a new corrected {@code String} representation from the given
	 * {@code String} representation of the service {@code URL}. <br/><br/>
	 * 
	 * <i>Annotation:</i> An example for the {@code String} representation of a
	 * corrected service {@code URL} is {@code http://task.diretto.org/v2} or
	 * {@code http://media.diretto.org}.
	 * 
	 * @param serviceURL The {@code String} representation of the service
	 *        {@code URL}
	 * @return A new corrected {@code String} representation
	 */
	public static String adjustServiceURL(String serviceURL)
	{
		serviceURL = serviceURL.trim();

		if(!(serviceURL.startsWith("http://") || serviceURL.startsWith("https://")))
		{
			serviceURL = "http://" + serviceURL;
		}

		if(serviceURL.endsWith("/"))
		{
			serviceURL = serviceURL.substring(0, serviceURL.length() - 1);
		}

		return serviceURL;
	}

	/**
	 * Returns the resource identifier of the given unique resource {@link URL}.
	 * <br/><br/>
	 * 
	 * <i>Annotation:</i> Note that this method only works correctly if the
	 * resource identifier is the last part of the unique resource {@code URL}
	 * and is separated from the main part by "/". For example the resource
	 * identifier {@code 2671f383-a28c-44d5-b86b-f7f04bb0a015} will be returned
	 * for a parameter with the {@code String} representation
	 * {@code http://api.diretto.org/v2/document/2671f383-a28c-44d5-b86b-f7f04bb0a015}
	 * 
	 * @param uniqueResourceURL A unique resource {@code URL}
	 * @return The resource identifier
	 */
	public static String getResourceIdentifier(URL uniqueResourceURL)
	{
		return getResourceIdentifier(uniqueResourceURL.toExternalForm());
	}

	/**
	 * Returns the resource identifier of the given {@code String}
	 * representation from an unique resource {@code URL}. <br/><br/>
	 * 
	 * <i>Annotation:</i> Note that this method only works correctly if the
	 * resource identifier is the last part of the unique resource {@code URL}
	 * and is separated from the main part by "/". For example the resource
	 * identifier {@code 2671f383-a28c-44d5-b86b-f7f04bb0a015} will be returned
	 * for the parameter
	 * {@code http://api.diretto.org/v2/document/2671f383-a28c-44d5-b86b-f7f04bb0a015}
	 * 
	 * @param uniqueResourceURL A unique resource {@code URL}
	 * @return The resource identifier
	 */
	public static String getResourceIdentifier(String uniqueResourceURL)
	{
		uniqueResourceURL = uniqueResourceURL.trim();

		if(uniqueResourceURL.endsWith("/"))
		{
			uniqueResourceURL = uniqueResourceURL.substring(0, uniqueResourceURL.length() - 1);
		}

		return uniqueResourceURL.substring(uniqueResourceURL.lastIndexOf("/") + 1, uniqueResourceURL.length()).trim();
	}

	/**
	 * Returns a {@code String} representation of the unique resource
	 * {@code URL} corresponding to the parent {@link Entity} of the
	 * {@link SubEntity} which is represented by the given unique resource
	 * {@code URL}. <br/><br/>
	 * 
	 * <i>Annotation:</i> Note that this method only works correctly if the
	 * parts of the unique resource {@code URL} are separated by "/". For
	 * example the {@code String} representation of the unique resource
	 * {@code URL}
	 * {@code http://api.diretto.org/v2/document/2671f383-a28c-44d5-b86b-f7f04bb0a015-a28c-44d5-b86b-f7f04bb0a015}
	 * will be returned for a parameter with the {@code String} representation
	 * {@code http://api.diretto.org/v2/document/2671f383-a28c-44d5-b86b-f7f04bb0a015-a28c-44d5-b86b-f7f04bb0a015/attachment/8552e623-ea4b-4778-95b3-71435f319738}
	 * 
	 * @param uniqueResourceURL A unique resource {@code URL} corresponding to a
	 *        {@code SubEntity}
	 * @return The result {@code String} representation
	 */
	public static String removeSubEntityPart(URL uniqueResourceURL)
	{
		return removeSubEntityPart(uniqueResourceURL.toExternalForm());
	}

	/**
	 * Returns a {@code String} representation of the unique resource
	 * {@code URL} corresponding to the parent {@link Entity} of the
	 * {@link SubEntity} which is represented by the given unique resource
	 * {@code URL} ({@code String} representation). <br/><br/>
	 * 
	 * <i>Annotation:</i> Note that this method only works correctly if the
	 * parts of the unique resource {@code URL} are separated by "/". For
	 * example the {@code String} representation of the unique resource
	 * {@code URL}
	 * {@code http://api.diretto.org/v2/document/2671f383-a28c-44d5-b86b-f7f04bb0a015-a28c-44d5-b86b-f7f04bb0a015}
	 * will be returned for a parameter with the {@code String} representation
	 * {@code http://api.diretto.org/v2/document/2671f383-a28c-44d5-b86b-f7f04bb0a015-a28c-44d5-b86b-f7f04bb0a015/attachment/8552e623-ea4b-4778-95b3-71435f319738}
	 * 
	 * @param uniqueResourceURL A unique resource {@code URL} ({@code String}
	 *        representation) corresponding to a {@code SubEntity}
	 * @return The result {@code String} representation
	 */
	public static String removeSubEntityPart(String uniqueResourceURL)
	{
		uniqueResourceURL = uniqueResourceURL.trim();

		if(uniqueResourceURL.endsWith("/"))
		{
			uniqueResourceURL = uniqueResourceURL.substring(0, uniqueResourceURL.length() - 1);
		}

		uniqueResourceURL = uniqueResourceURL.substring(0, uniqueResourceURL.lastIndexOf("/"));

		return uniqueResourceURL.substring(0, uniqueResourceURL.lastIndexOf("/")).trim();
	}
}
