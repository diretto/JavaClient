package org.diretto.api.client.main.core.entities;

import java.net.URL;
import java.util.List;

import org.diretto.api.client.base.characteristic.Describable;
import org.diretto.api.client.base.characteristic.Votable;
import org.diretto.api.client.base.data.Contributor;
import org.diretto.api.client.base.data.MediaType;
import org.diretto.api.client.base.entities.SubEntity;
import org.diretto.api.client.user.UserID;
import org.joda.time.DateTime;

/**
 * This interface represents an {@code Attachment}.
 * 
 * @author Tobias Schlecht
 */
public interface Attachment extends SubEntity<AttachmentID>, Votable, Describable
{
	/**
	 * Returns whether the file of this {@link Attachment} is an external
	 * resource.
	 * 
	 * @return {@code true} if the file of this {@code Attachment} is an
	 *         external resource; otherwise {@code false}
	 */
	boolean isExternal();

	/**
	 * Returns the publishing {@link DateTime}.
	 * 
	 * @return The publishing {@code DateTime}
	 */
	DateTime getPublishingTime();

	/**
	 * Returns the {@link UserID} of the publisher.
	 * 
	 * @return The {@code UserID} of the publisher
	 */
	UserID getPublisher();

	/**
	 * Returns the {@link MediaType} of the resource.
	 * 
	 * @return The {@code MediaType} of the resource
	 */
	MediaType getMediaType();

	/**
	 * Returns the size of the file in {@code Bytes}. <br/><br/>
	 * 
	 * <i>Annotation:</i> If the file is an external resource, the returned
	 * value will be {@code 0}.
	 * 
	 * @return The size of the file in {@code Bytes}
	 */
	long getFileSize();

	/**
	 * Returns the {@link URL} of the file.
	 * 
	 * @return The {@code URL} of the file
	 */
	URL getFileURL();

	/**
	 * Returns the license of this {@link Attachment}. <br/><br/>
	 * 
	 * <i>Annotation:</i> The returned {@code String} represents either the
	 * title of a license or a license text or an {@code URL} to a license.
	 * 
	 * @return The license of this {@code Attachment}
	 */
	String getLicense();

	/**
	 * Returns a {@link List} of all {@link Contributor}s.
	 * 
	 * @return A {@code List} of all {@code Contributor}s
	 */
	List<Contributor> getContributors();

	/**
	 * Returns a {@link List} with the {@link UserID}s of the creators.
	 * 
	 * @return A {@code List} with the {@code UserID}s of the creators
	 */
	List<UserID> getCreators();
}
