package com.sergio.resources;

import com.sergio.model.DepositRequest;
import com.sergio.model.OrderInfo;
import com.sergio.model.WithdrawRequest;
import com.sergio.model.events.Order;
import com.sergio.services.OperationService;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
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
import java.net.URI;
import java.util.List;

@Path("/operations")
public class OperationResource {

    @Inject
    OperationService operationService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject info(@Context UriInfo uriInfo) {
        URI uri = uriInfo.getBaseUriBuilder()
                .path(OperationResource.class)
                .path(OperationResource.class, "withdraw")
                .path(OperationResource.class, "deposit")
                .path(OperationResource.class, "check")
                .path(OperationResource.class, "events")
                .build("{orderId}", "{orderId}");
        return Json.createObjectBuilder().add("_links",
                Json.createArrayBuilder()
                        .add(uri.toASCIIString())
                        .build()
        ).build();
    }

    @POST
    @Path("/withdraw")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response withdraw(WithdrawRequest order, @Context UriInfo uriInfo) {
        String orderId = operationService.withdraw(order.getAccount(), order.getQuantity());
        UriBuilder uriBuilder = uriInfo.getBaseUriBuilder().path(OperationResource.class);
        uriBuilder.path(orderId);
        return Response.created(uriBuilder.build()).build();
    }

    @POST
    @Path("/deposit")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deposit(DepositRequest request, @Context UriInfo uriInfo) {
        String orderId = operationService.deposit(request.getAccount(), request.getQuantity());
        UriBuilder uriBuilder = uriInfo.getBaseUriBuilder().path(OperationResource.class);
        uriBuilder.path(orderId);
        return Response.created(uriBuilder.build()).build();
    }

    @GET
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response check(@PathParam("orderId") String orderId) {
        OrderInfo orderInfo = operationService.check(orderId);
        return Response.ok(orderInfo).build();
    }

    @GET
    @Path("/{orderId}/events")
    @Produces(MediaType.APPLICATION_JSON)
    public Response events(@PathParam("orderId") String orderId) {
        List<Order> allOrders = operationService.allOrders(orderId);
        return Response.ok(allOrders).build();
    }
}
