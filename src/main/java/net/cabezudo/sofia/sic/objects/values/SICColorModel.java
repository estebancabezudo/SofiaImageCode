package net.cabezudo.sofia.sic.objects.values;

import net.cabezudo.sofia.sic.elements.SICCompileTimeException;
import net.cabezudo.sofia.sic.tokens.SICToken;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.06.14
 */
public class SICColorModel extends SICString {

  public SICColorModel(SICToken token) throws SICCompileTimeException {
    super(token);
    switch (token.getValue()) {
      case "tv":
      case "hsb":
        break;
      default:
        throw new SICCompileTimeException("Invalid model " + token.getValue() + " for brightness function.", token);
    }
  }

  @Override
  public String getTypeName() {
    return "colorModel";
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
