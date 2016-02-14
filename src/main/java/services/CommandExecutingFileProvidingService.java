package services;

import java.io.File;
import java.io.IOException;

public class CommandExecutingFileProvidingService implements FileProvidingService {

	private static final String PATH = "/tmp/pi.jpg";
	private static final String COMMAND_BASE = "raspistill -n -w 640 -h 480 -q 100 -o ";

	private static final String COMMAND;

	static {
		COMMAND = COMMAND_BASE + PATH;
	}

	@Override
	public File getFile() {

		try {
			System.out.println("Running command " + COMMAND);
			Process p = Runtime.getRuntime().exec(COMMAND);
			p.waitFor();
			System.out.println("Process finished");

		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new File(PATH);
	}

}
