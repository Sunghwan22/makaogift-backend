package megaptera.makaoGift.services;

import megaptera.makaoGift.exceptions.ProductNotFound;
import megaptera.makaoGift.models.Product;
import megaptera.makaoGift.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
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

    int page = 1;

    Pageable pageable = PageRequest.of(page, 8);

    List<Product> products = List.of(product);

    Page<Product> pageProducts = new PageImpl<>(products, pageable, 8);

    assertThat(pageProducts).hasSize(1);
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