package com.pippohsios.betterpixelmon.capabilities;

import java.util.List;

import com.pippohsios.betterpixelmon.utils.ExecutableCommand;

public interface IPlayerAbilityHandler {
	public ExecutableCommand getCommand(int index);

	public List<ExecutableCommand> getCommands();

	public List<ExecutableCommand> getExecutableCommands();

	public List<ExecutableCommand> getUnexecutableCommands();

	public void setCommandExecutable(int index, boolean executable);
}
