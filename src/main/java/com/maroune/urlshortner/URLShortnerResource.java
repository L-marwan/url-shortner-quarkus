package com.maroune.urlshortner;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.jboss.logging.Logger;

@Path("/")
public class URLShortnerResource {
    private final Logger logger = Logger.getLogger(URLShortnerResource.class);

    private final IURLShortnerService shortnerService;

    public URLShortnerResource(IURLShortnerService shortnerService) {
        this.shortnerService = shortnerService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{key}")
    public Response redirect(@PathParam("key") String key) {
        logger.info("key: %s".formatted(key));
        var urlData = shortnerService.getURL(key);
        if (urlData == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        logger.info("URL Data: " + urlData);
        shortnerService.incrementVisits(key);
        return Response.status(Response.Status.FOUND).header("location", urlData.longURL()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{key}/metadata")
    @RolesAllowed("admin")
    public Response getMetadata(@PathParam("key") String key) {
        logger.info("key: %s".formatted(key));
        var urlData = shortnerService.getURL(key);
        if (urlData == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        logger.info("URL Data: " + urlData);

        return Response.ok(urlData).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    //@RolesAllowed("admin")
    public Response shorten(URLShortenRequest request, @Context SecurityContext securityContext) {
        var key = shortnerService.shortenURL(request.getUrl());
        var user = securityContext.getUserPrincipal() != null ?
                securityContext.getUserPrincipal().getName()
                : "Anonymous";
        logger.info("[%s] generated key: %s".formatted(user, key));
        return Response.ok(new URLShortenResponse(key, "kkk", request.getUrl())).build();
    }

}
