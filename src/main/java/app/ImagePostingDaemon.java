package app;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.RequiredArgsConstructor;
import services.FileProvidingService;
import services.SimpleFileProvidingService;
import util.PostUtils;

@RequiredArgsConstructor
public class ImagePostingDaemon {

	private final FileProvidingService fileService;
	
	private final ExecutorService executorService = Executors.newSingleThreadExecutor();
	
	public void start() {
		executorService.submit(new PostingRunnable());
	}
	
	public void stop() {
		executorService.shutdown();
	}
	
	public static void main(String[] args) {

		ImagePostingDaemon daemon = new ImagePostingDaemon(new SimpleFileProvidingService());
		daemon.start();
		daemon.stop();

	}

	private class PostingRunnable implements Runnable {
		@Override
		public void run() {
			File fileToPost = fileService.getFile();
			System.out.println("posting file " + fileToPost);
			System.out.println(PostUtils.post(fileToPost));
		} 
	}

}
