package controller.textbased;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import model.MainModel;
import view.textbased.View;

import static org.junit.Assert.assertEquals;

/**
 * Junit Class associated with isolation testing of the controller for flexible model with respect
 * to Model and View.
 */

public class ControllerFlexiTest {

  InputStream in;
  PrintStream out;
  ByteArrayOutputStream bytes;

  MainModel model;
  View view;
  Controller controller;
  StringBuilder log;
  StringBuilder logV;

  @Before
  public void setUp() {
    bytes = new ByteArrayOutputStream();
    out = new PrintStream(bytes);
    log = new StringBuilder();
    log.append("Input:");

    logV = new StringBuilder();
    logV.append("Output: ");
    view = new MockView(logV);
  }

  @Test
  public void testCreateFlexiPortfolio() {
    in = new ByteArrayInputStream(("1 testCreate 8 3 2").getBytes());
    controller = new ControllerFlexi("test", view, in, out, new Scanner(in),
            new MockFlexiModel(log, true, "uniqueCode", 0.0));
    controller.goController();
    assertEquals("Input: test testCreate test testCreate", log.toString().trim());
    assertEquals("Output: printFlexiblePortfolioMenu printEnterPortfolioNameToOpen" +
            " printChooseOtherName printFlexiblePortfolioMenu exitProgram", logV.toString().trim());
  }

  @Test
  public void testCreateFlexiPortfolioExists() {
    in = new ByteArrayInputStream(("1 testCreate 8 3 2").getBytes());
    controller = new ControllerFlexi("test", view, in, out, new Scanner(in),
            new MockFlexiModel(log, true, "uniqueCode", 0.0));
    controller.goController();
    assertEquals("Input: test testCreate test testCreate", log.toString().trim());
    assertEquals("Output: printFlexiblePortfolioMenu printEnterPortfolioNameToOpen " +
            "printChooseOtherName printFlexiblePortfolioMenu exitProgram", logV.toString().trim());
  }

  @Test
  public void testUploadFlexiPortfolio() {
    in = new ByteArrayInputStream(("2 E:/Profile/desktop/PDP/valid-2.xml 8 3 2").getBytes());
    controller = new ControllerFlexi("test", view, in, out, new Scanner(in),
            new MockFlexiModel(log, true, "uniqueCode", 0.0));
    controller.goController();
    assertEquals("Input: test E:/Profile/desktop/PDP/valid-2.xml " +
            "E:/Profile/desktop/PDP/valid-2.xml test E:/Profile/desktop/PDP/valid-2.xml test " +
            "valid-2 2022-11-16 test valid-2 2022-11-16 test valid-2 2022-11-16 test valid-2 " +
            "test valid-2 2022-11-16", log.toString().trim());
    assertEquals("Output: printFlexiblePortfolioMenu printAskForUploadPath " +
            "printPortfolioUploaded printFileComposition: uniqueCode printFlexiblePortfolioMenu " +
            "exitProgram", logV.toString().trim());
  }

  @Test
  public void testUploadFlexiPortfolioInvalidStructure() {
    in = new ByteArrayInputStream(("2 E:/Profile/desktop/PDP/incorrect-format-2.xml 8 3 2")
            .getBytes());
    controller = new ControllerFlexi("test", view, in, out, new Scanner(in),
            new MockFlexiModel(log, true, "uniqueCode", 0.0));
    controller.goController();
    assertEquals("Input: test E:/Profile/desktop/PDP/incorrect-format-2.xml " +
            "E:/Profile/desktop/PDP/incorrect-format-2.xml test E:/Profile/desktop/PDP/" +
            "incorrect-format-2.xml test incorrect-format-2 2022-11-16 test incorrect-format-2 " +
            "2022-11-16 test incorrect-format-2 2022-11-16 test incorrect-format-2 test " +
            "incorrect-format-2 2022-11-16", log.toString().trim());
    assertEquals("Output: printFlexiblePortfolioMenu printAskForUploadPath " +
            "printPortfolioUploaded printFileComposition: uniqueCode printFlexiblePortfolioMenu " +
            "exitProgram", logV.toString().trim());
  }

