	
import java.io.IOException;
import java.net.UnknownHostException;

import api.reader.commands.CloseConnection;
import api.reader.commands.EnableBuzzer;
import api.reader.commands.ReaderTags;
import api.reader.commands.ReaderTagsReset;
import api.reader.facade.ApiReaderFacade;
import api.reader.nesslab.exceptions.SessionFullException;
import api.reader.nesslab.utils.OperationUtil;
import api.reader.speedway.facade.ApiReaderSpeedway;
public class RFIDMain {
	
	public static void main(String[] args) {		
		try {
			
			/* The class ApiReaderNesslab to be instantiated, a 
			 * new connection with the Nesslab is opened. */
			ApiReaderFacade api = new ApiReaderSpeedway("192.168.1.3");
			api.defaultConfiguration();
			api.executeAction(new ReaderTags());
			while (api.hasResponse()) {
				try {
					api.captureTagsObject();
//					if(api.hasNewTag()){
//						System.out.println(api.getTagUniqueJsonRepresentation());
//					}
					
				} catch (SessionFullException e) {
					api.executeAction(new ReaderTagsReset());
				}
			}
			
			api.executeAction(new CloseConnection());
		} catch (UnknownHostException e) {
			System.err.println("Host not found: " + OperationUtil.getIpReader());
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Don't possible the conection: "+ OperationUtil.getIpReader());
			System.exit(1);
		}
	}
}