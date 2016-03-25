package integration;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import dao.ImageDAOImpl;

public class TestImageDAOImpl {

	@Test
	@Ignore
	public void shouldConnect() {
		ImageDAOImpl dao = new ImageDAOImpl(System.getenv("MONGOLAB_URI"), "heroku_kk6rc05p", "img");

		List<String> namespaceContents = dao.listContents();

		Assert.assertFalse(namespaceContents.isEmpty());
	}

}
