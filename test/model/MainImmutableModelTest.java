package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This is a jUnit class that tests all the methods of the MainImmutableModel class.
 */
public class MainImmutableModelTest {

  MainModel mod;

  @Before
  public void setUp() {
    mod = new MainImmutableModel();
  }

  @Test
  public void testCheckFileIsXML1() {
    assertEquals(true, mod.checkFileIsXML("E:/Profile/desktop/PDP/Group" +
            "/Assignment-4_Stocks_1/data/Muskaan/muskaan.xml"));
  }

  @Test
  public void testCheckFileIsXML2() {
    assertEquals(false, mod.checkFileIsXML("E:/Profile/desktop/PDP/Group" +
            "/Assignment-4_Stocks_1/data/Muskaan/muskaan.csv"));
  }

  @Test
  public void testCheckValidDateAndName1() {
    assertEquals("valid", mod.checkValidDateAndName("muskaan", "Muskaan",
            "2022-10-31"));
  }

  @Test
  public void testCheckValidDateAndName() {
    assertEquals("valid",
            mod.checkValidDateAndName("muskaan", "Muskaan",
                    "2022-10-31"));
  }

  @Test
  public void testCheckInvalidFileName() {
    assertEquals("File Not Found",
            mod.checkValidDateAndName("muskaan", "bhavik",
                    "2022-10-31"));
  }

  @Test
  public void testCheckInvalidDate1() {
    assertEquals("Incorrect Date Format",
            mod.checkValidDateAndName("muskaan", "muskaan",
                    "2022-30-31"));
  }

  @Test
  public void testCheckInvalidDate2() {
    assertEquals("Incorrect Date Format",
            mod.checkValidDateAndName("muskaan", "muskaan",
                    "20326-10-31"));
  }

  @Test
  public void testCheckInvalidDate3() {
    assertEquals("Incorrect Date Format",
            mod.checkValidDateAndName("muskaan", "muskaan",
                    "20326/30/31"));
  }

  @Test
  public void testCheckInvalidDate4() {
    assertEquals("Incorrect Date Format",
            mod.checkValidDateAndName("muskaan", "muskaan",
                    "10/10/2020"));
  }

  @Test
  public void testCheckFutureDate1() {
    assertEquals(true,
            mod.checkFutureDate("2022-12-11"));
  }

  @Test
  public void testCheckFutureDate2() {
    assertEquals(false,
            mod.checkFutureDate("2022-10-11"));
  }

  @Test
  public void testCreateUserDirectory1() {
    assertEquals(true,
            mod.createUserDirectory("muskaan"));
  }

  @Test
  public void testCreateUserDirectory2() {
    assertEquals(true,
            mod.createUserDirectory("xyz"));
  }

  @Test
  public void testUserDirectoryExist1() {
    assertEquals(true,
            mod.userDirectoryExist("muskaan"));
  }

  @Test
  public void testUserDirectoryExist2() {
    assertEquals(false,
            mod.userDirectoryExist("john"));
  }

  @Test
  public void testCheckFileNameFormat1() {
    assertEquals(true, mod.checkFileNameFormat("muskaan7"));
  }

  @Test
  public void testCheckFileNameFormat2() {
    assertEquals(false, mod.checkFileNameFormat("muskaan/bb"));
  }

  @Test
  public void testCheckDateFormat1() {
    assertEquals(true,
            mod.checkDateFormat("2000-08-30"));
  }

  @Test
  public void testMainModGetValueOnDate1() {
    assertEquals("28754.8", mod.mainModGetValueOnDate("BhavikTest",
            "testPortfolio",
            "2021-11-01"));
  }

  @Test
  public void testMainModGetValueOnDate2() {
    assertEquals("0.0", mod.mainModGetValueOnDate("BhavikTest",
            "valid-1",
            "2021-11-01"));
  }

  @Test
  public void testMainModCheckPortfolioNameExists1() {
    assertEquals(false, mod.mainModCheckPortfolioNameExists("muskaan",
            "bhavik"));
  }

  @Test
  public void testMainModCheckPortfolioNameExists2() {
    assertEquals(true, mod.mainModCheckPortfolioNameExists("muskaan",
            "muskaan"));
  }

  @Test
  public void testMainModHandleData1() {
    assertEquals(true,
            mod.mainModHandleData("GOOG", "10"));
  }

  @Test
  public void testMainModHandleData2() {
    assertEquals(false, mod.mainModHandleData("GOOG", "10.5"));
  }

  @Test
  public void testMainModHandleData3() {
    assertEquals(false,
            mod.mainModHandleData("ZZ", "10"));
  }

  @Test
  public void testMainModHandleData4() {
    assertEquals(true,
            mod.mainModHandleData("goog", "10"));
  }

  @Test
  public void testMainModHandleData5() {
    assertEquals(false,
            mod.mainModHandleData("GO1OG", "10"));
  }

  @Test
  public void testMainModHandleData6() {
    assertEquals(false,
            mod.mainModHandleData("GOOG", "10a"));
  }

  @Test
  public void testMainModHandleData7() {
    assertEquals(false,
            mod.mainModHandleData("Muskaan", "10"));
  }

  @Test
  public void testMainModCreatePortfolio1() {
    assertEquals(true, mod.mainModCreatePortfolio("Muskaan",
            "newPortfolio"));
  }

  @Test
  public void testMainModCreatePortfolio2() {
    assertEquals(false, mod.mainModCreatePortfolio("abc", "//"));
  }

  @Test
  public void testMainModCheckPathExists1() {
    assertEquals(true, mod.mainModCheckPathExists("C:/android/valid-2.xml"));
  }

  @Test
  public void testMainModCheckPathExists2() {
    assertEquals(false, mod.mainModCheckPathExists("E:/Profile/desktop/PDP/Group" +
            "/Assignment-4_Stocks_1/data/Muskaan/@@@.xml"));
  }

  @Test
  public void testMainModUploadPortfolio1() {
    assertEquals(false, mod.mainModUploadPortfolio("muskaan",
            "E:/Profile/desktop/PDP/Group" +
                    "/Assignment-4_Stocks_1/data/Muskaan/@@@.xml"));
  }

  @Test
  public void testMainModUploadPortfolio2() {
    assertEquals(true, mod.mainModUploadPortfolio("BhavikTest",
            "C:/android/valid-1.xml"));
  }

  @Test
  public void testMainModViewPortfolio1() {
    assertEquals("GOOG -> 10.0 stocks", mod.mainModViewPortfolio("muskaan",
            "muskaan", "").trim());
  }

  @Test
  public void testMainModViewPortfolio2() {
    assertEquals("File Not Found", mod.mainModViewPortfolio("muskaan",
            "@@@", ""));
  }

  @Test
  public void testMainModViewPortfolio() {
    assertEquals("File Not Found", mod.mainModViewPortfolio("@@@@",
            "muskaan", ""));
  }

  @Test
  public void testCheckHoliday1() {
    assertEquals(true,
            mod.checkHoliday("2022-10-30"));
  }

  @Test
  public void testCheckHoliday2() {
    assertEquals(false,
            mod.checkHoliday("2022-11-01"));
  }
}