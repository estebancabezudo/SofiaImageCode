package net.cabezudo.sofia.sic.tokens;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.06.13
 */
public class CommaToken extends SICToken {

  public CommaToken(Position position) {
    super(",", position);
  }

  @Override
  public boolean isComma() {
    return true;
  }

  @Override
  public String getCSSClass() {
    return "none";
  }

  @Override
  public TokenType getType() {
    return TokenType.COMMA;
  }
}
