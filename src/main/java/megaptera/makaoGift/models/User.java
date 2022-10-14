package megaptera.makaoGift.models;

import megaptera.makaoGift.dtos.UserCreatedDto;
import megaptera.makaoGift.dtos.UserDto;
import megaptera.makaoGift.exceptions.InsufficientAmountError;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "PERSON")
public class User {
  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private String identifier;

  private String encodedPassword;

  private Long amount;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  public User() {
  }

  public User(Long id, String name, String identifier, String encodedPassword, Long amount) {
    this.id = id;
    this.name = name;
    this.identifier = identifier;
    this.encodedPassword = encodedPassword;
    this.amount = amount;
  }

  public User(Long id, String name, String identifier) {
    this.id = id;
    this.name = name;
    this.identifier = identifier;
  }

  public User(long id, String name, String identifier, long amount) {
    this.id = id;
    this.name = name;
    this.identifier = identifier;
    this.amount = amount;
  }

  public Long id() {
    return id;
  }

  public String name() {
    return name;
  }

  public String identifier() {
    return identifier;
  }

  public String password() {
    return encodedPassword;
  }

  public Long amount() {
    return amount;
  }

  public static User fake(String identifier) {
    return new User(1L, "제로콜라", identifier, "Tjdghks245@", 500000L);
  }

  public boolean authenticate(PasswordEncoder passwordEncoder, String password) {
    return passwordEncoder.matches(password, this.encodedPassword);
  }

  public void changePassword(String password, PasswordEncoder passwordEncoder) {
    this.encodedPassword = passwordEncoder.encode(password);
  }

  public UserCreatedDto createdDto() {
    return new UserCreatedDto(name, 0L);
  }

  public UserDto toDto() {
    return new UserDto(name , amount);
  }

  public void pay(Long totalPrice) {
    if(this.amount < totalPrice) {
      throw new InsufficientAmountError();
    }

    this.amount -= totalPrice;
  }
}
