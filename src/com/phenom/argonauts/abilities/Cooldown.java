package com.phenom.argonauts.abilities;

/**
 * 
 * @author Evan Tellep
 *
 */
public enum Cooldown 
{
	/**
	 * Represents 1 second
	 */
	ONE_S(1_000),
	
	/**
	 * Represents 2 seconds
	 */
	TWO_S(2_000),
	
	/**
	 * Represents 3 seconds
	 */
	THREE_S(3_000),
	
	/**
	 * Represents 4 seconds
	 */
	FOUR_S(4_000),
	
	/**
	 * Represents 5 seconds
	 */
	FIVE_S(5_000),
	
	/**
	 * represents 10 seconds
	 */
	TEN_S(10_000),
	
	/**
	 * Represents 1 minute
	 */
	ONE_M(60_000),
	
	/**
	 * Represents 2 minutes
	 */
	TWO_M(120_000),
	
	/**
	 * represents 3 minutes
	 */
	THREE_M(180_000),
	
	/**
	 * Represents 4 minutes
	 */
	FOUR_M(240_000),
	
	/**
	 * Represents 5 minutes
	 */
	FIVE_M(300_000),
	
	/**
	 * Represents 10 minutes
	 */
	TEN_M(600_000);
	
	private int time; //The amount of time in milliseconds each constant represents
	
	/**
	 * Create a Cooldown
	 * @param milliseconds : The amount of time in milliseconds this Cooldown will represent
	 */
	Cooldown(int milliseconds)
	{
		this.time = milliseconds;
	}
	
	/**
	 * @return : The time the constant represents in millisecond format
	 */
	public int getTime()
	{
		return time;
	}
}
