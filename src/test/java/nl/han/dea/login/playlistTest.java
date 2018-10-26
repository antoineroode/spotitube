package nl.han.dea.login;

import nl.han.dea.controllers.playlistController;
import nl.han.dea.controllers.trackController;
import nl.han.dea.dao.playlistDAO;
import nl.han.dea.dao.trackDAO;
import nl.han.dea.dto.playlistResponseDTO;
import nl.han.dea.dto.trackResponseDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

public class playlistTest {
    private playlistDAO playlistDAO;

    @Before
    public void setup(){
        playlistDAO = new playlistDAO();
    }

    @Test
    public void getPlaylistsTest(){
        playlistDAO = Mockito.mock(playlistDAO.class);
        playlistController playlistController = Mockito.mock(playlistController.class);
        Mockito.when(playlistController.Playlist("1234")).thenReturn(Response.ok(new playlistResponseDTO()).build());
        Response test = playlistController.Playlist("1234");
        Assert.assertEquals(200, test.getStatus());
    }
}


//    @Test
//    public void getAllTracksTest(){
//        trackDAO = Mockito.mock(trackDAO.class);
//        trackController trackController = Mockito.mock(trackController.class);
//        Mockito.when(trackController.getAllTracks(1, "1234")).thenReturn(Response.ok(new trackResponseDTO()).build());
//        Response test = trackController.getAllTracks(1, "1234");
//        Assert.assertEquals(200, test.getStatus());
//    }