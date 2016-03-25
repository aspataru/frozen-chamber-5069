package resources;

import java.io.File;
import java.io.InputStream;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;

import services.ImageService;

@Path("/file")
public class ImageResource {

	@Autowired
	private ImageService imageService;

	@GET
	@Path("/download")
	@Produces("image/jpeg")
	public Response getFile() {
		Optional<File> fileOptional = imageService.retrieveSimpleFile();
		ResponseBuilder response = null;
		if (fileOptional.isPresent()) {
			response = Response.ok(fileOptional.get());
			response.header("Content-Disposition", "attachment; filename=pic.jpg");
		} else {
			response = Response.noContent();
		}

		return response.build();
	}

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		// String uploadedFileLocation = "/tmp/" + fileDetail.getFileName();
		imageService.storeSimpleFile(uploadedInputStream);

		return Response.status(200).entity("post ok").build();

	}

	@GET
	@Path("/rec/on")
	@Produces(MediaType.TEXT_PLAIN)
	public Response recOn() {
		imageService.startRecording();

		return Response.status(200).entity(imageService.isRecording()).build();
	}

	@GET
	@Path("/rec/off")
	@Produces(MediaType.TEXT_PLAIN)
	public Response recOff() {
		imageService.stopRecording();

		return Response.status(200).entity(imageService.isRecording()).build();
	}

	@GET
	@Path("/rec")
	@Produces(MediaType.TEXT_PLAIN)
	public Response rec() {
		return Response.status(200).entity(imageService.isRecording()).build();
	}

}