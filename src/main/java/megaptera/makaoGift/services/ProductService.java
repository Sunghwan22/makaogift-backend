package megaptera.makaoGift.services;

import megaptera.makaoGift.models.Product;
import megaptera.makaoGift.repositories.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService {
  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> list(Integer page) {
    Sort sort = Sort.by("id").descending();

    Pageable pageable = PageRequest.of(page -1 , 100, sort);

    return productRepository.findAll();
  }

  public Product detail(String id) {
    return null;
  }
}
