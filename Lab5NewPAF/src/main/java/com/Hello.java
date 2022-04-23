package com;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Hello")
public class Hello
{
@GET
@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public String hello()
{
return "Hello world.";
}


//add a new method to represent a new resource
@GET
@Path("/{name}")
@Produces(MediaType.TEXT_PLAIN)
public String helloName(@PathParam("name") String name)
{
return "Hello " + name;
}
}