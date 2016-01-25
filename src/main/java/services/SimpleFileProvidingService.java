package services;

import java.io.File;

public class SimpleFileProvidingService implements FileProvidingService {
	
	private static final String FILE_PATH = "/home/secre/Desktop/cat5.jpg";
	private static final File FILE = new File(FILE_PATH);

	@Override
	public File getFile() {
		return FILE;
	}

}
