package controller;

import java.sql.SQLException;
import java.util.List;

import entity.media.Media;

/**
 * This class controls the flow of events in home screen.
 *
 * @author nguyenlm
 */
public class HomeController extends BaseController {

  /**
   * This method gets all Media in DB and return back to home to display.
   *
   * @return List[Media]
   * @throws SQLException throws when query error occurs
   */
  public List getAllMedia() throws SQLException {
    return new Media().getAllMedia();
  }

}
