package RESTws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonArray;

import structures.TableHolder;

@Path("returntables")
public class ReturnTables {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response handle() {
		JsonArray tables = TableHolder.getInstance().retrieveAllTables();
		return Response.status(200).entity(tables.toString()).build();
	}
}
