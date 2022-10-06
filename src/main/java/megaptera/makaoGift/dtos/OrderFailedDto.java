package megaptera.makaoGift.dtos;

public class OrderFailedDto extends ErrorDto{
  public OrderFailedDto(Integer code, String message) {
    super(code, message);
  }
}
