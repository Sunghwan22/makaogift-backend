package megaptera.makaoGift.utils;

import com.auth0.jwt.exceptions.JWTDecodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {
  static final String SECRET = "SECRET";

  private JwtUtil jwtUtil;

  @BeforeEach
  void setUp() {
    jwtUtil = new JwtUtil(SECRET);
  }

  @Test
  void encodeAndDecode() {
    String original = "tidls45";
    String token = jwtUtil.encode(original);

    assertThat(token).contains(".");

    String identifier = jwtUtil.decode(token);

    assertThat(identifier).isEqualTo(original);
  }

  @Test
  void decodeError() {
    assertThrows(JWTDecodeException.class, () -> {
      jwtUtil.decode("xxx");
    });
  }
}