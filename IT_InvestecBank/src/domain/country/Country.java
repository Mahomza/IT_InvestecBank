package domain.country;
/**
 * <p>
 * Title: Country
 * </p>
 * <p>
 * Description: Country Object
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
public class Country
{
  private String code;
  private String name;

  /**
   * Get the country code
   *
   * @return  the country code
   */
  public String getCode()
  {
    return code;
  }
  /**
   * Sets country code.
   *
   * @param code the country code
   */
  public void setCode(String code)
  {
    this.code = code;
  }

  /**
   * Get country name.
   *
   * @return  the country name
   */
  public String getName()
  {
    return name;
  }
  /**
   * Sets country name.
   *
   * @param name the country name
   */
  public void setName(String name)
  {
    this.name = name;
  }
}
