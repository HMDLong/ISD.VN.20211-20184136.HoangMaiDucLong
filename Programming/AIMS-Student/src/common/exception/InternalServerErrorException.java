package common.exception;

/**
 * InternalServerErrorException is thrown when error occurs in
 * server runtime.
 *
 * @author nguyenlm
 */
public class InternalServerErrorException extends PaymentException {

  public InternalServerErrorException() {
    super("ERROR: Internal Server Error!");
  }

}
