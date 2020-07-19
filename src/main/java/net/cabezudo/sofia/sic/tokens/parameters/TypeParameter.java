package net.cabezudo.sofia.sic.tokens.parameters;

import java.nio.file.Path;
import net.cabezudo.sofia.sic.elements.SICParameter;
import net.cabezudo.sofia.sic.objects.SICObject;
import net.cabezudo.sofia.sic.tokens.SICToken;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.06.13
 */
public class TypeParameter extends SICParameter {

  public TypeParameter(SICToken name, SICToken value) {
    super(name, value);
  }

  @Override
  public boolean isTypeParameter() {
    return true;
  }

  @Override
  public SICObject compile(Path basePath) {
    throw new RuntimeException("Nothing to compile here.");
  }

}
