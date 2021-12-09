package common.exception;

/**
 * NotEnoughBalanceException throws when customer doesn't have
 * enough balance to pay.
 *
 * @author Admin
 *
 */
public class NotEnoughBalanceException extends PaymentException {

  public NotEnoughBalanceException() {
    super("ERROR: Not enough balance in card!");
  }

}
