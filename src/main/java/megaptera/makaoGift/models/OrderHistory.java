package megaptera.makaoGift.models;

import megaptera.makaoGift.dtos.OrderHistoryDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class OrderHistory {
  @Id
  @GeneratedValue
  private Long id;

  private String identifier;

  private String productName;

  private String company;

  private String description;

  private Long totalPrice;

  private Long quantity;

  private String name;

  private String address;

  private String message;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  public OrderHistory() {
  }

  public OrderHistory(String identifier,
                      String productName, String company,
                      String description, Long totalPrice,
                      Long quantity, String name,
                      String address, String message) {

    this.identifier = identifier;
    this.productName = productName;
    this.company = company;
    this.description = description;
    this.totalPrice = totalPrice;
    this.quantity = quantity;
    this.name = name;
    this.address = address;
    this.message = message;
  }

  public String productName() {
    return productName;
  }

  public Long id() {
    return id;
  }

  public String identifier() {
    return identifier;
  }

  public String company() {
    return company;
  }

  public String description() {
    return description;
  }

  public Long totalPrice() {
    return totalPrice;
  }

  public Long quantity() {
    return quantity;
  }

  public String name() {
    return name;
  }

  public String address() {
    return address;
  }

  public String message() {
    return message;
  }

  public OrderHistoryDto toDto() {
    return new OrderHistoryDto(id, identifier, productName,
        company, description, totalPrice,
        quantity, name, address, message);
  }
}
