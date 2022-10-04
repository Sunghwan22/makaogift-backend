package megaptera.makaoGift.services;

import megaptera.makaoGift.exceptions.ProductNotFound;
import megaptera.makaoGift.models.Product;
import megaptera.makaoGift.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

  @Test
  void detail() {
    Product product =
        new Product(1L, "apple", "아이폰31", "그라파이트", 100000L, "상품 설명");

    given(productRepository.findById(1L)).willReturn(Optional.of(product));

    Product foundProduct = productService.detail(1L);

    assertThat(foundProduct).isNotNull();
    assertThat(foundProduct.company()).isEqualTo("apple");
  }

  @Test
  void productNotFound() {
    Product product =
        new Product(1L, "apple", "아이폰31", "그라파이트", 100000L, "상품 설명");

    assertThrows(ProductNotFound.class, () -> {
      productService.detail(5L);
    });
  }

}