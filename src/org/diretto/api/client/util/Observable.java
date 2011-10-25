package org.diretto.api.client.util;

/**
 * This interface represents an {@code Observable} object with a specific
 * message type. The {@code Observable} objects accept registrations from all
 * classes which are implementing the interface {@link Observer} with the same
 * message type.
 * 
 * @author Tobias Schlecht
 * 
 * @param <T> The type of the message
 */
public interface Observable<T>
{
	/**
	 * Registers the specified {@link Observer} as an {@code Observer} of this
	 * {@link Observable} object.
	 * 
	 * @param observer The {@code Observer} to be registered
	 */
	void addObserver(Observer<T> observer);

	/**
	 * Removes the registration of the specified {@link Observer} from this
	 * {@link Observable} object.
	 * 
	 * @param observer The {@code Observer} whose registration is to be removed
	 */
	void removeObserver(Observer<T> observer);
}
