package model;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import model.database.Database;
import model.database.DatabaseFlexibleImpl;

import static org.junit.Assert.assertEquals;

/**
 * A class that contains JUnit test to test the MainFlexibleModel and its functionalities.
 */
public class MainFlexibleModelTest {

  MainModel mod = new MainFlexibleModel();

  @Before
  public void testSetUp() {
    mod = new MainFlexibleModel();
  }

  @Test
  public void testInvalidDateFormat() {
    assertEquals("Incorrect Date Format",
            mod.checkValidDateAndName("BhavikTest", "testPortfolio",
                    "2022/11/11"));
  }

  @Test
  public void testPortfolioFileDoesNotExist() {
    assertEquals("File Not Found", mod.checkValidDateAndName("muskaan",
            "muskaan", "2022/11/11"));
  }

  @Test
  public void testValidDateAndName() {
    assertEquals("valid", mod.checkValidDateAndName("muskaan", "muskaan",
            "2022/11/11"));
  }

  @Test
  public void testCreateUserDirectory() {
    assertEquals(true, mod.createUserDirectory("BBTest"));
  }

  @Test
  public void testMainModGetValueOnDate() {
    assertEquals("0.0", mod.mainModGetValueOnDate("BhavikTest", "testPortfolio",
            "2022-11-11"));
  }

  @Test
  public void testMainModCheckPortfolioNameExists() {
    assertEquals(true, mod.mainModCheckPortfolioNameExists("BhavikTest",
            "testPortfolio"));
  }

  @Test
  public void testMainModCheckPortfolioNameDoesNotExist() {
    assertEquals(false, mod.mainModCheckPortfolioNameExists("muskaan",
            "testPortfolio"));
  }

  @Test
  public void testMainModCreatePortfolioSuccessful() {
    assertEquals(true, mod.mainModCreatePortfolio("muskaan", "muskaan"));
  }

  @Test
  public void testMainModCreatePortfolioUnsuccessful() {
    assertEquals(false, mod.mainModCreatePortfolio("muskaan", "muskaan"));
  }

  @Test
  public void testMainModUploadPortfolioSuccessful() {
    assertEquals(true, mod.mainModUploadPortfolio("muskaan",
            "E:/Profile/desktop/PDP/new.xml"));
  }

  @Test
  public void testMainModUploadPortfolioUnsuccessful() {
    assertEquals(true, mod.mainModUploadPortfolio("muskaan",
            "E:/Pr/new.xml"));
  }

  @Test
  public void testMainModViewPortfolio() {
    assertEquals("|           GOOG |            10.0 |",
            mod.mainModViewPortfolio("test", "testCreate",
                    "2022-11-11").trim());
  }

  @Test
  public void testDeleteFile() {
    File f = new File("C:/android/valid-3.xml");
    assertEquals(true, f.exists() && f.isFile());
    f.delete();
    assertEquals(false, f.exists() && f.isFile());
  }

  @Test
  public void testMainModHandleData() {
    assertEquals(false, mod.mainModHandleData("GOOG", "10"));
  }

  @Test
  public void testAddStockToPortfolio() {
    assertEquals(true, mod.addStockToPortfolio("GOOG", "10",
            "2022-11-11", "0", 0));
  }

  @Test
  public void testAddStockToPortfolio2() {
    assertEquals(true,
            mod.addStockToPortfolio("GOOG", "1000", "2022-11-11",
                    "5", 1));
    mod.mainModConvertAllStocksToStockObj();
    assertEquals(true, mod.mainModCreatePortfolio("bb", "test"));
    assertEquals("|           GOOG |           10.34 |",
            mod.mainModViewPortfolio("bb", "test", "2022-11-12").trim());
  }

  // Not possible (NP) to sell stocks as insufficient quantity
  @Test
  public void testRemoveStockFromPortfolio() {
    assertEquals("NP", mod.removeStockFromPortfolio("GOOG", "10",
            "2022-11-11", "0"));
  }

  @Test
  public void testLoadPortfolio() {
    assertEquals(true, mod.loadPortfolio("bb", "new"));
  }

  @Test
  public void testCalculateCostBasis() {
    assertEquals("0.0", mod.calculateCostBasis("testPortfolio",
            "2022-11-11"));
  }

  @Test
  public void testCalculateCostBasisInvalidFee() {
    assertEquals(false, mod.checkAmount(-5));
  }

  @Test
  public void testReturnPerformanceTimestampScale() {
    assertEquals(true, mod.returnPerformanceTimestampScale("2022-11-11", "2022-11-15"));
  }

