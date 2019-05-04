package RESTws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/*
 * https://www.mkyong.com/java/how-to-execute-shell-command-from-java/
 */

@Path("createtopics")
public class CreateTopics {
	
	private String command = "kafka-topics --create --zookeeper 195.134.71.250:2181 --replication-factor 1 --partitions 1 --topic ";
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{username}")
	public Response handle(@PathParam("username") String username) throws IOException, InterruptedException {
		String topic = command + username + "; " + command + username + "Chat;";
		ObjectNode objectNode = new ObjectMapper().createObjectNode();
		objectNode.put("message", createKafkaTopic(topic));
		return Response.status(200).entity(objectNode.toString()).build();
	}
	
	private String createKafkaTopic(String topic) throws IOException, InterruptedException {
		// -- Linux --
		ProcessBuilder processBuilder = new ProcessBuilder();
		// Run a shell command
		processBuilder.command("bash", "-c", topic);
		StringBuilder output = new StringBuilder();
		Process process = processBuilder.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			output.append(line + "\n");
		}
		process.waitFor();
		return output.toString();
	} 	
}