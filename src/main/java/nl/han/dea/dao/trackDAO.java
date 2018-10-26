package nl.han.dea.dao;

import nl.han.dea.dto.track;
import nl.han.dea.dto.trackResponseDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


import static nl.han.dea.dao.connectionHandler.conn;
import static nl.han.dea.dao.connectionHandler.properties;

public class trackDAO {

    private Logger logger = Logger.getLogger(getClass().getName());

    public ArrayList<track> setTracks(String query, int id) throws SQLException {
        ArrayList<track> tracks = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            track track = new track();
            track.setId(result.getInt("trackID"));
            track.setTitle(result.getString("title"));
            track.setPerformer(result.getString("performer"));
            track.setDuration(result.getInt("duration"));
            track.setAlbum(result.getString("album"));
            track.setPlaycount(result.getInt("playcount"));
            track.setPublicationDate(result.getString("publicationDate"));
            track.setDescription(result.getString("description"));
            track.setOfflineAvailable(result.getBoolean("offlineAvailable"));
            tracks.add(track);
        }
        return tracks;
    }

    public trackResponseDTO ReturnTracks(int id) {
        trackResponseDTO trackResponse = new trackResponseDTO();
        String query = "SELECT tracks.trackID, tracks.title, tracks.performer, tracks.duration, tracks.album, tracks.playcount, " +
                "tracks.publicationDate, tracks.description, offlineAvailable " +
                "FROM tracks LEFT JOIN playlist_tracks ON tracks.trackID = playlist_tracks.trackID  " +
                "LEFT JOIN playlists ON playlists.playlistID = playlist_tracks.playlistID WHERE playlists.playlistID = ?";
        try {
            trackResponse.setTracks(setTracks(query, id));
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + properties.getProperty("connectionString"), e);
        }
        return trackResponse;
    }

    public trackResponseDTO ReturnTracksNotInPlaylist(int id) {
        trackResponseDTO trackResponse = new trackResponseDTO();
        String query = "SELECT tracks.trackID, tracks.title, tracks.performer, tracks.duration, tracks.album, tracks.playcount, " +
                "tracks.publicationDate, tracks.description, offlineAvailable FROM tracks " +
                "LEFT JOIN playlist_tracks ON tracks.trackID = playlist_tracks.trackID " +
                "LEFT JOIN playlists ON playlists.playlistID = playlist_tracks.playlistID " +
                "WHERE tracks.trackID NOT IN (SELECT trackID FROM playlist_tracks WHERE playlistID = ?) GROUP by tracks.trackID";
        try {
            trackResponse.setTracks(setTracks(query, id));
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + properties.getProperty("connectionString"), e);
        }
        return trackResponse;
    }

    public void addTrack(int id, track track) {
        String query = "INSERT INTO playlist_tracks(playlistID, trackID, offlineAvailable) VALUES(?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, track.getId());
            ps.setBoolean(3, track.isOfflineAvailable());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + properties.getProperty("connectionString"), e);
        }
    }

    public void deleteTrack(int playlistID, int trackID) {
        String query = "DELETE playlist_tracks FROM playlist_tracks WHERE playlistID = ? AND trackID = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, playlistID);
            ps.setInt(2, trackID);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + properties.getProperty("connectionString"), e);
        }
    }

}
