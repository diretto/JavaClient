package org.diretto.api.client.util;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * This {@code abstract} class provides an implementation of the
 * {@link Observable} interface. Furthermore it offers miscellaneous methods
 * which can be used by the extending classes to minimize their effort required
 * to fulfill the functionalities of an {@code Observable} object.
 * 
 * @author Tobias Schlecht
 * 
 * @param <T> The type of the message
 */
public abstract class AbstractObservable<T> implements Observable<T>
{
	private List<Observer<T>> observers = new Vector<Observer<T>>();

	@Override
	public synchronized void addObserver(Observer<T> observer)
	{
		if(observer == null)
		{
			throw new NullPointerException();
		}

		if(!observers.contains(observer))
		{
			observers.add(observer);
		}
	}

	/**
	 * Removes all registered {@link Observer}s of this {@link Observable}
	 * object.
	 */
	protected void clear()
	{
		observers.clear();
	}

	/**
	 * Determines whether the specified {@link Observer} is a registered
	 * {@code Observer} of this {@link Observable} object.
	 * 
	 * @param observer The {@code Observer} whose registration state is to be
	 *        tested
	 * @return {@code true} if the given {@code Observer} is registered;
	 *         otherwise false
	 */
	protected boolean containsObserver(Observer<T> observer)
	{
		if(observer == null)
		{
			throw new NullPointerException();
		}

		return observers.contains(observer);
	}

	/**
	 * Returns the number of registered {@link Observer}s of this
	 * {@link Observable} object.
	 * 
	 * @return The number of {@code Observer}s
	 */
	protected int countObservers()
	{
		return observers.size();
	}

	/**
	 * Returns an unmodifiable view of all registered {@link Observer}s of this
	 * {@link Observable} object.
	 * 
	 * @return A {@code List} with all {@code Observer}s
	 */
	protected List<Observer<T>> getAllObservers()
	{
		return Collections.unmodifiableList(observers);
	}

	/**
	 * Notifies all registered {@link Observer}s that this {@link Observable}
	 * object has changed and sends the specified {@code message}. The delivery
	 * of this {@code message} will be executed by invoking the
	 * {@link Observer#update(Observable, Object)} methods of all registered
	 * {@code Observer}s.
	 * 
	 * @param message The message to be sent
	 */
	protected synchronized void notifyObservers(T message)
	{
		for(Observer<T> observer : observers)
		{
			observer.update(this, message);
		}
	}

	@Override
	public synchronized void removeObserver(Observer<T> observer)
	{
		if(observer == null)
		{
			throw new NullPointerException();
		}

		observers.remove(observer);
	}
}
