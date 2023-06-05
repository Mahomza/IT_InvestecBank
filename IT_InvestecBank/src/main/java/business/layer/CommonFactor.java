package business.layer;

import service.ICommonFactor;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: CommonFactor</p>
 * <p>Description: The implementation class to find the highest common factor from given param:
 * - int[] array
 * </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Investec_Bank</p>
 *
 * @author Alex Mahomana
 * @version 1.0
 */
public class CommonFactor implements ICommonFactor
{
  private final int[] numbers;

  private int highestCommonFactor;

  /**
   * The Constructor to instantiate CommonFactor
   *
   * @param numbers the array of integer numbers
   */
  public CommonFactor(int[] numbers)
  {
    this.numbers = numbers;
  }

  @Override
  public void calculateHighestCommonFactor()
  {
    validateParams();
    highestCommonFactor = findHighestCommonFactor(numbers);
  }

  /**
   * Operation to find the highest common factor
   *
   * @param numbers the array of integer numbers
   * @return the highest common factor
   */
  private int findHighestCommonFactor(int[] numbers)
  {
    Map<Integer, Integer> counterMap = new HashMap<Integer, Integer>();
    Integer maxValue = 0;
    Integer mostOccurringValue = null;

    for (Integer valueAsKey : numbers)
    {
      Integer counter = counterMap.get(valueAsKey);
      counterMap.put(valueAsKey, counter == null ? 1 : counter + 1);
      counter = counterMap.get(valueAsKey);
      if (counter > maxValue)
      {
        maxValue = counter;
        mostOccurringValue = valueAsKey;
      }
    }
    return mostOccurringValue;
  }


  /**
   * Operation to retrieve the highest common factor.
   *
   * @return the highest common factor
   */
  public int getHighestCommonFactor()
  {
    return highestCommonFactor;
  }

  /**
   * Operation to validate input params
   */
  private void validateParams()
  {
    if (numbers.length < 1)
    {
      throw new IllegalStateException(String.format("Length of array param must be greater than zero; current length is: %s", numbers.length));
    }
  }
}
