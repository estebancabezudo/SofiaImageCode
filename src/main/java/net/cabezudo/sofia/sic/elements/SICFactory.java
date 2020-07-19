package net.cabezudo.sofia.sic.elements;

import net.cabezudo.sofia.sic.SICTokens;
import net.cabezudo.sofia.sic.exceptions.EmptyQueueException;
import net.cabezudo.sofia.sic.exceptions.InvalidParameterNameException;
import net.cabezudo.sofia.sic.tokens.Position;
import net.cabezudo.sofia.sic.tokens.SICToken;
import net.cabezudo.sofia.sic.tokens.parameters.ParameterFactory;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.06.13
 */
public class SICFactory {

  public SICElement get(SICTokens tokens) throws SICCompileTimeException, SICUnexpectedEndOfCodeException {
    SICToken token;
    try {
      consumeSpaces(tokens);
      token = tokens.consume();
    } catch (EmptyQueueException e) {
      throw new SICUnexpectedEndOfCodeException(e);
    }
    if (!token.isFunction()) {
      token.setError(true);
      throw new SICCompileTimeException(token.getValue() + " IS NOT a function.", token);
    }
    SICElement mainFunction;
    try {
      mainFunction = get(token, tokens);
    } catch (EmptyQueueException e) {
      throw new SICUnexpectedEndOfCodeException(e);
    }
    try {
      consumeSpaces(tokens);
      SICToken remainToken = tokens.peek();
      if (remainToken != null) {
        remainToken.setInvalidClass(true);
        remainToken.setError(true);
        throw new SICCompileTimeException("Unexpected token '" + remainToken.getValue() + "'. Extra token in code.", remainToken);
      }
    } catch (EmptyQueueException e) {
      throw new SICUnexpectedEndOfCodeException(e);
    }
    return mainFunction;
  }

  private SICElement get(SICToken token, SICTokens tokens) throws SICCompileTimeException, EmptyQueueException {

    Position position;

    if (token.isFunction()) {
      return createSICFunction(token, tokens);
    }
    position = token.getPosition();
    token.setError(true);
    throw new SICCompileTimeException("Unexpected value " + token.getValue() + ".", token);
  }

  private void consumeSpaces(SICTokens tokens) throws EmptyQueueException {
    SICToken token = tokens.peek();
    while (token != null && (token.isSpace() || token.isNewLine() || token.isTabulation())) {
      tokens.consume();
      token = tokens.peek();
    }
  }

  private SICElement createSICFunction(SICToken token, SICTokens tokens) throws SICCompileTimeException, EmptyQueueException {
    SICFunction sicFunction = new SICFunction(token);
    SICToken openParentheses = tokens.consume();
    if (!openParentheses.isOpenParentheses()) {
      openParentheses.setError(true);
      throw new SICCompileTimeException("Unexpected token '" + openParentheses.getDescription() + "'. Must be a open parentheses.", openParentheses);
    }

    SICToken separatorOrCloseParentheses;
    do {
      consumeSpaces(tokens);
      SICToken parameterNameOrFunction = tokens.consume();

      if (parameterNameOrFunction.isParameterName()) {
        consumeSpaces(tokens);
        SICToken equal = tokens.consume();
        if (!equal.isEqual()) {
          equal.setError(true);
          throw new SICCompileTimeException("Unexpected token '" + equal.getDescription() + "'. Must be a equal.", equal);
        }
        consumeSpaces(tokens);
        SICToken parameterValue = tokens.consume();
        consumeSpaces(tokens);
        separatorOrCloseParentheses = tokens.consume();
        if (!separatorOrCloseParentheses.isComma() && !separatorOrCloseParentheses.isCloseParentheses()) {

          separatorOrCloseParentheses.setError(true);
          throw new SICCompileTimeException("Unexpected '" + separatorOrCloseParentheses.getValue() + "'. Must be a comma or close parentheses after a parameter.", separatorOrCloseParentheses);
        }
        SICParameter sicParameter;
        try {
          sicParameter = ParameterFactory.get(parameterNameOrFunction, parameterValue);
          sicFunction.add(sicParameter);
        } catch (InvalidParameterNameException e) {
          parameterNameOrFunction.setError(true);
          throw new SICCompileTimeException(e.getMessage(), separatorOrCloseParentheses);
        }
        // Go to the next iteracion searching other parameter or token
        continue;
      }

      if (parameterNameOrFunction.isFunction()) {
        SICElement newSICFunction = createSICFunction(parameterNameOrFunction, tokens);
        sicFunction.add(newSICFunction);
        consumeSpaces(tokens);
        separatorOrCloseParentheses = tokens.consume();
        if (!separatorOrCloseParentheses.isComma() && !separatorOrCloseParentheses.isCloseParentheses()) {
          separatorOrCloseParentheses.setError(true);
          throw new SICCompileTimeException("Invalid token " + separatorOrCloseParentheses.getDescription() + ". Must be a comma or close parentheses after a function.", separatorOrCloseParentheses);
        }
        // Go to the next iteracion searching other parameter or token
        continue;
      }
      if (parameterNameOrFunction.isCloseParentheses()) {
        separatorOrCloseParentheses = parameterNameOrFunction;
        continue;
      }
      parameterNameOrFunction.setInvalidClass(true);
      parameterNameOrFunction.setError(true);
      throw new SICCompileTimeException("Invalid token " + parameterNameOrFunction.getValue() + ". Must be a parameter or function.", parameterNameOrFunction);
    } while (separatorOrCloseParentheses != null && separatorOrCloseParentheses.isComma());

    return sicFunction;
  }
}
