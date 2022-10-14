package megaptera.makaoGift.repositories;

import megaptera.makaoGift.models.OrderHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
  Page<OrderHistory> findByIdentifier(String identifier, Pageable pageable);


}
