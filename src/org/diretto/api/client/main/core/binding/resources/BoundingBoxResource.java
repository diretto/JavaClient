package org.diretto.api.client.main.core.binding.resources;

import java.util.ArrayList;

/**
 * This class represents a POJO based {@code BoundingBoxResource}. <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class BoundingBoxResource
{
	private ArrayList<Double> bbox;

	public ArrayList<Double> getBbox()
	{
		return bbox;
	}

	public void setBbox(ArrayList<Double> bbox)
	{
		this.bbox = bbox;
	}
}
