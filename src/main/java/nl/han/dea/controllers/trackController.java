package nl.han.dea.controllers;

import nl.han.dea.dao.trackDAO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/tracks")
public class trackController {
    private nl.han.dea.dao.trackDAO trackDAO = new trackDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracks(@QueryParam("forPlaylist") int forPlaylist, @QueryParam("token") String token) {
        return Response.ok(trackDAO.ReturnTracksNotInPlaylist(forPlaylist)).build();
    }

    @Inject
    public void setTrackDAO(nl.han.dea.dao.trackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }
}
