package megaptera.makaoGift.models;

import megaptera.makaoGift.dtos.ProductDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

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

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

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

  public String description() {
    return description;
  }

  public ProductDto toDto() {
    return new ProductDto(id, company, name, option, price, description);
  }
}
