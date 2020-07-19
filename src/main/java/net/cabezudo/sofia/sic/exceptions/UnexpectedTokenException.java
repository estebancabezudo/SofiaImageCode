package net.cabezudo.sofia.sic.exceptions;

import net.cabezudo.sofia.sic.tokens.SICToken;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.06.13
 */
public class UnexpectedTokenException extends SICException {

  private static final long serialVersionUID = 1L;

  public UnexpectedTokenException(String value, SICToken token) {
    super("Unexpected: " + value, token);
  }

  public UnexpectedTokenException(String expected, String value, Throwable cause, SICToken token) {
    super("Unexpected " + value + ". Waiting for a " + expected + ".", cause, token);
  }

  public UnexpectedTokenException(String expected, String value, SICToken token) {
    this(expected, value, null, token);
  }
}
