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
 * A class that contains JUnit test to test the DatabaseImmutableImpl class and its functionalities.
 */
public class DatabaseImmutableImplTest {

  Database f;
  StockModel s;
  List<StockModel> correct = new ArrayList<>();
  String path;
  PortfolioModel p;

  @Before
  public void setUp() {
    path = "C:/Users/Bhavik/IdeaProjects/Group/Assignment-4_Stocks_1/data/BhavikTest/immutable/";
    f = new DatabaseImmutableImpl(path);
    s = new StockModelImpl("GOOG", 10.00, 0, null, null, 0);
    correct.add(s);
    p = new PortfolioImmutableImpl();
  }


  @Test
  public void testWriteToPortfolio() {
    File file = new File(path + "testPortfolio.xml");
    assertEquals(false, file.exists() && !file.isDirectory());
    f.writeToPortfolio(correct, null, "testPortfolio", 1);
    assertEquals(true, file.exists() && !file.isDirectory());
  }

  @Test
  public void testCopyFileFromPath1() {
    String copyPath = "C:/android/valid-1.xml";
    File file = new File(path + "valid-1.xml");
    assertEquals(false, file.exists() && !file.isDirectory());
    f.copyFileFromPath(copyPath);
    assertEquals(true, file.exists() && !file.isDirectory());
  }

  @Test
  public void testCopyFileFromPath2() {
    String copyPath = "C:/android/invalid-ticker-1.xml";
    File file = new File(path + "invalid-ticker-1.xml");
    assertEquals(false, file.exists() && !file.isDirectory());
    f.copyFileFromPath(copyPath);
    assertEquals(true, file.exists() && !file.isDirectory());
  }

  @Test
  public void testCopyFileFromPath3() {
    String copyPath = "C:/android/incorrect-format-1.xml";
    File file = new File(path + "incorrect-format-1.xml");
    assertEquals(false, file.exists() && !file.isDirectory());
    f.copyFileFromPath(copyPath);
    assertEquals(true, file.exists() && !file.isDirectory());
  }

  @Test
  public void testReadFromPortfolio1() {
    assertEquals("Portfolio contains invalid ticker symbols",
            f.readFromPortfolio("invalid-ticker-1", 1).trim());
  }

  @Test
  public void testReadFromPortfolio2() {
    assertEquals("GOOG -> 10.0 stocks",
            f.readFromPortfolio("testPortfolio", 1).trim());
  }

  @Test
  public void testReadFromPortfolio3() {
    String copyPath = "C:/android/incorrect-format-1.xml";
    File file = new File(path + "incorrect-format-1.xml");
    assertEquals("Incorrect file format",
            f.readFromPortfolio("incorrect-format-1", 1).trim());
  }
}