package controller;

import java.util.ArrayList;
import java.util.List;

import common.exception.RushUnsupportedException;
import entity.order.Order;
import entity.order.OrderMedia;

/**
 * Controller class for rush order use case. Still under-implementing.
 *
 * @author HMDLong
 *
 */
public class RushOrderController extends PlaceOrderController {

  private ArrayList<OrderMedia> rushOrderItems;
  private static String[] rushSupportedProvince = {"Ha Noi", "Ho Chi Minh"};

  /**
   * This method check if an order is supported by rush order and add supported
   * items to a list.
   *
   * @param items list of ordered items in the cart
   * @param province province of delivery.
   * @throws RushUnsupportedException throws when the order is not
   *                                  rush-order-supported.
   */
  public void checkRushOrderSupport(List<OrderMedia> items, String province) throws RushUnsupportedException {
    // check items
    this.rushOrderItems = new ArrayList<OrderMedia>();
    for (OrderMedia item : items) {
      if (item.getMedia().isRushSupport()) {
        this.rushOrderItems.add(item);
      }
    }
    if (this.rushOrderItems.size() == 0) {
      throw new RushUnsupportedException("No supported items in your order");
    }
    // check province
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
    return 0;
  }

  public List<OrderMedia> getRushOrderItems() {
    return this.rushOrderItems;
  }

}
