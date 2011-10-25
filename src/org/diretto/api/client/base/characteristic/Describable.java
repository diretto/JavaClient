package org.diretto.api.client.base.characteristic;

/**
 * Implementing this interface enables the objects of an implementation class of
 * this interface to be describable.
 * 
 * @author Tobias Schlecht
 */
public interface Describable
{
	/**
	 * Returns the title.
	 * 
	 * @return The title
	 */
	String getTitle();

	/**
	 * Returns the description.
	 * 
	 * @return The description
	 */
	String getDescription();
}
