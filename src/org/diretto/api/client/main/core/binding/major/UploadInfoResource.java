package org.diretto.api.client.main.core.binding.major;

import org.diretto.api.client.main.core.binding.resources.HyperLinkResource;
import org.diretto.api.client.main.core.binding.resources.HyperLinkResourceWrapper;

/**
 * This class represents a POJO based {@code UploadInfoResource}. <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class UploadInfoResource
{
	private HyperLinkResource link;
	private UploadResource upload;

	public HyperLinkResource getLink()
	{
		return link;
	}

	public void setLink(HyperLinkResource link)
	{
		this.link = link;
	}

	public UploadResource getUpload()
	{
		return upload;
	}

	public void setUpload(UploadResource upload)
	{
		this.upload = upload;
	}

	public static class UploadResource
	{
		private String token;
		private HyperLinkResourceWrapper location;
		private HyperLinkResourceWrapper target;

		public String getToken()
		{
			return token;
		}

		public void setToken(String token)
		{
			this.token = token;
		}

		public HyperLinkResourceWrapper getLocation()
		{
			return location;
		}

		public void setLocation(HyperLinkResourceWrapper location)
		{
			this.location = location;
		}

		public HyperLinkResourceWrapper getTarget()
		{
			return target;
		}

		public void setTarget(HyperLinkResourceWrapper target)
		{
			this.target = target;
		}
	}
}
