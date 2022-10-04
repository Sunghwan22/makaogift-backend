package megaptera.makaoGift.dtos;

public class UserCreatedDto {
  private String userName;

  private Long amount;

  public UserCreatedDto(String name, Long amount) {
    this.userName = name;
    this.amount = amount;
  }

  public String getUserName() {
    return userName;
  }

  public Long getAmount() {
    return amount;
  }
}
