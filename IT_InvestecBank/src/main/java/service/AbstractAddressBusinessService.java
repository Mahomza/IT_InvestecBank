package service;


import domain.address.Address;
import fileloader.FileLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * <p>
 * Title: AbstractAddressBusinessService
 * </p>
 * <p>
 * Description: AbstractAddressBusinessService Object
 * </p>
 * <p>
 * Copyright Notice
 * ================
 * This file contains proprietary information of Investec Bank.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2023
 *
 * Company: Investec_Bank
 * </p>
 *
 * @author Alex Mahomana
 * @version 1.0
 */
public abstract class AbstractAddressBusinessService
{
  /**
   * Operation to retrieve addresses from json file
   *
   * @param relativePathToFile the path to the resource
   * @return json content as a String
   */
  public String retrieveAddresses(String relativePathToFile) throws IOException
  {
    StringBuilder json = new StringBuilder();
    Path jsonFilePath = getAbsolutePathOfResource(Address.class, relativePathToFile);

    if (jsonFilePath != null)
    {
      List<String> addressInfoLines = Files.readAllLines(jsonFilePath);
      for (String line : addressInfoLines)
      {
        json.append(line);
      }
    }
    return json.toString();
  }

  /**
   * Gets absolute path of resource file
   *
   * @param className    the class for which we are searching relative to it
   * @param relativePath the path to the resource
   * @return absolute path if found
   */
  protected static Path getAbsolutePathOfResource(Class className, String relativePath)
  {
    FileLoader fileLoader = new FileLoader();
    try
    {
      return Paths.get(fileLoader.getResourceAbsolutePath(className, relativePath));
    }
    catch (Exception e)
    {
      System.out.println(String.format("Error when trying to read resource file '%s'", relativePath));
      return null;
    }
  }
}
