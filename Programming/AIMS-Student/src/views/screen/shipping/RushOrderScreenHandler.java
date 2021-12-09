package views.screen.shipping;

import entity.order.Order;
import java.io.IOException;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

public class RushOrderScreenHandler extends BaseScreenHandler {

	private Order rushOrder;

	public RushOrderScreenHandler(Stage stage, String screenPath, Order order) throws IOException {
		super(stage, screenPath);
		this.rushOrder = order;
	}
	
	// getter and setter
	public Order getRushOrder() {
		return this.rushOrder;
	}
}
