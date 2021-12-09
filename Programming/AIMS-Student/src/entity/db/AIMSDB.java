package entity.db;

import java.sql.DriverManager;
import java.util.logging.Logger;

import java.sql.Connection;
import utils.*;

/**
 * Class for database connection.
 *
 * @author Admin
 *
 */
public class AIMSDB {

  private static Logger LOGGER = Utils.getLogger(Connection.class.getName());
  private static Connection connect;

  /**
   * This method establish a connection to database and return that connection.
   *
   * @return Connection
   */
  public static Connection getConnection() {
    if (connect != null) {
      return connect;
    }
    try {
      Class.forName("org.sqlite.JDBC");
      String url = "jdbc:sqlite:assets/db/aims.db";
      connect = DriverManager.getConnection(url);
      LOGGER.info("Connect database successfully");
    } catch (Exception e) {
      LOGGER.info(e.getMessage());
    }
    return connect;
  }

  public static void main(String[] args) {
    AIMSDB.getConnection();
  }
}
