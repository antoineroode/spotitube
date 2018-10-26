package nl.han.dea.services;

import nl.han.dea.dto.loginRequestDTO;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

public class loginService {

    private nl.han.dea.dao.loginDAO loginDAO;

    public Response ReturnLoginResponse(loginRequestDTO loginRequestDTO){

        if(loginDAO.AuthenticateUser(loginRequestDTO.getUser(), loginRequestDTO.getPassword())) {
            return Response.ok(loginDAO.ReturnLoginResponseDTO(loginRequestDTO)).build();
        }
        return Response.status(401).build();
    }

    @Inject
    public void setLoginDAO(nl.han.dea.dao.loginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }
}
