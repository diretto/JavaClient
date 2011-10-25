package org.diretto.api.client.base.data;

/**
 * This interface represents a {@code Contributor}.
 * 
 * @author Tobias Schlecht
 */
public interface Contributor
{
	/**
	 * Determines whether this {@link Contributor} is an external
	 * {@code Contributor}.
	 * 
	 * @return {@code true} if this {@code Contributor} is an external
	 *         {@code Contributor}; otherwise {@code false}
	 */
	boolean isExternalContributor();
}
