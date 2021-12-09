package entity.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utils.Configs;

/**
 * Class for order.
 *
 * @author Admin
 *
 */
public class Order {

  private int shippingFees;
  private List lstOrderMedia;
  private HashMap<String, String> deliveryInfo;

  public Order() {
    this.lstOrderMedia = new ArrayList<>();
  }

  public Order(List lstOrderMedia) {
    this.lstOrderMedia = lstOrderMedia;
  }

  public void addOrderMedia(OrderMedia om) {
    this.lstOrderMedia.add(om);
  }

  public void removeOrderMedia(OrderMedia om) {
    this.lstOrderMedia.remove(om);
  }

  public List getlstOrderMedia() {
    return this.lstOrderMedia;
  }

  public void setlstOrderMedia(List lstOrderMedia) {
    this.lstOrderMedia = lstOrderMedia;
  }

  public void setShippingFees(int shippingFees) {
    this.shippingFees = shippingFees;
  }

  public int getShippingFees() {
    return shippingFees;
  }

  public HashMap getDeliveryInfo() {
    return deliveryInfo;
  }

  public void setDeliveryInfo(HashMap deliveryInfo) {
    this.deliveryInfo = deliveryInfo;
  }

  /**
   * This method get total price of the order.
   *
   * @return Integer - total price of the order
   */
  public int getAmount() {
    double amount = 0;
    for (Object object : lstOrderMedia) {
      OrderMedia om = (OrderMedia) object;
      amount += om.getPrice();
    }
    return (int) (amount + (Configs.PERCENT_VAT / 100) * amount);
  }

}
