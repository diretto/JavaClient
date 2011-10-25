package org.diretto.api.client.base.characteristic;

import org.diretto.api.client.base.data.Votes;
import org.diretto.api.client.base.types.VoteType;
import org.diretto.api.client.session.UserSession;
import org.diretto.api.client.user.User;

/**
 * Implementing this interface enables the objects of an implementation class of
 * this interface to be votable.
 * 
 * @author Tobias Schlecht
 */
public interface Votable
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
	 * @return {@code true} if the voting was successful; otherwise
	 *         {@code false}
	 */
	boolean setUserVote(UserSession userSession, VoteType voteType);

	/**
	 * Removes the vote of the {@link User} if the {@code User} has been voted
	 * before. <br/><br/>
	 * 
	 * <i>Annotation:</i> The {@link UserSession} is used to acquire the
	 * {@code User} information.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @return {@code true} if the removal was successful; otherwise
	 *         {@code false}
	 */
	boolean removeUserVote(UserSession userSession);

	/**
	 * Returns the current {@link VoteType} of the {@link User}. <br/><br/>
	 * 
	 * <i>Annotation:</i> The {@link UserSession} is used to acquire the
	 * {@code User} information.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @return The current {@code VoteType} of the {@code User}
	 */
	VoteType getUserVote(UserSession userSession);

	/**
	 * Returns the {@link Votes} of all {@link User}s.
	 * 
	 * @return The {@code Votes} of all {@code User}s
	 */
	Votes getVotes();
}
