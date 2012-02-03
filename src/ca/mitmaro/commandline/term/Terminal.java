/*
 * ca.mitmaro.commandline.term.Terminal
 * A virtual terminal interface
 *  
 * The MIT License (MIT)
 * Copyright (c) 2012 Tim Oram
 * See LICENSE for full licensing information.
 */
package ca.mitmaro.commandline.term;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Terminal {
	
	private String prompt = "$ ";
	
	private int width = 80;
	
	public final BufferedReader in;
	public final PrintWriter out;
	public final PrintWriter err;
	
	public Terminal() {
		this.in = new BufferedReader(new InputStreamReader(System.in));
		this.out = new PrintWriter(System.out);
		this.err = new PrintWriter(System.err);
	}
	
	/**
	 * Set the virtual width of the terminal window
	 * 
	 * @param width The width
	 * @return A fluid interface
	 */
	public Terminal setTermWidth(int width) {
		this.width = width;
		return this;
	}
	
	/**
	 * @return The virtual terminal width 
	 */
	public int getTermWidth() {
		return this.width;
	}
	
	/**
	 * Print of the prompt
	 * @return A fluid interface
	 */
	public Terminal prompt() {
		this.out.print(this.prompt);
		this.out.flush();
		return this;
	}
	
	/**
	 * @param prompt
	 * @return A fluid interface
	 */
	public Terminal setPrompt(String prompt) {
		this.prompt = prompt;
		return this;
	}
	
}
