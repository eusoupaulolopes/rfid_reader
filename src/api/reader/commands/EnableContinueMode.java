package api.reader.commands;

import java.io.IOException;
import java.net.UnknownHostException;

import api.reader.interfaces.Command;
import api.reader.interfaces.ConnectReader;

public class EnableContinueMode implements Command {
	

	@Override
	public void execute(ConnectReader cr) throws UnknownHostException, IOException {
		cr.enableContinueMode();
	}

}
