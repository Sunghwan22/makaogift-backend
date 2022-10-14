package megaptera.makaoGift.exceptions;

public class OrderHistoryNotFound extends RuntimeException {
  public OrderHistoryNotFound() {
    super("상세 페이지를 찾을 수 없습니다");
  }
}
