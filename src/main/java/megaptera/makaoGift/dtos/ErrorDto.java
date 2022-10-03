package megaptera.makaoGift.dtos;

public class ErrorDto {
  public static final int LOGIN_INFORMATION_INCORRECT = 1001;

  private final Integer code;

  private final String message;

  public ErrorDto(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public Integer getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
