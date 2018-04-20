package api.reader.commands;

import java.io.IOException;
import java.net.UnknownHostException;

import api.reader.interfaces.Command;
import api.reader.interfaces.ConnectReader;

public class SetPowerControl implements Command {
	
	private String power;
	
	public SetPowerControl(String power) {
		this.power = power; 
	}

	@Override
	public void execute(ConnectReader cr) throws UnknownHostException, IOException {
		cr.setPowerControl(power);
		
		
	}

}
