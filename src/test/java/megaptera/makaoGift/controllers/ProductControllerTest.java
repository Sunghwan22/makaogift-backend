package megaptera.makaoGift.controllers;

import megaptera.makaoGift.models.Product;
import megaptera.makaoGift.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProductService productService;

  @Test
  void products() throws Exception {
    List<Product> products = List.of(
        new Product(1L, "apple", "아이폰31", "그라파이트", 100000L, "상품 설명"),
        new Product(2L, "google", "아이폰32", "스페이스그레이", 100000L, "상품 설명"),
        new Product(3L, "amazon", "아이폰33", "미드나이트", 100000L, "상품 설명")
    );

    given(productService.list(1)).willReturn(products);

    mockMvc.perform(MockMvcRequestBuilders.get("/products"))
        .andExpect(status().isOk())
        .andExpect(content().string(
            containsString("apple")))

        .andExpect(content().string(
            containsString("google")))

        .andExpect(content().string(
            containsString("amazon")));
  }

  @Test
  void productDetail() throws Exception {
    Product product =
        new Product(1L, "apple", "iphone", "midnight", 100000L, "description");

    given(productService.detail(any())).willReturn(product);

    mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
        .andExpect(status().isOk())
        .andExpect(content().string(
            containsString("apple")))

        .andExpect(content().string(
            containsString("iphone")))

        .andExpect(content().string(
            containsString("midnight")))

        .andExpect(content().string(
            containsString("100000")))

        .andExpect(content().string(
            containsString("description")));
  }
}
