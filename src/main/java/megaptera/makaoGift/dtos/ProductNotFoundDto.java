package megaptera.makaoGift.dtos;

public class ProductNotFoundDto extends ErrorDto{
  public ProductNotFoundDto() {
    super(1010, "물품을 찾을 수 없습니다");
  }
}
