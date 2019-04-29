package RESTws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;

import obj_classes.Table;
import structures.TableHolder;

@Path("removegametable")
public class RemoveGameTable {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response handle(String input)  {
		Table table = new Gson().fromJson(input, Table.class);
		TableHolder.getInstance().remove(table);
		ObjectNode objectNode = new ObjectMapper().createObjectNode();
		objectNode.put("message", "Ok");
		objectNode.put("Content", table.toString());
		return Response.status(200).entity(objectNode.toString()).build();
	}
}
