package megaptera.makaoGift.dtos;

public class SignupFailedDto extends ErrorDto{
  public SignupFailedDto(Integer code, String message) {
    super(code,message);
  }
}
