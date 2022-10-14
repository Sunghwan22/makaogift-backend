package megaptera.makaoGift.dtos;

public class ErrorDto {
  public static final int LOGIN_INFORMATION_INCORRECT = 1001;
  public static final int BLANK_INPUT_FIELD = 1002;
  public static final int NOT_EQUAL_CONFIRM_PASSWORD = 1003;
  public static final int ALREADY_EXIST_IDENTIFIER = 1004;
  public static final int INCORRECT_NAME = 1005;
  public static final int INCORRECT_IDENTIFIER = 1006;
  public static final int INCORRECT_PASSWORD = 1007;
  public static final int BLANKINFORMATION = 1008;
  public static final int USER_NOT_FOUND = 1009;
  public static final int PRODUCT_NOT_FOUND = 1010;
  public static final int INSUFFICIENT_AMOUNT_ERROR = 1011;

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