  @Test
  public void testUploadFlexiPortfolioInvalidTicker() {
    in = new ByteArrayInputStream(("2 E:/Profile/desktop/PDP/invalid-ticker-2.xml 8 3 2")
            .getBytes());
    controller = new ControllerFlexi("test", view, in, out, new Scanner(in),
            new MockFlexiModel(log, true, "uniqueCode", 0.0));
    controller.goController();
    assertEquals("Input: test E:/Profile/desktop/PDP/invalid-ticker-2.xml E:/Profile/" +
                    "desktop/PDP/invalid-ticker-2.xml test E:/Profile/desktop/PDP/" +
                    "invalid-ticker-2.xml " +
                    "test invalid-ticker-2 2022-11-16 test invalid-ticker-2 2022-11-16 " +
                    "test invalid-" +
                    "ticker-2 2022-11-16 test invalid-ticker-2 test invalid-ticker-2 2022-11-16",
            log.toString().trim());
    assertEquals("Output: printFlexiblePortfolioMenu printAskForUploadPath " +
            "printPortfolioUploaded printFileComposition: uniqueCode printFlexiblePortfolioMenu " +
            "exitProgram", logV.toString().trim());
  }

  @Test
  public void testUploadFlexiPortfolioInvalidQty() {
    in = new ByteArrayInputStream(("2 E:/Profile/desktop/PDP/invalid-qty.xml 8 3 2").getBytes());
    controller = new ControllerFlexi("test", view, in, out, new Scanner(in),
            new MockFlexiModel(log, true, "uniqueCode", 0.0));
    controller.goController();
    assertEquals("Input: test E:/Profile/desktop/PDP/invalid-qty.xml E:/Profile/desktop" +
            "/PDP/invalid-qty.xml test E:/Profile/desktop/PDP/invalid-qty.xml test invalid-qty " +
            "2022-11-16 test invalid-qty 2022-11-16 test invalid-qty 2022-11-16 test invalid-qty " +
            "test invalid-qty 2022-11-16", log.toString().trim());
    assertEquals("Output: printFlexiblePortfolioMenu printAskForUploadPath " +
            "printPortfolioUploaded printFileComposition: uniqueCode printFlexiblePortfolioMenu" +
            " exitProgram", logV.toString().trim());

  }

  @Test
  public void testUploadFlexiPortfolioInvalidSell() {
    in = new ByteArrayInputStream(("2 E:/Profile/desktop/PDP/sellmore.xml 8 3 2").getBytes());
    controller = new ControllerFlexi("test", view, in, out, new Scanner(in),
            new MockFlexiModel(log, true, "uniqueCode", 0.0));
    controller.goController();
    assertEquals("Input: test E:/Profile/desktop/PDP/sellmore.xml E:/Profile/desktop/PDP/" +
                    "sellmore.xml test E:/Profile/desktop/PDP/sellmore.xml test sellmore " +
                    "2022-11-16 test " +
                    "sellmore 2022-11-16 test sellmore 2022-11-16 test sellmore test sellmore " +
                    "2022-11-16",
            log.toString().trim());
    assertEquals("Output: printFlexiblePortfolioMenu printAskForUploadPath " +
            "printPortfolioUploaded printFileComposition: uniqueCode printFlexiblePortfolioMenu " +
            "exitProgram", logV.toString().trim());
  }

  @Test
  public void testMakeTransactionBuy() {
    in = new ByteArrayInputStream(("3 testCreate 1 AAPL 5 2022-11-11 3 8 3 2").getBytes());
    controller = new ControllerFlexi("test", view, in, out, new Scanner(in),
            new MockFlexiModel(log, true, "uniqueCode", 0.0));
    controller.goController();
    assertEquals("Input: test test testCreate test testCreate testCreate 2022-11-11 test " +
                    "testCreate 2022-11-11 test testCreate 2022-11-11 2022-11-11 test testCreate",
            log.toString().trim());
    assertEquals("Output: printFlexiblePortfolioMenu printEnterPortfolioNameToOpen " +
            "printWhichTransaction printEnterStockName printEnterStockQty printEnterPurchaseDate " +
            "printErrorFutureDate printWhichTransaction printFileUpdated " +
            "printFlexiblePortfolioMenu exitProgram", logV.toString().trim());
  }

