package RESTws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import structures.HashMapPlayers;

@Path("newtable")
public class NewTable {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{username}")
	public Response handle(@PathParam("username") String username) {
		HashMapPlayers.getInstance().push(username);
		ObjectNode objectNode = new ObjectMapper().createObjectNode();
		objectNode.put("message", "Ok");
		return Response.status(200).entity(objectNode.toString()).build();
	}
}
