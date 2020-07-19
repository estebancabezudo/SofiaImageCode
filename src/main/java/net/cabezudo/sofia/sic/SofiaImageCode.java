package net.cabezudo.sofia.sic;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import net.cabezudo.sofia.sic.elements.SICCompileTimeException;
import net.cabezudo.sofia.sic.elements.SICElement;
import net.cabezudo.sofia.sic.elements.SICFactory;
import net.cabezudo.sofia.sic.elements.SICUnexpectedEndOfCodeException;
import net.cabezudo.sofia.sic.exceptions.InvalidParameterException;
import net.cabezudo.sofia.sic.objects.SICObject;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.06.12
 */
public class SofiaImageCode {

  private SICTokens tokens;
  private final String code;
  private Path basePath;
  private SICElement sicElement;

  public SofiaImageCode(Path basePath, String plainCode) {
    this(basePath, plainCode, false);
  }

  public SofiaImageCode(String plainCode, boolean formatCode) {
    this(null, plainCode, formatCode);
  }

  public SofiaImageCode(Path basePath, String plainCode, boolean formatCode) {
    if (basePath == null) {
      String pathName = System.getProperty("java.io.tmpdir");
      this.basePath = Paths.get(pathName);
      if (this.basePath == null || !Files.exists(this.basePath)) {
        pathName = System.getProperty("user.dir");
        this.basePath = Paths.get(pathName);
        if (this.basePath == null || !Files.exists(this.basePath)) {
          pathName = System.getProperty("user.home");
          this.basePath = Paths.get(pathName);
        }
      }
    } else {
      this.basePath = basePath;
    }

    if (plainCode == null) {
      throw new InvalidParameterException("null string parameter.");
    }

    if (plainCode.isBlank()) {
      throw new InvalidParameterException("Empty code.");
    }
    if (formatCode) {
      code = formatCode(plainCode);
    } else {
      code = plainCode;
    }
    tokens = Tokenizer.tokenize(code);
  }

  public SICElement parse() throws SICCompileTimeException, SICUnexpectedEndOfCodeException {
    SICFactory sicFactory = new SICFactory();
    sicElement = sicFactory.get(tokens);
    return sicElement;
  }

  public SICTokens getTokens() {
    return tokens;
  }

  public SICObject compile() throws SICCompileTimeException {
    return sicElement.compile(basePath);
  }

  public String getShortCode() {
    return code;
  }

  public String getFormatedCode() {
    return sicElement.toString(0);
  }

  private String formatCode(String plainCode) {
    StringBuilder cleanCode = cleanCode(plainCode);
    StringBuilder sb = new StringBuilder(cleanCode.length() * 2);
    int tabs = 0;
    for (int i = 0; i < cleanCode.length(); i++) {
      char c = cleanCode.charAt(i);
      if (c == '(') {
        sb.append('(').append('\n');
        tabs++;
        sb.append(getSpaces(tabs));
        continue;
      }
      if (c == ',') {
        sb.append(',').append('\n');
        sb.append(getSpaces(tabs));
        continue;
      }
      if (c == ')') {
        tabs--;
        sb.append('\n').append(getSpaces(tabs)).append(')');
        continue;
      }
      sb.append(c);
    }
    return sb.toString();
  }

  private String getSpaces(int tabs) {
    return Utils.repeat(' ', tabs * 2);
  }

  private StringBuilder cleanCode(String code) {
    StringBuilder sb = new StringBuilder();
    char[] chars = code.toCharArray();
    for (char c : chars) {
      if (c != '\n' && c != ' ' && c != '\t' && c != '\u00A0') {
        sb.append(c);
      }
    }
    return sb;
  }
}
