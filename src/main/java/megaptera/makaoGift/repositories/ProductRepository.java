package megaptera.makaoGift.repositories;

import megaptera.makaoGift.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
  Page<Product> findAll(Pageable pageable);

  Optional<Product> findById(Long id);
}
