package org.diretto.api.client.util;

/**
 * This interface represents an {@code Observer} with a specific message type.
 * All implementing classes can register as an {@code Observer} at an
 * {@link Observable} object with the same message type.
 * 
 * @author Tobias Schlecht
 * 
 * @param <T> The type of the message
 */
public interface Observer<T>
{
	/**
	 * This method will be invoked if the {@code Observable} object has been
	 * changed.
	 * 
	 * @param observable The corresponding {@code Observable} object
	 * @param message The message of the {@code Observable} object
	 */
	void update(Observable<T> observable, T message);
}
