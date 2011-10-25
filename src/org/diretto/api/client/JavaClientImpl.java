package org.diretto.api.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.configuration.XMLConfiguration;
import org.diretto.api.client.base.exceptions.NoSuchServiceException;
import org.diretto.api.client.base.external.JacksonConverter;
import org.diretto.api.client.main.core.CoreService;
import org.diretto.api.client.main.core.CoreServiceImpl;
import org.diretto.api.client.main.core.binding.major.ServiceRegistryResource;
import org.diretto.api.client.main.core.binding.resources.ServicesResource;
import org.diretto.api.client.service.AbstractService;
import org.diretto.api.client.service.AbstractServiceID;
import org.diretto.api.client.service.Service;
import org.diretto.api.client.service.ServiceID;
import org.diretto.api.client.service.ServicePluginID;
import org.diretto.api.client.session.SystemSession;
import org.diretto.api.client.util.ConfigUtils;
import org.restlet.Client;
import org.restlet.Context;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;
import org.restlet.engine.converter.ConverterHelper;
import org.restlet.resource.ClientResource;

/**
 * This class is the implementation class of the {@link JavaClient} interface.
 * 
 * @author Tobias Schlecht
 */
public final class JavaClientImpl implements JavaClient
{
	private static final String CONFIG_FILE = "org/diretto/api/client/config.xml";

	private static final XMLConfiguration xmlConfiguration = ConfigUtils.getXMLConfiguration(CONFIG_FILE);

	private final URL apiBaseURL;
	private final SystemSession systemSession;

	private final Map<ServiceID, Service> apiServices = new ConcurrentHashMap<ServiceID, Service>();
	private final Map<ServicePluginID, Service> pluginServices = new ConcurrentHashMap<ServicePluginID, Service>();

	private CoreService coreService = null;
	private Client client = null;

	/**
	 * The constructor is {@code private} to have strict control what instances
	 * exist at any time. Instead of the constructor the {@code public}
	 * <i>static factory method</i> {@link #getInstance(SystemSession)} returns
	 * the instances of the class.
	 * 
	 * @param systemSession The corresponding {@code SystemSession}
	 */
	private JavaClientImpl(SystemSession systemSession)
	{
		this.systemSession = systemSession;

		apiBaseURL = systemSession.getAPIBaseURL();

		ClientResource clientResource = new ClientResource(apiBaseURL.toExternalForm() + "/service/registry");
		clientResource.setNext(getRestletClient());
		ServiceRegistryResource serviceRegistryResource = clientResource.get(ServiceRegistryResource.class);
		
		System.out.println("[CoreService JavaClientImpl] " + apiBaseURL.toExternalForm() + "/service/registry");
		
		ArrayList<ServicesResource.ListEntry> services = serviceRegistryResource.getServices().getList();

		for(ServicesResource.ListEntry listEntry : services)
		{
			try
			{
				ServiceID serviceID = new AbstractServiceID(listEntry.getAPI().getName(), listEntry.getAPI().getVersion())
				{
				};

				Service service = new AbstractService(serviceID, new URL(listEntry.getLink().getHref()), this)
				{
				};

				apiServices.put(serviceID, service);
			}
			catch(MalformedURLException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Returns a {@link JavaClient} instance for the given {@link SystemSession}.
	 * 
	 * @param systemSession The corresponding {@code SystemSession}
	 * @return A {@code JavaClient} instance
	 */
	static synchronized JavaClient getInstance(SystemSession systemSession)
	{
		return new JavaClientImpl(systemSession);
	}

	@Override
	public URL getAPIBaseURL()
	{
		return apiBaseURL;
	}

	@Override
	public SystemSession getSystemSession()
	{
		return systemSession;
	}

	@Override
	public synchronized CoreService getCoreService()
	{
		if(coreService == null)
		{
			coreService = CoreServiceImpl.getInstance(this);
		}

		return coreService;
	}

	@Override
	public synchronized Service getService(ServicePluginID servicePluginID)
	{
		Service service = pluginServices.get(servicePluginID);

		if(service == null)
		{
			service = apiServices.get(servicePluginID.asServiceID());

			if(service == null)
			{
				throw new NoSuchServiceException();
			}

			try
			{
				service = (Service) servicePluginID.getServiceClass().getDeclaredMethod("getInstance", URL.class, JavaClient.class).invoke(null, service.getServiceURL(), this);
			}
			catch(NoSuchMethodException e)
			{
				System.err.println("WARNING: The class \"" + JavaClientImpl.class.getCanonicalName() + "\" is not able to find the method declaration \"public static Service getInstance(URL, JavaClient)\" in the specified service class \"" + servicePluginID.getServiceClass().getCanonicalName() + "\". Without this method the \"JavaClient\" is not able to provide the requested service.");
			}
			catch(IllegalAccessException e)
			{
				System.err.println("WARNING: The class \"" + JavaClientImpl.class.getCanonicalName() + "\" does not have access to the definition of the specified method \"" + servicePluginID.getServiceClass().getCanonicalName() + ".getInstance(URL, JavaClient)\". Use the access modifier \"public\" for this method declaration.");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			pluginServices.put(servicePluginID, service);
		}

		return service;
	}

	/**
	 * Returns the <i>Restlet</i> {@link Client} of this {@link JavaClient}.
	 * 
	 * @return The <i>Restlet</i> {@code Client}
	 */
	public synchronized Client getRestletClient()
	{
		if(client == null)
		{
			Context restletContext = new Context();

			String[] names = xmlConfiguration.getStringArray("restlet-client/connector-parameters/parameter/@name");
			String[] values = xmlConfiguration.getStringArray("restlet-client/connector-parameters/parameter/@value");

			for(int i = 0; i < names.length; i++)
			{
				restletContext.getParameters().add(names[i], values[i]);
			}

			client = new Client(restletContext, Protocol.valueOf(xmlConfiguration.getString("restlet-client/connector-protocol")));

			List<ConverterHelper> converters = Engine.getInstance().getRegisteredConverters();
			converters.clear();
			converters.add(new JacksonConverter());

			try
			{
				client.start();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		return client;
	}
}
