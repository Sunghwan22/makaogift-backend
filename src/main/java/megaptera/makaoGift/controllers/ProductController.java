package megaptera.makaoGift.controllers;

import megaptera.makaoGift.dtos.ProductDto;
import megaptera.makaoGift.dtos.ProductsDto;
import megaptera.makaoGift.models.Product;
import megaptera.makaoGift.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
public class ProductController {
  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/{id}")
  public ProductDto productDetail(
      @PathVariable("id") Long productId) {

    Product product = productService.detail(productId);

    return product.toDto();
  }

  @GetMapping
  public ProductsDto products(
      @RequestParam(required = false, defaultValue = "1") Integer page
  ) {
    List<ProductDto> productDtos =
        productService.list(page)
            .stream().map(Product::toDto)
            .collect(Collectors.toList());

    return new ProductsDto(productDtos);
  }
}
