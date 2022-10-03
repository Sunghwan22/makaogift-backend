package megaptera.makaoGift.services;

import megaptera.makaoGift.dtos.LoginRequestDto;
import megaptera.makaoGift.exceptions.LoginFailed;
import megaptera.makaoGift.models.User;
import megaptera.makaoGift.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LoginService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User login(String identifier, String password) {
    User user = userRepository.findByIdentifier(identifier)
        .orElseThrow(LoginFailed::new);

    if (!user.authenticate(passwordEncoder, password)) {
      throw new LoginFailed();
    }

    return user;
  }
}
