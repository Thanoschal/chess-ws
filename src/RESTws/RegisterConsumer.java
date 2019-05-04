package RESTws;

import com.fasterxml.jackson.databind.ObjectMapper;

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

@Path("registerconsumer")
public class RegisterConsumer {
    private String query = "update users set kafka=true where username=?";
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{username}")
    public Response handle(@PathParam("username") String username) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/m111","root","root");
        PreparedStatement stmt = con.prepareStatement(this.query);
        stmt.setString(1, username);
        stmt.executeUpdate();
        stmt.close();
        con.close();
        return Response.status(200).entity(new ObjectMapper().createObjectNode().put("message", "Ok").toString()).build();
    }
}
