package api.reader.nesslab.utils;

import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ExportJsonFormat {
	
	private static final String TAG_ANTENNA = "antenna";
	private static final String TAG_RFID = "tag";

	public static String exportListTags(Map<String, TagAntenna> tagsSource){
		JsonArray jsonArray = new JsonArray();	
		for(String key: tagsSource.keySet()){
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty(TAG_ANTENNA, tagsSource.get(key).getAntenna());
			jsonObject.addProperty(TAG_RFID, tagsSource.get(key).getTagRFID());
			jsonArray.add(jsonObject);
		}
		
		return jsonArray.toString();
	}
	
	public static String exportTag(TagAntenna tagAntennaSource){
		JsonObject json = new JsonObject();
		json.addProperty(TAG_ANTENNA, tagAntennaSource.getAntenna());
		json.addProperty(TAG_RFID, tagAntennaSource.getTagRFID());
		return json.toString();
	}
}
