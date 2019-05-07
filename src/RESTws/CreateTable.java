package RESTws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import structures.HashMapPlayers;

@Path("createtable")
public class CreateTable {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{username}")
	public Response handle(@PathParam("username") String username) {
		HashMapPlayers.getInstance().push(username);
		return Response.status(200).entity(new ObjectMapper().createObjectNode().put("message", "Ok")).build();
	}
}