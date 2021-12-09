package common.exception;

/**
 * RushUnsupportedException throws when the order is not rush-shipping-supported.
 *
 * @author nguyenlm
 *
 */
public class RushUnsupportedException extends AimsException {
  public RushUnsupportedException(String message) {
    super(message);
  }
}
