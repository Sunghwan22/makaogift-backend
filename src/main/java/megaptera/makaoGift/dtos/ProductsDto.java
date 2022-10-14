package megaptera.makaoGift.dtos;

import java.util.List;

public class ProductsDto {
  private List<ProductDto> products;

  private int pageNumber;

  public ProductsDto() {
  }

  public ProductsDto(List<ProductDto> products, int pageNumber) {
    this.products = products;
    this.pageNumber = pageNumber;
  }

  public List<ProductDto> getProducts() {
    return products;
  }

  public int getPageNumber() {
    return pageNumber;
  }
}
