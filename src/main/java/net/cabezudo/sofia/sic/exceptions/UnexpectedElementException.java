package net.cabezudo.sofia.sic.exceptions;

import net.cabezudo.sofia.sic.tokens.SICToken;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.06.13
 */
public class UnexpectedElementException extends SICParseException {

  private static final long serialVersionUID = 1L;

  public UnexpectedElementException(String value, SICToken token) {
    super("Unexpected element: " + value, token);
  }

  public UnexpectedElementException(String expected, String value, Throwable cause, SICToken token) {
    super("Unexpected element. Waiting for a " + expected + " and have a " + value + ".", cause, token);
  }

  public UnexpectedElementException(String expected, String value, SICToken token) {
    this(expected, value, null, token);
  }
}
