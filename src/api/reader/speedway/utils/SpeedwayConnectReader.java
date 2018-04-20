package api.reader.speedway.utils;

import java.io.IOException;
import java.net.UnknownHostException;

import com.impinj.octane.ImpinjReader;
import com.impinj.octane.OctaneSdkException;

import api.reader.interfaces.ConnectReader;

public class SpeedwayConnectReader implements ConnectReader  {
	
	
	protected static SpeedwayConnectReader connectorReader;
	protected static ImpinjReader reader;
	
	

	
	
	public static SpeedwayConnectReader getInstance(String ip, Integer port)
			throws UnknownHostException, IOException, OctaneSdkException {
		if (connectorReader == null) {
			synchronized (SpeedwayConnectReader.class) {
				if (connectorReader == null) {
					connectorReader = new SpeedwayConnectReader();
					reader = new ImpinjReader();
					reader.connect(ip);
				}
			}
		}
		return connectorReader;
	}

	private SpeedwayConnectReader() {
		super();
	};
	
	
	public ImpinjReader getReader(){
		return reader;
	}

	@Override
	public void closeConnection() throws IOException {
		
		
	}

	/**
	 * Although it hurts open-close principle, this method is responsible for forwarding the action to interested method on Impinj connector Reader
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
		
		
	}

	@Override
	public void oneTagReader() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readerTags() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	};
}
