package net.cabezudo.sofia.sic.tokens;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.06.13
 */
public abstract class SICToken {

  private final Position position;
  private final String value;
  private boolean error = false;
  private boolean invalidClass = false;

  public SICToken(String value, Position position) {
    this.value = value;
    this.position = position;
  }

  @Override
  public String toString() {
    return value
            + " (type: " + this.getClass().getSimpleName()
            + ", position: " + position
            + ", length: " + length()
            + ")";
  }

  public int length() {
    return value.length();
  }

  public boolean isEmpty() {
    return length() == 0;
  }

  public void setError(boolean error) {
    this.error = error;
  }

  public boolean isError() {
    return error;
  }

  public void setInvalidClass(boolean invalidClass) {
    this.invalidClass = invalidClass;
  }

  public boolean isInvalidClass() {
    return invalidClass;
  }

  public Position getPosition() {
    return position;
  }

  public String getValue() {
    return value;
  }

  public boolean isBoolean() {
    return false;
  }

  public boolean isParameterName() {
    return false;
  }

  public boolean isNewLine() {
    return false;
  }

  public boolean isNumber() {
    return false;
  }

  public boolean isComma() {
    return false;
  }

  public boolean isOpenParentheses() {
    return false;
  }

  public boolean isCloseParentheses() {
    return false;
  }

  public boolean isParameter() {
    return false;
  }

  public boolean isTabulation() {
    return false;
  }

  public boolean isFunction() {
    return false;
  }

  public boolean isEqual() {
    return false;
  }

  public boolean isSpace() {
    return false;
  }

  public abstract String getCSSClass();

  public abstract TokenType getType();

  public String getDescription() {
    return getType().getDescription();
  }

  public Position getEndPosition() {
    return new Position(position.getLine(), position.getRow() + value.length());
  }
}
