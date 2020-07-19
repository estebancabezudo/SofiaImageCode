package net.cabezudo.sofia.sic.objects.values;

import net.cabezudo.sofia.sic.tokens.SICToken;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.06.14
 * @param <T>
 */
public abstract class SICNumber<T> extends SICValue<T> {

  public SICNumber(SICToken token) {
    super(token);
  }

  @Override
  public boolean isNumber() {
    return true;
  }

  public abstract boolean isZero();
}
