package api.reader.interfaces;

import java.io.IOException;
import java.net.UnknownHostException;

public interface Command {

	public void execute(ConnectReader cr) throws UnknownHostException, IOException;

}
