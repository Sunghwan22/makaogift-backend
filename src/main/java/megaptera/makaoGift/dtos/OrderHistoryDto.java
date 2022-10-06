package megaptera.makaoGift.dtos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrderHistoryDto {

  private String identifier;

  private String productName;

  private String company;

  private String description;

  private Long totalPrice;

  private Long quantity;

  private String name;

  private String address;

  private String message;

  private String createdAt;

  public OrderHistoryDto() {
  }

  public OrderHistoryDto(String identifier, String productName,
                         String company, String description, Long totalPrice,
                         Long quantity, String name, String address,
                         String message) {
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

  public String getIdentifier() {
    return identifier;
  }

  public String getProductName() {
    return productName;
  }

  public String getCompany() {
    return company;
  }

  public String getDescription() {
    return description;
  }

  public Long getTotalPrice() {
    return totalPrice;
  }

  public Long getQuantity() {
    return quantity;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getMessage() {
    return message;
  }

  public String getCreatedAt() {
    LocalDate now = LocalDate.now();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    return now.format(formatter);
  }
}
