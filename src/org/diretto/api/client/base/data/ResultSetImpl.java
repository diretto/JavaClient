package org.diretto.api.client.base.data;

import java.net.URL;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

import org.diretto.api.client.base.characteristic.ResultSetManager;
import org.diretto.api.client.base.entities.Entity;
import org.diretto.api.client.base.entities.EntityID;
import org.diretto.api.client.base.types.LoadType;
import org.diretto.api.client.session.Session;

/**
 * This class is the implementation class of the {@link ResultSet} interface.
 * 
 * @author Tobias Schlecht
 * 
 * @param <I> The type of the corresponding {@code EntityID}
 * @param <E> The type of the corresponding {@code Entity}
 */
public final class ResultSetImpl<I extends EntityID, E extends Entity<I>> implements ResultSet<I, E>
{
	private final ResultSetManager<I, E> resultSetManager;
	private final Session session;

	private final boolean isQueryResultSet;
	private final boolean loadCompletelyStatus;
	private final LoadType loadType;

	private final LinkedHashMap<I, E> linkedHashMap;
	private final Vector<E> vector;

	private URL nextPageURL;

	private int size;

	/**
	 * Constructs an object of the {@link ResultSet} interface. <br/><br/>
	 * 
	 * <i>Annotation:</i> If there is no next page, the {@link URL} must be set
	 * to {@code null}.
	 * 
	 * @param resultSetManager The corresponding {@code ResultSetManager}
	 * @param session The corresponding {@code Session}
	 * @param isQueryResultSet {@code true} if the {@code ResultSet} derives
	 *        from a query result page; otherwise {@code false}
	 * @param loadType The {@code LoadType} of the {@code ResultSet}
	 * @param linkedHashMap The {@code LinkedHashMap} whose mappings are to be
	 *        placed in this {@code ResultSet}
	 * @param nextPageURL The {@code URL} of the next page
	 */
	ResultSetImpl(ResultSetManager<I, E> resultSetManager, Session session, boolean isQueryResultSet, LoadType loadType, LinkedHashMap<I, E> linkedHashMap, URL nextPageURL)
	{
		this.resultSetManager = resultSetManager;
		this.session = session;

		this.isQueryResultSet = isQueryResultSet;

		if(loadType == LoadType.COMPLETE)
		{
			loadCompletelyStatus = true;
		}
		else
		{
			loadCompletelyStatus = false;
		}

		this.loadType = loadType;

		this.linkedHashMap = new LinkedHashMap<I, E>(linkedHashMap);
		vector = new Vector<E>(linkedHashMap.values());

		this.nextPageURL = nextPageURL;

		size = linkedHashMap.size();
	}

	@Override
	public boolean isEmpty()
	{
		if(size <= 0)
		{
			return true;
		}

		return false;
	}

	@Override
	public E get(I entityID)
	{
		E entity = linkedHashMap.get(entityID);

		if(entity == null && hasNextPage())
		{
			loadNextPageData();

			return get(entityID);
		}

		return entity;
	}

	@Override
	public Iterator<E> iterator()
	{
		return new ResultSetIterator();
	}

	/**
	 * Returns the corresponding {@link Session}.
	 * 
	 * @return The corresponding {@code Session}
	 */
	public Session getSession()
	{
		return session;
	}

	/**
	 * Determines if this {@link ResultSet} derives from a query result page.
	 * 
	 * @return {@code true} if the {@code ResultSet} derives from a query result
	 *         page; otherwise {@code false}
	 */
	public boolean isQueryResultSet()
	{
		return isQueryResultSet;
	}

	/**
	 * Returns whether the {@link Entity}s are completely loaded.
	 * 
	 * @return {@code true} if the complete {@code Entity}s and all of their sub
	 *         elements are loaded; otherwise {@code false}
	 */
	public boolean getLoadCompletelyStatus()
	{
		return loadCompletelyStatus;
	}

	/**
	 * Returns the {@link LoadType} of the {@link ResultSet}.
	 * 
	 * @return The {@code LoadType} of the {@code ResultSet}
	 */
	public LoadType getLoadType()
	{
		return loadType;
	}

	/**
	 * Returns an unmodifiable view of the data which are loaded at the present
	 * time.
	 * 
	 * @return A {@code List} of the currently loaded {@code Entity}s
	 */
	public List<E> getLoadedData()
	{
		return Collections.unmodifiableList(vector);
	}

	/**
	 * Returns the number of {@link Entity}s in this {@link ResultSet} which are
	 * loaded at the present time.
	 * 
	 * @return The number of the currently loaded {@code Entity}s
	 */
	public int getLoadedDataSize()
	{
		return size;
	}

	/**
	 * Returns the {@link URL} of the next page. <br/><br/>
	 * 
	 * <i>Annotation:</i> If there is no next page, the {@code URL} is
	 * {@code null}.
	 * 
	 * @return The {@code URL} of the next page
	 */
	public URL getNextPageURL()
	{
		return nextPageURL;
	}

	/**
	 * Sets the {@link URL} of the next page. <br/><br/>
	 * 
	 * <i>Annotation:</i> If there is no next page, the {@code URL} must be set
	 * to {@code null}.
	 * 
	 * @param nextPageURL The {@code URL} of the next page
	 */
	public void setNextPageURL(URL nextPageURL)
	{
		this.nextPageURL = nextPageURL;
	}

	/**
	 * Loads the data of the next page and updates the {@link LinkedHashMap},
	 * the {@link Vector} and the {@code size} of the {@link ResultSet}.
	 */
	private void loadNextPageData()
	{
		LinkedHashMap<I, E> data = resultSetManager.getNextPageData(this);

		linkedHashMap.putAll(data);
		vector.addAll(data.values());

		size = linkedHashMap.size();
	}

	/**
	 * Determines if the {@link ResultSet} has a next page.
	 * 
	 * @return {@code true} if the {@code ResultSet} has a next page; otherwise
	 *         {@code false}
	 */
	private boolean hasNextPage()
	{
		if(nextPageURL == null)
		{
			return false;
		}

		return true;
	}

	/**
	 * This {@code private} class represents an {@link Iterator} over the
	 * {@link ResultSet}.
	 */
	private class ResultSetIterator implements Iterator<E>
	{
		private int pointer = -1;

		private boolean hasNext;

		/**
		 * Constructs a new {@link ResultSetIterator}.
		 */
		private ResultSetIterator()
		{
			calculateHasNext();
		}

		/**
		 * Calculates and sets the {@code boolean} value of the {@code hasNext}
		 * variable.
		 */
		private void calculateHasNext()
		{
			if(pointer >= size - 1)
			{
				hasNext = hasNextPage();
			}
			else
			{
				hasNext = true;
			}
		}

		@Override
		public boolean hasNext()
		{
			return hasNext;
		}

		@Override
		public E next()
		{
			if(hasNext)
			{
				if(pointer >= size - 1)
				{
					loadNextPageData();
				}

				pointer++;
				calculateHasNext();

				return vector.get(pointer);
			}
			else
			{
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
}
