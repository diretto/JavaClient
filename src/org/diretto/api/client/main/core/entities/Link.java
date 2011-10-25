package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.characteristic.Creatable;
import org.diretto.api.client.base.characteristic.Describable;
import org.diretto.api.client.base.characteristic.Tagable;
import org.diretto.api.client.base.characteristic.Votable;
import org.diretto.api.client.base.entities.SubEntity;

/**
 * This interface represents a {@code Link}.
 * 
 * @author Tobias Schlecht
 */
public interface Link extends SubEntity<LinkID>, Votable, Tagable, Creatable, Describable
{
	/**
	 * Returns the {@link DocumentID} of the source {@link Document}. <br/><br/>
	 * 
	 * <i>Annotation:</i> A {@link Link} is an unidirectional connection between
	 * a source {@code Document} and a destination {@code Document}.
	 * 
	 * @return The {@code DocumentID} of the source {@code Document}
	 */
	DocumentID getSourceDocumentID();

	/**
	 * Returns the {@link DocumentID} of the destination {@link Document}.
	 * <br/><br/>
	 * 
	 * <i>Annotation:</i> A {@link Link} is an unidirectional connection between
	 * a source {@code Document} and a destination {@code Document}.
	 * 
	 * @return The {@code DocumentID} of the destination {@code Document}
	 */
	DocumentID getDestinationDocumentID();
}
