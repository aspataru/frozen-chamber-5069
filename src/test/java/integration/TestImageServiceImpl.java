package integration;

import java.io.File;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dao.ImageDAO;
import services.ImageServiceImpl;

public class TestImageServiceImpl {

	@Mock
	private ImageDAO imageDao;

	@InjectMocks
	private ImageServiceImpl imageService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnEmptyOptionalWhenNoFileIsPresent() {
		Optional<File> file = imageService.retrieveSimpleFile();
		Assert.assertFalse(file.isPresent());

	}

}
