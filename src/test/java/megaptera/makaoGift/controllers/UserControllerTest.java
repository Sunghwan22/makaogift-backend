package megaptera.makaoGift.controllers;

import megaptera.makaoGift.dtos.SignUpRequestDto;
import megaptera.makaoGift.exceptions.NotEqualConfirmPassword;
import megaptera.makaoGift.models.User;
import megaptera.makaoGift.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

//  @BeforeEach
//  void setup() {
//    given(userService.create(any())).willReturn(new User(
//        1L, "tidls45", "Tjdghks245@"
//    ));
//  }

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
    given(userService.create(any())).willReturn(new User(
        1L, "tidls45", "Tjdghks245@"
    ));

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
}
