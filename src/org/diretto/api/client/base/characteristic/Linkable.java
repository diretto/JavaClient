package org.diretto.api.client.base.characteristic;

import org.diretto.api.client.base.data.ResultSet;
import org.diretto.api.client.base.entities.Entity;
import org.diretto.api.client.base.entities.EntityID;
import org.diretto.api.client.main.core.entities.Link;
import org.diretto.api.client.main.core.entities.LinkID;
import org.diretto.api.client.session.UserSession;

/**
 * Implementing this interface enables the objects of an implementation class of
 * this interface to be linkable.
 * 
 * @author Tobias Schlecht
 * 
 * @param <T> The exact type of the {@code EntityID} of the {@code Link}
 *        destination
 */
public interface Linkable<T extends EntityID>
{
	/**
	 * Adds a new {@link Link} between this {@link Entity} and a destination
	 * {@code Entity} and returns the {@link LinkID} if it was successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param destinationEntityID The {@code EntityID} of the {@code Link}
	 *        destination
	 * @param title The title of the {@code Link}
	 * @param description The description of the {@code Link}
	 * @return The corresponding {@code LinkID}
	 */
	LinkID addLink(UserSession userSession, T destinationEntityID, String title, String description);

	/**
	 * Returns the {@link Link} with the specified {@link LinkID} or
	 * {@code null} if there is no {@code Link} with the given {@code LinkID}.
	 * 
	 * @param linkID A {@code LinkID}
	 * @return The {@code Link}
	 */
	Link getLink(LinkID linkID);

	/**
	 * Returns a {@link ResultSet} with all incoming {@link Link}s and the
	 * mapped {@link LinkID}s.
	 * 
	 * @return A {@code ResultSet} with the incoming {@code Link}s
	 */
	ResultSet<LinkID, Link> getIncomingLinks();

	/**
	 * Returns a {@link ResultSet} with all outgoing {@link Link}s and the
	 * mapped {@link LinkID}s.
	 * 
	 * @return A {@code ResultSet} with the outgoing {@code Link}s
	 */
	ResultSet<LinkID, Link> getOutgoingLinks();
}
