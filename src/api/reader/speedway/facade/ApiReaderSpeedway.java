package api.reader.speedway.facade;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import com.impinj.octane.OctaneSdkException;

import api.reader.facade.ApiReaderFacade;
import api.reader.interfaces.Command;
import api.reader.interfaces.ConnectReader;
import api.reader.nesslab.exceptions.SessionFullException;
import api.reader.nesslab.utils.CaptureTagsRepresentation;
import api.reader.nesslab.utils.NesslabConnectReader;
import api.reader.nesslab.utils.OperationUtil;
import api.reader.nesslab.utils.TagAntenna;
import api.reader.nesslab.utils.TranslateResponse;
import api.reader.speedway.utils.SpeedwayConnectReader;

/**
 * Layer access to methods of communication and configuration with the RFID
 * Reader Nesslab RF1000.
 */
public class ApiReaderSpeedway implements ApiReaderFacade {

	private ConnectReader cr;
	private final Integer SPEEDWAY_PORT_DEFAULT = 14150;
	private final String ipReader;

	/**
	 * Contructor with ip parameter, when the ip is different of default ip.
	 * 
	 * @param command
	 *            is a command of API.
	 */
	public ApiReaderSpeedway(String ip) {
		this.ipReader = ip;
	}

	/**
	 * This method exposes the proposed connector to actions defined by a command
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
		try {
			command.execute(SpeedwayConnectReader.getInstance(this.getIpReader(), SPEEDWAY_PORT_DEFAULT ));
		} catch (OctaneSdkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Returns the response of commands.
	 * 
	 * @return Response of reader.
	 * @throws UnknownHostException
	 *             Is trown when the host not found.
	 * @throws IOException
	 *             Is trown when any failure I/O ocurred.
	 * @throws  
	 */
	@Override
	public String getResponse() throws UnknownHostException, IOException {
		SpeedwayConnectReader connector;
		String response = "";
		try {
			connector = SpeedwayConnectReader.getInstance(this.getIpReader(), SPEEDWAY_PORT_DEFAULT );
			response = connector.getResponse();
			
		} catch (OctaneSdkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * Check if exists any response of Reader.
	 * 
	 * @return True if exists response or false if not exist.
	 * @throws UnknownHostException
	 *             Is trown when the host not found.
	 * @throws IOException
	 *             Is trown when any failure I/O ocurred.
	 */
	@Override
	public boolean hasResponse() throws UnknownHostException, IOException {
		NesslabConnectReader connector = NesslabConnectReader.getInstance(OperationUtil.getIpReader(),
				OperationUtil.PORT_READER);
		return connector.hasResponse();
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

	/**
	 * Captures and encapsulates the tag in a TagAntenna object and adds to the
	 * list of tags.
	 * 
	 * @throws UnknownHostException
	 *             Is trown when the host not found.
	 * @throws IOException
	 *             Is trown when any failure I/O ocurred.
	 */
	@Override
	public void captureTagsObject() throws UnknownHostException, IOException, SessionFullException {
		String response = getResponse();
		api.reader.nesslab.utils.CaptureTagsRepresentation.getObjectRepresentation(response);
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
		return api.reader.nesslab.utils.CaptureTagsRepresentation.getJsonRepresentation();
	}

	/**
	 * To return a json representation of one reading.
	 * 
	 * @return Set of tags read.
	 */
	@Override
	public boolean hasNewTag() {
		return CaptureTagsRepresentation.haveNewTag();
	}

	/**
	 * To return a json representation of each tag read.
	 * 
	 * @return Tag read.
	 */
	@Override
	public String getTagUniqueJsonRepresentation() {
		return CaptureTagsRepresentation.getJsonTagUnique();
	}

	@Override
	public void clearTemporaryMemory(int secondsPeriod) {
		CaptureTagsRepresentation.clearMemory(secondsPeriod);

	}

	@Override
	public final void defaultConfiguration() throws UnknownHostException, IOException {
		OperationUtil.setPortReader(5084);
		// executeAction(new SetScanTime(1L));
		// executeAction(new DisableBuzzer());
		// executeAction(new SetPowerControl("250"));
		// executeAction(new EnableContinueMode());
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
