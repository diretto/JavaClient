package org.diretto.api.client.main.core.binding.resources;

import java.util.ArrayList;

/**
 * This class represents a POJO based {@code ServicesResource}. <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class ServicesResource
{
	private ArrayList<ListEntry> list;

	public ArrayList<ListEntry> getList()
	{
		return list;
	}

	public void setList(ArrayList<ListEntry> list)
	{
		this.list = list;
	}

	public static class ListEntry
	{
		private APIResource api;
		private ServiceResource service;
		private DeploymentResource deployment;
		private HyperLinkResource link;

		public APIResource getAPI()
		{
			return api;
		}

		public void setAPI(APIResource api)
		{
			this.api = api;
		}

		public ServiceResource getService()
		{
			return service;
		}

		public void setService(ServiceResource service)
		{
			this.service = service;
		}

		public DeploymentResource getDeployment()
		{
			return deployment;
		}

		public void setDeployment(DeploymentResource deployment)
		{
			this.deployment = deployment;
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
}
