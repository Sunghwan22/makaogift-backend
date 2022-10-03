package megaptera.makaoGift.services;

import megaptera.makaoGift.dtos.SignUpRequestDto;
import megaptera.makaoGift.models.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
  public User create(SignUpRequestDto signUpRequestDto) {
    return null;
  }
}
