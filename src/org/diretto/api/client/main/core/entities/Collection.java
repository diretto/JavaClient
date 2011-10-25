package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.characteristic.Creatable;
import org.diretto.api.client.base.characteristic.Describable;
import org.diretto.api.client.base.data.ResultSet;
import org.diretto.api.client.base.entities.Entity;
import org.diretto.api.client.session.UserSession;
import org.diretto.api.client.user.User;

/**
 * This interface represents a {@code Collection}.
 * 
 * @author Tobias Schlecht
 */
public interface Collection extends Entity<CollectionID>, Creatable, Describable
{
	/**
	 * Returns the private setting of this {@link Collection}.
	 * 
	 * @return {@code true} if the {@code Collection} is {@code private} (then
	 *         only the {@code User} who created the {@code Collection} has
	 *         access to the {@code Collection}); {@code false} if the
	 *         {@code Collection} is {@code public}
	 */
	boolean getPrivateSetting();

	/**
	 * Changes the title of this {@link Collection}. <br/><br/>
	 * 
	 * <i>Annotation:</i> Note that only the creator of this {@code Collection}
	 * can change the title. Therefore the {@link UserSession} is used to
	 * control whether the corresponding {@link User} is the creator of this
	 * {@code Collection}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param title The title of the {@code Collection}
	 * @return {@code true} if the change was successful; otherwise
	 *         {@code false}
	 */
	boolean changeTitle(UserSession userSession, String title);

	/**
	 * Changes the description of this {@link Collection}. <br/><br/>
	 * 
	 * <i>Annotation:</i> Note that only the creator of this {@code Collection}
	 * can change the description. Therefore the {@link UserSession} is used to
	 * control whether the corresponding {@link User} is the creator of this
	 * {@code Collection}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param description The description of the {@code Collection}
	 * @return {@code true} if the change was successful; otherwise
	 *         {@code false}
	 */
	boolean changeDescription(UserSession userSession, String description);

	/**
	 * Changes the private setting of this {@link Collection}. <br/><br/>
	 * 
	 * <i>Annotation:</i> Note that only the creator of this {@code Collection}
	 * can change the private setting. Therefore the {@link UserSession} is used
	 * to control whether the corresponding {@link User} is the creator of this
	 * {@code Collection}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param privateSetting {@code true} if the {@code Collection} is
	 *        {@code private} (then only the {@code User} who created the
	 *        {@code Collection} has access to the {@code Collection});
	 *        {@code false} if the {@code Collection} is {@code public}
	 * @return {@code true} if the change was successful; otherwise
	 *         {@code false}
	 */
	boolean changePrivateSetting(UserSession userSession, boolean privateSetting);

	/**
	 * Adds the {@link Document} with the specified {@link DocumentID} to this
	 * {@link Collection}. <br/><br/>
	 * 
	 * <i>Annotation:</i> Note that only the creator of this {@code Collection}
	 * can add a {@code Document}. Therefore the {@link UserSession} is used to
	 * control whether the corresponding {@link User} is the creator of this
	 * {@code Collection}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param documentID A {@code DocumentID}
	 * @return {@code true} if the addition was successful; otherwise
	 *         {@code false}
	 */
	boolean addDocument(UserSession userSession, DocumentID documentID);

	/**
	 * Removes the {@link Document} with the specified {@link DocumentID} from
	 * this {@link Collection} if the {@code Collection} contains the
	 * {@code Document}. <br/><br/>
	 * 
	 * <i>Annotation:</i> Note that only the creator of this {@code Collection}
	 * can remove a {@code Document}. Therefore the {@link UserSession} is used
	 * to control whether the corresponding {@link User} is the creator of this
	 * {@code Collection}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param documentID A {@code DocumentID}
	 * @return {@code true} if the removal was successful; otherwise
	 *         {@code false}
	 */
	boolean removeDocument(UserSession userSession, DocumentID documentID);

	/**
	 * Returns a {@link ResultSet} with all {@link Document}s and the mapped
	 * {@link DocumentID}s.
	 * 
	 * @return A {@code ResultSet} with the {@code Document}s
	 */
	ResultSet<DocumentID, Document> getDocuments();

	/**
	 * Returns the {@link Document} with the specified {@link DocumentID}, or
	 * {@code null} if there is no {@code Document} with the given
	 * {@code DocumentID}.
	 * 
	 * @param documentID A {@code DocumentID}
	 * @return The {@code Document}
	 */
	Document getDocument(DocumentID documentID);
}
