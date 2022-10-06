package megaptera.makaoGift.controllers;

import megaptera.makaoGift.models.OrderHistory;
import megaptera.makaoGift.models.User;
import megaptera.makaoGift.services.OrderHistoryService;
import megaptera.makaoGift.services.OrderService;
import megaptera.makaoGift.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private OrderService orderService;

  @SpyBean
  private JwtUtil jwtUtil;

  @MockBean
  private OrderHistoryService orderHistoryService;

  @Test
  void order() throws Exception {
    String accessToken = jwtUtil.encode("tidls45");

    User user = new User(1L, "제로콜라", "tidls45", 1000000L);

    mockMvc.perform(MockMvcRequestBuilders.post("/orders")
            .header("Authorization", "Bearer " + accessToken)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content("{" +
            "\"productId\":\"1\"," +
            " \"quantity\":\"1\"," +
            "\"totalPrice\":\"500000\"," +
            "\"name\":\"아이폰\"," +
            "\"address\":\"화성\"," +
            "\"message\":\"화성 가즈아\"}"))
        .andExpect(status().isCreated());

    verify(orderService).order(user.identifier(),1L,1L,500000L,"아이폰","화성","화성 가즈아");
  }

  @Test
  void orderWithBlankTo() throws Exception {
    String accessToken = jwtUtil.encode("tidls45");

    User user = new User(1L, "제로콜라", "tidls45", 1000000L);

    mockMvc.perform(MockMvcRequestBuilders.post("/orders")
            .header("Authorization", "Bearer " + accessToken)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"productId\":\"1\"," +
                " \"quantity\":\"1\"," +
                "\"totalPrice\":\"500000\"," +
                "\"name\":\"\"," +
                "\"address\":\"화성\"," +
                "\"message\":\"화성 가즈아\"}"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void orderWithBlankAddress() throws Exception {
    String accessToken = jwtUtil.encode("tidls45");

    User user = new User(1L, "제로콜라", "tidls45", 1000000L);

    mockMvc.perform(MockMvcRequestBuilders.post("/orders")
            .header("Authorization", "Bearer " + accessToken)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"productId\":\"1\"," +
                " \"quantity\":\"1\"," +
                "\"totalPrice\":\"500000\"," +
                "\"name\":\"제로 콜라\"," +
                "\"address\":\"\"," +
                "\"message\":\"화성 가즈아\"}"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void list() throws Exception {
    OrderHistory orderHistory = mock(OrderHistory.class);

    String identifier = "tidls45";

    String accessToken = jwtUtil.encode(identifier);

    given(orderHistoryService.list(identifier))
        .willReturn(List.of(orderHistory));

    mockMvc.perform(MockMvcRequestBuilders.get("/orders")
            .header("Authorization", "Bearer " + accessToken))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(content().string(
            containsString("\"orderHistoryDtos\":[")
        ));

    verify(orderHistoryService).list(identifier);
  }
}
