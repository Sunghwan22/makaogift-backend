package megaptera.makaoGift.dtos;

public class InsufficientAmountErrorDto extends ErrorDto{
  public InsufficientAmountErrorDto() {
    super(1011, "잔액이 부족하여 선물하기가 불가능합니다");
  }
}
