package RESTws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import structures.HashMapPlayers;

@Path("getopponents")
public class GetOpponents {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{username}")
	public Response handle(@PathParam("username") String username) {
		JsonArray arr = HashMapPlayers.getInstance().retrieveAllKeys();
		JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", username);
		arr.remove(jsonObject);
		return Response.status(200).entity(arr.toString()).build();
	}
}