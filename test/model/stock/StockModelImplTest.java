package model.stock;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A class that contains JUnit test to test the StockModelImpl class and its functionalities.
 */
public class StockModelImplTest {

  StockModel stock;

  @Before
  public void setup() {
    stock = new StockModelImpl("GOOG", 10.0, 100, "2022-11-01", "Buy", 5);
  }

  @Test
  public void testGetTicker() {
    assertEquals("GOOG", stock.getTicker());
  }

  @Test
  public void testGetQuantity() {
    assertEquals(10.0, stock.getQuantity(), 0);
  }

  @Test
  public void testGetCostPrice() {
    assertEquals(100, stock.getCostPrice(), 0);
  }

  @Test
  public void testGetPurDate() {
    assertEquals("2022-11-01", stock.getPurDate());
  }

  @Test
  public void getTransaction() {
    assertEquals("Buy", stock.getTransaction());
  }
}