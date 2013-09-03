package com.nixsolutions.restlaba.rest;

import com.nixsolutions.restlaba.domain.Role;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path()
public interface RequestService {

    @GET
    @Path(value="/byId/{id}")
    public  getRoleById(String request);

}
