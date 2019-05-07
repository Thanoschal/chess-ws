package RESTws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import obj_classes.Table;
import structures.TableHolder;

@Path("newgametable")
public class NewGameTable {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response handle(String input)  {
		TableHolder.getInstance().add(new Gson().fromJson(input, Table.class));
		return Response.status(200).entity(new ObjectMapper().createObjectNode().put("message", "Ok").toString()).build();
	}
}