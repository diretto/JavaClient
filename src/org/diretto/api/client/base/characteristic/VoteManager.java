package org.diretto.api.client.base.characteristic;

import org.diretto.api.client.base.entities.Entity;
import org.diretto.api.client.base.entities.EntityID;
import org.diretto.api.client.base.types.VoteType;
import org.diretto.api.client.session.UserSession;
import org.diretto.api.client.user.User;

/**
 * A class implements the {@code VoteManager} interface to indicate that the
 * class has got the necessary methods for managing all voting functionalities
 * in respect of {@link Entity}s.
 * 
 * @author Tobias Schlecht
 */
public interface VoteManager
{
	/**
	 * Sets the given {@link VoteType} of the {@link User}. If the {@code User}
	 * has already been voted with another {@code VoteType}, the
	 * {@code VoteType} will be changed. <br/><br/>
	 * 
	 * <i>Annotation:</i> The {@link UserSession} is used to acquire the
	 * {@code User} information.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param voteType The {@code VoteType} of the {@code User}
	 * @param entityID The corresponding {@code EntityID}
	 * @return {@code true} if the voting was successful; otherwise
	 *         {@code false}
	 */
	boolean setUserVote(UserSession userSession, VoteType voteType, EntityID entityID);

	/**
	 * Removes the vote of the {@link User} if the {@code User} has been voted
	 * before. <br/><br/>
	 * 
	 * <i>Annotation:</i> The {@link UserSession} is used to acquire the
	 * {@code User} information.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param entityID The corresponding {@code EntityID}
	 * @return {@code true} if the removal was successful; otherwise
	 *         {@code false}
	 */
	boolean removeUserVote(UserSession userSession, EntityID entityID);

	/**
	 * Returns the current {@link VoteType} of the {@link User}. <br/><br/>
	 * 
	 * <i>Annotation:</i> The {@link UserSession} is used to acquire the
	 * {@code User} information.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param entityID The corresponding {@code EntityID}
	 * @return The current {@code VoteType} of the {@code User}
	 */
	VoteType getUserVote(UserSession userSession, EntityID entityID);
}
