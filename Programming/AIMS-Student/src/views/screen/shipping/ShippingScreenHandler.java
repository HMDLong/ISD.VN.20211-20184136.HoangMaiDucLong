package views.screen.shipping;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

import controller.PlaceOrderController;
import controller.RushOrderController;
import common.exception.InvalidDeliveryInfoException;
import common.exception.RushUnsupportedException;
import entity.cart.Cart;
import entity.invoice.Invoice;
import entity.order.Order;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.invoice.InvoiceScreenHandler;

/**
 * Class for handling shipping screen display and interaction.
 *
 * @author Admin
 *
 */
public class ShippingScreenHandler extends BaseScreenHandler implements Initializable {

  @FXML
  private Label screenTitle;

  @FXML
  private TextField name;

  @FXML
  private TextField phone;

  @FXML
  private TextField address;

  @FXML
  private TextField instructions;

  @FXML
  private ComboBox<String> province;

  @FXML
  private RadioButton rushOrderButton;

  private Order order;

  public ShippingScreenHandler(Stage stage, String screenPath, Order order) throws IOException {
    super(stage, screenPath);
    this.order = order;
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load
    name.focusedProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue && firstTime.get()) {
        content.requestFocus(); // Delegate the focus to container
        firstTime.setValue(false); // Variable value changed for future references
      }
    });
    this.province.getItems().addAll(Configs.PROVINCES);
  }

  @FXML
  void submitDeliveryInfo(MouseEvent event) throws IOException, InterruptedException, SQLException {

    // add info to messages
    HashMap<String, String> messages = new HashMap<>();
    messages.put("name", name.getText());
    messages.put("phone", phone.getText());
    messages.put("address", address.getText());
    messages.put("instructions", instructions.getText());
    messages.put("province", province.getValue());
    try {
      // process and validate delivery info
      getBaseController().processDeliveryInfo(messages);
    } catch (InvalidDeliveryInfoException e) {
      throw new InvalidDeliveryInfoException(e.getMessage());
    }

    // If customer place a rush order, start process rush order
    if (rushOrderButton.isSelected()) {
      RushOrderController rushController = new RushOrderController();
      try {
        rushController.checkRushOrderSupport(Cart.getCart().getListMedia(), messages.get("province"));
      } catch (RushUnsupportedException e) {
        throw new RushUnsupportedException(e.getMessage());
      }
      // if satisfied, transition to rush order screen
      RushOrderScreenHandler rushOrderScreen = new RushOrderScreenHandler(this.stage, Configs.RUSH_ORDER_SCREEN_PATH,
          order);
    }
    // calculate shipping fees
    int shippingFees = getBaseController().calculateShippingFee(order);
    order.setShippingFees(shippingFees);
    order.setDeliveryInfo(messages);

    // create invoice screen
    Invoice invoice = getBaseController().createInvoice(order);
    BaseScreenHandler invoiceScreenHandler = new InvoiceScreenHandler(this.stage, Configs.INVOICE_SCREEN_PATH, invoice);
    invoiceScreenHandler.setPreviousScreen(this);
    invoiceScreenHandler.setHomeScreenHandler(homeScreenHandler);
    invoiceScreenHandler.setScreenTitle("Invoice Screen");
    invoiceScreenHandler.setBaseController(getBaseController());
    invoiceScreenHandler.show();
  }

  @Override
  public PlaceOrderController getBaseController() {
    return (PlaceOrderController) super.getBaseController();
  }

  public void notifyError() {
    // TODO: implement later on if we need
  }

}
