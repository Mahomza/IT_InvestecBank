package validator;

/**
 * <p>Title: PreconditionException</p>
 * <p>Description: This excpetion is used to raise error that communicates the
 * fact that certain preconditions have failed. It contains utility methods
 * that constructs the messages depending on the static method called. The
 * advantages of this method is that the string literals are contained in
 * one place and the developer does not the need to type the messages in
 * repeatedly.
 * This the same principle as used in the junit API.</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Investec_Bank</p>
 * @author  Alex Mahomana
 * @version 1.0
 *
 */
public class PreconditionException extends RuntimeException
{
  private static final String NOT_BETWEEN = " is not between ";
  private static final String PARAM_VALUE = ". The parameter value is : ";
  private static final String THE_PARAM = "The parameter ";
  private static final long serialVersionUID = 7239731802441034466L;

  /**
   * Constructs a new PreconditionException with the specified detail message.
   * @param message String
   */
  public PreconditionException(String message)
  {
    super(message);
  }

  /**
   * This method is used to create an exception that will return the message
   * "The parameter : <parameterName> is null and may not be null."
   * @param parameterName String
   * @return PreconditionException
   */
  public static PreconditionException parameterIsNull(String parameterName)
  {
    //Concatenate the message.
    StringBuilder message = new StringBuilder();
    message.append("The parameter: ");
    message.append(parameterName);
    message.append(" is null and may not be null");
    //Create the exception using the message.
    return new PreconditionException(message.toString());
  }

  /**
   * This method is used to create an exception that will return the message
   * "The collection : <parameterName> is empty and may not be empty."
   * @param parameterName String
   * @return PreconditionException
   */
  public static PreconditionException parameterIsEmpty(String parameterName)
  {
    //Concatenate the message.
    StringBuilder message = new StringBuilder();
    message.append("The collection: ");
    message.append(parameterName);
    message.append(" is empty and may not be empty");
    //Create the exception using the message.
    return new PreconditionException(message.toString());
  }

  /**
   * This method is used to create an exception that will return the message
   * "The parameter : <parameterName> is not between <minval> and <maxval>. The parameter value is : <paramval>."
   * @param parameterName String
   * @param parameterValue long
   * @param minimumValue long
   * @param maximumValue long
   * @return PreconditionException
   */
  public static PreconditionException parameterNotInRange(String parameterName,
          long parameterValue,
          long minimumValue, long maximumValue)
  {
    //Concatenate the message.
    StringBuilder message = new StringBuilder();
    message.append(THE_PARAM);
    message.append(parameterName);
    message.append(NOT_BETWEEN);
    message.append(minimumValue);
    message.append(" and ");
    message.append(maximumValue);
    message.append(PARAM_VALUE);
    message.append(parameterValue);
    //Create the exception using the message.
    return new PreconditionException(message.toString());
  }

  /**
   * This method is used to create an exception that will return the message
   * "The parameter : <parameterName> is less than <minval>.The parameter value is : <paramval>."
   * @param parameterName String
   * @param parameterValue long
   * @param minimumValue long
   * @return PreconditionException
   */
  public static PreconditionException parameterNotInRange(String parameterName,
          long parameterValue, long minimumValue)
  {
    //Concatenate the message.
    StringBuilder message = new StringBuilder();
    message.append(THE_PARAM);
    message.append(parameterName);
    message.append(" is less than ");
    message.append(minimumValue);
    message.append(PARAM_VALUE);
    message.append(parameterValue);
    //Create the exception using the message.
    return new PreconditionException(message.toString());
  }

  /**
   * This method is used to create an exception that will return the message
   * "The parameter : <parameterName> is not between <minval> and <maxval>.The parameter value is : <paramval>."
   * @param parameterName String
   * @param parameterValue double
   * @param minimumValue double
   * @param maximumValue double
   * @return PreconditionException
   */
  public static PreconditionException parameterNotInRange(String parameterName,
          double parameterValue,
          double minimumValue, double maximumValue)
  {
    //Concatenate the message.
    StringBuilder message = new StringBuilder();
    message.append(THE_PARAM);
    message.append(parameterName);
    message.append(NOT_BETWEEN);
    message.append(minimumValue);
    message.append(" and ");
    message.append(maximumValue);
    message.append(PARAM_VALUE);
    message.append(parameterValue);
    //Create the exception using the message.
    return new PreconditionException(message.toString());
  }

  /**
   * This method is used to create an exception that will return the message
   * "The parameter : <parameterName> is less than <minval>.The parameter value is : <paramval>."
   * @param parameterName String
   * @param parameterValue double
   * @param minimumValue double
   * @return PreconditionException
   */
  public static PreconditionException parameterNotInRange(String parameterName,
          double parameterValue, double minimumValue)
  {
    //Concatenate the message.
    StringBuilder message = new StringBuilder();
    message.append(THE_PARAM);
    message.append(parameterName);
    message.append(NOT_BETWEEN);
    message.append(minimumValue);
    message.append(PARAM_VALUE);
    message.append(parameterValue);
    //Create the exception using the message.
    return new PreconditionException(message.toString());
  }
  
  /**
   * This method is used to create an exception that will return the message
   * "The parameter: <parameterName> is of type: <typeName>. Expected type: <expectedTypeName>."
   * @param parameterName
   * @param typeName
   * @param expectedTypeName
   * @return PreconditionException
   */
  public static PreconditionException incorrectType(String parameterName, String typeName, String expectedTypeName)
  {
    StringBuilder message = new StringBuilder();
    message.append(THE_PARAM);
    message.append(parameterName);
    message.append(" is of type: ");
    message.append(typeName);
    message.append(". Expected type: ");
    message.append(expectedTypeName);
    message.append(".");
    return new PreconditionException(message.toString());
  }


  /** Generates an appropriate exception to indicate that a user role requirement was
   * not met, with optional reason.
   * @param roleName Role "short description" (required)
   * @param reason (optional), e.g. "Foo was not Bar"
   */
  public static PreconditionException requiresUserRole( String roleName, String reason )
  {
    // Note: For the past decade or two, StringBuffer is not required for string concat
    // (all decent Java compilers translate String concat to StringBuffer/StringBuilder)
    return new PreconditionException("User must at least have role '" + roleName +
        (reason != null ? "', because " + reason : "'."));
  }
}
