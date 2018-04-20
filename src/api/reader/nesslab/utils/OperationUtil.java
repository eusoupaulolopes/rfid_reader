package api.reader.nesslab.utils;

public class OperationUtil {

	public static final String BUZZER_ON = ">x b 1 /r/n";
	public static final String BUZZER_OFF = ">x b 0 /r/n";
	public static final String BUZZER_STATUS = ">y b /r/n";
	public static final String ANTENNA_STATUS = ">y e /r/n";
	public static final String SCANTIME_STATUS = ">y t /r/n";
	public static final String POWER_CONTROL_STATUS = ">y p /r/n";
	public static final String READ_TAGS_INVENTORY = ">f \r\n";
	public static final String READ_TAGS = ">r \r\n";
	public static final String CONTINUE_MODE_ON = ">x c 1 \r\n";
	public static final String CONTINUE_MODE_OFF = ">x c 0 \r\n";
	public static final String CONTINUE_MODE_STATUS = ">y c \r\n";
	public static final String IP_READER_NESSLAB_DEFAULT = "192.168.10.91";
	public static final String GET_IP_VALUE = ">y r / r/ n ";
	public static final String FORCE_STOP_READER = ">3 / r/n";
	public static final String MULTI_TAG_READ_RESET = ">g /r /n";
	public static final String ONE_TAG_READ = ">e /r /n";
	public static int PORT_READER = 5578;

	private static String ipReader;

	public static String setScanTime(long time) {
		String timeMS = String.valueOf(time);
		return ">x t " + timeMS + "/r/n";
	}

	public static String setPowerControl(String power) {
		return ">x p " + power + "/r/n";
	}

	public static String setIpAdress(String ip) {
		return ">x r" + ip + "/r/n";
	}

	// pattern: 0 0 0 0
	public static String enableAntennas(String antennas) {
		switch (antennas) {
		case "0 0 0 1":
			return ">x e 1 /r/n";
		case "0 0 1 0":
			return ">x e 2 /r/n";
		case "0 0 1 1":
			return ">x e 3 /r/n";
		case "0 1 0 0":
			return ">x e 4 /r/n";
		case "0 1 0 1":
			return ">x e 5 /r/n";
		case "0 1 1 0":
			return ">x e 6 /r/n";
		case "0 1 1 1":
			return ">x e 7 /r/n";
		case "1 0 0 0":
			return ">x e 8 /r/n";
		case "1 0 0 1":
			return ">x e 9 /r/n";
		case "1 0 1 0":
			return ">x e 10 /r/n";
		case "1 0 1 1":
			return ">x e 11 /r/n";
		case "1 1 0 0":
			return ">x e 12 /r/n";
		case "1 1 0 1":
			return ">x e 13 /r/n";
		case "1 1 1 0":
			return ">x e 14 /r/n";
		case "1 1 1 1":
			return ">x e 15 /r/n";
		default:
			return "invalid command.";
		}
	}

	public static void setIpReader(String ipReaderConnector) {
		ipReader = ipReaderConnector;
	}

	public static String getIpReader() {
		return ipReader;
	}

	public static int getPortReader() {
		return PORT_READER;
	}

	public static void setPortReader(int pORT_READER) {
		PORT_READER = pORT_READER;
	}

}
