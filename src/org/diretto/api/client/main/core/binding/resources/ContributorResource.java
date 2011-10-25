package org.diretto.api.client.main.core.binding.resources;

/**
 * This class represents a POJO based {@code ContributorResource}. <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class ContributorResource
{
	private External external;
	private HyperLinkResourceWrapper user;

	public External getExternal()
	{
		return external;
	}

	public void setExternal(External external)
	{
		this.external = external;
	}

	public HyperLinkResourceWrapper getUser()
	{
		return user;
	}

	public void setUser(HyperLinkResourceWrapper user)
	{
		this.user = user;
	}

	public static class External
	{
		private String name;
		private HyperLinkResource link;

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}

		public HyperLinkResource getLink()
		{
			return link;
		}

		public void setLink(HyperLinkResource link)
		{
			this.link = link;
		}
	}

	public interface ContributorEntry
	{
	}

	public static class ExternalEntry implements ContributorEntry
	{
		private External external;

		public External getExternal()
		{
			return external;
		}

		public void setExternal(External external)
		{
			this.external = external;
		}
	}

	public static class PlatformEntry implements ContributorEntry
	{
		private HyperLinkResourceWrapper user;

		public HyperLinkResourceWrapper getUser()
		{
			return user;
		}

		public void setUser(HyperLinkResourceWrapper user)
		{
			this.user = user;
		}
	}
}
