package com.maroune.urlshortner;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.net.URISyntaxException;

@Path("/short")
public class URLShortnerResource {
    private final Logger logger = Logger.getLogger(URLShortnerResource.class);

    private final IURLShortnerService shortnerService;

    public URLShortnerResource(IURLShortnerService shortnerService) {
        this.shortnerService = shortnerService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{key}")
    public Response redirect(@PathParam("key") String key) throws URISyntaxException {
        logger.info("key: %s".formatted(key));
        String url = shortnerService.getURL(key);
        if (url == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FOUND).header("location", url).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response shorten(URLShortenRequest request) {
        var key = shortnerService.shortenURL(request.getUrl());
        return Response.ok(new URLShortenResponse(key, "kkk", request.getUrl())).build();
    }

}
