package RESTws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Path("join")
public class Join {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response handle() {
		ObjectNode objectNode = new ObjectMapper().createObjectNode();
		objectNode.put("message", "black");
		return Response.status(200).entity(objectNode.toString()).build();
	}
}
