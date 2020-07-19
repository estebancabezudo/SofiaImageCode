package net.cabezudo.sofia.sic.objects;

import java.nio.file.Path;
import net.cabezudo.sofia.sic.elements.SICCompileTimeException;
import net.cabezudo.sofia.sic.elements.SICParameters;
import net.cabezudo.sofia.sic.exceptions.SICRuntimeException;
import net.cabezudo.sofia.sic.tokens.SICToken;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.06.14
 */
public class ObjectFactory {

  private ObjectFactory() {
    // Nothing to do here. Utility class.
  }

  public static SICObjectFunction get(Path basePath, SICToken token, SICParameters parameters) throws SICCompileTimeException {
    switch (token.getValue()) {
      case "main":
        return new MainFunctionObject(basePath, parameters);
      case "loadImage":
        return new LoadImageFunctionObject(basePath, parameters);
      case "resize":
        return new ResizeFunctionObject(basePath, token, parameters);
      case "brightness":
        return new BrightnessFunctionObject(basePath, token, parameters);
      case "gray":
        return new GrayFunctionObject(basePath, token, parameters);
      default:
        throw new SICRuntimeException("[ObjectFactory:SICObjectFunction] Invalid name for a function object: " + token.getValue());
    }
  }
}
