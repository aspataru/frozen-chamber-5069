package services;

import java.io.File;
import java.io.InputStream;

public interface ImageService {

	File retrieveSimpleFile();

	void storeSimpleFile(InputStream inputStream);

	void startRecording();

	void stopRecording();

	boolean isRecording();

}
