package common.exception;

/**
 * InvalidTransactionAmountException throws when transaction amount
 * is invalid.
 *
 * @author Admin
 *
 */
public class InvalidTransactionAmountException extends PaymentException {
  public InvalidTransactionAmountException() {
    super("ERROR: Invalid Transaction Amount!");
  }
}
