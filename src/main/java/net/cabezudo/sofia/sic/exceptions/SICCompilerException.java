package net.cabezudo.sofia.sic.exceptions;

import net.cabezudo.sofia.sic.tokens.SICToken;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.06.13
 */
public class SICCompilerException extends SICException {

  private static final long serialVersionUID = 1L;

  public SICCompilerException(String message, SICToken token) {
    super(message, token);
  }

  public SICCompilerException(Throwable cause, SICToken token) {
    super(cause.getMessage(), cause, token);
  }

  public SICCompilerException(String message, Throwable cause, SICToken token) {
    super(message, cause, token);
  }
}
