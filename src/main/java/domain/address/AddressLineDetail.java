package domain.address;

/**
 * <p>
 * Title: AddressLineDetail
 * </p>
 * <p>
 * Description: AddressLineDetail Object
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
public class AddressLineDetail
{
  private String line1;
  private String line2;

  /**
   * Get address line 1.
   *
   * @return address line 1
   */
  public String getLine1()
  {
    return line1;
  }

  /**
   * Sets address line 1.
   *
   * @param line1 address line 1.
   */
  public void setLine1(String line1)
  {
    this.line1 = line1;
  }

  /**
   * Get address line 2.
   *
   * @return address line 2.
   */
  public String getLine2()
  {
    return line2;
  }

  /**
   * Sets address line 2
   *
   * @param line2 address line 2.
   */
  public void setLine2(String line2)
  {
    this.line2 = line2;
  }
}
