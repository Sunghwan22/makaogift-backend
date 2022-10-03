package megaptera.makaoGift.services;

import megaptera.makaoGift.exceptions.LoginFailed;
import megaptera.makaoGift.models.User;
import megaptera.makaoGift.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class LoginServiceTest {
  private UserRepository userRepository;
  private Argon2PasswordEncoder passwordEncoder;
  private LoginService loginService;

  @BeforeEach
  void setUp() {
    userRepository = mock(UserRepository.class);

    passwordEncoder = new Argon2PasswordEncoder();

    loginService = new LoginService(userRepository, passwordEncoder);

    String identifier = "tidls45";

    User user = User.fake(identifier);
    user.changePassword("password", passwordEncoder);

    given(userRepository.findByIdentifier(identifier))
        .willReturn(Optional.of(user));
  }

  @Test
  void loginSuccess() {
    String identifier = "tidls45";

    User foundUser = loginService.login(identifier, "password");

    assertThat(foundUser.identifier()).isEqualTo(identifier);
  }

  @Test
  void loginWithIncorrectIdentifier() {
    assertThrows(LoginFailed.class, () -> {
      loginService.login("xxx", "password");
    });
  }

  @Test
  void loginWithIncorrectPassword() {
    assertThrows(LoginFailed.class, () -> {
      loginService.login("tidls45", "xxx");
    });
  }

  @Test
  void loginFailed() {
    assertThrows(LoginFailed.class, () -> {
      loginService.login("xxx", "xxx");
    });
  }
}