package domain.address.type;

/**
 * <p>
 * Title: Type
 * </p>
 * <p>
 * Description: Type Object
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
public class Type
{
  private String code;
  private String name;

  /**
   * Sets type code.
   *
   * @return the type code
   */
  public String getCode()
  {
    return code;
  }

  /**
   * Sets type code.
   *
   * @param code the type name
   */
  public void setCode(String code)
  {
    this.code = code;
  }

  /**
   * Get type name.
   *
   * @return the type name
   */
  public String getName()
  {
    return name;
  }

  /**
   * Sets type name.
   *
   * @param name the type name
   */
  public void setName(String name)
  {
    this.name = name;
  }
}
