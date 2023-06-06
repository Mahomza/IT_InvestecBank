package json;


import lookup.EValueObject;
import resources.IMapperConstant;
import validator.PreconditionValidator;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * <p>Title: JSONHolder</p>
 *
 * <p>Description: JSONHolder</p>
 *
 * <p>Copyright: Copyright (c) 2023</p>
 *
 * <p> Company: Investec_Bank</p>.
 *
 * @author Alex Mahomana
 * @version 1.0
 */
public class JSONHolder implements Serializable
{
  private static final long serialVersionUID = 1L;

  private String value;
  private Map<String, JSONHolder> dataMap;

  /**
   * Instantiates a new JSON holder.
   *
   * @param dataMap the data map
   */
  public JSONHolder(Map<String, JSONHolder> dataMap)
  {
    this.dataMap = dataMap;
  }

  /**
   * Instantiates a new JSON holder.
   *
   * @param value the value
   */
  public JSONHolder(String value)
  {
    this();
    this.value = value;
  }

  /**
   * Instantiates a new JSON holder.
   */
  public JSONHolder()
  {
    dataMap = new HashMap<>();
  }

  /**
   * Checks if is value.
   *
   * @return true, if is value
   */
  public boolean isValue()
  {
    return value != null && (dataMap == null || dataMap.isEmpty());
  }

  /**
   * Checks if is nested data.
   *
   * @return true, if is nested data
   */
  public boolean isNestedData()
  {
    return !isValue();
  }

  /**
   * Checks is the JSON object has or empty properties.
   *
   * @return the boolean
   */
  public boolean hasNullOrEmptyProperties()
  {
    return (dataMap == null || dataMap.isEmpty()) && (value == null || value.isEmpty());
  }

  /**
   * Gets the data map.
   *
   * @return the data map
   */
  public Map<String, JSONHolder> getDataMap()
  {
    if (dataMap == null)
    {
      dataMap = new HashMap<>();
    }
    return Collections.unmodifiableMap(dataMap);
  }

  /**
   * Gets data map.
   *
   * @param <T>          the type parameter
   * @param remappingKey the remapping key
   * @return the data map
   */
  public <T> Map<T, JSONHolder> getDataMap(Function<String, T> remappingKey)
  {
    if (dataMap == null)
    {
      return new HashMap<>();
    }
    else
    {
      Map<T, JSONHolder> map = new HashMap<T, JSONHolder>();
      for (Entry<String, JSONHolder> entry : dataMap.entrySet())
      {
        map.put(remappingKey.apply(entry.getKey()), entry.getValue());
      }
      return map;
    }
  }

  /**
   * Gets data map.
   *
   * @param <K>            the type parameter
   * @param <V>            the type parameter
   * @param remappingKey   the remapping key
   * @param remappingValue the remapping value
   * @return the data map
   */
  public <K, V> Map<K, V> getDataMap(Function<String, K> remappingKey, Function<JSONHolder, V> remappingValue)
  {
    if (dataMap == null)
    {
      return new HashMap<>();
    }
    else
    {
      Map<K, V> map = new HashMap<>();
      for (Entry<String, JSONHolder> entry : dataMap.entrySet())
      {
        map.put(remappingKey.apply(entry.getKey()), remappingValue.apply(entry.getValue()));
      }
      return map;
    }
  }

  /**
   * Gets the values only map.
   *
   * @return the values only map
   */
  public Map<String, String> getValuesOnlyMap()
  {
    return getValuesOnlyMap(String::toString, String::toString);
  }

  /**
   * Gets the values only map.
   *
   * @param <K>                   the key type
   * @param <V>                   the value type
   * @param remappingKeyFuction   the remapping key fuction
   * @param remappingValueFuction the remapping value fuction
   * @return the values only map
   */
  public <K, V> Map<K, V> getValuesOnlyMap(Function<String, K> remappingKeyFuction, Function<String, V> remappingValueFuction)
  {
    return getDataMap().entrySet().stream()
            .filter(es -> es.getValue().isValue())
            .collect(Collectors.toMap(es -> remappingKeyFuction.apply(es.getKey()), es -> remappingValueFuction.apply(es.getValue().getValue())));
  }

  /**
   * Gets the values only remapping key.
   *
   * @param <K>                 the key type
   * @param remappingKeyFuction the remapping key fuction
   * @return the values only remapping key
   */
  public <K> Map<K, String> getValuesOnlyRemappingKey(Function<String, K> remappingKeyFuction)
  {
    return getValuesOnlyMap(remappingKeyFuction, String::toString);
  }

