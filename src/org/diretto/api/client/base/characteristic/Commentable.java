package org.diretto.api.client.base.characteristic;

import org.diretto.api.client.base.data.ResultSet;
import org.diretto.api.client.main.core.entities.Comment;
import org.diretto.api.client.main.core.entities.CommentID;
import org.diretto.api.client.session.UserSession;

/**
 * Implementing this interface enables the objects of an implementation class of
 * this interface to be commentable.
 * 
 * @author Tobias Schlecht
 */
public interface Commentable
{
	/**
	 * Adds a new {@link Comment} and returns the {@link CommentID} if it was
	 * successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param content The content of the {@code Comment}
	 * @return The corresponding {@code CommentID}
	 */
	CommentID addComment(UserSession userSession, String content);

	/**
	 * Returns the {@link Comment} with the specified {@link CommentID} or
	 * {@code null} if there is no {@code Comment} with the given
	 * {@code CommentID}.
	 * 
	 * @param commentID A {@code CommentID}
	 * @return The {@code Comment}
	 */
	Comment getComment(CommentID commentID);

	/**
	 * Returns a {@link ResultSet} of all {@link Comment}s and the mapped
	 * {@link CommentID}s.
	 * 
	 * @return A {@code ResultSet} with the {@code Comment}s
	 */
	ResultSet<CommentID, Comment> getComments();
}
