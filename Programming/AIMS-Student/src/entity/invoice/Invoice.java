package entity.invoice;

import entity.order.Order;

/**
 * Class for invoice.
 *
 * @author Admin
 *
 */
public class Invoice {

  private Order order;
  private int amount;

  public Invoice() {

  }

  public Invoice(Order order) {
    this.order = order;
  }

  public Order getOrder() {
    return order;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getAmount() {
    return amount;
  }

  public void saveInvoice() {

  }
}
