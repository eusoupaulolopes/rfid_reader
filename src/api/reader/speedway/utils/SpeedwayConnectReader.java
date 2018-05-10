package api.reader.speedway.utils;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.apache.mina.core.session.DummySession;

import com.example.sdksamples.TagReportListenerImplementation;
import com.impinj.octane.AutoStartMode;
import com.impinj.octane.AutoStopMode;
import com.impinj.octane.ImpinjReader;
import com.impinj.octane.OctaneSdkException;
import com.impinj.octane.Settings;

import api.reader.interfaces.ConnectReader;

public class SpeedwayConnectReader implements ConnectReader {

	protected static SpeedwayConnectReader connectorReader;
	protected static ImpinjReader reader;
	protected Scanner s = new Scanner(System.in);

	public static SpeedwayConnectReader getInstance(String ip, Integer port)
			throws UnknownHostException, IOException, OctaneSdkException {
		if (connectorReader == null) {
			synchronized (SpeedwayConnectReader.class) {
				if (connectorReader == null) {
					connectorReader = new SpeedwayConnectReader();
					startReader(ip);
				}
			}
		}
		return connectorReader;
	}

	private static void startReader(String ip) {
		reader = new ImpinjReader();
		try {
			reader.connect(ip);
		} catch (OctaneSdkException e) {
			System.err.println(e.getMessage());
		}
	}

	private SpeedwayConnectReader() {
		super();
	};

	public ImpinjReader getReader() {
		return reader;
	}

	@Override
	public void closeConnection() throws IOException {
		System.out.println("Disconnecting from " + reader.getAddress());
		if (reader.isConnected())
			reader.disconnect();
		System.out.println("Ending execution, Have a good day! ");
		System.exit(-1);
		
	}

	/**
	 * Although it hurts open-close principle, this method is responsible for
	 * forwarding the action to interested method on Impinj connector Reader
	 */
	public void send(String action) {
		// TODO
	}

	@Override
	public void disableBuzzer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void enableBuzzer() {

	}

	@Override
	public void disableContinueMode() {
		// TODO Auto-generated method stub

	}

	@Override
	public void enableAntennas(String pattern) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enableContinueMode() {
		// TODO Auto-generated method stub

	}

	@Override
	public void forceStopReader() {
		System.out.println("Stopping  " + reader.getAddress());
		try {
			if (reader.isConnected())
				reader.stop();
		} catch (OctaneSdkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void oneTagReader() {
		// TODO Auto-generated method stub

	}

	@Override
	public void readTags() {
		try {
			reader.start();
			System.out.println("Press Enter to stop.");

		} catch (OctaneSdkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void readerTagsReset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void requestStatusAntenna() {
		// TODO Auto-generated method stub

	}

	@Override
	public void requestStatusMode() {
		// TODO Auto-generated method stub

	}

	@Override
	public void requestStatusPowerAntenna() {
		// TODO Auto-generated method stub

	}

	@Override
	public void requestStatusScanTime() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resquestStatusBuzzer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPowerControl(String power) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setScanTime(long time) {
		// TODO Auto-generated method stub

	}

	public String getResponse() {
		return SpeedwayCaptureTagRepresentation.getJsonTagUnique();
	}
	public boolean hasResponse() throws IOException {
		return SpeedwayCaptureTagRepresentation.haveNewTag();

	}

	public void applySettings(Settings settings) throws OctaneSdkException {
		System.out.println("Applying Default Settings");
		reader.setTagReportListener(new TagReportListenerImp());
         reader.applySettings(settings);


	};

	
}
