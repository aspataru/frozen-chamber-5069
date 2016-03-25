package services;

import static util.FileUtils.writeToFile;

import java.io.File;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;

public class ImageServiceImpl implements ImageService {

	private static final String FILE_PATH = "pic.jpg";

	private final AtomicBoolean recording = new AtomicBoolean(false);

	@Override
	public File retrieveSimpleFile() {
		// TODO be polite when there is no file
		return new File(FILE_PATH);
	}

	@Override
	public void storeSimpleFile(InputStream inputStream) {
		writeToFile(inputStream, FILE_PATH);
	}

	@Override
	public void startRecording() {
		recording.set(true);
	}

	@Override
	public void stopRecording() {
		recording.set(false);
	}

	@Override
	public boolean isRecording() {
		return recording.get();
	}

}
