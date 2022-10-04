package megaptera.makaoGift.dtos;

public class ProductDto {
  private String company;

  private String name;

  private String option;

  private Long price;

  private String description;

  public ProductDto(String company, String name, String option, Long price, String description) {
    this.company = company;
    this.name = name;
    this.option = option;
    this.price = price;
    this.description = description;
  }

  public String getCompany() {
    return company;
  }

  public String getName() {
    return name;
  }

  public String getOption() {
    return option;
  }

  public Long getPrice() {
    return price;
  }

  public String getDescription() {
    return description;
  }
}
