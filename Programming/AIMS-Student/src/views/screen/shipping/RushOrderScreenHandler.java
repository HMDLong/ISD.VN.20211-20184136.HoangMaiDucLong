package views.screen.shipping;

import entity.cart.CartMedia;
import entity.invoice.Invoice;
import entity.media.Media;
import entity.order.Order;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.RushOrderController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.invoice.InvoiceScreenHandler;

public class RushOrderScreenHandler extends BaseScreenHandler implements Initializable{

  @FXML
  private Button confirmBtn;
  
  @FXML
  private TableView<RushTableMedia> rushItems;
  
  @FXML
  private TableColumn<RushTableMedia, Integer> quantityCol;
  
  @FXML
  private TableColumn<RushTableMedia, Integer> priceCol;
  
  @FXML
  private TableColumn<RushTableMedia, String> itemCol;
  
  @FXML
  private DatePicker rushDate;
  
  @FXML
  private TextField rushInstruction;
  
  private ObservableList<RushTableMedia> itemsList;
  
  private Order rushOrder;
  
  @Override
  public void initialize(URL url, ResourceBundle bundle) {
    confirmBtn.setOnMouseClicked(e -> {
      try {
        submitRushOrderInfo();
      } catch(Exception excep) {
        excep.printStackTrace();
      }
    });
  }

  public RushOrderScreenHandler(Stage stage, String screenPath, Order order) throws IOException {
    super(stage, screenPath);
    this.rushOrder = order;
  }

  // getter and setter
  public Order getRushOrder() {
    return this.rushOrder;
  }
  
  @Override
  public void show() {
    RushOrderController rushControl = (RushOrderController) getBaseController();
    itemsList = FXCollections.observableArrayList(rushControl.getRushTableItems());
    quantityCol.setCellValueFactory(new PropertyValueFactory<RushTableMedia, Integer>("quantity"));
    priceCol.setCellValueFactory(new PropertyValueFactory<RushTableMedia, Integer>("price"));
    itemCol.setCellValueFactory(new PropertyValueFactory<RushTableMedia, String>("name"));
    rushItems.setItems(itemsList);
    super.show();
  }
  
  private void submitRushOrderInfo() {
    RushOrderController rushControl = (RushOrderController) getBaseController();
    try {
      Invoice invoice = rushControl.processRushOrder(this.rushOrder, rushDate.getChronology().toString(), rushInstruction.getText());
      BaseScreenHandler invoiceScreenHandler = new InvoiceScreenHandler(this.stage, Configs.INVOICE_SCREEN_PATH, invoice);
      invoiceScreenHandler.setPreviousScreen(this);
      invoiceScreenHandler.setHomeScreenHandler(homeScreenHandler);
      invoiceScreenHandler.setScreenTitle("Invoice Screen");
      invoiceScreenHandler.setBaseController(getBaseController());
      invoiceScreenHandler.show();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
