package api.reader.nesslab.utils;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import api.reader.nesslab.exceptions.SessionFullException;
/**
 * This class centralizes all functions for to encapsulate 
 * tags RFID in Objects or JSON representations. 
 * */
public class CaptureTagsRepresentation {

	private static Map<String, TagAntenna> tags;
	private static TagAntenna tag;
	private static final String CODE_SUCESS_INVENTORY = "9C01";
	private static final String CODE_ERRO_DUPLICATION_EXECUTION = "9C91";
	private static final String CODE_ERRO_STOP_FORCE = "9S00";
	
	
	private static String jsonRepresentation = "";
	private static String jsonTagUnique = "";
	
	
	private static Map<String, TagAntenna> getInstanceTags(){
		if(tags == null){
			tags = new HashMap<>();
		}
		return tags;
	}
	
	public static boolean verifyTagExists(String key){
		return getInstanceTags().containsKey(key);
	}


	public static void getObjectRepresentation(String response)
			throws UnknownHostException, IOException, SessionFullException {
		TagAntenna tmp;
		tmp = new TagAntenna(response);
		if (response.equals(CODE_SUCESS_INVENTORY) 
				|| response.equals(CODE_ERRO_DUPLICATION_EXECUTION)
				|| response.equals(CODE_ERRO_STOP_FORCE)) {
			throw new SessionFullException("Session memory is full.");
		} else {
			if (verifyTagExists(tmp.getTagRFID())) {
				tmp = getInstanceTags().get(tmp.getTagRFID());
				tmp.setCountReader(tmp.getCountReader() + 1L);
				jsonTagUnique= "";
			} else {
				getInstanceTags().put(tmp.getTagRFID(), tmp);
				jsonTagUnique = ExportJsonFormat.exportTag(tmp);
				tag = tmp;
				jsonRepresentation = ExportJsonFormat.exportListTags(getInstanceTags());
			}
		}

	}
	
	public static void clearMemory(int timeSecondsRange){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				getInstanceTags().clear();
				
			}
		}, 0, timeSecondsRange*1000L);
	}

	public static String getJsonRepresentation() {
		return jsonRepresentation;
	}

	public static List<TagAntenna> getTags() {
	    List<TagAntenna> newList = new ArrayList<TagAntenna>();
		for(String key: getInstanceTags().keySet()){
			newList.add(getInstanceTags().get(key));
		}
		
		return newList;
	}
	
	public static boolean haveNewTag(){
		return !jsonTagUnique.equals("");
	}

	public static String getJsonTagUnique() {
		return jsonTagUnique;
	}

	public static void setJsonTagUnique(String jsonTagUnique) {
		CaptureTagsRepresentation.jsonTagUnique = jsonTagUnique;
	}
	
	public static TagAntenna getTag(){
		return tag;
	}
	
	

}
