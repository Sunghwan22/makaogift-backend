package megaptera.makaoGift.models;

import megaptera.makaoGift.dtos.ProductDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
  @Test
  void toDto() {
    Product product = new Product(1L, "애플", "아이폰32", "컬러", 100000L, "상품 설명");

    ProductDto productDto = product.toDto();

    assertThat(productDto.getCompany()).isEqualTo("애플");
    assertThat(productDto.getPrice()).isEqualTo(100000L);
  }
}
