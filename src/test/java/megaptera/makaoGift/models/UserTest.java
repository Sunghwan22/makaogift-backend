package megaptera.makaoGift.models;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
  @Test
  void changePassword() {
    PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

    User user = User.fake("tidls45");

    user.changePassword("password", passwordEncoder);

    assertThat(user.authenticate(passwordEncoder, "password")).isTrue();
    assertThat(user.authenticate(passwordEncoder, "xxx")).isFalse();
  }

  @Test
  void pay() {
    Long totalAmount = 200_000L;

    User user = User.fake("tidls45");

    user.pay(totalAmount);

    assertThat(user.amount()).isEqualTo(300_000L);
  }

}