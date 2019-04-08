package RESTws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import java.sql.*;

import obj_classes.*;


@Path("/register")
public class Register {	

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
			if(uname.equals(user.getName())) {
				con.close();
				return "This username already exists!";
			}
		}
		
		stmt.executeUpdate("insert into users (username ,password) values (" + "'" + user.getName() + "'" +  "," +  "'" + user.getPassword() + "'" +")");
				
		con.close();  
		
		return "Success!! New account created with username: " + user.getName();

	}
}