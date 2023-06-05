package fileloader;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * <p>Title: FileLoader</p>
 * <p/>
 * <p>Description: provides functions to get file paths or load files</p>
 * <p/>
 * <p>Copyright: Copyright (c) 2023</p>
 * <p/>
 * <p>Company: Investec_Bank</p>
 *
 * @author Alex Mahomana
 * @version 1.0
 */
public class FileLoader
{
  /**
   * Returns the absolute path of resource
   *
   * @param className            the class for which to search resources relative to it
   * @param relativeResourcePath the relative source path of where the resource can be found relative to the class provided
   * @return absolute path string if found
   * @throws URISyntaxException if there is a syntax exception
   */
  public String getResourceAbsolutePath(Class className, String relativeResourcePath) throws URISyntaxException
  {
    URL url = className.getClassLoader().getResource(relativeResourcePath);
    return Paths.get(url.toURI()).toFile().getAbsolutePath();
  }
}
