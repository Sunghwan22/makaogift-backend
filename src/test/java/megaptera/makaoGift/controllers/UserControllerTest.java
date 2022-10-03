package megaptera.makaoGift.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
class UserControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  void signup() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/user")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"name\":\"제로콜라\"," +
                "\"identifier\":\"tidls45\"," +
                "\"password\":\"password\"," +
                "\"confirmPassword\":\"password\"" +
                "}"))
        .andExpect(status().isCreated());


  }
}
