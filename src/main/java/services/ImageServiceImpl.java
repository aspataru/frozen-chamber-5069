package services;

import static util.FileUtils.writeToFile;

import java.io.File;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;

import dao.ImageDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

	private static final String FILE_PATH = "pic.jpg";

	private final AtomicBoolean recording = new AtomicBoolean(false);

	private final ImageDAO imageDao;

	@Override
	public File retrieveSimpleFile() {
		// TODO be polite when there is no file
		return new File(FILE_PATH);
	}

	@Override
	public void storeSimpleFile(InputStream inputStream) {
		writeToFile(inputStream, FILE_PATH);
		if (isRecording()) {
			String newFilename = System.currentTimeMillis() + ".jpg";
			imageDao.store(FILE_PATH, newFilename);
			log.info("Stored image {}", newFilename);
		}
	}

	@Override
	public void startRecording() {
		recording.set(true);
		log.info("Recording ON");
	}

	@Override
	public void stopRecording() {
		recording.set(false);
		log.info("Recording OFF");
	}

	@Override
	public boolean isRecording() {
		return recording.get();
	}

}
