package nl.han.dea.dto;

import java.util.*;

public class playlistResponseDTO {



    private int length;
    private List<playlist> playlists = new ArrayList<playlist>();


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(playlist playlist) {
        this.playlists.add(playlist);
    }
}
