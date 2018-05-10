package api.reader.nesslab.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import api.reader.interfaces.ConnectReader;

/**
 * The class is responsible for open connection and to send messages for the
 * hardware Reader Nesslab.
 */
public class NesslabConnectReader implements ConnectReader {

	private Socket echo;
	private PrintWriter out;
	private BufferedReader in;
	protected static NesslabConnectReader connectReader;

	protected NesslabConnectReader() {
	}

	/**
	 * Method used for to return a connection with the reader. The method is
	 * used exclusively for API Facade.
	 * 
	 * @param ip
	 *            is the IP of Reader.
	 * @param port
	 *            is the port of connection with o Reader, by default is 5578.
	 * @throws IOException
	 */
	/* Singleton */
	public synchronized static NesslabConnectReader getInstance(String ip, int port) throws IOException {
		if (connectReader == null) {
			connectReader = new NesslabConnectReader();
			connectReader.setEcho(new Socket(ip, port));
			connectReader
					.setIn(new BufferedReader(new InputStreamReader(connectReader.getConnection().getInputStream())));
			connectReader.setOut(new PrintWriter(connectReader.getConnection().getOutputStream(), true));
			System.out.println("The reader is connected.");

			return connectReader;
		} else {
			return connectReader;
		}
	}

	/**
	 * Method used for to send a message for the reader, following the
	 * specification of proprietary protocol. The method is used exclusively for
	 * API Facade.
	 * 
	 * @param message
	 *            is the message according with the protocol.
	 **/
	private void send(String message) {
		this.out.println(message);
	}

	public void disableBuzzer() {
		this.send(OperationUtil.BUZZER_OFF);
	}

	public void disableContinueMode() {
		this.send(OperationUtil.CONTINUE_MODE_OFF);
	}

	public void enableAntennas(String pattern) {
		this.send(OperationUtil.enableAntennas(pattern));
	}

	public void enableBuzzer() {
		this.send(OperationUtil.BUZZER_ON);
	}

	public void enableContinueMode() {
		this.send(OperationUtil.CONTINUE_MODE_ON);
	}

	public void forceStopReader() {
		this.send(OperationUtil.FORCE_STOP_READER);
	}

	public void oneTagReader() {
		this.send(OperationUtil.ONE_TAG_READ);
	}

	public void readTags() {
		this.send(OperationUtil.READ_TAGS_INVENTORY);
	}

	public void readerTagsReset() {
		this.send(OperationUtil.MULTI_TAG_READ_RESET);
	}

	public void requestStatusAntenna() {
		this.send(OperationUtil.ANTENNA_STATUS);
	}

	public void requestStatusMode() {
		this.send(OperationUtil.CONTINUE_MODE_STATUS);
	}

	public void requestStatusPowerAntenna() {
		this.send(OperationUtil.POWER_CONTROL_STATUS);
	}

	public void requestStatusScanTime() {
		this.send(OperationUtil.SCANTIME_STATUS);
	}

	public void resquestStatusBuzzer() {
		this.send(OperationUtil.BUZZER_STATUS);
	}

	public void setPowerControl(String power) {
		this.send(OperationUtil.setPowerControl(power));
	}
	
	public void setScanTime(long time){
		this.send((OperationUtil.setScanTime(time)));
	}
	
	

	/**
	 * Method used for to return a response of hardware reader.
	 * 
	 * @return response of hardware reader.
	 */
	public String getResponse() throws IOException {
		return in.readLine();
	}

	/**
	 * Method used for to return if exists any response in buffer of reader.
	 * 
	 * @return true if exists any response, false if not exist.
	 * @throws IOException
	 *             Is trown when any failure I/O ocurred.
	 */
	public boolean hasResponse() throws IOException {
		return in.read() > 1 ? true : false;
	}

	/**
	 * Method used for to return the socket of connection with the reader.
	 * 
	 * @return Socket connection with reader.
	 */
	public Socket getConnection() {
		return echo;
	}

	/**
	 * Method used for to close connection with the hardware reader.
	 * 
	 * @throws IOException
	 *             Is trown when any failure I/O ocurred.
	 */
	public void closeConnection() throws IOException {
		out.close();
		in.close();
		echo.close();
	}

	private void setEcho(Socket echo) {
		this.echo = echo;
	}

	private void setOut(PrintWriter out) {
		this.out = out;
	}

	private void setIn(BufferedReader in) {
		this.in = in;
	}

}
