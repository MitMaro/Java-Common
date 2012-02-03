/*
 * ca.mitmaro.commandline.userinterface.SimplePrompt
 * A prompt that asks a question and waits for direct user input.
 *  
 * The MIT License (MIT)
 * Copyright (c) 2012 Tim Oram
 * See LICENSE for full licensing information.
 */
package ca.mitmaro.commandline.userinterface;

import java.io.IOException;

import ca.mitmaro.commandline.term.Terminal;

public class SimplePrompt extends Prompt<String> {
	
	/**
	 * @param question The question to ask
	 * @param term A terminal interface
	 */
	public SimplePrompt(String question, Terminal term) {
		super(question, term);
	}
	
	
	/**
	 * Prompts with a question and waits for the user to respond.
	 * This method will block until there is a response.
	 * 
	 * @return True if yes was selected, false if no was selected
	 * @throws IOException
	 */
	public String waitForResponse() throws IOException {
		
		return this.promptForNonEmptyInput();
		
	}
}