  @Test
  public void testGetScaleBarGraph() {
    assertEquals(0.0, mod.getScaleBarGraph("2022-11-11", "2022-11-15"),
            0);
  }

  @Test
  public void testGetTimeStampForYaxis() {
    assertEquals(0, mod.getTimeStampForYaxis().size());
  }

  @Test
  public void testGetNumOfStars() {
    assertEquals(0, mod.getNumOfStars(10).size());
  }

  @Test
  public void testCalcQuantityOfUploadedFile() {
    assertEquals(true,
            mod.calcQuantityOfUploadedFile());
  }

  @Test
  public void testUploadInvalidFile1() {
    File f = new File("C:/Users/Bhavik/IdeaProjects/Group/Assignment-4_Stocks_1/data/"
            + "bbfinal/flexible/invalid-qty.xml");
    assertEquals(false, f.exists() && f.isFile());
    assertEquals(true, mod.mainModUploadPortfolio("bbfinal",
            "C:/android/invalid-qty.xml"));
    assertEquals("", mod.mainModViewPortfolio("bbfinal", "invalid-qty",
            "2022-11-15"));
    assertEquals(false, f.exists() && f.isFile());
  }

  @Test
  public void testUploadInvalidFile2() {
    File f = new File("C:/Users/Bhavik/IdeaProjects/Group/Assignment-4_Stocks_1/data/"
            + "bbfinal/flexible/invalid-ticker-1.xml");
    assertEquals(false, f.exists() && f.isFile());
    assertEquals(true, mod.mainModUploadPortfolio("bbfinal",
            "C:/android/invalid-ticker-1.xml"));
    assertEquals("Portfolio contains invalid ticker symbols",
            mod.mainModViewPortfolio("bbfinal",
                    "invalid-ticker-1",
                    "2022-11-15"));
  }

  @Test
  public void testUploadInvalidFile3() {
    File f = new File("C:/Users/Bhavik/IdeaProjects/Group/Assignment-4_Stocks_1/data/"
            + "bbfinal/flexible/valid-2.xml");
    assertEquals(false, f.exists() && f.isFile());
    assertEquals(true, mod.mainModUploadPortfolio("bbfinal",
            "C:/android/valid-2.xml"));
    assertEquals("Portfolio contains invalid ticker symbols",
            mod.mainModViewPortfolio("bbfinal",
                    "invalid-ticker-1",
                    "2022-11-15"));
  }

  @Test
  public void testMultipleTransactionsTogether() {
    assertEquals(true,
            mod.addStockToPortfolio("GOOG", "10", "2022-11-01", "0", 0));
    assertEquals("NA",
            mod.removeStockFromPortfolio("GOOG", "10", "2022-10-31", "0"));
    assertEquals("NP",
            mod.removeStockFromPortfolio("GOOG", "11", "2022-11-02", "0"));
    assertEquals("true",
            mod.removeStockFromPortfolio("GOOG", "5", "2022-11-02", "0"));
    assertEquals("NP",
            mod.removeStockFromPortfolio("AAPL", "10", "2022-10-31", "0"));
    assertEquals(true,
            mod.addStockToPortfolio("AAPL", "10", "2022-10-28", "0", 0));
    assertEquals("true",
            mod.removeStockFromPortfolio("AAPL", "10", "2022-10-31", "0"));
  }

