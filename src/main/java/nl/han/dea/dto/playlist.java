package nl.han.dea.dto;

import java.util.List;

public class playlist {

    private int id;
    private String name;
    private boolean owner;
    private List<track> tracks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public List<track> getTracks() {
        return tracks;
    }

    public void setTracks(List<track> tracks) {
        this.tracks = tracks;
    }
}
