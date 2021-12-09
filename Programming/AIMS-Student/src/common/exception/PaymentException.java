package common.exception;

/**
 * PaymentException wraps all exception in payment process.
 *
 * @author nguyenlm
 *
 */
public class PaymentException extends RuntimeException {
  public PaymentException(String message) {
    super(message);
  }
}
