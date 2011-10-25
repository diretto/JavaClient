package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.entities.AbstractSubEntity;
import org.diretto.api.client.user.UserID;

/**
 * This class is the implementation class of the {@link KeyValue} interface.
 * 
 * @author Tobias Schlecht
 */
final class KeyValueImpl extends AbstractSubEntity<KeyValueID> implements KeyValue
{
	private final UserID creator;
	private final String key;
	private final String value;

	/**
	 * Constructs an object of the {@link KeyValue} interface.
	 * 
	 * @param builder The {@code KeyValueBuilder} object
	 */
	KeyValueImpl(KeyValueBuilder builder)
	{
		super(builder.getKeyValueID());

		creator = builder.getCreator();
		key = builder.getKey();
		value = builder.getValue();
	}

	@Override
	public UserID getCreator()
	{
		return creator;
	}

	@Override
	public String getKey()
	{
		return key;
	}

	@Override
	public String getValue()
	{
		return value;
	}

	@Override
	public String toString()
	{
		return key + ":" + value;
	}
}
