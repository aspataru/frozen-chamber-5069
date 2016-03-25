package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import services.StatusService;

@Path("/")
public class StatusResource {

	@Autowired
	private StatusService service;

	@GET
	@Path("status")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return service.getStatus();
	}

}
