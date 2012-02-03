/*
 * ca.mitmaro.commandline.command.Exit
 * Standard exit command. Prompts user to confirm exit.
 *  
 * The MIT License (MIT)
 * Copyright (c) 2012 Tim Oram
 * See LICENSE for full licensing information.
 */
package ca.mitmaro.commandline.command;

import java.io.IOException;

import ca.mitmaro.commandline.term.Terminal;
import ca.mitmaro.commandline.userinterface.YesNoQuestion;

public class Exit extends Command {
	
	private YesNoQuestion menu; 
	
	/**
	 * @param terminal A terminal interface
	 */
	public Exit(Terminal terminal) {
		super(terminal);
		
		this.menu = new YesNoQuestion(terminal);
		
		this.menu.setTitle("\nAre you sure you wish to exit?");
		this.setPrompt("$ exit: ");
	}
	
	/**
	 * Set the user prompt message
	 * 
	 * @param prompt The prompt string
	 */
	public void setPrompt(String prompt) {
		this.menu.setPrompt(prompt);
	}

	/**
	 * Prompts the user to confirm quit. Returns a 0 if quit was a confirm, else returns
	 * a 1.
	 * 
	 * @param args The arguments passed to this command
	 * @return 1 is the command was successful, a number > 1 for an error and 0 for a non-error quit. 
	 */
	public int call(String[] args) {
		
		try {
			if (this.menu.waitForResponse().equals("yes")) {
				return 0; // 0 = good shutdown
			}
		} catch (IOException e) {
			this.terminal.out.println("An error occured. Aborting quit.");
		}
		return 1;
	}
	
}
