package megaptera.makaoGift.services;

import megaptera.makaoGift.exceptions.ProductNotFound;
import megaptera.makaoGift.models.Product;
import megaptera.makaoGift.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductService {
  private final ProductRepository productRepository;
  private Pageable pageable;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product detail(Long id) {
    return productRepository.findById(id)
        .orElseThrow(ProductNotFound::new);
  }

  public Page<Product> list(Integer page) {
    Sort sort = Sort.by("id");
    pageable = PageRequest.of(page - 1, 8, sort);

    return productRepository.findAll(pageable);
  }

  public int pages() {
    return productRepository.findAll(pageable).getTotalPages();
  }
}
