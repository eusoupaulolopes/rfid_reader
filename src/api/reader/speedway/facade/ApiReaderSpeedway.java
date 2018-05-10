package api.reader.speedway.facade;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import com.impinj.octane.AutoStartMode;
import com.impinj.octane.AutoStopMode;
import com.impinj.octane.OctaneSdkException;
import com.impinj.octane.ReaderMode;
import com.impinj.octane.ReportConfig;
import com.impinj.octane.ReportMode;
import com.impinj.octane.Settings;

import api.reader.facade.ApiReaderFacade;
import api.reader.interfaces.Command;
import api.reader.nesslab.exceptions.SessionFullException;
import api.reader.nesslab.utils.CaptureTagsRepresentation;
import api.reader.nesslab.utils.TagAntenna;
import api.reader.nesslab.utils.TranslateResponse;
import api.reader.speedway.utils.SpeedwayCaptureTagRepresentation;
import api.reader.speedway.utils.SpeedwayConnectReader;

/**
 * Layer access to methods of communication and configuration with the RFID
 * Reader Nesslab RF1000.
 */
public class ApiReaderSpeedway implements ApiReaderFacade {

	private SpeedwayConnectReader cr;
	private final Integer SPEEDWAY_PORT_DEFAULT = 14150;
	private final String ipReader;

	/**
	 * Contructor with ip parameter, when the ip is different of default ip.
	 * 
	 * @param command
	 *            is a command of API.
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public ApiReaderSpeedway(String ip) throws UnknownHostException, IOException {
		this.ipReader = ip;
		try {
			this.cr = SpeedwayConnectReader.getInstance(this.ipReader, SPEEDWAY_PORT_DEFAULT);
		} catch (OctaneSdkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method exposes the proposed connector to actions defined by a
	 * command
	 * 
	 * @param command
	 *            a command of API.
	 * @throws UnknownHostException
	 *             thrown when the host was not found.
	 * @throws IOException
	 *             thrown when any failure I/O ocurrs.
	 */
	@Override
	public void executeAction(Command command) throws UnknownHostException, IOException {
		command.execute(cr);
	}

	@Override
	public String getResponse() throws UnknownHostException, IOException {
		return cr.getResponse();
	}

	@Override
	public boolean hasResponse() throws UnknownHostException, IOException {
		return cr.hasResponse();
	}
	
	@Override
	public boolean hasNewTag() {
		return SpeedwayCaptureTagRepresentation.haveNewTag();
	}


	/**
	 * To return all tags captured by methods getTagStringRepresentation() and
	 * captureTagsObject().
	 * 
	 * @return Set tag read by methods.
	 * @throws UnknownHostException
	 *             Is trown when the host not found.
	 * @throws IOException
	 *             Is trown when any failure I/O ocurred.
	 */
	@Override
	public List<TagAntenna> getTagsList() {
		return api.reader.nesslab.utils.CaptureTagsRepresentation.getTags();
	}

	@Override
	public void captureTagsObject() throws UnknownHostException, IOException, SessionFullException {
		String response = getResponse();
		// TODO Representacao JSON das tags
		// api.reader.nesslab.utils.CaptureTagsRepresentation.getObjectRepresentation(response);
		//SpeedwayCaptureTagRepresentation.getObjectRepresentation(response);
	}

	/**
	 * Translates the response of reader by a understandable pattern.
	 * 
	 * @return Response translated.
	 * @throws UnknownHostException
	 *             Is trown when the host not found.
	 * @throws IOException
	 *             Is trown when any failure I/O ocurred.
	 */
	@Override
	public String getTranslatedResponse() throws UnknownHostException, IOException {
		return TranslateResponse.translate(this.getResponse());
	}

	/**
	 * To return a json representation of one reading.
	 * 
	 * @return Set of tags read.
	 */
	@Override
	public String getJsonRepresentation() {
		return SpeedwayCaptureTagRepresentation.getJsonTagUnique();
	}

	/**
	 * To return a json representation of each tag read.
	 * 
	 * @return Tag read.
	 */
	@Override
	public String getTagUniqueJsonRepresentation() {
		return SpeedwayCaptureTagRepresentation.getJsonTagUnique();
	}

	@Override
	public void clearTemporaryMemory(int secondsPeriod) {
		CaptureTagsRepresentation.clearMemory(secondsPeriod);

	}

	@Override
	public final void defaultConfiguration() {
		Settings settings = cr.getReader().queryDefaultSettings();

		// this will eventually cause buffer events
		settings.getReport().setIncludeAntennaPortNumber(true);
		settings.getReport().setIncludeChannel(true);
		settings.getReport().setMode(ReportMode.Individual );
		//settings.setReaderMode(ReaderMode.AutoSetDenseReader);
		
		settings.getReport().setIncludeAntennaPortNumber(true);		
		settings.getAutoStart().setMode(AutoStartMode.Periodic);
        settings.getAutoStart().setPeriodInMs(5000);
        settings.getAutoStop().setMode(AutoStopMode.None);
        settings.getAutoStop().setDurationInMs(10000000);

		settings.getAntennas().enableAll();

		try {
			cr.applySettings(settings);
		} catch (OctaneSdkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public TagAntenna getTag() {
		return CaptureTagsRepresentation.getTag();
	}

	public String getIpReader() {
		// TODO Auto-generated method stub
		return ipReader;
	}

}
