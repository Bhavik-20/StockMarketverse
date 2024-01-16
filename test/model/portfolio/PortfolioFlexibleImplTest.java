package model.portfolio;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import model.stock.StockModel;
import model.stock.StockModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * A class that contains JUnit test to test the PortfolioFlexibleImpl class and its functionalities.
 */
public class PortfolioFlexibleImplTest {

  PortfolioModel pFlexi;
  StockModel stock;
  List<StockModel> lStock;
  List<String> pTicks;

  List<String> pDate;

  List<String> pType;

  List<String> pQuantity;
  List<Double> pCommFee;
  List<Double> pCP;

  @Before
  public void setup() {
    pFlexi = new PortfolioFlexibleImpl();
    stock = new StockModelImpl("GOOG", 10.0, 100, "2022-11-01", "Buy", 5);
    lStock = new ArrayList<>();
    lStock.add(stock);

    pTicks = new ArrayList<>();
    pTicks.add("GOOG");

    pDate = new ArrayList<>();
    pDate.add("2022-11-11");

    pType = new ArrayList<>();
    pType.add("Buy");

    pQuantity = new ArrayList<>();
    pQuantity.add("10");

    pCommFee = new ArrayList<>();
    pCommFee.add(5.0);

    pCP = new ArrayList<>();
    pCP.add(100.0);
  }


  @Test
  public void testViewPortfolio() {
    assertEquals("|           GOOG |            10.0 |",
            pFlexi.viewPortfolio("BhavikTest", "testPortfolio",
                    "2022-11-10").trim());
  }

  @Test
  public void testGetFlexiValueOnDate() {
    assertEquals("967.3000000000001",
            pFlexi.getFlexiValueOnDate("2022-11-12", lStock).trim());
  }

  @Test
  public void testDeleteFile() {
    File f = new File("C:/Users/Bhavik/IdeaProjects/Group/Assignment-4_Stocks_1/data" +
            "/BhavikTest/flexible/valid-2.xml");
    assertEquals(true, f.exists() && f.isFile());
    pFlexi.deleteFile("BhavikTest", "valid-2");
    assertEquals(false, f.exists() && f.isFile());
  }

  @Test
  public void testCalculateCostBasis() {

    assertEquals("1005.0", pFlexi.calculateCostBasis("2022-11-11", pDate,
            pType, pQuantity, pTicks, pCP, pCommFee, lStock));
  }

  @Test
  public void testGetQuantityOfStockBeforeDate() {
    assertEquals(10.0, pFlexi.getQuantityOfStockBeforeDate("GOOG",
            "2022-11-11", pDate,
            pType, pQuantity, pTicks, pCP, pCommFee, lStock), 0);
  }

  @Test
  public void testGetTotalQuantityOfAStock() {
    assertEquals(10.0, pFlexi.getTotalQuantityOfAStock("GOOG", pDate,
            pType, pQuantity, pTicks, pCP, pCommFee, lStock), 0);
  }

  @Test
  public void testCreatePortfolio() {
    //    tbc
    assertEquals(true, pFlexi.createPortfolio("BhavikTest", "testCP",
            lStock, null));
  }

  @Test
  public void testUploadPortfolio() {
    assertEquals(true, pFlexi.uploadPortfolio("BhavikTest",
            "C:/android/valid-2.xml"));
  }
}