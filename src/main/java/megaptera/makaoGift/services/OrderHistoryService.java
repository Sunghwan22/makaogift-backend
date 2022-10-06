package megaptera.makaoGift.services;

import megaptera.makaoGift.dtos.OrderHistoryDto;
import megaptera.makaoGift.models.OrderHistory;
import megaptera.makaoGift.repositories.OrderHistoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderHistoryService {
  private final OrderHistoryRepository orderHistoryRepository;

  public OrderHistoryService(OrderHistoryRepository orderHistoryRepository) {
    this.orderHistoryRepository = orderHistoryRepository;
  }

  public List<OrderHistory> list(String identifier) {
    return orderHistoryRepository.findByIdentifier(identifier);
  }
}
