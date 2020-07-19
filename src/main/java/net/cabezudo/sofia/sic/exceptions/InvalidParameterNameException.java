package net.cabezudo.sofia.sic.exceptions;

import net.cabezudo.sofia.sic.tokens.SICToken;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.06.14
 */
public class InvalidParameterNameException extends SICParseException {

  private static final long serialVersionUID = 1L;

  public InvalidParameterNameException(SICToken token) {
    super("Invalid name parameter " + token.getValue() + ".", token);
  }
}
