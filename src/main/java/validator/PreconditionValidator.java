package validator;


import lookup.EValueObject;


/**
 * <p>Title: PreconditionValidator</p>
 * <p>Description: This class does some of the standard precondition validation
 * that is required for all the methods. These methods are all static, an
 * instance of this class is not required.</p>
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Investec_Bank</p>
 *
 * @author Alex Mahomana
 * @version 1.0
 * <p>
 */
public class PreconditionValidator
{

  /**
   * PreconditionValidator: Private default constructor used to hide the public constructor since this class only has static method implementations.
   */
  private PreconditionValidator()
  {

  }

  /**
   * This method validates whether a particular object is null or not and raises
   * the co-ordinating exception if it is found to be null.
   *
   * @param parameterName String
   *                      This is a logical string value for the parameter that is being tested.
   * @param object        Object
   *                      This is the object that is being evaluated.
   * @throws PreconditionException
   */
  public static void validateIsNull(String parameterName, Object object)
  {
    if (object == null || (object instanceof Short) && EValueObject.NULL_PRIMITIVE.isDefaultValue((Short) object))
    {
      throw PreconditionException.parameterIsNull(parameterName);
    }
    else if ((object instanceof Integer) && EValueObject.NULL_PRIMITIVE.isDefaultValue((Integer) object))
    {
      throw PreconditionException.parameterIsNull(parameterName);
    }
    else if ((object instanceof Long) && EValueObject.NULL_PRIMITIVE.isDefaultValue((Long) object))
    {
      throw PreconditionException.parameterIsNull(parameterName);
    }
  }

}
