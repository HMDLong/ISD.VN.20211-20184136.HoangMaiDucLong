package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;

import common.exception.InvalidDeliveryInfoException;
import entity.cart.Cart;
import entity.cart.CartMedia;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderMedia;

/**
 * This class controls the flow of place order usecase in our AIMS project.
 *
 * @author nguyenlm
 */
public class PlaceOrderController extends BaseController {

  /**
   * Just for logging purpose.
   */
  private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

  /**
   * This method checks the availability of product when user click PlaceOrder
   * button.
   *
   * @throws SQLException throws when error occurs in DB query
   */
  public void placeOrder() throws SQLException {
    Cart.getCart().checkAvailabilityOfProduct();
  }

  /**
   * This method creates the new Order based on the Cart.
   *
   * @return Order
   * @throws SQLException throws when error occurs while querying DB
   */
  public Order createOrder() throws SQLException {
    Order order = new Order();
    for (Object object : Cart.getCart().getListMedia()) {
      CartMedia cartMedia = (CartMedia) object;
      OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(), 
                                             cartMedia.getQuantity(), 
                                             cartMedia.getPrice());
      order.getlstOrderMedia().add(orderMedia);
    }
    return order;
  }

  /**
   * This method creates the new Invoice based on order.
   *
   * @param order the order from which the invoice is created
   * @return Invoice
   */
  public Invoice createInvoice(Order order) {
    return new Invoice(order);
  }

  /**
   * This method takes responsibility for processing the shipping info from user.
   *
   * @param info delivery info
   * @throws InterruptedException
   * @throws IOException
   */
  public void processDeliveryInfo(HashMap info) throws InterruptedException, IOException {
    LOGGER.info("Process Delivery Info");
    LOGGER.info(info.toString());
    validateDeliveryInfo(info);
  }

  /**
   * This method validates the delivery info.
   *
   * @param info delivery information
   * @throws InterruptedException
   * @throws IOException
   */
  public void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException {
    String message = new String();
    if (!validateName(info.get("name"))) {
      message += "Invalid name\n";
    }
    if (!validatePhoneNumber(info.get("phone"))) {
      message += "Invalid phone number\n";
    }
    if (!validateAddress(info.get("address"))) {
      message += "Invalid address";
    }
    if (message.length() != 0) {
      throw new InvalidDeliveryInfoException(message);
    }
  }

  /**
   * This method validate phone number.
   *
   * @param phoneNumber the phone number to be validated
   * @return boolean
   */
  public boolean validatePhoneNumber(String phoneNumber) {
    // TODO: your work
    if (!phoneNumber.startsWith("0")) {
      return false;
    }
    if (phoneNumber.length() != 10) {
      return false;
    }
    try {
      Integer.parseInt(phoneNumber);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  public boolean validateName(String name) {
    // TODO: your work
    return name.matches("^[a-zA-Z\\s]+");
  }

  public boolean validateAddress(String address) {
    // TODO: your work
    return address.matches("^[a-zA-Z,\\s]+");
  }

  /**
   * This method calculates the shipping fees of order.
   *
   * @param order the order to calculate shipping fee
   * @return shippingFee
   */
  public int calculateShippingFee(Order order) {
    Random rand = new Random();
    int fees = (int) (((rand.nextFloat() * 10) / 100) * order.getAmount());
    LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
    return fees;
  }
}
