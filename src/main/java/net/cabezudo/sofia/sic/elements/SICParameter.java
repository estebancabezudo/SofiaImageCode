package net.cabezudo.sofia.sic.elements;

import net.cabezudo.sofia.sic.Utils;
import net.cabezudo.sofia.sic.tokens.SICToken;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.06.13
 */
public abstract class SICParameter extends SICParameterOrFunction {

  private final SICToken name;
  private final SICToken value;

  public SICParameter(SICToken name, SICToken value) {
    super(name.getValue(), name);
    this.name = name;
    this.value = value;
  }

  @Override
  public String toString(int deep) {
    return Utils.repeat(' ', deep * 2) + name.getValue() + "=" + value.getValue();
  }

  public boolean isFactorParameter() {
    return false;
  }

  public boolean isHeightParameter() {
    return false;
  }

  public boolean isNameParameter() {
    return false;
  }

  public boolean isMethodParameter() {
    return false;
  }

  public boolean isModelParameter() {
    return false;
  }

  public boolean isScaleParameter() {
    return false;
  }

  public boolean isTypeParameter() {
    return false;
  }

  public boolean isValueParameter() {
    return false;
  }

  public SICToken getNameToken() {
    return name;
  }

  public SICToken getValueToken() {
    return value;
  }

  public boolean isWidthParameter() {
    return false;
  }
}
