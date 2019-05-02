package RESTws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;

@Path("gamestats")
public class GameStats {

    private String query = "select number, description from gamestats";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response handle() throws ClassNotFoundException, SQLException {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/m111","root","root");
        PreparedStatement stmt = con.prepareStatement(this.query);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            float number = rs.getFloat("number");
            String description = rs.getString("description");
            objectNode.put(description, number);
        }
        stmt.close();
        con.close();
        return Response.status(200).entity(objectNode.toString()).build();
    }
}
