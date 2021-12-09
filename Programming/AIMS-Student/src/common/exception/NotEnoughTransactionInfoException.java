package common.exception;

/**
 * NotEnoughTransactionInfoException throws when customer doesn't provide
 * enough information to make transaction.
 *
 * @author Admin
 *
 */
public class NotEnoughTransactionInfoException extends PaymentException {
  public NotEnoughTransactionInfoException() {
    super("ERROR: Not Enough Transcation Information");
  }
}
