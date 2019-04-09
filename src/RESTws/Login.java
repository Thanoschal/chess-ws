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

@Path("/login")
public class Login {
	
	private String query = "select * from users where username=? and password=?";
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(String input) throws SQLException, ClassNotFoundException {
		int status;
		ObjectNode objectNode = new ObjectMapper().createObjectNode();
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/m111","root","root");
		User user = new Gson().fromJson(input, User.class);
		PreparedStatement stmt = con.prepareStatement(this.query);
		stmt.setString(1, user.getName());
		stmt.setString(2, user.getPassword());
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			status = 200;
			objectNode.put("status", "Ok");
		}
		else {
			status = 400;
			objectNode.put("status", "Failed");
		}
		rs.close();
		stmt.close();
		con.close();  
		return Response.status(status).entity(objectNode.toString()).build();
	}

}
