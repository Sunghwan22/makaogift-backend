package megaptera.makaoGift.controllers;

import megaptera.makaoGift.dtos.LoginFailedDto;
import megaptera.makaoGift.dtos.LoginRequestDto;
import megaptera.makaoGift.dtos.LoginResultDto;
import megaptera.makaoGift.exceptions.LoginFailed;
import megaptera.makaoGift.models.User;
import megaptera.makaoGift.services.LoginService;
import megaptera.makaoGift.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("session")
public class SessionController {
  private final LoginService loginService;
  private final JwtUtil jwtUtil;

  public SessionController(LoginService loginService, JwtUtil jwtUtil) {
    this.loginService = loginService;
    this.jwtUtil = jwtUtil;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public LoginResultDto login(
      @Validated @RequestBody LoginRequestDto loginRequestDto
  ) {
    String identifier = loginRequestDto.getIdentifier();

    String password = loginRequestDto.getPassword();

    User user = loginService.login(identifier, password);

    String accessToken = jwtUtil.encode(identifier);

    return new LoginResultDto(
        accessToken,
        user.name(),
        user.amount());
  }

  @ExceptionHandler(LoginFailed.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public LoginFailedDto loginFailed() {
    return new LoginFailedDto(1001, "아이디 혹은 비밀번호가 맞지 않습니다");
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public LoginFailedDto LoginFailed(MethodArgumentNotValidException exception) {
    for (ObjectError error : exception.getBindingResult().getAllErrors()) {

      return new LoginFailedDto(1002, error.getDefaultMessage());
    }
    return new LoginFailedDto(1002, "공백일 수 없습니다");
  }
}
