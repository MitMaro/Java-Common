/*
 * ca.mitmaro.commandline.command.Help
 * Standard help class. Displays the help message and usage message
 * for all commands in the system.
 *  
 * The MIT License (MIT)
 * Copyright (c) 2012 Tim Oram
 * See LICENSE for full licensing information.
 */
package ca.mitmaro.commandline.command;

import java.util.ArrayList;

import ca.mitmaro.commandline.help.System;
import ca.mitmaro.commandline.term.Terminal;
import ca.mitmaro.commandline.help.Message;
import ca.mitmaro.lang.StringUtils;

public class Help extends Command {
	
	private System help;
	
	/**
	 * @param help The command line help system
	 * @param term The terminal interface
	 */
	public Help(System help, Terminal term) {
		super(term);
		this.help = help;
	}

	/**
	 * Displays a list of commands with their usage and help messages
	 * 
	 * @param args The arguments passed to this command
	 * @return Always returns a successful, aka 1, code. 
	 */
	public int call(String[] args) {
		
		String padding = StringUtils.repeat(" ", this.help.getMaxCommandNameLength());
		
		if (args == null || args.length == 0) {
			this.terminal.out.println("Operation List\n");
			for (ArrayList<Message> msgs: this.help.messages.values()) {
				for (Message msg: msgs) {
					this.printHelpMessage(msg, padding);
				}
			}
		} else if (args.length == 1) {
			// print usage for command
			if (this.help.messages.containsKey(args[0])) {
				int len = 0;
				for (Message msg: this.help.messages.get(args[0])) {
					if (msg.getUsage().length() > len) {
						len = msg.getUsage().length();
					}
					
				}
				for (Message msg: this.help.messages.get(args[0])) {
					this.printHelpMessage(msg, StringUtils.repeat(" ", len));
				}
			} else {
				throw new IllegalArgumentException("Provided command " + args[0] + " Parameter(s).");
			}
		} else {
			throw new IllegalArgumentException("Invalid Parameter(s).");
		}
		
		return 1;
	}
	
	/**
	 * Prints a single formatted command help and usage message.
	 * 
	 * @param help_msg The help message class to print
	 * @param padding Additional padding to add to the front of the output lines.
	 */
	private void printHelpMessage(Message help_msg, String padding) {
		
		int length = this.help.getMaxCommandNameLength() - help_msg.getUsage().length() + 3;
		
		int base_width = padding.length() + this.help.getMaxCommandNameLength();
		
		String command_padding = StringUtils.repeat(" ", this.help.getMaxCommandNameLength());
		
		// print usage
		this.terminal.out.print(padding + help_msg.getUsage());
		
		// command/column alignment
		this.terminal.out.print(String.format(String.format("%%0%dd", length), 0).replace("0"," "));
		
		// print help message with word wrap
		length = base_width;
		for (String word: help_msg.getHelp().split("\\s")) {
			
			// word wrap
			length += word.length();
			if (length > this.terminal.getTermWidth()) {
				this.terminal.out.println();
				// additional padding
				this.terminal.out.print(command_padding + padding + "   ");
				length = base_width + word.length();
			}
			this.terminal.out.print(word + " ");
		}
		this.terminal.out.println();
	}
	
}

