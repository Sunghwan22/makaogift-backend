package megaptera.makaoGift.models;

import megaptera.makaoGift.dtos.ProductDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
  @Id
  @GeneratedValue
  private Long id;

  private String company;

  private String name;

  private String option;

  private Long price;

  private String description;

  public Product() {
  }

  public Product(Long id, String company, String name, String option, Long price, String description) {
    this.id = id;
    this.company = company;
    this.name = name;
    this.option = option;
    this.price = price;
    this.description = description;
  }

  public Long id() {
    return id;
  }

  public String company() {
    return company;
  }

  public String name() {
    return name;
  }

  public String option() {
    return option;
  }

  public Long price() {
    return price;
  }

  public String getDescription() {
    return description;
  }

  public ProductDto toDto() {
    return new ProductDto(company, name , option, price , description);
  }
}
