package common.exception;

/**
 * InvalidCardException throws when card's information
 * is invalid.
 *
 * @author Admin
 */
public class InvalidCardException extends PaymentException {
  public InvalidCardException() {
    super("ERROR: Invalid card!");
  }
}
