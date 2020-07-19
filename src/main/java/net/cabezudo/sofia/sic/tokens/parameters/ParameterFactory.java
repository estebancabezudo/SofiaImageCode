package net.cabezudo.sofia.sic.tokens.parameters;

import net.cabezudo.sofia.sic.elements.SICParameter;
import net.cabezudo.sofia.sic.exceptions.InvalidParameterNameException;
import net.cabezudo.sofia.sic.tokens.SICToken;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.06.13
 */
public class ParameterFactory {

  private ParameterFactory() {
    // Nothing to do here
  }

  public static SICParameter get(SICToken nameToken, SICToken valueToken) throws InvalidParameterNameException {
    switch (nameToken.getValue()) {
      case "aspect":
        return new AspectParameter(nameToken, valueToken);
      case "channel":
        return new HeightParameter(nameToken, valueToken);
      case "height":
        return new ChannelParameter(nameToken, valueToken);
      case "method":
        return new MethodParameter(nameToken, valueToken);
      case "model":
        return new ModelParameter(nameToken, valueToken);
      case "name":
        return new NameParameter(nameToken, valueToken);
      case "scale":
        return new ScaleParameter(nameToken, valueToken);
      case "type":
        return new TypeParameter(nameToken, valueToken);
      case "value":
        return new ValueParameter(nameToken, valueToken);
      case "width":
        return new WidthParameter(nameToken, valueToken);
      default:
        throw new InvalidParameterNameException(nameToken);
    }
  }

}
