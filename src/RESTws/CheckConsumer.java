package RESTws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;

@Path("checkconsumer")
public class CheckConsumer {

    private String query = "select kafka from users where username=?";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{username}")
    public Response handle(@PathParam("username") String username) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/m111","root","root");
        PreparedStatement stmt = con.prepareStatement(this.query);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        ObjectNode node = new ObjectMapper().createObjectNode();
        int status;
        if (rs.next()) {
            status = 200;
            boolean flag = rs.getBoolean("kafka");
            node.put("message", "Ok");
            node.put("flag", flag);
        }
        else {
            status = 400;
            node.put("message", "Failed");
            node.put("flag", false);
        }
        stmt.close();
        con.close();
        return Response.status(status).entity(node.toString()).build();
    }
}
