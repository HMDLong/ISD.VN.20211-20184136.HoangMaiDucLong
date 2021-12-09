package entity.cart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.exception.MediaNotAvailableException;
import entity.media.Media;

/**
 * Class for shopping cart.
 *
 * @author Admin
 *
 */
public class Cart {

  private List<CartMedia> lstCartMedia;
  private static Cart cartInstance;

  /**
   * This method return the current Cart instance.
   *
   * @return Cart
   */
  public static Cart getCart() {
    if (cartInstance == null) {
      cartInstance = new Cart();
    }
    return cartInstance;
  }

  private Cart() {
    lstCartMedia = new ArrayList<>();
  }

  public void addCartMedia(CartMedia cm) {
    lstCartMedia.add(cm);
  }

  public void removeCartMedia(CartMedia cm) {
    lstCartMedia.remove(cm);
  }

  public List getListMedia() {
    return lstCartMedia;
  }

  public void emptyCart() {
    lstCartMedia.clear();
  }

  /**
   * This method gets total number of items in the current Cart.
   *
   * @return total number of items in cart
   */
  public int getTotalMedia() {
    int total = 0;
    for (Object obj : lstCartMedia) {
      CartMedia cm = (CartMedia) obj;
      total += cm.getQuantity();
    }
    return total;
  }

  /**
   * This method calculates subtotal of the items in the current Cart.
   *
   * @return current subtotal
   */
  public int calSubtotal() {
    int total = 0;
    for (Object obj : lstCartMedia) {
      CartMedia cm = (CartMedia) obj;
      total += cm.getPrice() * cm.getQuantity();
    }
    return total;
  }

  /**
   * This method check for product's availability when customer adds products to cart.
   *
   * @throws SQLException
   */
  public void checkAvailabilityOfProduct() throws SQLException {
    boolean allAvai = true;
    for (Object object : lstCartMedia) {
      CartMedia cartMedia = (CartMedia) object;
      int requiredQuantity = cartMedia.getQuantity();
      int availQuantity = cartMedia.getMedia().getQuantity();
      if (requiredQuantity > availQuantity) {
        allAvai = false;
      }
    }
    if (!allAvai) {
      throw new MediaNotAvailableException("Some media not available");
    }
  }

  /**
   * This method fetch an item in the cart.
   *
   * @param media the item to fetch
   * @return CartMedia
   */
  public CartMedia checkMediaInCart(Media media) {
    for (CartMedia cartMedia : lstCartMedia) {
      if (cartMedia.getMedia().getId() == media.getId()) {
        return cartMedia;
      }
    }
    return null;
  }

}
