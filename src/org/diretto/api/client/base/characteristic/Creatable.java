package org.diretto.api.client.base.characteristic;

import org.diretto.api.client.user.UserID;
import org.joda.time.DateTime;

/**
 * A class implements the {@code Creatable} interface to indicate that the class
 * has got methods for providing information about the creation of the
 * corresponding resource.
 * 
 * @author Tobias Schlecht
 */
public interface Creatable
{
	/**
	 * Returns the creation {@link DateTime}.
	 * 
	 * @return The creation {@code DateTime}
	 */
	DateTime getCreationTime();

	/**
	 * Returns the {@link UserID} of the creator.
	 * 
	 * @return The {@code UserID} of the creator
	 */
	UserID getCreator();
}
