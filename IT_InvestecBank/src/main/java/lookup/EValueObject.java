package lookup;

/**
 * <p>Title: EValueObject</p>
 * <p>Description: Enum describing the the default short value, this value is typically assigned to all short values and default int or long values to identify an invalid or non captured number value.</p>
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Investec_Bank</p>
 *
 * @author Alex Mahomana
 * @version 1.0
 */
public enum EValueObject
{
  NULL_PRIMITIVE((short) -1);

  private final short defaultValueCd;

  /**
   * EValueObject
   *
   * @param defaultValueCd
   */
  private EValueObject(short defaultValueCd)
  {
    this.defaultValueCd = defaultValueCd;
  }

  /**
   * getDefaultValueCd
   *
   * @return short
   */
  public short getDefaultValueCd()
  {
    return defaultValueCd;
  }

  /**
   * Helper method EValueObject.NULL_PRIMITIVE.isDefaultValue(value)
   */
  public boolean isDefaultValue(short value)
  {
    return this.defaultValueCd == value;
  }

  /**
   * Helper method EValueObject.NULL_PRIMITIVE.isDefaultValue(value)
   */
  public boolean isDefaultValue(int value)
  {
    return this.defaultValueCd == value;
  }

  /**
   * Helper method EValueObject.NULL_PRIMITIVE.isDefaultValue(value)
   */
  public boolean isDefaultValue(long value)
  {
    return this.defaultValueCd == value;
  }

  public long valueFor(Long value)
  {
    if (value == null)
    {
      return NULL_PRIMITIVE.getDefaultValueCd();
    }

    return value;
  }

  public short valueFor(Short value)
  {
    if (value == null)
    {
      return NULL_PRIMITIVE.getDefaultValueCd();
    }

    return value;
  }

  public int valueFor(Integer value)
  {
    if (value == null)
    {
      return NULL_PRIMITIVE.getDefaultValueCd();
    }

    return value;
  }
}
