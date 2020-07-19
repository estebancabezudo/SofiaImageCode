package net.cabezudo.sofia.sic.elements;

import net.cabezudo.sofia.sic.exceptions.SICCompilerException;
import net.cabezudo.sofia.sic.tokens.SICToken;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.07.03
 */
public class SICCompileTimeException extends SICCompilerException {

  private static final long serialVersionUID = 1L;

  public SICCompileTimeException(String message, SICToken token) {
    super(message, token);
  }

  public SICCompileTimeException(String message, Throwable cause, SICToken token) {
    super(message, cause, token);
  }
}
