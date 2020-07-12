package net.cabezudo.sofia.sic;

import java.nio.file.Path;
import java.nio.file.Paths;
import net.cabezudo.sofia.sic.elements.SICCompileTimeException;
import net.cabezudo.sofia.sic.objects.SICObject;
import net.cabezudo.sofia.sic.objects.SICRuntimeException;
import org.junit.Test;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.07.10
 */
public class ResizeTest {

  @Test
  public void happyPathResizeImage() throws SICRuntimeException, SICCompileTimeException {
    String code = "main(loadImage(name=/images/test.600.400.jpg),resize(width=60,height=40))";
    run(code);
  }

  private void run(String code) throws SICRuntimeException, SICCompileTimeException {
    Path resourceDirectory = Paths.get("test", "resources");
    String resourcesPath = resourceDirectory.toFile().getAbsolutePath();
    Path basePath = Paths.get(resourcesPath);
    SofiaImageCode sofiaImageCode = new SofiaImageCode(basePath, code, true);
    SICObject object = sofiaImageCode.compile();
    object.run();
  }

}
