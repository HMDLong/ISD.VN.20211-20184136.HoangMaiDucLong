package common.exception;

/**
 * SuspiciousTransactionException throws when the transaction is deemed
 * suspicious by the system.
 *
 * @author nguyenlm
 *
 */
public class SuspiciousTransactionException extends PaymentException {
  public SuspiciousTransactionException() {
    super("ERROR: Suspicious Transaction Report!");
  }
}
