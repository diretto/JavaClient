package org.diretto.api.client.base.entities;

import java.net.MalformedURLException;
import java.net.URL;

import org.diretto.api.client.util.URLTransformationUtils;

/**
 * This {@code abstract} class provides a skeletal implementation of the
 * {@link EntityID} interface, to minimize the effort required to implement this
 * interface.
 * 
 * @author Tobias Schlecht
 */
public abstract class AbstractEntityID implements EntityID
{
	private volatile int hashCode = 0;

	protected final URL uniqueResourceURL;
	protected final String resourceIdentifier;

	/**
	 * Provides base implementation to construct an {@link EntityID}.
	 * 
	 * @param uniqueResourceURL The corresponding unique resource {@code URL}
	 */
	public AbstractEntityID(URL uniqueResourceURL)
	{
		this.uniqueResourceURL = uniqueResourceURL;

		if(this.uniqueResourceURL == null)
		{
			throw new NullPointerException();
		}

		resourceIdentifier = URLTransformationUtils.getResourceIdentifier(uniqueResourceURL);
	}

	/**
	 * Provides base implementation to construct an {@link EntityID}.
	 * 
	 * @param uniqueResourceURL The {@code String} representation of the
	 *        corresponding unique resource {@code URL}
	 * @throws MalformedURLException
	 */
	public AbstractEntityID(String uniqueResourceURL) throws MalformedURLException
	{
		this(new URL(uniqueResourceURL));
	}

	@Override
	public String getResourceIdentifier()
	{
		return resourceIdentifier;
	}

	@Override
	public URL getUniqueResourceURL()
	{
		return uniqueResourceURL;
	}

	@Override
	public boolean equals(Object o)
	{
		if(o == this)
		{
			return true;
		}

		if(!(o instanceof EntityID))
		{
			return false;
		}

		EntityID entityID = (EntityID) o;

		// Comparison of the String representations of the unique resource URLs
		// instead of using the equals method of java.net.URL because of the
		// following reason:
		//
		// Joshua Bloch (Effective Java - Second Edition): "Whether or not a
		// class is immutable, do not write an equals method that depends on
		// unreliable resources. It’s extremely difficult to satisfy the
		// consistency requirement if you violate this prohibition. For example,
		// java.net.URL’s equals method relies on comparison of the IP addresses
		// of the hosts associated with the URLs. Translating a host name to an
		// IP address can require network access, and it isn’t guaranteed to
		// yield the same results over time. This can cause the URL equals
		// method to violate the equals contract and has caused problems in
		// practice. (Unfortunately, this behavior cannot be changed due to
		// compatibility requirements.)"

		return entityID.getUniqueResourceURL().toExternalForm().equals(uniqueResourceURL.toExternalForm());
	}

	@Override
	public int hashCode()
	{
		int result = hashCode;

		if(result == 0)
		{
			result = 17;
			result = 31 * result + uniqueResourceURL.toExternalForm().hashCode();
			hashCode = result;
		}

		return result;
	}

	@Override
	public String toString()
	{
		return uniqueResourceURL.toExternalForm();
	}
}
