package org.diretto.api.client.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;

/**
 * {@code ConfigUtils} is a noninstantiable utility class and is responsible for
 * configuration related aspects.
 * 
 * @author Tobias Schlecht
 */
public final class ConfigUtils
{
	private static final Map<String, XMLConfiguration> xmlConfigurations = new ConcurrentHashMap<String, XMLConfiguration>();

	/**
	 * The constructor is {@code private} to suppress the default constructor
	 * for noninstantiability.
	 */
	private ConfigUtils()
	{
		throw new AssertionError();
	}

	/**
	 * Returns the {@link XMLConfiguration} object, which is loaded from the
	 * specified XML configuration file.
	 * 
	 * @param configFile The name of the XML configuration file
	 * @return The {@code XMLConfiguration} object
	 */
	public static synchronized XMLConfiguration getXMLConfiguration(String configFile)
	{
		XMLConfiguration xmlConfiguration = xmlConfigurations.get(configFile);

		if(xmlConfiguration == null)
		{
			try
			{
				xmlConfiguration = new XMLConfiguration(configFile);
				xmlConfiguration.setExpressionEngine(new XPathExpressionEngine());
				xmlConfiguration.setThrowExceptionOnMissing(true);
				xmlConfigurations.put(configFile, xmlConfiguration);
			}
			catch(ConfigurationException e)
			{
				e.printStackTrace();
			}
		}

		return xmlConfiguration;
	}
}
