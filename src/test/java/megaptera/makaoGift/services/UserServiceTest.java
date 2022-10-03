package megaptera.makaoGift.services;

import megaptera.makaoGift.dtos.SignUpRequestDto;
import megaptera.makaoGift.exceptions.AlreadyExistIdentifier;
import megaptera.makaoGift.exceptions.NotEqualConfirmPassword;
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
import static org.mockito.Mockito.verify;

class UserServiceTest {
  private UserRepository userRepository;
  private Argon2PasswordEncoder passwordEncoder;
  private UserService userService;

  @BeforeEach
  void setup() {
    userRepository = mock(UserRepository.class);
    passwordEncoder = new Argon2PasswordEncoder();

    given(userRepository.findByIdentifier("tidls1234"))
        .willReturn(Optional.of(User.fake("tidls1234")));

    userService = new UserService(userRepository, passwordEncoder);
  }

  @Test
  void create() {
    //todo: 유저가 주문내역도 가지고 있어야 하나? 일단은 잠시 버류
    SignUpRequestDto signUpRequestDto = new SignUpRequestDto(
        "코카콜라", "tidls45", "Tjdghks245@", "Tjdghks245@"
    );

    User user = userService.create(signUpRequestDto);

    assertThat(user).isNotNull();
    assertThat(user.password()).isNotNull();

    verify(userRepository).save(user);
  }

  @Test
  void createWithAlreadyExistIdentifier() {
    //todo: 유저가 주문내역도 가지고 있어야 하나? 일단은 잠시 버류
    SignUpRequestDto signUpRequestDto = new SignUpRequestDto(
        "코카콜라", "tidls1234", "Tjdghks245@", "Tjdghks245@"
    );

    assertThrows(AlreadyExistIdentifier.class, () -> {
      userService.create(signUpRequestDto);
    });
  }

  @Test
  void createWithNotEqualConfirmPassword() {
    //todo: 유저가 주문내역도 가지고 있어야 하나? 일단은 잠시 버류
    SignUpRequestDto signUpRequestDto = new SignUpRequestDto(
        "코카콜라", "tidls1234", "Tjdghks245@", "xxx"
    );

    assertThrows(NotEqualConfirmPassword.class, () -> {
      userService.create(signUpRequestDto);
    });
  }
}
