package net.cabezudo.sofia.sic.exceptions;

import net.cabezudo.sofia.sic.tokens.Position;
import net.cabezudo.sofia.sic.tokens.SICToken;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.06.14
 */
public abstract class SICException extends Exception {

  private static final long serialVersionUID = 1L;

  private final SICToken token;

  public SICException(String message, SICToken token) {
    super(message);
    this.token = token;
  }

  public SICException(String message, Throwable cause, SICToken token) {
    super(message, cause);
    this.token = token;
  }

  public String getValue() {
    return token.getValue();
  }

  public Position getPosition() {
    return token.getPosition();
  }

}
