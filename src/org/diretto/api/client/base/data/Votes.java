package org.diretto.api.client.base.data;

/**
 * The {@code Votes} class encapsulates voting data and therefore contains among
 * other things the values for {@code UP} votes and {@code DOWN} votes.
 * <br/><br/>
 * 
 * <i>Annotation:</i> It is an immutable class.
 * 
 * @author Tobias Schlecht
 */
public final class Votes
{
	public final int UP;
	public final int DOWN;

	private final int calculatedValue;

	/**
	 * Creates a {@link Votes} object.
	 * 
	 * @param up The value for {@code UP} votes
	 * @param down The value for {@code DOWN} votes
	 */
	public Votes(int up, int down)
	{
		UP = up;
		DOWN = down;

		calculatedValue = UP - DOWN;
	}

	/**
	 * Returns the calculated value of the {@link Votes}. <br/><br/>
	 * 
	 * <i>Annotation:</i> The returned value is the result of the following
	 * calculation. <br/><br/>
	 * 
	 * {@code Votes.UP - Votes.DOWN}
	 * 
	 * @return The calculated value of the {@code Votes}
	 */
	public int getCalculatedValue()
	{
		return calculatedValue;
	}
}
