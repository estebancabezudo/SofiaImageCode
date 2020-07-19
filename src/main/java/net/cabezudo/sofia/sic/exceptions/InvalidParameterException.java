package net.cabezudo.sofia.sic.exceptions;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.07.09
 */
public class InvalidParameterException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public InvalidParameterException(String message) {
    super(message);
  }

}
