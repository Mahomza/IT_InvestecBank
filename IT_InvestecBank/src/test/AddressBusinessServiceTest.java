package test;

import business.AddressBusinessService;
import lookup.EAddressType;
import domain.address.Address;
import validator.PreconditionValidator;

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
  private static final String FILE_NAME = "resources/addresses.json";

  /**
   * the main methods to execute test cases
   *
   * @param args the array of strings
   */
  public static void main(String[] args)
  {
    try
    {
      AddressBusinessService addressBusinessService = new AddressBusinessService(FILE_NAME);
      List<Address> addresses = addressBusinessService.fetchAddressesFromJsonContent();

      for (Address address : addresses)
      {
        //validate address from list
        validate(address);

        // Print all addresses.
        String addressLine = prettyPrintAddress(address);
        System.out.println(addressLine);
        System.out.println("---------------------------------------------------");
      }

      // Print all addresses.
      prettyPrintAllAddresses(addresses);

      System.out.println("---------------------------------------------------");

      // Print address for certain type.
      printAddressByType(EAddressType.POSTAL_ADDRESS, addresses);
      printAddressByType(EAddressType.PHYSICAL_ADDRESS, addresses);
      printAddressByType(EAddressType.BUSINESS_ADDRESS, addresses);

      //validate(address);
    }
    catch (Exception e)
    {
      System.out.println("Error: " + e.getMessage());
    }
  }

  /**
   * Operation to create address String in given format : {Type:details-city-province/state-postal code - country}
   *
   * @param address the address to create String from, in above defined format.
   * @return the address line as a String.
   */
  private static String prettyPrintAddress(Address address)
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
  private static void prettyPrintAllAddresses(List<Address> addresses)
  {
    for (Address address : addresses)
    {
      String addressLine = prettyPrintAddress(address);
      System.out.println(addressLine);
    }
  }

  /**
   * Operation to print address for certain type.
   *
   * @param eAddressType the enum for type of address
   * @param addresses    the list of addresses to iterate through while printing
   */
  private static void printAddressByType(EAddressType eAddressType, List<Address> addresses)
  {
    for (Address address : addresses)
    {
      String name = address.getType().getName().toLowerCase();
      boolean printAddress = (name.contains(eAddressType.getName()));
      if (printAddress)
      {
        String addressLine = prettyPrintAddress(address);
        System.out.println(addressLine);
      }
    }
  }

  /**
   * Operation to validate the address.
   *
   * @param address the address to validate
   */
  private static void validate(Address address)
  {
    PreconditionValidator.validateIsNull("PostalCode", address.getPostalCode());
    PreconditionValidator.validateIsNull("CountryName", address.getCountry().getName());
    if (address.getCountry().getCode().equalsIgnoreCase("ZA"))
    {
      PreconditionValidator.validateIsNull("Province", address.getProvinceOrState().getName());
    }
    try
    {
      Long.parseLong(address.getPostalCode());
    }
    catch (NumberFormatException ex)
    {
      System.out.println(ex.getMessage());
    }
  }
}
