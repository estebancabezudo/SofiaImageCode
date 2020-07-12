package net.cabezudo.sofia.sic;

import net.cabezudo.sofia.sic.tokens.Position;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.07.03
 */
public class Message {

  private final String message;
  private final Position position;

  public Message(String message, Position position) {
    this.message = message;
    this.position = position;
  }

  public Message(String message) {
    this(message, null);
  }
}