package net.cabezudo.sofia.sic.tokens;

import java.math.BigDecimal;
import net.cabezudo.sofia.logger.Logger;
import net.cabezudo.sofia.sic.tokens.functions.BrightnessFunctionToken;
import net.cabezudo.sofia.sic.tokens.functions.GrayFunctionToken;
import net.cabezudo.sofia.sic.tokens.functions.LoadImageFunctionToken;
import net.cabezudo.sofia.sic.tokens.functions.MainFunctionToken;
import net.cabezudo.sofia.sic.tokens.functions.ResizeFunctionToken;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.06.13
 */
public class TokensFactory {

  public static SICToken get(StringBuilder sb, Position position) {
    SICToken token = get(sb.toString(), position);
    return token;
  }

  public static SICToken get(char c, Position position) {
    SICToken token = get(Character.toString(c), position);
    return token;
  }

  public static SICToken get(String s, Position position) {
    switch (s.length()) {
      case 1:
        switch (s) {
          case "\n":
            return new NewLineToken(position);
          case " ":
          case "\u00A0":
            return new SpaceToken(position);
          case "\t":
            return new TabulationToken(position);
          case ",":
            return new CommaToken(position);
          case "=":
            return new EqualToken(position);
          case "(":
            return new OpenParenthesesToken(position);
          case ")":
            return new CloseParenthesesToken(position);
        }
        break;
      case 2:
        switch (s) {
          case "\"\"":
            return new StringToken("", position);
        }
        break;
    }
    switch (s.toLowerCase()) {
      case "true":
        return new TrueToken(position);
      case "false":
        return new FalseToken(position);
      default:
        do {
          if (s.startsWith("\"") && s.endsWith("\"")) {
            return new StringToken(s.substring(1, s.length() - 1), position);
          }
          try {
            BigDecimal value = new BigDecimal(s);
            Logger.debug("Found number %s in factory.", value);
            return new NumberToken(s, position);
          } catch (NumberFormatException e) {
            switch (s) {
              case "brightness":
                return new BrightnessFunctionToken(position);
              case "gray":
                return new GrayFunctionToken(position);
              case "loadImage":
                return new LoadImageFunctionToken(position);
              case "main":
                return new MainFunctionToken(position);
              case "resize":
                return new ResizeFunctionToken(position);
              case "aspect":
              case "channel":
              case "height":
              case "model":
              case "name":
              case "scale":
              case "type":
              case "value":
              case "width":
              case "method":
                return new ParameterNameToken(s, position);
              default:
                return new ParameterValueToken(s, position);
            }
          }
        } while (false);
    }
  }
}
