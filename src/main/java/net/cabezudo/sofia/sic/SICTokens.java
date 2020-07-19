package net.cabezudo.sofia.sic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import net.cabezudo.sofia.sic.exceptions.EmptyQueueException;
import net.cabezudo.sofia.sic.tokens.Position;
import net.cabezudo.sofia.sic.tokens.SICToken;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.06.13
 */
public class SICTokens implements Iterable<SICToken> {

  private Position position;
  private final List<SICToken> list = new ArrayList<>();
  private final Queue<SICToken> queue = new LinkedList<>();
  private SICToken lastToken;

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    list.forEach((token) -> {
      sb.append(token.toString()).append("\n");
    });
    return sb.toString();
  }

  public String toCode() {
    StringBuilder sb = new StringBuilder();
    list.forEach(token -> {
      sb.append(token.getValue());
    });
    return sb.toString();
  }

  public boolean add(SICToken token) {
    if (token == null || token.isEmpty()) {
      return false;
    }

    if (queue.isEmpty()) {
      position = token.getPosition();
    }

    list.add(token);
    return queue.offer(token);
  }

  public SICToken element() {
    return queue.element();
  }

  public SICToken peek() throws EmptyQueueException {
    return queue.peek();
  }

  public Position getPosition() {
    return position;
  }

  boolean hasNext() {
    return queue.size() > 0;
  }

  public SICToken consume() throws EmptyQueueException {
    SICToken token = queue.poll();
    if (token == null) {
      lastToken.setError(true);
      throw new EmptyQueueException(lastToken.getEndPosition());
    }
    lastToken = token;
    return token;
  }

  @Override
  public Iterator<SICToken> iterator() {
    return list.iterator();
  }
}
