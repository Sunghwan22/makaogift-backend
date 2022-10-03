package megaptera.makaoGift.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class SignUpRequestDto {
  @Pattern(regexp = "^[가-힣]{3,7}$", message = "이름을 다시 확인해주세요")
  @NotBlank(message = "이름을 입력해주세요")
  private String name;

  @Pattern(regexp = "^[a-z0-9]{4,12}$", message = "아이디를 다시 확인해주세요")
  @NotBlank(message = "아이디를 입력해주세요")
  private String identifier;

  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}"
      , message = "비밀번호를 다시 확인해주세요")
  @NotBlank(message = "비밀번호를 입력해주세요")
  private String password;

  @NotBlank(message = "확인 비밀번호를 입력해주세요")
  private String confirmPassword;

  public SignUpRequestDto() {
  }

  public SignUpRequestDto(String name, String identifier, String password, String confirmPassword) {
    this.name = name;
    this.identifier = identifier;
    this.password = password;
    this.confirmPassword = confirmPassword;
  }

  public String getName() {
    return name;
  }

  public String getIdentifier() {
    return identifier;
  }

  public String getPassword() {
    return password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }
}
