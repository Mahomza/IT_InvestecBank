package business.layer;

import org.junit.Assert;
import org.junit.Test;

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
   * Negative Test operation to print highest common factor
   */
  @Test(expected = IllegalStateException.class)
  public void testShouldFailIfArrayIsEmpty()
  {
    int[] numbers = new int[0];
    commonFactor = new CommonFactor(numbers);
    commonFactor.calculateHighestCommonFactor();
  }

  /**
   * Positive Test operation to print highest common factor
   */
  @Test
  public void testShouldPassIfArrayIsNotEmpty()
  {
    int[] numbers = {1, 3, 4, 3, 9, 3, 3, 7};
    commonFactor = new CommonFactor(numbers);
    commonFactor.calculateHighestCommonFactor();
    Integer maxValue = commonFactor.getHighestCommonFactor();
    System.out.println("testShouldPassIfArrayIsNotEmpty() - Highest Common Factor : " + maxValue);

    Assert.assertNotNull(maxValue);
    Assert.assertTrue(maxValue > 0);
  }
}