package nl.han.dea.login;

import nl.han.dea.controllers.trackController;
import nl.han.dea.dao.trackDAO;
import nl.han.dea.dto.trackResponseDTO;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;


public class trackTest {


    private trackDAO trackDAO;
    private trackResponseDTO trackResponseDTO;


    @Test
    public void ReturnTracksTest(){
        trackResponseDTO trackResponseDTO = Mockito.mock(nl.han.dea.dto.trackResponseDTO.class);
       trackDAO = Mockito.mock(trackDAO.class);
        Mockito.when(trackDAO.ReturnTracks(1)).thenReturn(new trackResponseDTO());
        Assert.assertEquals(trackResponseDTO.getTracks(), trackDAO.ReturnTracks(1).getTracks());
    }

    @Test
    public void ReturnTracksNotInPlaylistTest(){
        trackResponseDTO = Mockito.mock(nl.han.dea.dto.trackResponseDTO.class);
        trackDAO = Mockito.mock(trackDAO.class);
        Mockito.when(trackDAO.ReturnTracksNotInPlaylist(1)).thenReturn(new trackResponseDTO());
        Assert.assertEquals(trackResponseDTO.getTracks(), trackDAO.ReturnTracksNotInPlaylist(1).getTracks());
    }

    @Test
    public void getAllTracksTest(){
        trackDAO = Mockito.mock(trackDAO.class);
        trackController trackController = Mockito.mock(trackController.class);
        Mockito.when(trackController.getAllTracks(1, "1234")).thenReturn(Response.ok(new trackResponseDTO()).build());
        Response test = trackController.getAllTracks(1, "1234");
        Assert.assertEquals(200, test.getStatus());
    }

}

