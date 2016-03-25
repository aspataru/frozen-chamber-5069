package services;

import java.io.File;
import java.io.InputStream;
import java.util.Optional;

public interface ImageService {

	Optional<File> retrieveSimpleFile();

	void storeSimpleFile(InputStream inputStream);

	void startRecording();

	void stopRecording();

	boolean isRecording();

}
