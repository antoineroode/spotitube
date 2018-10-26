package nl.han.dea.dao;

import nl.han.dea.dto.playlist;
import nl.han.dea.dto.playlistResponseDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static nl.han.dea.dao.connectionHandler.conn;
import static nl.han.dea.dao.connectionHandler.properties;

public class playlistDAO {

    private Logger logger = Logger.getLogger(getClass().getName());

    public playlistResponseDTO ReturnPlaylist(String token) {
        String query = "SELECT * FROM playlists " +
                "LEFT JOIN playlist_token ON playlists.playlistID = playlist_token.playlistID " +
                "LEFT JOIN users ON users.token = playlist_token.token " +
                "WHERE users.token = ?";
        PreparedStatement ps = null;
        playlistResponseDTO playlistResponseDTO = new playlistResponseDTO();
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, token);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                playlist playlist = new playlist();
                playlist.setId(result.getInt("playlistID"));
                playlist.setName(result.getString("name"));
                playlist.setOwner(result.getBoolean("owner"));
                playlistResponseDTO.setPlaylists(playlist);
                playlistResponseDTO.setLength(3463);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + properties.getProperty("connectionString"), e);
        }
        return playlistResponseDTO;
    }

    public void setPlaylistName(playlist playlist, int id) {
        String query = "UPDATE playlists SET name = ? WHERE playlistID = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, playlist.getName());
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + properties.getProperty("connectionString"), e);
        }
    }

    public void addPlaylist(playlist playlist, String token) {
        String query1 = "INSERT INTO playlists (name, owner) VALUES(?, true)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query1);
            ps.setString(1, playlist.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + properties.getProperty("connectionString"), e);
        }
        addPlaylistToken(playlist, token);
    }

    public void addPlaylistToken(playlist playlist, String token) {
        String query = "SELECT playlistID FROM playlists WHERE name = ?";
        String query2 = "INSERT INTO playlist_token (playlistID, token) VALUES(?,?)";
        int id = 0;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, playlist.getName());
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                id = result.getInt("playlistID");
            }
            ps = conn.prepareStatement(query2);
            ps.setInt(1, id);
            ps.setString(2, token);
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + properties.getProperty("connectionString"), e);
        }
    }

    public void delete(int id) {
        String query = "DELETE playlists FROM playlists WHERE  playlistID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + properties.getProperty("connectionString"), e);
        }
    }
}
