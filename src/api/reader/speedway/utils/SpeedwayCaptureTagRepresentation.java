package api.reader.speedway.utils;

import com.google.gson.Gson;

public class SpeedwayCaptureTagRepresentation {
	private static String jsonRepresentation = "";
	private static String jsonTagUnique = "";
	private static String oldJsonTagUnique = "";

	public static void getObjectRepresentation(String response) {
		if (!response.isEmpty()) {
			oldJsonTagUnique = jsonTagUnique;
			jsonTagUnique = response;
			jsonRepresentation += jsonTagUnique;

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

	public static void setJsonRepresentation(String jsonRepresentation) {
		SpeedwayCaptureTagRepresentation.jsonRepresentation = jsonRepresentation;
	}

	public static String getJsonRepresentation() {
		
		return jsonTagUnique;
	}

}
