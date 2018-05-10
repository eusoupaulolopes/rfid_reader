
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.example.sdksamples.DisconnectedOperation;

import api.reader.commands.CloseConnection;
import api.reader.commands.ForceStopReader;
import api.reader.commands.ReadTags;
import api.reader.commands.ReaderTagsReset;
import api.reader.facade.ApiReaderFacade;
import api.reader.nesslab.exceptions.SessionFullException;
import api.reader.nesslab.utils.OperationUtil;
import api.reader.speedway.facade.ApiReaderSpeedway;

public class RFIDMain {

	public static void main(String[] args) {
		try {

			/*
			 * The class ApiReaderNesslab to be instantiated, a new connection
			 * with the Nesslab is opened.
			 */
			ApiReaderFacade api = new ApiReaderSpeedway("192.168.1.2");
			// ApiReaderFacade api = new ApiReaderNesslab("10.7.125.7");

			api.defaultConfiguration();
			api.executeAction(new ReadTags());
			
			Scanner s = new Scanner(System.in);
			s.nextLine();
			
			api.executeAction(new CloseConnection());

		} catch (UnknownHostException e) {
			System.err.println("Host not found: " + OperationUtil.getIpReader());
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Unable to connect: " + OperationUtil.getIpReader());
			System.exit(1);
		} catch (Exception e) {
			System.err.println("Unknown exception " + e.getMessage());
			System.exit(1);
		}
	}
}