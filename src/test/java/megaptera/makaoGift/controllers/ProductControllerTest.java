package megaptera.makaoGift.controllers;

import megaptera.makaoGift.models.OrderHistory;
import megaptera.makaoGift.models.Product;
import megaptera.makaoGift.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
    Product product = mock(Product.class);
    int page = 1;

    Pageable pageable = PageRequest.of(page, 8);

    List<Product> products = List.of(product);

    Page<Product> pageProducts = new PageImpl<>(products, pageable, 8);

    given(productService.list(1)).willReturn(pageProducts);

    mockMvc.perform(MockMvcRequestBuilders.get("/products"))
        .andExpect(status().isOk());

    verify(productService).list(page);
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
