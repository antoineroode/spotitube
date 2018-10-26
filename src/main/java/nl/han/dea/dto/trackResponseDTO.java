package nl.han.dea.dto;

import java.util.ArrayList;

public class trackResponseDTO {

    private ArrayList<track> tracks = new ArrayList<>();

    public ArrayList<track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<track> tracks) {
        this.tracks = tracks;
    }

}
