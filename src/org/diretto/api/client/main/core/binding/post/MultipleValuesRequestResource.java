package org.diretto.api.client.main.core.binding.post;

import java.util.ArrayList;

/**
 * This class represents a POJO based {@code MultipleValuesRequestResource}.
 * <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class MultipleValuesRequestResource
{
	private ArrayList<String> values;

	public ArrayList<String> getValues()
	{
		return values;
	}

	public void setValues(ArrayList<String> values)
	{
		this.values = values;
	}
}
