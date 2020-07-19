package net.cabezudo.sofia.sic;

import net.cabezudo.sofia.sic.tokens.Position;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.07.03
 */
public class Message {

  private final String text;
  private final Position position;

  public Message(String message, Position position) {
    this.text = message;
    this.position = position;
  }

  public Message(String message) {
    this(message, null);
  }

  public String getText() {
    return text;
  }

  public Position getPosition() {
    return position;
  }
}
