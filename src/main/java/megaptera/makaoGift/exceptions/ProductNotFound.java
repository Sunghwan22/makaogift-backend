package megaptera.makaoGift.exceptions;

public class ProductNotFound extends RuntimeException {
  public ProductNotFound() {
    super("상품을 찾을 수 없습니다");
  }
}
