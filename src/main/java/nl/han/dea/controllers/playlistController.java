package nl.han.dea.controllers;

import nl.han.dea.dao.playlistDAO;
import nl.han.dea.dao.trackDAO;
import nl.han.dea.dto.playlist;
import nl.han.dea.dto.track;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/playlists")
public class playlistController {

    private trackDAO trackDAO = new trackDAO();
    private playlistDAO playlistDAO = new playlistDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response Playlist(@QueryParam("token") String token) {
        return Response.ok(playlistDAO.ReturnPlaylist(token)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, playlist playlist) {
        playlistDAO.addPlaylist(playlist, token);
        return Response.ok(playlistDAO.ReturnPlaylist(token)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") final int id, @QueryParam("token") String token) {
        playlistDAO.delete(id);
        return Response.ok(playlistDAO.ReturnPlaylist(token)).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response PlaylistNameEdit(@PathParam("id") final int id, @QueryParam("token") String token, playlist playlist) {
        playlistDAO.setPlaylistName(playlist, id);
        return Response.ok(playlistDAO.ReturnPlaylist(token)).build();
    }

    @Path("{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response allTracks(@PathParam("id") int id)  {
        return Response.ok(trackDAO.ReturnTracks(id)).build();
    }

    @Path("{id}/tracks")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTrack(track track, @PathParam("id") int id) {
        trackDAO.addTrack(id, track);
        return Response.ok(trackDAO.ReturnTracks(id)).build();
    }

    @Path("{id}/tracks/{trackid}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteTrack(@PathParam("id") int id, @PathParam("trackid") int trackid) {
        trackDAO.deleteTrack(id, trackid);
        return Response.ok(trackDAO.ReturnTracks(id)).build();
    }

    @Inject
    public void setTrackDAO(trackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @Inject
    public void setPlaylistDAO(playlistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }
}
