package RESTws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;

import obj_classes.User;

@Path("login")
public class Login {
	
	private String query = "select * from users where username=? and password=?";
	private String loggedIn = "update users set connected=true where username=? and connected=false";
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response handle(String input) throws SQLException, ClassNotFoundException {
		int status;
		ObjectNode node = new ObjectMapper().createObjectNode();
		User user = new Gson().fromJson(input, User.class);
		Class.forName("com.mysql.jdbc.Driver");
		try (Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/m111","root","root"); PreparedStatement stmt = con.prepareStatement(query)) {
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getPassword());
			if (stmt.executeQuery().next()) {
				try (PreparedStatement updstmt = con.prepareStatement(loggedIn)){
					updstmt.setString(1, user.getName());
					if (updstmt.executeUpdate() > 0) {
						status = 200;
						node.put("message", "Ok");
					}
					else {
						status = 401;
						node.put("message", "Already logged in");
					}
				}
			}
			else {
				status = 400;
				node.put("message", "Not authorized");
			}
		}
		return Response.status(status).entity(node.toString()).build();
	}
}