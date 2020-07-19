package net.cabezudo.sofia.sic.objects.values;

import net.cabezudo.sofia.sic.elements.SICCompileTimeException;
import net.cabezudo.sofia.sic.tokens.SICToken;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.06.14
 */
public class SICGrayMethod extends SICString {

  public SICGrayMethod(SICToken token) throws SICCompileTimeException {
    super(token);
    switch (token.getValue()) {
      case "averaging":
      case "luma":
      case "desaturation":
      case "decomposition":
      case "colorChannel":
      case "grayShades":
        break;
      default:
        throw new SICCompileTimeException("Invalid model " + token.getValue() + " for gray function.", token);
    }
  }

  @Override
  public String getTypeName() {
    return "grayMethod";
  }

  @Override
  public boolean isString() {
    return true;
  }

  @Override
  public String getValue() {
    return getToken().getValue();
  }
}
