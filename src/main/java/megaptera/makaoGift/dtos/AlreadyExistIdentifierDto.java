package megaptera.makaoGift.dtos;

public class AlreadyExistIdentifierDto extends ErrorDto {
  public AlreadyExistIdentifierDto() {
    super(1004, "해당 아이디는 사용할 수 없습니");
  }
}
