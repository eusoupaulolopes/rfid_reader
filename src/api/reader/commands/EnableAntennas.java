package api.reader.commands;

import java.io.IOException;
import java.net.UnknownHostException;

import api.reader.interfaces.Command;
import api.reader.interfaces.ConnectReader;

public class EnableAntennas implements Command {
	
	private String pattern;
	
	public EnableAntennas(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public void execute(ConnectReader cr) throws UnknownHostException, IOException {
		cr.enableAntennas(pattern);
	}

}
