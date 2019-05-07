package RESTws;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;

@Path("top5")
public class Top5 {
    private String query = "select winner, wins from top5";
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response handle() throws ClassNotFoundException, SQLException {
        JsonArray jsonArray = new JsonArray();
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/m111","root","root"); PreparedStatement stmt = con.prepareStatement(query)) {
           ResultSet rs = stmt.executeQuery();
           while (rs.next()) {
               JsonObject jsonObject = new JsonObject();
               jsonObject.addProperty("winner", rs.getString("winner"));
               jsonObject.addProperty("wins", rs.getInt("wins"));
               jsonArray.add(jsonObject);
           }
       }
        return Response.status(200).entity(jsonArray.toString()).build();
    }
}