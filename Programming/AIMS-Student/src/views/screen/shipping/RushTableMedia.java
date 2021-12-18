package views.screen.shipping;

public class RushTableMedia {
  private String name;
  private int quantity;
  private int price;
  
  public RushTableMedia(String name, int quantity, int price) {
    this.name = name;
    this.quantity = quantity;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public int getQuantity() {
    return quantity;
  }

  public int getPrice() {
    return price;
  }
}
