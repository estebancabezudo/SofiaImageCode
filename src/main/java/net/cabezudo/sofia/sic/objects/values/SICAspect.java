package net.cabezudo.sofia.sic.objects.values;

import net.cabezudo.sofia.sic.elements.SICCompileTimeException;
import net.cabezudo.sofia.sic.tokens.SICToken;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.07.04
 */
public class SICAspect extends SICDecimalOrAspect {

  public SICAspect(SICToken token) throws SICCompileTimeException {
    super(token);
  }

  @Override
  public String getTypeName() {
    return "aspect";
  }
}
