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

@Path("personalstats")
public class PersonalStats {

    private String query = "SELECT COUNT(*) AS number, 'gamesPlayed' AS description FROM tables WHERE white=? or black=? UNION ALL " +
            "SELECT COUNT(*) AS number, 'gamesWon' AS description FROM tables WHERE winner=? UNION ALL " +
            "SELECT COUNT(*) AS number, 'gamesLost' AS description FROM tables WHERE (white=? or black=?) and winner <> ? and winner is not null UNION ALL " +
            "SELECT COUNT(*) AS number, 'draws' AS description FROM tables WHERE  (white=? or black=?) and winner is null UNION ALL " +
            "SELECT COUNT(*) AS number, 'white' AS description FROM tables WHERE white=? UNION ALL " +
            "SELECT COUNT(*) AS number, 'black' AS description FROM tables WHERE black=? UNION ALL " +
            "SELECT AVG(mv) as number, 'avgMoves' as description FROM (SELECT (moves-1) AS mv FROM tables WHERE (white=? or black=?) and winner<>? and winner is not null UNION ALL SELECT moves AS mv FROM tables WHERE (white=? or black=?) and (winner=? or winner is null)) AS a";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{username}")
    public Response handle(@PathParam("username") String username) throws SQLException, ClassNotFoundException {
        ObjectNode node = new ObjectMapper().createObjectNode();
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/m111","root","root"); PreparedStatement stmt = con.prepareStatement(query)) {
            for (int i = 1; i < 17; i++) stmt.setString(i, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) node.put(rs.getString("description"), rs.getFloat("number"));
        }
        return Response.status(200).entity(node.toString()).build();
    }
}