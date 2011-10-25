package org.diretto.api.client.main.core.binding.major;

import java.util.ArrayList;

/**
 * This class represents a POJO based {@code MultipleCollectionsResource}.
 * <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(SnapShot) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class MultipleCollectionsResource
{
	private ArrayList<CollectionReceptionResource> collections;

	public ArrayList<CollectionReceptionResource> getCollections()
	{
		return collections;
	}

	public void setCollections(ArrayList<CollectionReceptionResource> collections)
	{
		this.collections = collections;
	}
}