  @Test
  public void testMakeTransactionSell() {
    in = new ByteArrayInputStream(("3 testCreate 2 AAPL 2 2022-11-11 3 8 3 2").getBytes());
    controller = new ControllerFlexi("test", view, in, out, new Scanner(in),
            new MockFlexiModel(log, true, "uniqueCode", 0.0));
    controller.goController();
    assertEquals("Input: test test testCreate test testCreate testCreate 2022-11-11 test " +
                    "testCreate 2022-11-11 test testCreate 2022-11-11 2022-11-11 test testCreate",
            log.toString().trim());
    assertEquals("Output: printFlexiblePortfolioMenu printEnterPortfolioNameToOpen " +
            "printWhichTransaction printEnterStockName printEnterStockQty printEnterSellDate " +
            "printErrorFutureDate printWhichTransaction printFileUpdated printFlexiblePortfolio" +
            "Menu exitProgram", logV.toString().trim());
  }

  @Test
  public void testViewCompositionBeforeATransaction() {
    in = new ByteArrayInputStream(("4 testCreate 2022-11-15 4 testCreate 2022-11-05 8 3 2")
            .getBytes());
    controller = new ControllerFlexi("test", view, in, out, new Scanner(in),
            new MockFlexiModel(log, true, "uniqueCode", 0.0));
    controller.goController();
    assertEquals("Input: test 2022-11-15 2022-11-15 2022-11-05 2022-11-05",
            log.toString().trim());
    assertEquals("Output: printFlexiblePortfolioMenu printEnterPortfolioNameToOpen " +
            "printEnterDateForViewFlexi printErrorFutureDate printFlexiblePortfolioMenu " +
            "printEnterPortfolioNameToOpen printEnterDateForViewFlexi printErrorFutureDate " +
            "printFlexiblePortfolioMenu exitProgram", logV.toString().trim());
  }

  @Test
  public void testExamineValueOnCertainDate() {
    in = new ByteArrayInputStream(("5 testCreate 2022-11-15 8 3 2")
            .getBytes());
    controller = new ControllerFlexi("test", view, in, out, new Scanner(in),
            new MockFlexiModel(log, true, "uniqueCode", 0.0));
    controller.goController();
    assertEquals("Input: test testCreate 2022-11-15 test testCreate 2022-11-15 test " +
            "testCreate 2022-11-15 2022-11-15", log.toString().trim());
    assertEquals("Output: printFlexiblePortfolioMenu printEnterPortfolioName " +
                    "printEnterDate printErrorFutureDate printFlexiblePortfolioMenu exitProgram"
            , logV.toString().trim());
  }

  @Test
  public void testCalculateCostBasisOnCertainDate() {
    in = new ByteArrayInputStream(("6 testCreate 2022-11-15 10 8 3 2")
            .getBytes());
    controller = new ControllerFlexi("test", view, in, out, new Scanner(in),
            new MockFlexiModel(log, true, "uniqueCode", 0.0));
    controller.goController();
    assertEquals("Input: test testCreate 2022-11-15 test testCreate 2022-11-15 test " +
                    "testCreate 2022-11-15 test testCreate testCreate 10.0 2022-11-15",
            log.toString().trim());
    assertEquals("Output: printFlexiblePortfolioMenu printEnterPortfolioNameToOpen " +
            "printEnterDateCostBasis askForCommissionFee printTotalCostBasis: uniqueCode " +
            "printFlexiblePortfolioMenu exitProgram", logV.toString().trim());
  }

  @Test
  public void testViewPerformance() {
    in = new ByteArrayInputStream(("7 testCreate 2022-11-01 2022-11-15 8 3 2")
            .getBytes());
    controller = new ControllerFlexi("test", view, in, out, new Scanner(in),
            new MockFlexiModel(log, true, "uniqueCode", 0.0));
    controller.goController();
    assertEquals("Input: test test testCreate 2022-11-01 test testCreate 2022-11-01 " +
            "2022-11-01", log.toString().trim());
    assertEquals("Output: printFlexiblePortfolioMenu printEnterPortfolioNameToOpen " +
                    "printEnterFromDate printErrorFutureDate printFlexiblePortfolioMenu " +
                    "printInvalidMainMenuOption printFlexiblePortfolioMenu exitProgram",
            logV.toString().trim());
  }


}