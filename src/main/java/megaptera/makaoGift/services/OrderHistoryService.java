package megaptera.makaoGift.services;

import megaptera.makaoGift.exceptions.OrderHistoryNotFound;
import megaptera.makaoGift.models.OrderHistory;
import megaptera.makaoGift.repositories.OrderHistoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderHistoryService {
  private final OrderHistoryRepository orderHistoryRepository;
  private Pageable pageable;

  public OrderHistoryService(OrderHistoryRepository orderHistoryRepository) {
    this.orderHistoryRepository = orderHistoryRepository;
  }

  public Page<OrderHistory> list(String identifier, Integer page) {
    Sort sort = Sort.by("id");
    pageable = PageRequest.of(page - 1, 8, sort);

    return orderHistoryRepository.findByIdentifier(identifier, pageable);
  }

  public int pages(String identifier) {
    return orderHistoryRepository.findByIdentifier(identifier, pageable).getTotalPages();
  }

  public OrderHistory detail(Long orderHistoryId) {
    return orderHistoryRepository.findById(orderHistoryId)
        .orElseThrow(OrderHistoryNotFound::new);
  }
}
