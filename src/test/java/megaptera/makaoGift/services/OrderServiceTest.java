package megaptera.makaoGift.services;

import megaptera.makaoGift.dtos.OrderDto;
import megaptera.makaoGift.models.Product;
import megaptera.makaoGift.models.User;
import megaptera.makaoGift.repositories.OrderHistoryRepository;
import megaptera.makaoGift.repositories.ProductRepository;
import megaptera.makaoGift.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class OrderServiceTest {
  private UserRepository userRepository;
  private ProductRepository productRepository;
  private OrderHistoryRepository orderHistoryRepository;
  private OrderService orderService;

  @BeforeEach
  void setUp() {
    userRepository = mock(UserRepository.class);
    productRepository = mock(ProductRepository.class);
    orderHistoryRepository = mock(OrderHistoryRepository.class);

     orderService = new OrderService(productRepository, userRepository, orderHistoryRepository);
  }

  @Test
  void order() {
    OrderDto orderDto = new OrderDto(1L,1L, 500_000L, "제로콜라","화성", "화성 가즈아");

    User user =new User(1L, "코카콜라", "tidls45", 1_100_000L);

    Product product = new Product(1L, "애플", "아이폰14", "맥스", 500_000L, "갖고 싶다.");

    given(userRepository.findByIdentifier("tidls45"))
        .willReturn(Optional.of(user));

    given(productRepository.findById(1L))
        .willReturn(Optional.of(product));

    orderService.order(
        user.identifier(),
        product.id(),
        orderDto.getQuantity(),
        orderDto.getTotalPrice(),
        orderDto.getName(),
        orderDto.getAddress(),
        orderDto.getMessage());

    assertThat(user.amount()).isEqualTo(600_000L);

    verify(orderHistoryRepository).save(any());
  }
}
