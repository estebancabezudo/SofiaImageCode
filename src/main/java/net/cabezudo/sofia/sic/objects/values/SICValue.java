package net.cabezudo.sofia.sic.objects.values;

import net.cabezudo.sofia.sic.exceptions.InvalidParameterException;
import net.cabezudo.sofia.sic.tokens.Position;
import net.cabezudo.sofia.sic.tokens.SICToken;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.06.14
 * @param <T>
 */
public abstract class SICValue<T> {

  private final SICToken token;
  private T value;

  public SICValue(SICToken token) {
    if (token == null) {
      throw new InvalidParameterException("Invalid null parameter.");
    }
    this.token = token;
  }

  public SICToken getToken() {
    return token;
  }

  public abstract T getValue();

  public boolean isAspect() {
    return false;
  }

  public boolean isColorChannel() {
    return false;
  }

  public boolean isDecimal() {
    return false;
  }

  public boolean isImageFilePath() {
    return false;
  }

  public boolean isNumber() {
    return false;
  }

  public boolean isInteger() {
    return false;
  }

  public boolean isPercentage() {
    return false;
  }

  public boolean isPixels() {
    return false;
  }

  public boolean isString() {
    return false;
  }

  public abstract String getTypeName();

  @Override
  public String toString() {
    return token.getValue();
  }

  public Position getPosition() {
    return token.getPosition();
  }
}
