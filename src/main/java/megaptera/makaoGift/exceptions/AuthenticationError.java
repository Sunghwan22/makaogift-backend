package megaptera.makaoGift.exceptions;

public class AuthenticationError extends RuntimeException {
  public AuthenticationError() {
    super("Authentication Error");
  }
}
