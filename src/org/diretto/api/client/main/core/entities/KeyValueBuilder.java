package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.data.Builder;
import org.diretto.api.client.user.UserID;

/**
 * This class implements the {@link Builder} interface and serves for creating
 * {@code Builder} objects for {@link KeyValue}s.
 * 
 * @author Tobias Schlecht
 */
public final class KeyValueBuilder implements Builder<KeyValue>
{
	private final KeyValueID keyValueID;

	private UserID creator = null;
	private String key = null;
	private String value = null;

	/**
	 * Constructs a {@link KeyValueBuilder} object.
	 * 
	 * @param keyValueID The {@code KeyValueID}
	 */
	public KeyValueBuilder(KeyValueID keyValueID)
	{
		this.keyValueID = keyValueID;
	}

	/**
	 * Sets the {@link UserID} of the creator.
	 * 
	 * @param creator The {@code UserID} of the creator
	 * @return The {@code KeyValueBuilder} object
	 */
	public KeyValueBuilder creator(UserID creator)
	{
		this.creator = creator;
		return this;
	}

	/**
	 * Sets the key of the {@link KeyValue}.
	 * 
	 * @param key The key
	 * @return The {@code KeyValueBuilder} object
	 */
	public KeyValueBuilder key(String key)
	{
		this.key = key;
		return this;
	}

	/**
	 * Sets the value of the {@link KeyValue}.
	 * 
	 * @param value The value
	 * @return The {@code KeyValueBuilder} object
	 */
	public KeyValueBuilder value(String value)
	{
		this.value = value;
		return this;
	}

	/**
	 * Returns the {@link KeyValueID}.
	 * 
	 * @return The {@code KeyValueID}
	 */
	public KeyValueID getKeyValueID()
	{
		return keyValueID;
	}

	/**
	 * Returns the {@link UserID} of the creator.
	 * 
	 * @return The {@code UserID} of the creator
	 */
	public UserID getCreator()
	{
		return creator;
	}

	/**
	 * Returns the key of the {@link KeyValue}.
	 * 
	 * @return The key of the {@code KeyValue}
	 */
	public String getKey()
	{
		return key;
	}

	/**
	 * Returns the value of the {@link KeyValue}.
	 * 
	 * @return The value of the {@code KeyValue}
	 */
	public String getValue()
	{
		return value;
	}

	@Override
	public KeyValue build()
	{
		return new KeyValueImpl(this);
	}
}
