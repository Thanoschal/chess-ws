package RESTws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import obj_classes.User;

@Path("/login")
public class Login {
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public String register(String userString) throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/m111","root","root");
		
		Gson g = new Gson();
		User user = g.fromJson(userString, User.class);
		
		Statement stmt = con.createStatement();  
		ResultSet rs = stmt.executeQuery("select * from users");
		
		while(rs.next()) {
			String uname = rs.getString(2);
			String pass = rs.getString(3);
			if(uname.equals(user.getName())) {
				if(pass.equals(user.getPassword())) {
					con.close();
					return "Authorized";
				}
				
			}
		}
				
		con.close();  
		
		return "Not Autorized";

	}
}
