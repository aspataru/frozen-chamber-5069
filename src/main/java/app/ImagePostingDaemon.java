package app;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.RequiredArgsConstructor;
import services.CommandExecutingFileProvidingService;
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
		FileProvidingService fileService = new CommandExecutingFileProvidingService();
		ImagePostingDaemon daemon = new ImagePostingDaemon(fileService);
		daemon.start();
		daemon.stop();

	}

	private class PostingRunnable implements Runnable {
		@Override
		public void run() {
			while (true) {
				File fileToPost = fileService.getFile();
				System.out.println("posting file " + fileToPost);
				System.out.println(PostUtils.post(fileToPost));
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