  @Test
  public void testMultipleTransactionsAndViewComposition() {
    assertEquals(true,
            mod.addStockToPortfolio("GOOG", "10", "2022-11-01", "0", 0));
    mod.mainModConvertAllStocksToStockObj();
    assertEquals(true, mod.mainModCreatePortfolio("bbfinal", "1"));

    assertEquals("|           GOOG |            10.0 |",
            mod.mainModViewPortfolio("bbfinal", "1", "2022-11-01").trim());
    assertEquals("",
            mod.mainModViewPortfolio("bbfinal", "1", "2022-10-31").trim());
    assertEquals("",
            mod.mainModViewPortfolio("bbfinal", "1", "2022-10-01").trim());
    assertEquals(true, mod.loadPortfolio("bbfinal", "1"));
    assertEquals("NA",
            mod.removeStockFromPortfolio("GOOG", "10", "2022-10-31", "0"));
    assertEquals("NP",
            mod.removeStockFromPortfolio("GOOG", "11", "2022-11-02", "0"));
    assertEquals("true",
            mod.removeStockFromPortfolio("GOOG", "5", "2022-11-02", "0"));
    mod.mainModConvertAllStocksToStockObj();
    assertEquals(true, mod.mainModCreatePortfolio("bbfinal", "1"));

    assertEquals("|           GOOG |             5.0 |",
            mod.mainModViewPortfolio("bbfinal", "1", "2022-11-03").trim());
    assertEquals(true, mod.loadPortfolio("bbfinal", "1"));
    assertEquals("NP",
            mod.removeStockFromPortfolio("AAPL", "10", "2022-10-31", "0"));
    assertEquals(true,
            mod.addStockToPortfolio("AAPL", "10", "2022-10-28", "0", 0));
    assertEquals("true",
            mod.removeStockFromPortfolio("AAPL", "10", "2022-10-31", "0"));
    mod.mainModConvertAllStocksToStockObj();
    assertEquals(true, mod.mainModCreatePortfolio("bbfinal", "1"));

    assertEquals("|           AAPL |            10.0 |",
            mod.mainModViewPortfolio("bbfinal", "1", "2022-10-29").trim());
    assertEquals("|           AAPL |             0.0 |",
            mod.mainModViewPortfolio("bbfinal", "1", "2022-10-31").trim());
  }

  @Test
  public void testCostBasisOfDCA() {
    mod.loadPortfolio("bb", "new");
    String res = mod.applyDCAOnPortfolio("bb", "new",
            "2022-11-01", "2022-12-01",
            "1000", "15", "5", "GOOG 10, AAPL 90");
    assertEquals("true", res);
    mod.mainModConvertAllStocksToStockObj();
    mod.mainModCreatePortfolio("bb", "new");
    mod.loadPortfolio("bb", "new");
    assertEquals("6448.1586", mod.calculateCostBasis("new", "2022-11-30"));
  }

  @Test
  public void testCostBasisOfDCADifferentDates() {
    mod.loadPortfolio("bb", "new");
    String res = mod.applyDCAOnPortfolio("bb", "new",
            "2022-11-01", "2022-12-01",
            "1000", "15", "5", "GOOG 10, AAPL 90");
    assertEquals("true", res);
    mod.mainModConvertAllStocksToStockObj();
    mod.mainModCreatePortfolio("bb", "new");
    mod.loadPortfolio("bb", "new");

    assertEquals("2000.0",
            mod.calculateCostBasis("new", "2022-11-30"));
    assertEquals("1000.0",
            mod.calculateCostBasis("new", "2022-11-15"));
    assertEquals("1000.0",
            mod.calculateCostBasis("new", "2022-11-10"));
    assertEquals("0.0",
            mod.calculateCostBasis("new", "2022-10-31"));
  }

  @Test
  public void testDCAOngoing() {
    mod.loadPortfolio("bb", "new");
    String res = mod.applyDCAOnPortfolio("bb", "new", "2022-11-01",
            "2022-12-31",
            "1000", "10", "5", "GOOG 10, AAPL 90");
    assertEquals("true", res);
    mod.mainModConvertAllStocksToStockObj();
    mod.mainModCreatePortfolio("final", "final");
    assertEquals(
            "GOOG 3.21131268730584\n" +
                    "AAPL 17.780645925106104",
            mod.mainModViewPortfolio("final", "final", "2022-11-11").trim()
    );
  }

  @Test
  public void testCompo() {
    Database db = new DatabaseFlexibleImpl(System.getProperty("user.dir") + "/data/" + "final"
            + "/flexible/");
    assertEquals(
            4,
            db.readFromFlexiPortfolio("final", 2).size()
    );
  }

  @Test
  public void testPortfolioValue() {
    mod.loadPortfolio("bb", "t1");
    String res = mod.applyDCAOnPortfolio("bb", "t1",
            "2022-11-01", "2022-12-01",
            "1000", "15", "5", "GOOG 10, AAPL 90");
    assertEquals("true", res);
    mod.mainModConvertAllStocksToStockObj();
    mod.mainModCreatePortfolio("bb", "t1");
    mod.loadPortfolio("bb", "t1");

    assertEquals("1974.3919370687772",
            mod.mainModGetValueOnDate("bb", "t1", "2022-11-30"));
    assertEquals("995.3842778530602",
            mod.mainModGetValueOnDate("bb", "t1", "2022-11-15"));
    assertEquals("971.6583735305389",
            mod.mainModGetValueOnDate("bb", "t1", "2022-11-10"));
    assertEquals("0.0",
            mod.mainModGetValueOnDate("bb", "t1", "2022-10-31"));
  }
}