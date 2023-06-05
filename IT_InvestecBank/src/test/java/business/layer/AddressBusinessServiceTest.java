package business.layer;

import domain.address.Address;
import lookup.EAddressType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import validator.PreconditionValidator;

import java.io.IOException;
import java.util.List;

/**
 * <p>Title: AddressBusinessServiceTest</p>
 * <p>Description: The test class for AddressBusinessService
 * - Reading addresses from json file.
 * </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Investec_Bank</p>
 *
 * @author Alex Mahomana
 * @version 1.0
 */
public class AddressBusinessServiceTest
{
  private static final String FILE_NAME = "main/java/resources/addresses.json";
  private List<Address> addresses;

  @Before
  public void fetchAddressesFromJsonContent() throws IOException
  {
    AddressBusinessService addressBusinessService = new AddressBusinessService(FILE_NAME);
    addresses = addressBusinessService.fetchAddressesFromJsonContent();
  }

  @Test
  public void testShouldPassIfAddressListIsNotEmptyOrNull()
  {
    //Print all addresses
    prettyPrintAllAddresses(addresses);
    //Assert Not Null
    Assert.assertNotNull(addresses);
    //Assert True if address list size is greater than ZERO
    Assert.assertTrue(addresses.size() > 0);
  }

  @Test
  public void testShouldPassIfAddressByTypeFound()
  {
    EAddressType addressType = EAddressType.PHYSICAL_ADDRESS;
    String addressLine = findAddressByType(addressType, addresses);
    System.out.println(addressLine);
    //Assert Not Null for Address
    Assert.assertNotNull(addressLine);
  }

  /**
   * Operation to create address String in given format : {Type:details-city-province/state-postal code - country}
   *
   * @param address the address to create String from, in above defined format.
   * @return the address line as a String.
   */
  private String prettyPrintAddress(Address address)
  {
    String addressLine =
            String.format("Type: %s - Line Details : %s - City : %s - Province/State : %s - Postal Code : %s - Country : %s",
                    address.getType().getName(),
                    address.getAddressLineDetail().getLine1(),
                    address.getCityOrTown(),
                    address.getProvinceOrState().getName(),
                    address.getPostalCode(),
                    address.getCountry().getName());
    return addressLine;
  }

  /**
   * Operation to print all addresses from the given list
   *
   * @param addresses the list of addresses to iterate through while printing
   */
  private void prettyPrintAllAddresses(List<Address> addresses)
  {
    for (Address address : addresses)
    {
      try
      {
        validate(address);
        String addressLine = prettyPrintAddress(address);
        System.out.println(addressLine);
      }
      catch (Exception e)
      {
        System.out.println(e.getMessage());
      }
    }
  }

  /**
   * Operation to print address for certain type.
   *
   * @param eAddressType the enum for type of address
   * @param addresses    the list of addresses to iterate through while printing
   * @return the address or validation error message as String
   */
  private String findAddressByType(EAddressType eAddressType, List<Address> addresses)
  {
    for (Address address : addresses)
    {
      String name = address.getType().getName().toLowerCase();
      boolean printAddress = (name.contains(eAddressType.getName()));
      if (printAddress)
      {
        try
        {
          validate(address);
          String addressLine = prettyPrintAddress(address);
          return addressLine;
        }
        catch (Exception e)
        {
          return e.getMessage();
        }
      }
    }
    return null;
  }

  /**
   * Operation to validate the address.
   *
   * @param address the address to validate
   */
  private void validate(Address address)
  {
    PreconditionValidator.validateIsNull("PostalCode", address.getPostalCode());
    PreconditionValidator.validateIsNull("CountryName", address.getCountry().getName());
    PreconditionValidator.validateIsNull("address.getAddressLineDetail()", address.getAddressLineDetail());
    //Ensure that one between address line1 or line2 is not null nor empty
    if ((address.getAddressLineDetail().getLine1() == null ||
         address.getAddressLineDetail().getLine1().isEmpty()) &&
        (address.getAddressLineDetail().getLine2() == null ||
         address.getAddressLineDetail().getLine2().isEmpty()))
    {
      throw new IllegalStateException("Invalid address - please make sure one address line is not null.");
    }
    //If Country code is "ZA" ensure that province or state is not null
    if (address.getCountry().getCode().equalsIgnoreCase("ZA"))
    {
      PreconditionValidator.validateIsNull("Province", address.getProvinceOrState().getName());
    }
    //Ensuring that postal code is numeric -  NumberFormatException will be thrown if contains any character.
    Long.parseLong(address.getPostalCode());
  }
}