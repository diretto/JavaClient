package org.diretto.api.client.base.characteristic;

import org.diretto.api.client.base.data.ResultSet;
import org.diretto.api.client.base.entities.Entity;
import org.diretto.api.client.main.core.entities.Tag;
import org.diretto.api.client.main.core.entities.TagID;
import org.diretto.api.client.session.UserSession;

/**
 * Implementing this interface enables the objects of an implementation class of
 * this interface to be tagable.
 * 
 * @author Tobias Schlecht
 */
public interface Tagable
{
	/**
	 * Adds a new {@link Tag} to the {@link Entity} and returns the
	 * {@link TagID} if it was successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param value The value of the {@code Tag}
	 * @return The corresponding {@code TagID}
	 */
	TagID addTag(UserSession userSession, String value);

	/**
	 * Returns the {@link Tag} with the specified {@link TagID} or {@code null}
	 * if there is no {@code Tag} with the given {@code TagID}.
	 * 
	 * @param tagID A {@code TagID}
	 * @return The {@code Tag}
	 */
	Tag getTag(TagID tagID);

	/**
	 * Returns a {@link ResultSet} of all {@link Tag}s and the mapped
	 * {@link TagID}s.
	 * 
	 * @return A {@code ResultSet} with the {@code Tag}s
	 */
	ResultSet<TagID, Tag> getTags();
}
