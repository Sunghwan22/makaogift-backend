package megaptera.makaoGift.dtos;

public class UserNotFoundDto extends ErrorDto{
  public UserNotFoundDto( ) {
    super(1009, "유저를 찾을 수 없습니다");
  }
}
