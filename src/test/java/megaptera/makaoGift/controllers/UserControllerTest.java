package megaptera.makaoGift.controllers;

import megaptera.makaoGift.exceptions.NotEqualConfirmPassword;
import megaptera.makaoGift.exceptions.UserNotFound;
import megaptera.makaoGift.models.User;
import megaptera.makaoGift.services.UserService;
import megaptera.makaoGift.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @SpyBean
  private JwtUtil jwtUtil;

  @Test
  void signup() throws Exception {
    given(userService.create(any())).willReturn(new User(
        1L, "tidls45", "Tjdghks245@"
    ));

    mockMvc.perform(MockMvcRequestBuilders.post("/user")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"name\":\"제로콜라\"," +
                "\"identifier\":\"tidls45\"," +
                "\"password\":\"Tjdghks245@\"," +
                "\"confirmPassword\":\"Tjdghks245@\"" +
                "}"))
        .andExpect(status().isCreated());

    verify(userService).create(any());
  }

  @Test
  void signupWithNotEqualConfirmPassword() throws Exception {
    given(userService.create(any())).willThrow(
        new NotEqualConfirmPassword()
    );

    mockMvc.perform(MockMvcRequestBuilders.post("/user")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"name\":\"제로콜라\"," +
                "\"identifier\":\"tidls45\"," +
                "\"password\":\"Tjdghks245@@@@\"," +
                "\"confirmPassword\":\"xxx\"" +
                "}"))
        .andExpect(status().isBadRequest());

    verify(userService).create(any());
  }

  @Test
  void signupWithIncorrectIdentifier() throws Exception {
    given(userService.create(any())).willReturn(new User(
        1L, "tidls45", "Tjdghks245@"
    ));

    mockMvc.perform(MockMvcRequestBuilders.post("/user")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"name\":\"제로콜라\"," +
                "\"identifier\":\"Tjdghks245@\"," +
                "\"password\":\"Tjdghks245@\"," +
                "\"confirmPassword\":\"xxx\"" +
                "}"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void signupWithIncorrectPassword() throws Exception {
    given(userService.create(any())).willReturn(new User(
        1L, "tidls45", "Tjdghks245@"
    ));

    mockMvc.perform(MockMvcRequestBuilders.post("/user")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"name\":\"제로콜라\"," +
                "\"identifier\":\"tidls45\"," +
                "\"password\":\"Tjdghks245\"," +
                "\"confirmPassword\":\"Tjdghks245\"" +
                "}"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void signupWithIncorrectName() throws Exception {
    given(userService.create(any())).willReturn(new User(
        1L, "tidls45", "Tjdghks245@"
    ));

    mockMvc.perform(MockMvcRequestBuilders.post("/user")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"name\":\"zerocoke\"," +
                "\"identifier\":\"tidls45\"," +
                "\"password\":\"Tjdghks245@\"," +
                "\"confirmPassword\":\"Tjdghks245@\"" +
                "}"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void signupWithBlankField() throws Exception {
    given(userService.create(any())).willReturn(new User(
        1L, "tidls45", "Tjdghks245@"
    ));

    mockMvc.perform(MockMvcRequestBuilders.post("/user")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"name\":\"\"," +
                "\"identifier\":\"tidls45\"," +
                "\"password\":\"Tjdghks245@\"," +
                "\"confirmPassword\":\"Tjdghks245@\"" +
                "}"))
        .andExpect(status().isBadRequest());

    mockMvc.perform(MockMvcRequestBuilders.post("/user")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"name\":\"제로콜라\"," +
                "\"identifier\":\"\"," +
                "\"password\":\"Tjdghks245@\"," +
                "\"confirmPassword\":\"Tjdghks245@\"" +
                "}"))
        .andExpect(status().isBadRequest());

    mockMvc.perform(MockMvcRequestBuilders.post("/user")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"name\":\"제로콜라\"," +
                "\"identifier\":\"tidls45\"," +
                "\"password\":\"\"," +
                "\"confirmPassword\":\"Tjdghks245@\"" +
                "}"))
        .andExpect(status().isBadRequest());

    mockMvc.perform(MockMvcRequestBuilders.post("/user")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"name\":\"zerocoke\"," +
                "\"identifier\":\"tidls45\"," +
                "\"password\":\"Tjdghks245@\"," +
                "\"confirmPassword\":\"\"" +
                "}"))
        .andExpect(status().isBadRequest());

    mockMvc.perform(MockMvcRequestBuilders.post("/user")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"name\":\"\"," +
                "\"identifier\":\"\"," +
                "\"password\":\"\"," +
                "\"confirmPassword\":\"\"" +
                "}"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void userWithAccessToken() throws Exception {
    given(userService.detail(any()))
        .willReturn(User.fake("tidls45"));

    String accessToken = jwtUtil.encode("tidls45");

    mockMvc.perform(MockMvcRequestBuilders.get("/user/me")
            .header("Authorization", "Bearer " + accessToken))
        .andExpect(status().isOk())
        .andExpect(content().string(
            containsString("\"amount\":500000")
        ));
  }

  @Test
  void accountWithOutAccessToken() throws Exception {
    given(userService.detail(any()))
        .willThrow(new UserNotFound("tidls45"));

    String accessToken = jwtUtil.encode("tidls45");

    mockMvc.perform(MockMvcRequestBuilders.get("/user/me"))
        .andExpect(status().isBadRequest());
  }
}
