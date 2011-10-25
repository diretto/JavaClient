package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.characteristic.Creatable;
import org.diretto.api.client.base.characteristic.Votable;
import org.diretto.api.client.base.entities.SubEntity;

/**
 * This interface represents a {@code Comment}.
 * 
 * @author Tobias Schlecht
 */
public interface Comment extends SubEntity<CommentID>, Votable, Creatable
{
	/**
	 * Returns the content of the {@link Comment}.
	 * 
	 * @return The content of the {@code Comment}
	 */
	String getContent();
}
