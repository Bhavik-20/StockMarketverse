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
 * A class that contains JUnit test to test PortfolioImmutableImpl class and its functionalities.
 */
public class PortfolioImmutableImplTest {

  PortfolioModel pImmu;
  StockModel stock;
  List<StockModel> lStock;
  List<String> pTicks;

  List<String> pDate;

  List<String> pType;

  List<String> pQuantity;

  List<Double> pCP;
  List<Double> pCommFee;

  @Before
  public void setup() {
    pImmu = new PortfolioImmutableImpl();
    stock = new StockModelImpl("GOOG", 10.0, 100, "2022-11-01", "Buy", 0);
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
            pImmu.viewPortfolio("BhavikTest", "testPortfolio",
                    "2022-11-10").trim());
  }

  @Test
  public void testGetFlexiValueOnDate() {
    assertEquals(null,
            pImmu.getFlexiValueOnDate("2022-11-12", lStock));
  }

  @Test
  public void testDeleteFile() {
    File f = new File("C:/Users/Bhavik/IdeaProjects/Group/Assignment-4_Stocks_1/data" +
            "/BhavikTest/immutable/valid-1.xml");
    assertEquals(true, f.exists() && f.isFile());
    pImmu.deleteFile("BhavikTest", "valid-1");
    assertEquals(false, f.exists() && f.isFile());
  }

  @Test
  public void testCalculateCostBasis() {
    assertEquals(null, pImmu.calculateCostBasis("2022-11-11", pDate,
            pType, pQuantity, pTicks, pCP, pCommFee, lStock));
  }

  @Test
  public void testGetQuantityOfStockBeforeDate() {
    assertEquals(0.0, pImmu.getQuantityOfStockBeforeDate("GOOG",
            "2022-11-11", pDate,
            pType, pQuantity, pTicks, pCP, pCommFee, lStock), 0);
  }

  @Test
  public void testGetTotalQuantityOfAStock() {
    assertEquals(0.0, pImmu.getTotalQuantityOfAStock("GOOG", pDate,
            pType, pQuantity, pTicks, pCP, pCommFee, lStock), 0);
  }

  @Test
  public void testCreatePortfolio() {
    assertEquals(true, pImmu.createPortfolio("BhavikTest", "testCP",
            lStock, null));
  }

  @Test
  public void testUploadPortfolio() {
    assertEquals(true, pImmu.uploadPortfolio("BhavikTest",
            "C:/android/valid-1.xml"));
  }
}