  /**
   * Gets the values only remapping value.
   *
   * @param <V>                   the value type
   * @param remappingValueFuction the remapping value fuction
   * @return the values only remapping value
   */
  public <V> Map<String, V> getValuesOnlyRemappingValue(Function<String, V> remappingValueFuction)
  {
    return getValuesOnlyMap(String::toString, remappingValueFuction);
  }

  /**
   * Gets the value.
   *
   * @return the value
   */
  public String getValue()
  {
    return value;
  }

  /**
   * Gets the value for property.
   *
   * @param <V>             the type parameter
   * @param property        the property
   * @param mappingFunction the mapping function
   * @return the value for property
   */
  public <V> Optional<V> getValueForProperty(String property, Function<String, V> mappingFunction)
  {
    PreconditionValidator.validateIsNull("property", property);
    if (isNestedData())
    {
      JSONHolder jsonHolder = getDataMap().get(property);
      if (jsonHolder != null)
      {
        if (jsonHolder.isValue())
        {
          return Optional.of(mappingFunction.apply(jsonHolder.getValue()));
        }
        else
        {
          throw new IllegalArgumentException("The property requested is not a value, call getNestedDataFor instead");
        }
      }
      else
      {
        return Optional.empty();
      }
    }
    else
    {
      throw new IllegalArgumentException("This level does not have nested data");
    }
  }

  public <V> Optional<V> getValueForProperty(IMapperConstant mapperConstant, Function<String, V> mappingFunction)
  {
    return getValueForProperty(mapperConstant.getDesc(), mappingFunction);
  }

  /**
   * Gets the value for property.
   *
   * @param property the property
   * @return the value for property
   */
  public Optional<String> getValueForProperty(String property)
  {
    return getValueForProperty(property, String::toString);
  }

  public Optional<String> getValueForProperty(IMapperConstant mapperConstant)
  {
    return getValueForProperty(mapperConstant.getDesc(), String::toString);
  }

  /**
   * Gets the nested data for.
   *
   * @param identifier                the identifier
   * @param notFoundExceptionSupplier the not found exception supplier
   * @return the nested data for the provided identifier a nullable supplier can be provided that will raise an exception if the data is not found. If the supplier is null the method will simply return an empty JSONHolder
   */
  public JSONHolder getNestedDataFor(String identifier, Supplier<? extends RuntimeException> notFoundExceptionSupplier)
  {
    PreconditionValidator.validateIsNull("identifier", identifier);
    if (isNestedData())
    {
      JSONHolder jsonHolder = getDataMap().get(identifier);
      if (jsonHolder != null)
      {
        return jsonHolder;
      }
      else if (notFoundExceptionSupplier == null)
      {
        return new JSONHolder();
      }
      else
      {
        throw notFoundExceptionSupplier.get();
      }
    }
    else
    {
      throw new IllegalArgumentException("The data requested is a value, call getValueForProperty instead");
    }
  }

  public JSONHolder getNestedDataFor(IMapperConstant mapperConstant, Supplier<? extends RuntimeException> notFoundExceptionSupplier)
  {
    return getNestedDataFor(mapperConstant.getDesc(), notFoundExceptionSupplier);
  }

  /**
   * Gets mandatory nested data for.
   *
   * @param identifier the identifier
   * @return the mandatory nested data for
   */
  public JSONHolder getMandatoryNestedDataFor(String identifier)
  {
    return getNestedDataFor(identifier, () -> new IllegalArgumentException(String.format("The data requested ,%s, cannot be found", identifier)));
  }

  public JSONHolder getMandatoryNestedDataFor(IMapperConstant mapperConstant)
  {
    return getMandatoryNestedDataFor(mapperConstant.getDesc());
  }

  /**
   * Gets nested data for.
   *
   * @param identifier the identifier
   * @return the nested data for
   */
  public JSONHolder getNestedDataFor(String identifier)
  {
    return getNestedDataFor(identifier, null);
  }

  public JSONHolder getNestedDataFor(IMapperConstant mapperConstant)
  {
    return getNestedDataFor(mapperConstant.getDesc(), null);
  }

  /**
   * Adds the value.
   *
   * @param identifier the identifier
   * @param value      the value
   * @return the json holder
   */
  public JSONHolder addValue(String identifier, String value)
  {
    this.dataMap.put(identifier, new JSONHolder(value));
    return this;
  }

  public JSONHolder addValue(IMapperConstant mapperConstant, String value)
  {
    return addValue(mapperConstant.getDesc(), value);
  }

  /**
   * Add value json holder.
   *
   * @param identifier the identifier
   * @param value      the value
   * @return the json holder
   */
  public JSONHolder addValue(String identifier, Object value)
  {
    this.dataMap.put(identifier, new JSONHolder(value.toString()));
    return this;
  }

