package lookup;

/**
 * <p>
 * Title: EAddressType
 * </p>
 * <p>
 * <p>
 * Description: EAddressType
 * </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2023
 * </p>
 * <p>
 * <p>
 * Company: Investec_Bank
 * </p>
 *
 * @author Alex Mahomana
 * @version 1.0
 */
public enum EAddressType
{
  POSTAL_ADDRESS("postal"),
  PHYSICAL_ADDRESS("physical"),
  BUSINESS_ADDRESS("business");
  private String name;

  /**
   * Constructor for EAddressType
   *
   * @param name the name of address type
   */
  EAddressType(String name)
  {
    this.name = name;
  }

  /**
   * Operation to get the type name
   *
   * @return the name of address type
   */
  public String getName()
  {
    return name;
  }
}
