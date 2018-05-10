package api.reader.speedway.utils;

import com.google.gson.Gson;

public class SpeedwayCaptureTagRepresentation {

	private static String jsonTagUnique = "";
	private static String oldJsonTagUnique = "";
	
	
	/**
	 * Follow specification of Nesslab reader.
	 * @param response
	 */
	public static void getObjectRepresentation(String response) {
		if (!response.isEmpty()) {
			jsonTagUnique = response;

		}
	}

	public static boolean haveNewTag() {
		return !jsonTagUnique.equals(oldJsonTagUnique);
	}

	public static String getJsonTagUnique() {
		return jsonTagUnique;
	}

	public static void setJsonTagUnique(String jsonTag) {
		oldJsonTagUnique = jsonTagUnique;
		jsonTagUnique = jsonTag;
	}


	/**
	 * Modify this method to alter printer mode 
	 * @param string
	 */
	public static void showTagUnique() {
		System.out.println(jsonTagUnique);
		
	}

}
