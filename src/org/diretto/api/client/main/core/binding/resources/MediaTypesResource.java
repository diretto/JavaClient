package org.diretto.api.client.main.core.binding.resources;

import java.util.LinkedHashMap;

/**
 * This class represents a POJO based {@code MediaTypesResource}. <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class MediaTypesResource
{
	private LinkedHashMap<String, StoredResource> stored;
	private LinkedHashMap<String, ExternalResource> external;

	public LinkedHashMap<String, StoredResource> getStored()
	{
		return stored;
	}

	public void setStored(LinkedHashMap<String, StoredResource> stored)
	{
		this.stored = stored;
	}

	public LinkedHashMap<String, ExternalResource> getExternal()
	{
		return external;
	}

	public void setExternal(LinkedHashMap<String, ExternalResource> external)
	{
		this.external = external;
	}

	public static class StoredResource
	{
		private String type;
		private String extension;
		private long maxSize;

		public String getType()
		{
			return type;
		}

		public void setType(String type)
		{
			this.type = type;
		}

		public String getExtension()
		{
			return extension;
		}

		public void setExtension(String extension)
		{
			this.extension = extension;
		}

		public long getMaxSize()
		{
			return maxSize;
		}

		public void setMaxSize(long maxSize)
		{
			this.maxSize = maxSize;
		}
	}

	public static class ExternalResource
	{
		private String type;
		private String name;

		public String getType()
		{
			return type;
		}

		public void setType(String type)
		{
			this.type = type;
		}

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}
	}
}
