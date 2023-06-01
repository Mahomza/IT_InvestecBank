package business;

import domain.address.Address;
import domain.address.AddressLineDetail;
import domain.address.type.Type;
import domain.country.Country;
import domain.province.ProvinceOrState;
import json.JSONHolder;
import json.JSONMapper;
import service.AbstractAddressBusinessService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: AddressBusinessService
 * </p>
 * <p>
 * Description: AddressBusinessService Object
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
public class AddressBusinessService extends AbstractAddressBusinessService
{
  private final String fileName;
  private List<Address> addresses;

  /**
   * The Constructor to instantiate AddressBusinessService
   *
   * @param fileName the name of json file to read data from.
   */
  public AddressBusinessService(String fileName)
  {
    this.fileName = fileName;
  }

  /**
   * Fetch the list of addresses from Json content.
   *
   * @return the list of addresses
   */
  public List<Address> fetchAddressesFromJsonContent() throws IOException
  {
    addresses = new ArrayList<>();
    Address addressObj = new Address();
    JSONMapper jsonMapper = new JSONMapper();
    String json = getJsonContent();
    json = json.replace('[', ' ').replace(']', ' ');
    List<JSONHolder> jsonHolderList = jsonMapper.parseJSONStr(json);

    int count = 0;
    for (JSONHolder jsonHolder : jsonHolderList)
    {
      count = jsonHolder.getDataMap().entrySet().size();

      for (Map.Entry<String, JSONHolder> entry : jsonHolder.getDataMap().entrySet())
      {
        count--;
        if (entry.getKey().equalsIgnoreCase("id"))
        {
          addressObj.setId(entry.getValue().getValue());
        }
        if (entry.getKey().equalsIgnoreCase("type"))
        {
          Type type = new Type();
          type.setCode(entry.getValue().getDataMap().get("code").getValue());
          type.setName(entry.getValue().getDataMap().get("name").getValue());
          addressObj.setType(type);
        }
        if (entry.getKey().equalsIgnoreCase("addressLineDetail"))
        {
          AddressLineDetail addressLineDetail = new AddressLineDetail();
          addressLineDetail.setLine1(entry.getValue().getDataMap().get("line1").getValue());
          addressLineDetail.setLine2(entry.getValue().getDataMap().get("line2").getValue());
          addressObj.setAddressLineDetail(addressLineDetail);
        }
        if (entry.getKey().equalsIgnoreCase("provinceOrState"))
        {
          ProvinceOrState provinceOrState = new ProvinceOrState();
          provinceOrState.setCode(entry.getValue().getDataMap().get("code").getValue());
          provinceOrState.setName(entry.getValue().getDataMap().get("name").getValue());
          addressObj.setProvinceOrState(provinceOrState);
        }
        if (entry.getKey().equalsIgnoreCase("suburbOrDistrict"))
        {
          addressObj.setSuburbOrDistrict(entry.getValue().getValue());
        }
        if (entry.getKey().equalsIgnoreCase("cityOrTown"))
        {
          addressObj.setCityOrTown(entry.getValue().getValue());
        }
        if (entry.getKey().equalsIgnoreCase("country"))
        {
          Country country = new Country();
          country.setCode(entry.getValue().getDataMap().get("code").getValue());
          country.setName(entry.getValue().getDataMap().get("name").getValue());
          addressObj.setCountry(country);
        }
        if (entry.getKey().equalsIgnoreCase("postalCode"))
        {
          addressObj.setPostalCode(entry.getValue().getValue());
        }
        if (entry.getKey().equalsIgnoreCase("lastUpdated"))
        {
          Date lastUpdated;
          try
          {
            lastUpdated = new SimpleDateFormat("yyyy-MM-dd").parse(entry.getValue().getValue());
          }
          catch (ParseException e)
          {
            throw new RuntimeException(e);
          }
          addressObj.setLastUpdated(lastUpdated);
        }
        if (addressObj != null && count < 1)
        {
          addresses.add(addressObj);
          addressObj = new Address();
          count = 0;
        }
      }
    }
    return addresses;
  }

  /**
   * Operation to read {addresses.json} file and return json String.
   *
   * @return the json content as a String
   */
  public String getJsonContent() throws IOException
  {
    String json = retrieveAddresses(fileName);
    return json;
  }
}
