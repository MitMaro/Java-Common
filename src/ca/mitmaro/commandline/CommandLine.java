/*
 * ca.mitmaro.commandline.CommandLine
 * A menu system with number prefixes where items can be selected
 * using the item name or the prefixed number.
 * 
 *  
 * The MIT License (MIT)
 * Copyright (c) 2012 Tim Oram
 * See LICENSE for full licensing information.
 */
package ca.mitmaro.commandline;

import java.util.HashMap;
import java.util.Set;

import ca.mitmaro.commandline.command.Command;
import ca.mitmaro.commandline.command.Help;
import ca.mitmaro.commandline.command.Exit;
import ca.mitmaro.commandline.term.Terminal;
import ca.mitmaro.commandline.help.System;
import ca.mitmaro.commandline.help.Message;

public abstract class CommandLine {
	
	protected final System help = new System();
	
	private final HashMap<String, Command> commands = new HashMap<String, Command>();
	
	private boolean catch_exceptions;
	private boolean display_trace;
	
	protected final Terminal terminal;
	
	/**
	 * @param terminal The terminal interface
	 */
	public CommandLine(Terminal terminal) {
		this.terminal = terminal;
		// default commands
		this
			.addCommand("help", new Help(this.help, this.terminal), new Message("help", "Print a list of all system commands."))
			.addCommand("exit", new Exit(this.terminal), new Message("exit", "Exit the application."))
		;
	}
	
	/**
	 * Add a command to the interface
	 * 
	 * @param name The name of the command
	 * @param command The command class
	 * @return A fluid interface
	 */
	public CommandLine addCommand(String name, Command command) {
		this.commands.put(name, command);
		return this;
	}
	
	/**
	 * Add a command to the interface with a help message
	 * @param name The name of the command
	 * @param command The command class
	 * @param help_msg The help and usage message class 
	 * @return A fluid interface
	 */
	public CommandLine addCommand(String name, Command command, Message help_msg) {
		return this
			.addCommand(name, command)
			.addHelpMessage(name, help_msg)
		;
 	}
	
	/**
	 * @param name The command name
	 * @param help_msg The help and usage message class
	 * @return A fluid interface
	 */
	public CommandLine addHelpMessage(String name, Message help_msg) {
		this.help.addMessage(name, help_msg);
		return this;
	}
	
	
	/**
	 * Get a set of command names
	 * 
	 * @return The command names
	 */
	public Set<String> getCommandNames() {
		return this.commands.keySet();
	}
	
	/**
	 * Get a command by name
	 * 
	 * @param name A command name
	 * @return The command
	 */
	public Command getCommand(String name) {
		return this.commands.get(name);
	}
	
	/**
	 * Run the command with the given name
	 * @param name The command name
	 * @return
	 */
	public int runCommand(String name) {
		return this.commands.get(name).call(null);
	}
	
	public void catchExceptions(boolean v) {
		this.catch_exceptions = v;
	}
	
	public void displayTrace(boolean v) {
		this.display_trace = v;
	}
	
	/**
	 * Runs the command line application.
	 * @throws Exception
	 */
	public void run() throws Exception {
		
		int code;
		
		while (true) {
			if (this.catch_exceptions) {
				try {
					if ((code = this.mainloop()) != 1) {
						java.lang.System.exit(code);
					}
				} catch (IllegalArgumentException e) {
					this.terminal.out.println("Error: " + e.getMessage() + "\n");
					this.runCommand("help");
					continue;
				} catch (Exception e) {
					if (this.display_trace) {
						e.printStackTrace();
					}
					continue;
				}
			} else {
				if ((code = this.mainloop()) != 1) {
					java.lang.System.exit(code);
				}
			}
		}
	}
	
	/**
	 * The main loop of the application. If this method returns a values
	 * other than 1 the loop will exit. The returned value will be passed
	 * to System.exit().
	 * @return A 1 returned by this me
	 */
	public abstract int mainloop();
	
}

