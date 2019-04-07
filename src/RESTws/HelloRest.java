package RESTws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/register")
public class HelloRest {

  @GET
  public String message() {
    return "Hello, rest!";
  }
}