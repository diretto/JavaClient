package org.diretto.api.client.service;

/**
 * This {@code abstract} class provides a skeletal implementation of the
 * {@link ServiceID} interface, to minimize the effort required to implement
 * this interface.
 * 
 * @author Tobias Schlecht
 */
public abstract class AbstractServiceID implements ServiceID
{
	private volatile int hashCode = 0;

	protected final String name;
	protected final String apiVersion;

	/**
	 * Provides base implementation to construct a {@link ServiceID}.
	 * 
	 * @param name The unique name of the {@code Service}
	 * @param apiVersion The API version of the {@code Service}
	 */
	protected AbstractServiceID(String name, String apiVersion)
	{
		this.name = name;
		this.apiVersion = apiVersion;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public String getAPIVersion()
	{
		return apiVersion;
	}

	@Override
	public boolean equals(Object o)
	{
		if(o == this)
		{
			return true;
		}

		if(!(o instanceof ServiceID))
		{
			return false;
		}

		ServiceID serviceID = (ServiceID) o;

		return serviceID.getName().equals(name) && serviceID.getAPIVersion().equals(apiVersion);
	}

	@Override
	public int hashCode()
	{
		int result = hashCode;

		if(result == 0)
		{
			result = 17;
			result = 31 * result + name.hashCode();
			result = 31 * result + apiVersion.hashCode();
			hashCode = result;
		}

		return result;
	}

	@Override
	public String toString()
	{
		return "[" + name + " - " + apiVersion + "]";
	}
}
