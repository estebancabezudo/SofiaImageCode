package net.cabezudo.sofia.sic.exceptions;

import net.cabezudo.sofia.sic.tokens.SICToken;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.06.13
 */
public class EOSException extends SICParseException {

  private static final long serialVersionUID = 1L;

  public EOSException(SICToken token) {
    super("Unexpected end of string", token);
  }
}
