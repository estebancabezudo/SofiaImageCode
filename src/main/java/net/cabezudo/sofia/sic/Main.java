package net.cabezudo.sofia.sic;

import java.nio.file.Path;
import java.nio.file.Paths;
import net.cabezudo.sofia.sic.elements.SICCompileTimeException;
import net.cabezudo.sofia.sic.elements.SICUnexpectedEndOfCodeException;
import net.cabezudo.sofia.sic.exceptions.EmptyQueueException;
import net.cabezudo.sofia.sic.objects.SICObject;
import net.cabezudo.sofia.sic.objects.SICRuntimeException;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.06.13
 */
public class Main {

  public static void main(String... args) throws EmptyQueueException, SICCompileTimeException, SICUnexpectedEndOfCodeException, SICRuntimeException {
    test("main(\n    loadImage(\n    name=/images/test.600.400.jpg\n  ),\n  resize(\n    width=300,\n    height=200\n  )\n)\n");
  }

  private static void test(String code) throws SICRuntimeException, SICCompileTimeException, SICUnexpectedEndOfCodeException {
    Path resourceDirectory = Paths.get("src", "test", "resources");

    String resourcesPath = resourceDirectory.toFile().getAbsolutePath();
    Path basePath = Paths.get(resourcesPath);
    SofiaImageCode sofiaImageCode = new SofiaImageCode(basePath, code, true);
    sofiaImageCode.parse();
    SICObject object = sofiaImageCode.compile();
    object.run();
  }
}
