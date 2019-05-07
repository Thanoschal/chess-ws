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
        ObjectNode node = new ObjectMapper().createObjectNode();
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/m111","root","root"); PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
                node.put(rs.getString("description"), rs.getFloat("number"));
        }
        return Response.status(200).entity(node.toString()).build();
    }
}