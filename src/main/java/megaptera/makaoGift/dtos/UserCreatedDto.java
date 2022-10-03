package megaptera.makaoGift.dtos;

public class UserCreatedDto {
  private String name;

  private Long amount;

  public UserCreatedDto(String name, Long amount) {
    this.name = name;
    this.amount = amount;
  }

  public String getName() {
    return name;
  }

  public Long getAmount() {
    return amount;
  }
}
