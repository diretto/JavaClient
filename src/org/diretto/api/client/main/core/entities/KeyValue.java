package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.entities.SubEntity;
import org.diretto.api.client.user.UserID;

/**
 * This interface represents a {@code KeyValue}.
 * 
 * @author Tobias Schlecht
 */
public interface KeyValue extends SubEntity<KeyValueID>
{
	/**
	 * Returns the {@link UserID} of the creator.
	 * 
	 * @return The {@code UserID} of the creator
	 */
	UserID getCreator();

	/**
	 * Returns the key of the {@link KeyValue}.
	 * 
	 * @return The key of the {@code KeyValue}
	 */
	String getKey();

	/**
	 * Returns the value of the {@link KeyValue}.
	 * 
	 * @return The value of the {@code KeyValue}
	 */
	String getValue();
}
