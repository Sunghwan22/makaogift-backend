package megaptera.makaoGift.models;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON")
public class User {
  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private String identifier;

  private String password;

  private Long amount;

  public User() {
  }

  public User(Long id, String name, String identifier, String password, Long amount) {
    this.id = id;
    this.name = name;
    this.identifier = identifier;
    this.password = password;
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
    return password;
  }

  public Long amount() {
    return amount;
  }

  public static User fake(String identifier) {
    return new User(1L, "제로콜라", identifier, "Tjdghks245@", 500000L);
  }

  public boolean authenticate(PasswordEncoder passwordEncoder, String password) {
    return passwordEncoder.matches(password, this.password);
  }

  public void changePassword(String password, PasswordEncoder passwordEncoder) {
    this.password = passwordEncoder.encode(password);
  }
}
