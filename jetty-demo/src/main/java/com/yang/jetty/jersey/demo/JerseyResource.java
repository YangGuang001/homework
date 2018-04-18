package com.yang.jetty.jersey.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class JerseyResource {

    @GET
    @Produces("text/plain")
    public String getHello() {
        return "hello world";
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHelloById(final @PathParam("id") String id) {
        return "hello world ..." + id;
    }
}
