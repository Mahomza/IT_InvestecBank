package test;


import business.CommonFactor;


/**
 * <p>Title: CommonFactorTest</p>
 * <p>Description: The test class for CommonFactor
 * </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Investec_Bank</p>
 *
 * @author Alex Mahomana
 * @version 1.0
 */
public class CommonFactorTest
{
  private static CommonFactor commonFactor;

  /**
   * the main methods to execute test cases
   *
   * @param args the array of strings
   */
  public static void main(String[] args)
  {
    try
    {
      testShouldPassIfArrayIsNotEmpty();
      testShouldFailIfArrayIsEmpty();
    }
    catch (Exception e)
    {
      System.out.println("Error: " + e.getMessage());
    }
  }

  /**
   * Negative Test operation to print highest common factor
   */
  public static void testShouldFailIfArrayIsEmpty()
  {
    int[] numbers = new int[0];
    commonFactor = new CommonFactor(numbers);
    commonFactor.calculateHighestCommonFactor();
    int maxValue = commonFactor.getHighestCommonFactor();
    System.out.println("testShouldFailIfArrayIsEmpty() - Highest Common Factor : " + maxValue);
  }

  /**
   * Positive Test operation to print highest common factor
   */
  public static void testShouldPassIfArrayIsNotEmpty()
  {
    int[] numbers = {1, 3, 4, 3, 9, 3, 3, 7};
    commonFactor = new CommonFactor(numbers);
    commonFactor.calculateHighestCommonFactor();
    int maxValue = commonFactor.getHighestCommonFactor();
    System.out.println("testShouldPassIfArrayIsNotEmpty() - Highest Common Factor : " + maxValue);
  }

}