package megaptera.makaoGift.services;

import megaptera.makaoGift.dtos.SignUpRequestDto;
import megaptera.makaoGift.exceptions.AlreadyExistIdentifier;
import megaptera.makaoGift.exceptions.NotEqualConfirmPassword;
import megaptera.makaoGift.exceptions.UserNotFound;
import megaptera.makaoGift.models.User;
import megaptera.makaoGift.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User create(SignUpRequestDto signUpRequestDto) {
    if(!signUpRequestDto.getPassword().equals(signUpRequestDto.getConfirmPassword())) {
      throw new NotEqualConfirmPassword();
    }

    Optional<User> foundUser = userRepository.findByIdentifier(
        signUpRequestDto.getIdentifier());

    if (foundUser.isPresent()) {
      throw new AlreadyExistIdentifier();
    }

    User user = new User(
        null,
        signUpRequestDto.getName(),
        signUpRequestDto.getIdentifier());

    user.changePassword(signUpRequestDto.getPassword(), passwordEncoder);

    userRepository.save(user);

    return user;
  }

  public User detail(String identifier) {
    return userRepository.findByIdentifier(identifier)
        .orElseThrow(() -> new UserNotFound(identifier));
  }
}
