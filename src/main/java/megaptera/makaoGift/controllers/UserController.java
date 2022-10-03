package megaptera.makaoGift.controllers;

import megaptera.makaoGift.dtos.SignUpRequestDto;
import megaptera.makaoGift.dtos.UserCreatedDto;
import megaptera.makaoGift.models.User;
import megaptera.makaoGift.services.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
  private UserService userService;

  @PostMapping
  public UserCreatedDto signup(
      @Validated SignUpRequestDto signUpRequestDto
  ) {
    User user = userService.create(signUpRequestDto);

    return user.createdDto();
  }
}
