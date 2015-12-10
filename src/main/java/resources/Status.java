package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import services.BogusService;

@Path("/")
public class Status {
	
	@Autowired
	private BogusService service;
	
    @GET
    @Path("status")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return service.sayBogus();
    }

}
