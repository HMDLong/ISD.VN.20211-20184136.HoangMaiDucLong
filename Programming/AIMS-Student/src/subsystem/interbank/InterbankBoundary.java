package subsystem.interbank;

import common.exception.UnrecognizedException;
import utils.Api;

/**
 * Solid class for interbank interface.
 *
 * @author Admin
 *
 */
public class InterbankBoundary {

  String query(String url, String data) {
    String response = null;
    try {
      response = Api.post(url, data);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      throw new UnrecognizedException();
    }
    return response;
  }

}
