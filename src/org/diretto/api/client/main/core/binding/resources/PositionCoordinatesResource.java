package org.diretto.api.client.main.core.binding.resources;

import java.util.ArrayList;

/**
 * This class represents a POJO based {@code PositionCoordinatesResource}.
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
public final class PositionCoordinatesResource
{
	private String type;
	private ArrayList<Double> coordinates;

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public ArrayList<Double> getCoordinates()
	{
		return coordinates;
	}

	public void setCoordinates(ArrayList<Double> coordinates)
	{
		this.coordinates = coordinates;
	}

	public static class LocationResource
	{
		private PositionCoordinatesResource position;
		private int variance;

		public PositionCoordinatesResource getPosition()
		{
			return position;
		}

		public void setPosition(PositionCoordinatesResource position)
		{
			this.position = position;
		}

		public int getVariance()
		{
			return variance;
		}

		public void setVariance(int variance)
		{
			this.variance = variance;
		}
	}
}
