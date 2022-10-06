package megaptera.makaoGift.repositories;

import megaptera.makaoGift.models.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
  List<OrderHistory> findByIdentifier(String identifier);
}
