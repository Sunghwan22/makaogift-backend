package megaptera.makaoGift.dtos;

import java.util.List;

public class OrderHistoryDtos {
  private List<OrderHistoryDto> orderHistories;
  private int pageNumber;

  public OrderHistoryDtos(List<OrderHistoryDto> orderHistories, int pageNumber) {
    this.orderHistories = orderHistories;
    this.pageNumber = pageNumber;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public List<OrderHistoryDto> getOrderHistories() {
    return orderHistories;
  }
}
