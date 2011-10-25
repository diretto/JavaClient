package org.diretto.api.client.main.core;

import org.apache.commons.configuration.XMLConfiguration;
import org.diretto.api.client.service.AbstractServiceID;
import org.diretto.api.client.util.ConfigUtils;

/**
 * This class serves for the identification of the {@link CoreService}.
 * <br/><br/>
 * 
 * <i>Annotation:</i> <u>Singleton Pattern</u>
 * 
 * @author Tobias Schlecht
 */
public final class CoreServiceID extends AbstractServiceID
{
	private static final String CONFIG_FILE = "org/diretto/api/client/main/core/config.xml";

	private static final XMLConfiguration xmlConfiguration = ConfigUtils.getXMLConfiguration(CONFIG_FILE);

	public static final CoreServiceID INSTANCE = new CoreServiceID(xmlConfiguration.getString("name"), xmlConfiguration.getString("api-version"));

	/**
	 * Constructs the sole instance of the {@link CoreServiceID}. <br/><br/>
	 * 
	 * <i>Annotation:</i> <u>Singleton Pattern</u>
	 */
	private CoreServiceID(String name, String apiVersion)
	{
		super(name, apiVersion);
	}

	/**
	 * Returns the {@link XMLConfiguration} object, which is loaded from the XML
	 * configuration file corresponding to the whole {@link CoreService}
	 * implementation.
	 * 
	 * @return The {@code XMLConfiguration} object
	 */
	XMLConfiguration getXMLConfiguration()
	{
		return xmlConfiguration;
	}
}
