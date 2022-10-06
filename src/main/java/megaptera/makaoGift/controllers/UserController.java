package megaptera.makaoGift.controllers;

import megaptera.makaoGift.dtos.AlreadyExistIdentifierDto;
import megaptera.makaoGift.dtos.ErrorDto;
import megaptera.makaoGift.dtos.NotEqualConfirmPasswordDto;
import megaptera.makaoGift.dtos.SignUpRequestDto;
import megaptera.makaoGift.dtos.SignupFailedDto;
import megaptera.makaoGift.dtos.UserCreatedDto;
import megaptera.makaoGift.dtos.UserDto;
import megaptera.makaoGift.exceptions.AlreadyExistIdentifier;
import megaptera.makaoGift.exceptions.NotEqualConfirmPassword;
import megaptera.makaoGift.models.User;
import megaptera.makaoGift.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("user")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("me")
  public UserDto user(
      @RequestAttribute("identifier") String identifier
  ) {

    User user = userService.detail(identifier);

    return user.toDto();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserCreatedDto signup(
      @Validated @RequestBody SignUpRequestDto signUpRequestDto
  ) {
    User user = userService.create(signUpRequestDto);

    return user.createdDto();
  }

  @ExceptionHandler(NotEqualConfirmPassword.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public NotEqualConfirmPasswordDto notEqualConfirmPassword() {
    return new NotEqualConfirmPasswordDto();
  }

  @ExceptionHandler(AlreadyExistIdentifier.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public AlreadyExistIdentifierDto AlreadyExistIdentifier() {
    return new AlreadyExistIdentifierDto();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public SignupFailedDto registerFailed(MethodArgumentNotValidException exception) {
    for (ObjectError error : exception.getBindingResult().getAllErrors()) {

      Integer code = getCodeByDefaultMessage(Objects.requireNonNull(error.getDefaultMessage()));

      return new SignupFailedDto(code, error.getDefaultMessage());
    }
    return new SignupFailedDto(1004, "멸크망크");
  }

  private Integer getCodeByDefaultMessage(String message) {
    Integer code = 0;

    if(message.equals("이름을 입력해주세요")) {
      code = 1010;
    }

    if(message.equals("비밀번호를 입력해주세요")) {
      code = 1010;
    }

    if(message.equals("아이디를 입력해주세요")) {
      code = 1010;
    }

    if(message.equals("이름을 다시 확인해주세요")) {
      code = 1005;
    }

    if(message.equals("아이디를 다시 확인해주세요")) {
      code =  1006;
    }

    if(message.equals("비밀번호를 다시 확인해주세요")) {
      code = 1007;
    }

    if(message.equals("공백일 수 없습니다")) {
      code = 1008;
    }
    return code;
  }
}
