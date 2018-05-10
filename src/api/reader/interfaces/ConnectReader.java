package api.reader.interfaces;

import java.io.IOException;

public interface ConnectReader {

	void closeConnection() throws IOException;

	void disableBuzzer();

	void enableBuzzer();

	void disableContinueMode();

	void enableAntennas(String pattern);
	
	void enableContinueMode();
	
	void forceStopReader();
	
	void oneTagReader();
	
	void readTags();
	
	void readerTagsReset();
	
	void requestStatusAntenna();
	
	void requestStatusMode();
	
	void requestStatusPowerAntenna();
	
	void requestStatusScanTime();

	void resquestStatusBuzzer();

	void setPowerControl(String power);
	
	void setScanTime(long time);
	
}
