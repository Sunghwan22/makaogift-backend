package megaptera.makaoGift.exceptions;

public class UserNotFound extends RuntimeException {
  public UserNotFound(String identifier) {
    super("아이디이 해당하는 계정을 찾을 수 없습니다 아이디:" + identifier);
  }
}
