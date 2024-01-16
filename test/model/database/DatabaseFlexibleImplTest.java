package model.database;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import model.portfolio.PortfolioImmutableImpl;
import model.portfolio.PortfolioModel;
import model.stock.StockModel;
import model.stock.StockModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * A class that contains JUnit test to test the DatabaseFlexibleImpl class and its functionalities.
 */
public class DatabaseFlexibleImplTest {
  Database f;
  StockModel s;
  List<StockModel> correct = new ArrayList<>();
  String path;
  PortfolioModel p;

  @Before
  public void setUp() {
    path = "C:/Users/Bhavik/IdeaProjects/Group/Assignment-4_Stocks_1/data/BhavikTest/flexible/";
    f = new DatabaseFlexibleImpl(path);
    s = new StockModelImpl("GOOG", 10.00, 10, "2022-11-03", "Buy", 5);
    correct.add(s);
    p = new PortfolioImmutableImpl();
  }


  @Test
  public void testWriteToPortfolio() {
    File file = new File(path + "testPortfolio.xml");
    assertEquals(false, file.exists() && !file.isDirectory());
    // tbc
    f.writeToPortfolio(correct, null, "testPortfolio", 2);
    assertEquals(true, file.exists() && !file.isDirectory());
  }

  @Test
  public void testCopyFileFromPath1() {
    String copyPath = "C:/android/valid-2.xml";
    File file = new File(path + "valid-2.xml");
    assertEquals(false, file.exists() && !file.isDirectory());
    f.copyFileFromPath(copyPath);
    assertEquals(true, file.exists() && !file.isDirectory());
  }

  @Test
  public void testCopyFileFromPath2() {
    String copyPath = "C:/android/invalid-ticker-2.xml";
    File file = new File(path + "invalid-ticker-2.xml");
    assertEquals(false, file.exists() && !file.isDirectory());
    f.copyFileFromPath(copyPath);
    assertEquals(true, file.exists() && !file.isDirectory());
  }

  @Test
  public void testCopyFileFromPath3() {
    String copyPath = "C:/android/incorrect-format-2.xml";
    File file = new File(path + "incorrect-format-2.xml");
    assertEquals(false, file.exists() && !file.isDirectory());
    f.copyFileFromPath(copyPath);
    assertEquals(true, file.exists() && !file.isDirectory());
  }

  @Test
  public void testReadFromPortfolio1() {
    assertEquals(null,
            f.readFromFlexiPortfolio("invalid-ticker-2", 2));
  }

  /**
   * Expected return type is a list of StockModel objects.
   */
  @Test
  public void testReadFromPortfolio2() {
    assertEquals("[model.stock.StockModelImpl@7bb58ca3]",
            f.readFromFlexiPortfolio("testPortfolio", 2).toString());
  }

  @Test
  public void testReadFromPortfolio3() {
    String copyPath = "C:/android/incorrect-format-2.xml";
    File file = new File(path + "incorrect-format-2.xml");
    assertEquals(null,
            f.readFromFlexiPortfolio("incorrect-format-2", 2));
  }
}