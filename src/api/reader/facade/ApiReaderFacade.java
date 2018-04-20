package api.reader.facade;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import api.reader.interfaces.Command;
import api.reader.nesslab.exceptions.SessionFullException;
import api.reader.nesslab.utils.TagAntenna;

public interface ApiReaderFacade {

	void executeAction(Command command) throws UnknownHostException, IOException;
	
	String getResponse() throws UnknownHostException, IOException;
	
	boolean hasResponse() throws UnknownHostException, IOException;
	
	List<TagAntenna> getTagsList();
	
	TagAntenna getTag();
	
	void captureTagsObject() throws UnknownHostException, IOException, SessionFullException;
	
	String getTranslatedResponse() throws UnknownHostException, IOException;
	
	String getJsonRepresentation();
	
	boolean hasNewTag();
	
	void clearTemporaryMemory(int secondsPeriod);
	
	String getTagUniqueJsonRepresentation();
	
	void defaultConfiguration() throws UnknownHostException, IOException;
}
