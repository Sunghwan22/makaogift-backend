package megaptera.makaoGift.backdoor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("backdoor")
public class BackdoorController {
  private final JdbcTemplate jdbcTemplate;

  private final PasswordEncoder passwordEncoder;

  public BackdoorController(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
    this.jdbcTemplate = jdbcTemplate;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping("setup-database")
  public String setupDatabase() {
    LocalDateTime now = LocalDateTime.now();

    jdbcTemplate.execute("DELETE FROM person");

    jdbcTemplate.update("" +
            "INSERT INTO person(" +
            "id, name, identifier, encoded_password, amount, " +
            "created_at, updated_at)" +
            " VALUES(1, ?, ?, ?, ?, ?, ?)",
        "코카콜라", "tidls45", passwordEncoder.encode("Tjdghks245@"), 1_000_000L, now, now
    );

    jdbcTemplate.update("" +
            "INSERT INTO person(" +
            "id, name, identifier, encoded_password, amount, " +
            "created_at, updated_at)" +
            " VALUES(2, ?, ?, ?, ?, ?, ?)",
        "제로콜라", "tidls3144", passwordEncoder.encode("Tjdghks245@"), 2_000_000L, now, now
    );

    return "OK";
  }

  @GetMapping("change-amount")
  public String changeAmount(
      @RequestParam Long userId,
      @RequestParam Long amount
  ) {
    //todo 잔액 변경

    jdbcTemplate.update("UPDATE person SET amount=? WHERE id=? ", amount, userId);

    return "OK";
  }

  @GetMapping("setup-product")
  public String setupProduct() {
    LocalDateTime now = LocalDateTime.now();

    jdbcTemplate.execute("DELETE FROM product");
  // id, company , name , option, price , description
    jdbcTemplate.update("" +
            "INSERT INTO product(" +
            "id, company, name, option, price, description, " +
            "created_at, updated_at)" +
            " VALUES(1, ?, ?, ?, ?, ?, ?, ?)",
        "애플", "아이폰14", "맥스", 1_500_000L, "갖고 싶다", now, now
    );

    // id, company , name , option, price , description
    jdbcTemplate.update("" +
            "INSERT INTO product(" +
            "id, company, name, option, price, description, " +
            "created_at, updated_at)" +
            " VALUES(2, ?, ?, ?, ?, ?, ?, ?)",
        "삼성전자", "갤럭시S22", "맥스", 100_000L, "역시 애플이 좋은듯", now, now
    );

    // id, company , name , option, price , description
    jdbcTemplate.update("" +
            "INSERT INTO product(" +
            "id, company, name, option, price, description, " +
            "created_at, updated_at)" +
            " VALUES(3, ?, ?, ?, ?, ?, ?, ?)",
        "애플", "맥북M2", "16인치프로", 4_000_000L, "갖고 싶다", now, now
    );

    jdbcTemplate.update("" +
            "INSERT INTO product(" +
            "id, company, name, option, price, description, " +
            "created_at, updated_at)" +
            " VALUES(4, ?, ?, ?, ?, ?, ?, ?)",
        "LG", "그램", "몰라옵션이뭐여", 700_000L, "맥북 갖고 싶다", now, now
    );

    jdbcTemplate.update("" +
            "INSERT INTO product(" +
            "id, company, name, option, price, description, " +
            "created_at, updated_at)" +
            " VALUES(5, ?, ?, ?, ?, ?, ?, ?)",
        "DRUG HOMME", "트렌치코트", "XL", 250_000L, "품절입니다", now, now
    );

    jdbcTemplate.update("" +
            "INSERT INTO product(" +
            "id, company, name, option, price, description, " +
            "created_at, updated_at)" +
            " VALUES(6, ?, ?, ?, ?, ?, ?, ?)",
        "VAMILLO", "고래", "특징: 예쁨", 200_000L, "품절입니다", now, now
    );

    jdbcTemplate.update("" +
            "INSERT INTO product(" +
            "id, company, name, option, price, description, " +
            "created_at, updated_at)" +
            " VALUES(7, ?, ?, ?, ?, ?, ?, ?)",
        "VAMILLO", "스킨", "이것도 맘에드내", 200_000L, "품절은 아니네", now, now
    );

    jdbcTemplate.update("" +
            "INSERT INTO product(" +
            "id, company, name, option, price, description, " +
            "created_at, updated_at)" +
            " VALUES(8, ?, ?, ?, ?, ?, ?, ?)",
        "롯데건설", "시그니엘 레지던스", "한강뷰 150평 펜트하우스", 14_000_000_000L, "인생은 한강뷰 아니면 한강행", now, now
    );

    return "OK";
  }

  @GetMapping("delete-products")
  public String deleteProducts() {
    //todo 잔액 변경

    jdbcTemplate.execute("DELETE FROM product");

    return "OK";
  }
}
