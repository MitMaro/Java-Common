/*
 * ca.mitmaro.commandline.help.Message
 * A command usage and help message
 *  
 * The MIT License (MIT)
 * Copyright (c) 2012 Tim Oram
 * See LICENSE for full licensing information.
 */
package ca.mitmaro.commandline.help;

public class Message {
	
	private String usage;
	private String help;
	
	/**
	 * @param usage The usage pattern
	 * @param help The help text
	 */
	public 	Message(String usage, String help) {
		this.usage = usage;
		this.help = help;
	}
	
	/**
	 * @return The usage pattern
	 */
	public String getUsage() {
		return this.usage;
	}
	
	/**
	 * @return The help text message
	 */
	public String getHelp() {
		return this.help;
	}
}
