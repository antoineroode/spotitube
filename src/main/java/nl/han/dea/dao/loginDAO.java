package nl.han.dea.dao;

import nl.han.dea.dto.loginRequestDTO;
import nl.han.dea.dto.loginResponseDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static nl.han.dea.dao.connectionHandler.conn;
import static nl.han.dea.dao.connectionHandler.properties;


public class loginDAO {
    private Logger logger = Logger.getLogger(getClass().getName());
    public connectionHandler connectionHandler = new connectionHandler();
    public static loginDAO loginDAO = new loginDAO();
    public static void main(String[] args) {

    }

    private ResultSet returnResult(String user, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE user = ? && password = ? ";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, user);
        ps.setString(2, password);
       return ps.executeQuery();
    }

    public boolean AuthenticateUser(String user, String password) {

        ResultSet result = null;
        try {
            result = returnResult(user, password);
            while (result.next()) {
                if (user.equals(result.getString("user")) && password.equals(result.getString("password"))) {
                    System.out.println("true");
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    public loginResponseDTO ReturnLoginResponseDTO(loginRequestDTO loginRequestDTO){
        ResultSet result = null;
        loginResponseDTO loginResponseDTO = new loginResponseDTO();
        try {
            result = returnResult(loginRequestDTO.getUser(), loginRequestDTO.getPassword());
            while (result.next()) {
                loginResponseDTO.setUser(result.getString("user"));
                loginResponseDTO.setToken(result.getString("token"));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + properties.getProperty("connectionString"), e);
        }

            return loginResponseDTO;
    }


}
