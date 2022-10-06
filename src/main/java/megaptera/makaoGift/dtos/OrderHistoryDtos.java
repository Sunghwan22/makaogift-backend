package megaptera.makaoGift.dtos;

import java.util.List;

public class OrderHistoryDtos {
  private List<OrderHistoryDto> orderHistoryDtos;

  public OrderHistoryDtos() {
  }

  public OrderHistoryDtos(List<OrderHistoryDto> orderHistoryDtos) {

    this.orderHistoryDtos = orderHistoryDtos;
  }

  public List<OrderHistoryDto> getOrderHistoryDtos() {
    return orderHistoryDtos;
  }
}