  /**
   * Add value json holder.
   *
   * @param mapperConstant the identifier
   * @param value          the value
   * @return the json holder
   */
  public JSONHolder addValue(IMapperConstant mapperConstant, Object value)
  {
    return addValue(mapperConstant.getDesc(), value);
  }

  public JSONHolder addValue(IMapperConstant mapperConstant, Date date)
  {
    DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    return addValue(mapperConstant, dateFormatter.format(date));
  }

  /**
   * Add value json holder.
   *
   * @param identifier the identifier
   * @param value      the value
   * @return the json holder
   */
  public JSONHolder addValue(Object identifier, Object value)
  {
    JSONHolder objectValue = JSONHolder.class.isAssignableFrom(value.getClass()) ? (JSONHolder) value : new JSONHolder(value.toString());
    this.dataMap.put(identifier.toString(), objectValue);
    return this;
  }

  /**
   * Add non-null values
   *
   * @param identifier the identifier
   * @param value      value to add
   * @return the json holder
   */
  public JSONHolder addNonNullValue(Object identifier, Object value)
  {
    boolean isNull = value instanceof Short || value instanceof Integer || value instanceof Long
                     ? value.equals(EValueObject.NULL_PRIMITIVE.getDefaultValueCd())
                     : value == null || (JSONHolder.class.isAssignableFrom(value.getClass()) && ((JSONHolder) value).hasNullOrEmptyProperties());
    if (!isNull)
    {
      addValue(identifier, value);
    }
    return this;
  }

  /**
   * Add value if changed
   *
   * @param identifier the identifier
   * @param value      value to add
   * @return the json holder
   */
  public JSONHolder addValueIfChanged(Object identifier, Object value, Object compareValueWith)
  {
    boolean hasChanged = value == null ? compareValueWith != null : !value.equals(compareValueWith);
    if (hasChanged)
    {
      addValue(identifier, value);
    }
    return this;
  }

  /**
   * Adds the nested data.
   *
   * @param identifier the identifier
   * @param jsonHolder the json holder
   * @return the json holder
   */
  public static JSONHolder createWithValue(String identifier, JSONHolder jsonHolder)
  {
    return new JSONHolder().addValue(identifier, jsonHolder);
  }

  public static JSONHolder createWithValue(IMapperConstant mapperConstant, JSONHolder jsonHolder)
  {
    return createWithValue(mapperConstant.getDesc(), jsonHolder);
  }

  /**
   * Create with value json holder.
   *
   * @param identifier the identifier
   * @param jsonHolder the json holder
   * @return the json holder
   */
  public static JSONHolder createWithValue(Object identifier, JSONHolder jsonHolder)
  {
    return new JSONHolder().addValue(identifier.toString(), jsonHolder);
  }

  /**
   * Create with value json holder.
   *
   * @param identifier the identifier
   * @param value      the value
   * @return the json holder
   */
  public static JSONHolder createWithValue(String identifier, String value)
  {
    return new JSONHolder().addValue(identifier, value);
  }

  public static JSONHolder createWithValue(IMapperConstant mapperConstant, String value)
  {
    return createWithValue(mapperConstant.getDesc(), value);
  }

  /**
   * Create with value json holder.
   *
   * @param identifier the identifier
   * @param value      the value
   * @return the json holder
   */
  public static JSONHolder createWithValue(Object identifier, String value)
  {
    return new JSONHolder().addValue(identifier.toString(), value);
  }

  /**
   * Create with value json holder.
   *
   * @param identifier the identifier
   * @param value      the value
   * @return the json holder
   */
  public static JSONHolder createWithValue(Object identifier, Object value)
  {
    return new JSONHolder().addValue(identifier.toString(), value.toString());
  }

  /**
   * Create with value json holder.
   *
   * @param identifier the identifier
   * @param value      the value
   * @return the json holder
   */
  public static JSONHolder createWithValue(String identifier, Object value)
  {
    return new JSONHolder().addValue(identifier, value.toString());
  }

  public static JSONHolder createWithValue(IMapperConstant mapperConstant, Object value)
  {
    return new JSONHolder().addValue(mapperConstant.getDesc(), value.toString());
  }

  /**
   * Adds the nested data.
   *
   * @param identifier the identifier
   * @param jsonHolder the json holder
   * @return the json holder
   */
  public JSONHolder addValue(String identifier, JSONHolder jsonHolder)
  {
    this.dataMap.put(identifier, jsonHolder);
    return this;
  }

  public JSONHolder addValue(IMapperConstant mapperConstant, JSONHolder jsonHolder)
  {
    return addValue(mapperConstant.getDesc(), jsonHolder);
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    return new JSONMapper().toJSON(this);
  }

}
