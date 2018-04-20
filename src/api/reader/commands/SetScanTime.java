package api.reader.commands;

import java.io.IOException;
import java.net.UnknownHostException;

import api.reader.interfaces.Command;
import api.reader.interfaces.ConnectReader;

public class SetScanTime implements Command {
	
	protected long time;
	
	public SetScanTime(long time) {
		this.time = time;
	}

	@Override
	public void execute(ConnectReader cr) throws UnknownHostException, IOException {
		cr.setScanTime(time);
	}

}
