package megaptera.makaoGift.dtos;

public class LoginFailedDto extends ErrorDto{
  public LoginFailedDto(int code, String message) {
    super(code, message);
  }
}
