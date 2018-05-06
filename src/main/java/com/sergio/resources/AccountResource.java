package com.sergio.resources;

import com.sergio.model.AccountInfo;
import com.sergio.model.CreateAccountRequest;
import com.sergio.services.AccountService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

@Path("/accounts")
public class AccountResource {

    @Inject
    AccountService accountService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all(@Context UriInfo uriInfo) {
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") String accountId) {
        AccountInfo account = accountService.get(accountId);
        return Response.ok(account).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CreateAccountRequest accountRequest, @Context UriInfo uriInfo){
        String ownerName = accountRequest.getOwnerName();
        String ownerSurnames =accountRequest.getOwnerSurnames();
        String accountId = accountService.create(ownerName, ownerSurnames);
        UriBuilder uriBuilder = uriInfo.getBaseUriBuilder().path(AccountResource.class);
        uriBuilder.path(accountId);
        return Response.created(uriBuilder.build()).build();
    }

}
