package megaptera.makaoGift.repositories;

import megaptera.makaoGift.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
