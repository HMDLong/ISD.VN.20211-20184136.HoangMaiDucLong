package common.exception;

/**
 * InvalidVersionException throws when version is not matched.
 *
 * @author nguyenlm
 *
 */
public class InvalidVersionException extends PaymentException {
  public InvalidVersionException() {
    super("ERROR: Invalid Version Information!");
  }
}
