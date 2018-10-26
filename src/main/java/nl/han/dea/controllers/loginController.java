package nl.han.dea.controllers;

import nl.han.dea.dao.connectionHandler;
import nl.han.dea.dto.loginRequestDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;


@Path("/login")
public class loginController {

    private nl.han.dea.services.loginService loginService;
    private connectionHandler cd = new connectionHandler();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(loginRequestDTO loginRequestDTO){
        cd.connectToDB();
       return loginService.ReturnLoginResponse(loginRequestDTO);
    }

    @Inject
    public void setLoginService(nl.han.dea.services.loginService loginService) {
        this.loginService = loginService;
    }

}
