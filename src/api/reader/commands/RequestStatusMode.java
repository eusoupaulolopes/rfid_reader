package api.reader.commands;

import java.io.IOException;
import java.net.UnknownHostException;

import api.reader.interfaces.Command;
import api.reader.interfaces.ConnectReader;
import api.reader.nesslab.utils.OperationUtil;

public class RequestStatusMode implements Command {
	
	@Override
	public void execute(ConnectReader cr) throws UnknownHostException, IOException {
		cr.requestStatusMode(); 	
	}

}
