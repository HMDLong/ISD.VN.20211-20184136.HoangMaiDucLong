package views.screen;

import java.io.IOException;
import java.util.Hashtable;

import controller.BaseController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.screen.home.HomeScreenHandler;

/**
 * Base class for handling display and user interaction on screen.
 * Other handler will inherit this class.
 *
 * @author Admin
 *
 */
public class BaseScreenHandler extends FXMLScreenHandler {

  private Scene scene;
  private BaseScreenHandler prev;
  protected final Stage stage;
  protected HomeScreenHandler homeScreenHandler;
  protected Hashtable<String, String> messages;
  private BaseController bController;

  private BaseScreenHandler(String screenPath) throws IOException {
    super(screenPath);
    this.stage = new Stage();
  }

  public void setPreviousScreen(BaseScreenHandler prev) {
    this.prev = prev;
  }

  public BaseScreenHandler getPreviousScreen() {
    return this.prev;
  }

  public BaseScreenHandler(Stage stage, String screenPath) throws IOException {
    super(screenPath);
    this.stage = stage;
  }

  /**
   * This method display the screen.
   */
  public void show() {
    if (this.scene == null) {
      this.scene = new Scene(this.content);
    }
    this.stage.setScene(this.scene);
    this.stage.show();
  }

  public void setScreenTitle(String string) {
    this.stage.setTitle(string);
  }

  /**
   * This method sets base controller of the screen.
   *
   * @param baseController
   */
  public void setBaseController(BaseController baseController) {
    this.bController = baseController;
  }

  public BaseController getBaseController() {
    return this.bController;
  }

  public void forward(Hashtable<String, String> messages) {
    this.messages = messages;
  }

  public void setHomeScreenHandler(HomeScreenHandler HomeScreenHandler) {
    this.homeScreenHandler = HomeScreenHandler;
  }

}
