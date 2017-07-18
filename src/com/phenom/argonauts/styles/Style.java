package com.phenom.argonauts.styles;

public enum Style {
	TITAN("Titan"),
	WARRIOR("Warrior"),
	ARTILLERY("Artillery");
	
	private final String style; //The string that the constant represents
	
	/**
	 * Create a Style
	 * @param style : The style you want to add to the enum
	 */
	private Style(String style)
	{
		this.style = style;
	}
	
	/**
	 * @return : The style in string format
	 */
	public String toString()
	{
		return style;
	}
}
