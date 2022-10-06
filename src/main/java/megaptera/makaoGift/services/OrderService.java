package megaptera.makaoGift.services;

import megaptera.makaoGift.exceptions.ProductNotFound;
import megaptera.makaoGift.exceptions.UserNotFound;
import megaptera.makaoGift.models.OrderHistory;
import megaptera.makaoGift.models.Product;
import megaptera.makaoGift.models.User;
import megaptera.makaoGift.repositories.OrderHistoryRepository;
import megaptera.makaoGift.repositories.ProductRepository;
import megaptera.makaoGift.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderService {
  private final ProductRepository productRepository;
  private final UserRepository userRepository;
  private final OrderHistoryRepository orderHistoryRepository;

  public OrderService(ProductRepository productRepository, UserRepository userRepository, OrderHistoryRepository orderHistoryRepository) {
    this.productRepository = productRepository;
    this.userRepository = userRepository;
    this.orderHistoryRepository = orderHistoryRepository;
  }

  public Long order(String identifier, Long productId,
                    Long quantity, Long totalPrice,
                    String name, String address, String message) {

    Product product = productRepository.findById(productId)
        .orElseThrow(ProductNotFound::new);

    User user = userRepository.findByIdentifier(identifier)
        .orElseThrow(() -> new UserNotFound(identifier));

    user.pay(totalPrice);

    OrderHistory orderHistory = new OrderHistory(identifier, product.name(), product.company(),
        product.description(), totalPrice, quantity, name, address, message);

    orderHistoryRepository.save(orderHistory);

    return totalPrice;
  }
}
