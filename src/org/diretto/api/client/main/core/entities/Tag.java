package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.characteristic.Creatable;
import org.diretto.api.client.base.characteristic.Votable;
import org.diretto.api.client.base.entities.SubEntity;

/**
 * This interface represents a {@code Tag}.
 * 
 * @author Tobias Schlecht
 */
public interface Tag extends SubEntity<TagID>, Votable, Creatable
{
	/**
	 * Returns the value of the {@link Tag}.
	 * 
	 * @return The value of the {@code Tag}
	 */
	String getValue();
}
