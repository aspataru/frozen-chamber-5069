package util;

import java.io.File;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

public class PostUtils {

	public static String post(/* String url, File f, String formName */File f) {
		try {
		FormDataMultiPart form = new FormDataMultiPart().field("file", f, MediaType.MULTIPART_FORM_DATA_TYPE);

		Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
		Response entity = client.target("http://frozen-chamber-5069.herokuapp.com").path("file/upload")
				.request(MediaType.MULTIPART_FORM_DATA)
				// .accept(MediaType.TEXT_PLAIN)
				.post(Entity.entity(form, MediaType.MULTIPART_FORM_DATA_TYPE));

		return entity.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
