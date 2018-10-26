//package nl.han.dea.login;
//
//import nl.han.dea.controllers.loginController;
//import nl.han.dea.dao.loginDAO;
//import nl.han.dea.dto.loginRequestDTO;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mockito;
//import nl.han.dea.services.*;
//
//import javax.ws.rs.core.Response;
//
//public class LoginTest {
//    private loginController loginController;
//    private loginRequestDTO loginRequestDTO;
//    private nl.han.dea.dao.loginDAO loginDAO;
//
//    @Before
//public void TestSetup()
//    {
//       loginController = new loginController();
//        loginRequestDTO = new loginRequestDTO();
//        loginRequestDTO.setPassword("9");
//        loginRequestDTO.setUser("Antoine");
//    }
//    @Test
//    public void testIfUserserviceAgreesReturnsOk200(){
//        //test
//        loginDAO = Mockito.mock(nl.han.dea.dao.loginDAO.class);
//        loginService loginService = Mockito.mock(loginService.class);
//        loginController.setLoginService(loginService);
//        Mockito.when(loginService.AuthenticateUser("Antoine", "9")).thenReturn(true);
//
//
//        //verify
//        Response test = loginController.login(loginRequestDTO);
//        Assert.assertEquals(200, test.getStatus());
//    }
//
//
//}


//import org.junit.Test;
//
//@Test
//public void userAuthenticationTest(){
//        loginRequestDTO = new loginRequestDTO();
//        loginRequestDTO.setPassword("9");
//        loginRequestDTO.setUser("Antoine");
//        loginService.ReturnLoginResponse(loginRequestDTO);
//        Mockito.verify(loginDAO).AuthenticateUser(loginRequestDTO.getUser(), loginRequestDTO.getPassword());
//        }