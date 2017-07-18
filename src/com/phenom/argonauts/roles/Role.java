package com.phenom.argonauts.roles;

public enum Role {

	TANK("Tank"),
	HEALER("Healer"),
	DPS("DPS");
	
	private final String role; //The amount of time in milliseconds each constant represents
	
	/**
	 * Create a Cooldown
	 * @param milliseconds : The amount of time in milliseconds this Cooldown will represent
	 */
	private Role(String role)
	{
		this.role = role;
	}
	
	/**
	 * @return : The time the constant represents in millisecond format
	 */
	public String toString()
	{
		return role;
	}
	
}
