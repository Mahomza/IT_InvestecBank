package domain.address;

import domain.country.Country;
import domain.province.ProvinceOrState;
import domain.address.type.Type;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Title: Address
 * </p>
 * <p>
 * Description: Address Object
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
public class Address implements Serializable
{
  private String id;
  private Type type;
  private AddressLineDetail addressLineDetail;
  private ProvinceOrState provinceOrState;
  private String cityOrTown;
  private Country country;
  private String postalCode;
  private Date lastUpdated;
  private String suburbOrDistrict;

  /**
   * Get the address id.
   *
   * @return the address id
   */
  public String getId()
  {
    return id;
  }

  /**
   * Sets the address id.
   *
   * @param id the address id
   */
  public void setId(String id)
  {
    this.id = id;
  }

  /**
   * Get the address type.
   *
   * @return the address type
   */
  public Type getType()
  {
    return type;
  }

  /**
   * Sets the address type.
   *
   * @param type the address type
   */
  public void setType(Type type)
  {
    this.type = type;
  }

  /**
   * Get the address line detail.
   *
   * @return the address line detail
   */
  public AddressLineDetail getAddressLineDetail()
  {
    return addressLineDetail;
  }

  /**
   * Sets the address line detail.
   *
   * @param addressLineDetail the address line detail
   */
  public void setAddressLineDetail(AddressLineDetail addressLineDetail)
  {
    this.addressLineDetail = addressLineDetail;
  }

  /**
   * Get the province or state.
   *
   * @return the province or state
   */
  public ProvinceOrState getProvinceOrState()
  {
    return provinceOrState;
  }

  /**
   * Sets the province or state.
   *
   * @param provinceOrState the province or state
   */
  public void setProvinceOrState(ProvinceOrState provinceOrState)
  {
    this.provinceOrState = provinceOrState;
  }

  /**
   * Get the city or town.
   *
   * @return the city or town
   */

  public String getCityOrTown()
  {
    return cityOrTown;
  }

  /**
   * Sets the city or town.
   *
   * @param cityOrTown the city or town
   */
  public void setCityOrTown(String cityOrTown)
  {
    this.cityOrTown = cityOrTown;
  }

  /**
   * Get the country.
   *
   * @return the country
   */
  public Country getCountry()
  {
    return country;
  }

  /**
   * Sets the country.
   *
   * @param country the country
   */
  public void setCountry(Country country)
  {
    this.country = country;
  }

  /**
   * Get the postal code of address.
   *
   * @return the postal code
   */
  public String getPostalCode()
  {
    return postalCode;
  }

  /**
   * Sets the postal code of address.
   *
   * @param postalCode the postal code
   */
  public void setPostalCode(String postalCode)
  {
    this.postalCode = postalCode;
  }

  /**
   * Get the last updated date of address.
   *
   * @return the last updated date
   */
  public Date getLastUpdated()
  {
    return lastUpdated;
  }

  /**
   * Sets the last updated date of address.
   *
   * @param lastUpdated the last updated date
   */
  public void setLastUpdated(Date lastUpdated)
  {
    this.lastUpdated = lastUpdated;
  }

  /**
   * Get the suburb or district of address.
   *
   * @return the suburb or district
   */
  public String getSuburbOrDistrict()
  {
    return suburbOrDistrict;
  }

  /**
   * Sets the suburb or district of address.
   *
   * @param suburbOrDistrict the suburb or district
   */
  public void setSuburbOrDistrict(String suburbOrDistrict)
  {
    this.suburbOrDistrict = suburbOrDistrict;
  }
}
