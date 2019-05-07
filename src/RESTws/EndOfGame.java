package RESTws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;

import obj_classes.FinishedGame;

@Path("endofgame")
public class EndOfGame {
	
	private String updateStatement = "insert into tables (white, black, moves, winner, winnerColor) values (?,?,?,?,?)";
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response handle(String input) throws ClassNotFoundException, SQLException {
		int status;
		ObjectNode node = new ObjectMapper().createObjectNode();
		FinishedGame finishedGame = new Gson().fromJson(input, FinishedGame.class);
		Class.forName("com.mysql.jdbc.Driver");
		try (Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/m111","root","root"); PreparedStatement stmt = con.prepareStatement(updateStatement)) {
			stmt.setString(1, finishedGame.getWhite());
			stmt.setString(2, finishedGame.getBlack());
			stmt.setInt(3, finishedGame.getMoves());
			stmt.setString(4, finishedGame.getWinner());
			stmt.setString(5, finishedGame.getWinnerColor());
			if (stmt.executeUpdate() > 0) {
				status = 200;
				node.put("message", "Ok");
			}
			else {
				status = 500;
				node.put("message", "Failed");
			}
		}
		return Response.status(status).entity(node.toString()).build();
	}
}