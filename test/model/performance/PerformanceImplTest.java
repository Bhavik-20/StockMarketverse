package model.performance;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.stock.StockModel;
import model.stock.StockModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class is a Junit test class to test the functionalities of the PerformanceImpl model.
 */
public class PerformanceImplTest {

  PerformanceModel pm;

  @Before
  public void setUp() {
    pm = new PerformanceImpl();
  }

  @Test
  public void testReturnPerformanceTimestampScaleNegativeDiff() {
    assertEquals(false, pm.returnPerformanceTimestampScale("2022-11-01", "2022-10-14"));
  }

  @Test
  public void testReturnPerformanceTimestampScaleMoreThan30Years() {
    assertEquals(false, pm.returnPerformanceTimestampScale("1980-11-01", "2022-10-14"));
  }

  @Test
  public void testReturnPerformanceTimestampScaleDays() {
    assertEquals(true, pm.returnPerformanceTimestampScale("2022-11-01", "2022-11-14"));
    assertEquals("[2022-11-01, 2022-11-02, 2022-11-03, 2022-11-04, 2022-11-05," +
            " 2022-11-06, 2022-11-07, 2022-11-08, 2022-11-09, 2022-11-10, 2022-11-11, 2022-11-12," +
            " 2022-11-13, 2022-11-14]", pm.getTimeStampForYaxis().toString());
  }

  @Test
  public void testReturnPerformanceTimestampScaleMonths() {
    assertEquals(true, pm.returnPerformanceTimestampScale("2022-05-01", "2022-11-14"));
    assertEquals("[May 2022, Jun 2022, Jul 2022, Aug 2022, Sep 2022, Oct 2022, Nov 2022]",
            pm.getTimeStampForYaxis().toString());
  }

  @Test
  public void testReturnPerformanceTimestampScaleMonths2() {
    assertEquals(true, pm.returnPerformanceTimestampScale("2022-08-01", "2022-10-14"));
    assertEquals("[2022-08-01, 2022-08-04, 2022-08-07, 2022-08-10," +
                    " 2022-08-13, 2022-08-16, 2022-08-19, 2022-08-22, 2022-08-25," +
                    " 2022-08-28, 2022-08-31, 2022-09-03, 2022-09-06, 2022-09-09," +
                    " 2022-09-12, 2022-09-15, 2022-09-18, 2022-09-21, 2022-09-24," +
                    " 2022-09-27, 2022-09-30, 2022-10-03, 2022-10-06, 2022-10-09, 2022-10-12]",
            pm.getTimeStampForYaxis().toString());
  }

  @Test
  public void testReturnPerformanceTimestampScaleYears() {
    assertEquals(true, pm.returnPerformanceTimestampScale("2010-05-01", "2022-11-14"));
    assertEquals("[2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, " +
            "2021, 2022]", pm.getTimeStampForYaxis().toString());
  }


  @Test
  public void testGetValueTimeStampForXaxis() {
    StockModel stock = new StockModelImpl("GOOG", 10.0, 100, "2022-11-01", "Buy", 5);
    List<StockModel> currData = new ArrayList<>();
    currData.add(stock);
    assertEquals(true, pm.returnPerformanceTimestampScale("2022-11-01", "2022-11-05"));
    assertEquals(18.1, pm.getScaleForBarGraph("2022-11-01", "2022-11-05",
            currData), 0);
    assertEquals("[905.0, 870.6999999999999, 834.9, 867.0, 867.0]",
            pm.getValueTimeStampForXaxis().toString());
  }
}