package megaptera.makaoGift.controllers;

import megaptera.makaoGift.exceptions.LoginFailed;
import megaptera.makaoGift.models.User;
import megaptera.makaoGift.repositories.UserRepository;
import megaptera.makaoGift.services.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
class SessionControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private LoginService loginService;

  @BeforeEach
  void setUp() {
    User user = User.fake("tidls45");

    given(loginService.login("tidls45", "password"))
        .willReturn(user);

    given(loginService.login("tidls45", "xxx"))
        .willThrow(new LoginFailed());
  }

  @Test
  void login() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/session")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"identifier\":\"tidls45\"," +
                "\"password\":\"Tjdghks245@\"" +
                "}"))
        .andExpect(status().isCreated())
        .andExpect(content().string(containsString(
            "\"amount\":"
        )));
  }

  @Test
  void loginFailed() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/session")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"identifier\":\"tidls45\"," +
                "\"password\":\"xxx\"" +
                "}"))
        .andExpect(status().isBadRequest());
  }
}
