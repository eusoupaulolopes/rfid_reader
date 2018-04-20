package api.reader.nesslab.utils;


/**
 * This class should translate all responses of requests in reader.
 * */
public class TranslateResponse {
	
	
	public static String translate(String response){
		if(response != null){
			if(response.contains(">b")){
				return buzzerResponse(response);
			} else if (response.contains(">c")){
				return modeResonse(response);
			} else if(response.contains(">e")){
				return antennaStatusResponse(response);
			} else if(response.contains(">p")){
				return powerResponse(response);
			} else if(response.contains(">t")){
				return scanTimeResponse(response);
			} else {
				return response;
			}
		} else {
			return response;
		}
	}
	
	private static String powerResponse(String response){
		String[] message = response.split("p");
		return "the power is "+ message[1] + "dBm"; 
	}
	
	private static String buzzerResponse(String response){
		String[] message = response.split("b");
		if(message[1].equals("1")){
			return "buzzer is enabled";
		} else {
			return "buzzer is disabled";
		}
	}
	
	private static String scanTimeResponse(String response){
		String[] message = response.split("t");
		return "scan time is " + message[1] + "ms";
	}
	
	private static String antennaStatusResponse(String response){
		String[] message = response.split("e");
		switch (message[1]) {
			case "1":
				return "antenna port enable: 4";			
			case "2":
				return "antenna port enable: 3";
			case "3":
				return "antenna port enable: 3 and 4";
			case "4":
				return "antenna port enable: 2";
			case "5":
				return "antenna port enable: 2 and 4";
			case "6":
				return "antenna port enable: 2 and 3";
			case "7":
				return "antenna port enable: 2, 3 and 4";
			case "8":
				return "antenna port enable: 1";
			case "9":
				return "antenna port enable: 1 and 4";
			case "10":
				return "antenna port enable: 1 and 3";
			case "11":
				return "antenna port enable: 1, 3, and 4";
			case "12":
				return "antenna port enable: 1 and 2";
			case "13":
				return "antenna port enable: 1, 2, and 4";
			case "14":
				return "antenna port enable: 1, 2 and 3";
			case "15":
				return "antenna port enable: 1, 2, 3, and 4";
			default:
				return "invalid response.";
			}
	}
	
	private static String modeResonse(String response){
		String[] message = response.split("c");
		if(message[1].equals("1")){
			return "continue mode on";
		} else {
			return "continue mode off";
		}
	}
	
	
}
