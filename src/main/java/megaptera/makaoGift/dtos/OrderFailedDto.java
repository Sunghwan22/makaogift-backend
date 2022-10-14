package megaptera.makaoGift.dtos;

public class OrderFailedDto extends ErrorDto{
  // todo : 지금 프론트엔드에서 값을 message라고 못받는다(같은 이름이 있음 처리해줘야함)
  public OrderFailedDto(Integer code, String message) {
    super(code, message);
  }
}
