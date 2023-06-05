package domain.province;

/**
 * <p>
 * Title: ProvinceOrState
 * </p>
 * <p>
 * Description: ProvinceOrState Object
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
public class ProvinceOrState
{
  private String code;
  private String name;

  /**
   * Get province/state code.
   *
   * @return the province/state code
   */
  public String getCode()
  {
    return code;
  }

  /**
   * Sets province/state code.
   *
   * @param code the province/state code
   */
  public void setCode(String code)
  {
    this.code = code;
  }

  /**
   * Get province/state name.
   *
   * @return the province/state name
   */
  public String getName()
  {
    return name;
  }

  /**
   * Sets province/state name.
   *
   * @param name the province/state name
   */
  public void setName(String name)
  {
    this.name = name;
  }
}
