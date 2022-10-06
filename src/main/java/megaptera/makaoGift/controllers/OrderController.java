package megaptera.makaoGift.controllers;

import megaptera.makaoGift.dtos.OrderDto;
import megaptera.makaoGift.dtos.OrderFailedDto;
import megaptera.makaoGift.dtos.OrderHistoryDto;
import megaptera.makaoGift.dtos.OrderHistoryDtos;
import megaptera.makaoGift.dtos.OrderResultDto;
import megaptera.makaoGift.dtos.ProductNotFoundDto;
import megaptera.makaoGift.dtos.UserNotFoundDto;
import megaptera.makaoGift.exceptions.ProductNotFound;
import megaptera.makaoGift.exceptions.UserNotFound;
import megaptera.makaoGift.models.OrderHistory;
import megaptera.makaoGift.services.OrderHistoryService;
import megaptera.makaoGift.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("orders")
public class OrderController {
  private final OrderService orderService;
  private final OrderHistoryService orderHistoryService;

  public OrderController(OrderService orderService, OrderHistoryService orderHistoryService) {
    this.orderService = orderService;
    this.orderHistoryService = orderHistoryService;
  }

  @GetMapping
  public OrderHistoryDtos list(
      @RequestAttribute("identifier") String identifier
  ) {
    List<OrderHistoryDto> orderHistoryDtos =
        orderHistoryService.list(identifier)
            .stream()
            .map(OrderHistory::toDto)
            .collect(Collectors.toList());


        return new OrderHistoryDtos(orderHistoryDtos);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OrderResultDto order(
      @RequestAttribute("identifier") String identifier,
      @Validated @RequestBody OrderDto orderDto
  ) {
    Long amount = orderService.order(
        identifier,
        orderDto.getProductId(),
        orderDto.getQuantity(),
        orderDto.getTotalPrice(),
        orderDto.getName(),
        orderDto.getAddress(),
        orderDto.getMessage()
    );
    return new OrderResultDto(amount);
  }

  @ExceptionHandler(ProductNotFound.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ProductNotFoundDto productNotFoundDto() {
      return new ProductNotFoundDto();
  }

  @ExceptionHandler(UserNotFound.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public UserNotFoundDto userNotFoundDto() {
    return new UserNotFoundDto();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public OrderFailedDto orderFailedDto(MethodArgumentNotValidException exception) {
    for (ObjectError error : exception.getBindingResult().getAllErrors()) {

      return new OrderFailedDto(1002, error.getDefaultMessage());
    }
    return new OrderFailedDto(1002, "공백일 수 없습니다");
  }
}
