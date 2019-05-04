package RESTws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import structures.TableHolder;

@Path("showalltables")
public class ShowAllTables {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response handle() {
		return Response.status(200).entity(TableHolder.getInstance().retrieveAllTables().toString()).build();
	}
}
