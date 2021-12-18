package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.exception.RushUnsupportedException;
import entity.cart.CartMedia;
import entity.invoice.Invoice;
import entity.order.Order;
import views.screen.shipping.RushTableMedia;

/**
 * Controller class for rush order use case. Still under-implementing.
 *
 * @author HMDLong
 *
 */
public class RushOrderController extends PlaceOrderController {

  private ArrayList<CartMedia> rushOrderItems;
  private static ArrayList<String> rushSupportedProvince = new ArrayList<>();
  
  public RushOrderController() {
    super();
    rushSupportedProvince.add("Bắc Kạn");
    rushSupportedProvince.add("Hà Giang");
    rushSupportedProvince.add("Hà Nội");
  }

  /**
   * This method check if an order is supported by rush order and add supported
   * items to a list.
   *
   * @param items list of ordered items in the cart
   * @param province province of delivery.
   * @throws RushUnsupportedException throws when the order is not
   *                                  rush-order-supported.
   */
  public void checkRushOrderSupport(List<CartMedia> items, String province) throws RushUnsupportedException {
    // check items
    this.rushOrderItems = new ArrayList<CartMedia>();
    for (CartMedia item : items) {
      if (item.getMedia().isRushSupport()) {
        this.rushOrderItems.add(item);
      }
    }
    if (this.rushOrderItems.size() == 0) {
      throw new RushUnsupportedException("No supported items in your order");
    }
    // check province
    if (!rushSupportedProvince.contains(province)) {
      throw new RushUnsupportedException("No supported items in your order");
    }
  }

  /**
   * This method calculates shipping fee, including normal shipping fee and
   * additional rush shipping fee.
   *
   * @param order the order to calculate fee
   * @return the total amount of shipping fee
   */
  @Override
  public int calculateShippingFee(Order order) {
    // implementing
    return super.calculateShippingFee(order) + getRushShippingFee();
  }
  
  public int getRushShippingFee() {
    return this.rushOrderItems.size() * 10;
  }
  
  public Invoice processRushOrder(Order rushOrder, String rushDate, String rushInstruction) {
    HashMap<String, String> messages = rushOrder.getDeliveryInfo();
    messages.put("rushDate", rushDate);
    messages.put("rushInstruction", rushInstruction);
    rushOrder.setShippingFees(calculateShippingFee(rushOrder));
    return super.createInvoice(rushOrder);
  }

  public List<CartMedia> getRushOrderItems() {
    return this.rushOrderItems;
  }
  
  public List<RushTableMedia> getRushTableItems() {
    ArrayList<RushTableMedia> rushTableItems = new ArrayList<>();
    for(CartMedia item : this.rushOrderItems) {
      RushTableMedia tableItem = new RushTableMedia(item.getMedia().getTitle(),
                                                    item.getQuantity(),
                                                    item.getPrice());
      rushTableItems.add(tableItem);
    }
    return rushTableItems;
  }
}
