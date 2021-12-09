package controller;

import java.sql.SQLException;
import entity.cart.Cart;

/**
 * This class controls the flow of events when users view the Cart.
 *
 * @author nguyenlm
 */
public class ViewCartController extends BaseController {

  /**
   * This method checks the available products in Cart.
   *
   * @throws SQLException throws when error occurs while querying DB.
   */
  public void checkAvailabilityOfProduct() throws SQLException {
    Cart.getCart().checkAvailabilityOfProduct();
  }

  /**
   * This method calculates the cart subtotal.
   *
   * @return subtotal
   */
  public int getCartSubtotal() {
    int subtotal = Cart.getCart().calSubtotal();
    return subtotal;
  }

}
