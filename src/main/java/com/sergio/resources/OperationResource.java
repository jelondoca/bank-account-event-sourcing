package com.sergio.resources;

import com.sergio.model.WithdrawalRequest;
import com.sergio.model.OrderInfo;
import com.sergio.services.OperationService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
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

@Path("/operations")
public class OperationResource {

    @Inject
    OperationService operationService;

    @POST
    @Path("/withdraw")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response withdraw(WithdrawalRequest order, @Context UriInfo uriInfo) {
        String orderId = operationService.withdraw(order.getAccount(), order.getQuantity());
        UriBuilder uriBuilder = uriInfo.getBaseUriBuilder().path(OperationResource.class);
        uriBuilder.path(orderId.toString());
        return Response.created(uriBuilder.build()).build();
    }

    @GET
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response check(@PathParam("orderId") String orderId) {
        OrderInfo orderInfo = operationService.check(orderId);
        return Response.ok(orderInfo).build();
    }
}
