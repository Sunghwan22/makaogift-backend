package megaptera.makaoGift.dtos;

public class NotEqualConfirmPasswordDto extends ErrorDto {
  public NotEqualConfirmPasswordDto() {
    super(1033, "비밀번호가 일치하지 않습니다");
  }
}
