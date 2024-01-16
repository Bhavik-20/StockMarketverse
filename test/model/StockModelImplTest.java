package model;

import org.junit.Before;
import org.junit.Test;

import model.stock.StockModel;
import model.stock.StockModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * This is a jUnit class that is used to test the methods of the StockImpl class.
 */
public class StockModelImplTest {

  StockModel stock;

  @Before
  public void setup() {
    stock = new StockModelImpl("GOOG", 10.0, 0, null, null, 5);
  }

  @Test
  public void testGetTicker() {
    assertEquals("GOOG", stock.getTicker());
  }

  @Test
  public void testGetQuantity() {
    assertEquals(10.0, stock.getQuantity(), 0);
  }

}