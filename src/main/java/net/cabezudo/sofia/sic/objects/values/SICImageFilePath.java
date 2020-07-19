package net.cabezudo.sofia.sic.objects.values;

import java.nio.file.Files;
import java.nio.file.Path;
import net.cabezudo.sofia.logger.Logger;
import net.cabezudo.sofia.sic.elements.SICCompileTimeException;
import net.cabezudo.sofia.sic.exceptions.SICRuntimeException;
import net.cabezudo.sofia.sic.tokens.SICToken;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.01.00, 2020.06.14
 */
public class SICImageFilePath extends SICValue<Path> {

  private Path filePath;

  public SICImageFilePath(Path basePath, SICToken token) throws SICCompileTimeException {
    super(token);
    String imageFileName = getToken().getValue();
    if (basePath == null) {
      throw new SICRuntimeException("The image base path IS NOT defined.");
    }
    String newImageFileName = imageFileName; // Just for show the old path in the error.
    while (newImageFileName.startsWith("/")) {
      Logger.warning("imageFileName start with slash character");
      newImageFileName = newImageFileName.substring(1);
    }
    filePath = basePath.resolve(newImageFileName);
    Logger.info("File to search: %s.", filePath);
    if (!Files.exists(filePath)) {
      throw new SICCompileTimeException("The file " + imageFileName + " do NOT exist.", getToken());
    }
  }

  @Override
  public String getTypeName() {
    return "imagePath";
  }

  @Override
  public boolean isImageFilePath() {
    return true;
  }

  @Override
  public Path getValue() {
    return filePath;
  }
}
