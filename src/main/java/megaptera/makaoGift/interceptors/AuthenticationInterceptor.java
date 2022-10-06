package megaptera.makaoGift.interceptors;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import megaptera.makaoGift.exceptions.AuthenticationError;
import megaptera.makaoGift.utils.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationInterceptor implements HandlerInterceptor {
  private final JwtUtil jwtUtil;

  public AuthenticationInterceptor(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @Override
  public boolean preHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler) throws Exception {

    String authorization = request.getHeader("Authorization");

    if(authorization == null) {
      return true;
    }

    if(!authorization.startsWith("Bearer ")) {
      throw new AuthenticationError();
    }

    String accessToken = authorization.substring("Bearer ".length());

    try {
      String identifier = jwtUtil.decode(accessToken);

      request.setAttribute("identifier", identifier);
      return true;
    } catch (SignatureVerificationException exception) {
      throw new AuthenticationError();
    }
  }
}
