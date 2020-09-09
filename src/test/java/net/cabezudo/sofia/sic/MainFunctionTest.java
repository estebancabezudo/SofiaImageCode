package net.cabezudo.sofia.sic;

import java.nio.file.Path;
import java.nio.file.Paths;
import net.cabezudo.sofia.sic.elements.SICCompileTimeException;
import net.cabezudo.sofia.sic.elements.SICUnexpectedEndOfCodeException;
import net.cabezudo.sofia.sic.objects.SICObject;
import net.cabezudo.sofia.sic.objects.SICRuntimeException;
import org.junit.Test;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.07.10
 */
public class MainFunctionTest {

  @Test(expected = SICCompileTimeException.class)
  public void happyPathResizeImage() throws SICRuntimeException, SICCompileTimeException, SICUnexpectedEndOfCodeException {
    String code = "main(height=40)";
    run(code);
  }

  private void run(String code) throws SICRuntimeException, SICCompileTimeException, SICUnexpectedEndOfCodeException {
    Path resourceDirectory = Paths.get("src", "test", "resources");
    String resourcesPath = resourceDirectory.toFile().getAbsolutePath();
    Path basePath = Paths.get(resourcesPath);
    SofiaImageCode sofiaImageCode = new SofiaImageCode(basePath, code, true);
    sofiaImageCode.parse();
    SICObject object = sofiaImageCode.compile();
    object.run();
  }
}
