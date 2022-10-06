package megaptera.makaoGift.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class OrderDto {
  private Long productId;

  private Long quantity;

  private Long totalPrice;

  @NotBlank(message = "이름을 입력해주세요")
  @Pattern(regexp = "^[가-힣]{3,7}$", message = "이름을 다시 확인해주세요")
  private String name;

  @NotBlank(message = "주소를 입력해주세요")
  private String address;

  private String message;

  public OrderDto() {
  }

  public OrderDto(Long productId, Long quantity, Long totalPrice, String name, String address, String message) {
    this.productId = productId;
    this.quantity = quantity;
    this.totalPrice = totalPrice;
    this.name = name;
    this.address = address;
    this.message = message;
  }

  public Long getProductId() {
    return productId;
  }

  public Long getQuantity() {
    return quantity;
  }

  public Long getTotalPrice() {
    return totalPrice;
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
}
