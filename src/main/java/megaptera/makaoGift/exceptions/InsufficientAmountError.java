package megaptera.makaoGift.exceptions;

public class InsufficientAmountError extends RuntimeException {
  public InsufficientAmountError() {
    super("잔액이 부족합니다");
  }
}
