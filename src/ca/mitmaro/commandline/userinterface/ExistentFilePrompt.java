/*
 * ca.mitmaro.commandline.userinterface.ExistentFilePrompt
 * A prompt that prompts the user to supply an existing file.
 *  
 * The MIT License (MIT)
 * Copyright (c) 2012 Tim Oram
 * See LICENSE for full licensing information.
 */
package ca.mitmaro.commandline.userinterface;

import java.io.IOException;
import java.io.File;

import ca.mitmaro.commandline.term.Terminal;

public class ExistentFilePrompt extends FilePrompt {
	
	
	/**
	 * @param question The question to ask
	 * @param term The terminal interface
	 */
	public ExistentFilePrompt(String question, Terminal term) {
		super(question, term);
	}

	/**
	 * Prompt for a file path and check it exists.
	 * 
	 * @return A file object pointing to an existing file.
	 * @throws IOException
	 */
	public File waitForResponse() throws IOException {
		
		File f;
		
		do {
			f = super.waitForResponse();
			
			if (f.isFile() && f.canRead()) {
				return f;
			}
			
			this.terminal.out.println("File does not exist or is not readable");
			this.terminal.out.flush();
		} while (true);
	}
	
}
