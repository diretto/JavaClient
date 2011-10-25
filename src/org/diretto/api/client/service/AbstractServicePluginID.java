package org.diretto.api.client.service;

/**
 * This {@code abstract} class provides a skeletal implementation of the
 * {@link ServicePluginID} interface, to minimize the effort required to
 * implement this interface.
 * 
 * @author Tobias Schlecht
 */
public abstract class AbstractServicePluginID extends AbstractServiceID implements ServicePluginID
{
	private volatile int hashCode = 0;

	protected final ServiceID serviceID;

	protected final Class<Service> serviceClass;

	/**
	 * Provides base implementation to construct a {@link ServicePluginID}.
	 * 
	 * @param name The unique name of the {@code Service}
	 * @param apiVersion The API version of the Service
	 * @param serviceClass The implementation class of the {@code Service}
	 */
	protected AbstractServicePluginID(String name, String apiVersion, Class<Service> serviceClass)
	{
		super(name, apiVersion);

		serviceID = new AbstractServiceID(name, apiVersion)
		{
		};

		this.serviceClass = serviceClass;
	}

	@Override
	public Class<Service> getServiceClass()
	{
		return serviceClass;
	}

	@Override
	public ServiceID asServiceID()
	{
		return serviceID;
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

		return serviceID.getName().equals(name) && serviceID.getAPIVersion().equals(apiVersion) && serviceID.getClass().equals(serviceClass);
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
			result = 31 * result + serviceClass.hashCode();
			hashCode = result;
		}

		return result;
	}

	@Override
	public String toString()
	{
		return "[" + name + " - " + apiVersion + " - " + serviceClass.toString() + "]";
	}
}
