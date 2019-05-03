package RESTws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Path("logout")
public class Logout {

    private String query = "update users set connected=false where username=?";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{username}")
    public Response handle(@PathParam("username") String username) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/m111","root","root");
        PreparedStatement stmt = con.prepareStatement(this.query);
        stmt.setString(1, username);
        stmt.executeUpdate();
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("message", "Ok");
        return Response.status(200).entity(objectNode.toString()).build();
    }
}
