package entity.payment;

/**
 * Class for credit card.
 *
 * @author Admin
 *
 */
public class CreditCard {
  private String cardCode;
  private String owner;
  private int cvvCode;
  private String dateExpired;

  /**
   * CreditCard constructor.
   *
   * @param cardCode
   * @param owner
   * @param cvvCode
   * @param dateExpired
   */
  public CreditCard(String cardCode, String owner, int cvvCode, String dateExpired) {
    super();
    this.cardCode = cardCode;
    this.owner = owner;
    this.cvvCode = cvvCode;
    this.dateExpired = dateExpired;
  }
}
