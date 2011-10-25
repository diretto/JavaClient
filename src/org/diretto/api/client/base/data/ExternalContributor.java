package org.diretto.api.client.base.data;

import java.net.URL;

/**
 * This class is one of the implementation classes of the {@link Contributor}
 * interface and represents an {@code ExternalContributor}. <br/><br/>
 * 
 * <i>Annotation:</i> It is an immutable class.
 * 
 * @author Tobias Schlecht
 */
public class ExternalContributor implements Contributor
{
	private final String name;
	private final URL site;

	/**
	 * Constructs an {@link ExternalContributor}.
	 * 
	 * @param name The name of the {@code ExternalContributor}
	 * @param site The site {@code URL} or {@code null} if the
	 *        {@code ExternalContributor} has no site.
	 */
	public ExternalContributor(String name, URL site)
	{
		this.name = name;
		this.site = site;
	}

	@Override
	public boolean isExternalContributor()
	{
		return true;
	}

	/**
	 * Returns the name of the {@link ExternalContributor}.
	 * 
	 * @return The name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Returns the site {@link URL} of the {@link ExternalContributor}.
	 * 
	 * @return The site {@code URL} or {@code null} if the
	 *         {@code ExternalContributor} has no site.
	 */
	public URL getSite()
	{
		return site;
	}
}
