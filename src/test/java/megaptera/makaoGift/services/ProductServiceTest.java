package megaptera.makaoGift.services;

import megaptera.makaoGift.models.Product;
import megaptera.makaoGift.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


class ProductServiceTest {
  private ProductService productService;
  private ProductRepository productRepository;

  @BeforeEach
  void setup() {
    productRepository = mock(ProductRepository.class);
     productService = new ProductService(productRepository);
  }

  @Test
  void list() {
    Product product = mock(Product.class);

    given(productRepository.findAll()).willReturn(List.of(product));

    List<Product> products = productService.list(1);

    assertThat(products).hasSize(1);
  }

}