/*
 * ca.mitmaro.commandline.command.Command
 * Command abstract.
 *  
 * The MIT License (MIT)
 * Copyright (c) 2012 Tim Oram
 * See LICENSE for full licensing information.
 */
package ca.mitmaro.commandline.command;

import ca.mitmaro.commandline.term.Terminal;

public abstract class Command {
	
	protected Terminal terminal;
	
	/**
	 * @param term A terminal interface
	 */
	public Command(Terminal term) {
		this.terminal = term;
	}
	
	/**
	 * @param args The arguments passed to this command
	 * @return 1 is the command was successful, a number > 1 for an error and 0 for a non-error quit. 
	 */
	public abstract int call(String[] args);
}

