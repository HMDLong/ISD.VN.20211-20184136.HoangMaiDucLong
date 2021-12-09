package common.exception;

/**
 * UnrecognizedException throws when an unknown error occurs.
 *
 * @author nguyenlm
 *
 */
public class UnrecognizedException extends RuntimeException {
  public UnrecognizedException() {
    super("ERROR: Something went wrowng!");
  }
}
