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
public class GrayTest {

  @Test
  public void happyPathGrayAveraging() throws SICRuntimeException, SICCompileTimeException {
    String code = "main(loadImage(name=/images/test.600.400.jpg),gray(method=averaging))";
    run(code);
  }

  @Test
  public void happyPathGrayLuma() throws SICRuntimeException, SICCompileTimeException {
    String code = "main(loadImage(name=/images/test.600.400.jpg),gray(method=luma))";
    run(code);
  }

  @Test
  public void happyPathGrayLumaBasic() throws SICRuntimeException, SICCompileTimeException {
    String code = "main(loadImage(name=/images/test.600.400.jpg),gray(method=luma,type=basic))";
    run(code);
  }

  @Test
  public void happyPathGrayLumaBT709() throws SICRuntimeException, SICCompileTimeException {
    String code = "main(loadImage(name=/images/test.600.400.jpg),gray(method=luma,type=bt709))";
    run(code);
  }

  @Test
  public void happyPathGrayLumaBT601() throws SICRuntimeException, SICCompileTimeException {
    String code = "main(loadImage(name=/images/test.600.400.jpg),gray(method=luma,type=bt601))";
    run(code);
  }

  @Test
  public void happyPathGrayDesaturation() throws SICRuntimeException, SICCompileTimeException {
    String code = "main(loadImage(name=/images/test.600.400.jpg),gray(method=desaturation))";
    run(code);
  }

  @Test
  public void happyPathGrayDecompositionMaximum() throws SICRuntimeException, SICCompileTimeException {
    String code = "main(loadImage(name=/images/test.600.400.jpg),gray(method=decomposition,type=maximum))";
    run(code);
  }

  @Test
  public void happyPathGrayDecompositionMinimum() throws SICRuntimeException, SICCompileTimeException {
    String code = "main(loadImage(name=/images/test.600.400.jpg),gray(method=decomposition,type=minimum))";
    run(code);
  }

  @Test
  public void happyPathGrayColorChannelRed() throws SICRuntimeException, SICCompileTimeException {
    String code = "main(loadImage(name=/images/test.600.400.jpg),gray(method=colorChannel,channel=red))";
    run(code);
  }

  @Test
  public void happyPathGrayColorChannelGreen() throws SICRuntimeException, SICCompileTimeException {
    String code = "main(loadImage(name=/images/test.600.400.jpg),gray(method=colorChannel,channel=green))";
    run(code);
  }

  @Test
  public void happyPathGrayColorChannelBlue() throws SICRuntimeException, SICCompileTimeException {
    String code = "main(loadImage(name=/images/test.600.400.jpg),gray(method=colorChannel,channel=blue))";
    run(code);
  }

  @Test(expected = SICCompileTimeException.class)
  public void grayColorChannelWhitoutChannelParameter() throws SICRuntimeException, SICCompileTimeException {
    String code = "main(loadImage(name=/images/test.600.400.jpg),gray(method=colorChannel))";
    run(code);
  }

  @Test
  public void happyPathGrayGrayShades() throws SICRuntimeException, SICCompileTimeException {
    String code = "main(loadImage(name=/images/test.600.400.jpg),gray(method=grayShades,value=30))";
    run(code);
  }

  @Test(expected = SICCompileTimeException.class)
  public void grayUnexpectedFunction() throws SICRuntimeException, SICCompileTimeException {
    String code = "main(loadImage(name=/images/test.600.400.jpg),gray(gray()))";
    run(code);
  }

  @Test(expected = SICCompileTimeException.class)
  public void grayUnexpectedParameter() throws SICRuntimeException, SICCompileTimeException {
    String code = "main(loadImage(name=/images/test.600.400.jpg),gray(width=3))";
    run(code);
  }

  @Test(expected = SICCompileTimeException.class)
  public void grayGrayShadesWhitoutValue() throws SICRuntimeException, SICCompileTimeException {
    String code = "main(loadImage(name=/images/test.600.400.jpg),gray(method=grayShades))";
    run(code);
  }

  private void run(String code) throws SICRuntimeException, SICCompileTimeException {
    Path resourceDirectory = Paths.get("src", "test", "resources");
    String resourcesPath = resourceDirectory.toFile().getAbsolutePath();
    Path basePath = Paths.get(resourcesPath);
    SofiaImageCode sofiaImageCode = new SofiaImageCode(basePath, code, true);
    SICObject object = sofiaImageCode.compile();
    object.run();
  }

}
