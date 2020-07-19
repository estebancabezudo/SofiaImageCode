package net.cabezudo.sofia.sic.tokens;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.06.26
 */
public class InvalidToken extends SICToken {

  public InvalidToken(String value, Position position) {
    super(value, position);
  }

  @Override
  public String getCSSClass() {
    return "none";
  }

  @Override
  public TokenType getType() {
    return TokenType.INVALID;
  }

}
