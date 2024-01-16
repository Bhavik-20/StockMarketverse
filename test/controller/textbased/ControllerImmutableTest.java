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

public class ControllerImmutableTest {

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
  public void testCreateNewPortfolio() {
    in = new ByteArrayInputStream("1 testCreate GOOG 10 5 3 2".getBytes());
    controller = new ControllerImmutable("test", view, in, out, new Scanner(in),
            new MockImmuModel(log, true, "uniqueCode", 0.0));
    controller.goController();
    assertEquals("Input: test testCreate test testCreate", log.toString());
    assertEquals("Output: printMainMenu printEnterNewPortfolioName printChooseOtherName" +
                    " printMainMenu printInvalidMainMenuOption printMainMenu " +
                    "printInvalidMainMenuOption printMainMenu exitProgram",
            logV.toString().trim());
  }


  @Test
  public void testCreateNewPortfolioInvalidName() {
    in = new ByteArrayInputStream("1 test/invalid 5 3 2".getBytes());
    controller = new ControllerImmutable("test", view, in, out, new Scanner(in),
            new MockImmuModel(log, true, "uniqueCode", 0.0));
    controller.goController();
    assertEquals("Input: test test/invalid test test/invalid", log.toString());
    assertEquals("Output: printMainMenu printEnterNewPortfolioName printChooseOtherName" +
                    " printMainMenu exitProgram",
            logV.toString().trim());
  }

  @Test
  public void testCreateNewPortfolioInvalidQty() {
    in = new ByteArrayInputStream("1 invalidQty GOOG -10 GOOG 10 n 5 3 2".getBytes());
    controller = new ControllerImmutable("test", view, in, out, new Scanner(in),
            new MockImmuModel(log, true, "uniqueCode", 0.0));
    controller.goController();
    assertEquals("Input: test invalidQty test invalidQty", log.toString());
    assertEquals("Output: printMainMenu printEnterNewPortfolioName printChooseOtherName" +
                    " printMainMenu printInvalidMainMenuOption printMainMenu " +
                    "printInvalidMainMenuOption" +
                    " printMainMenu printInvalidMainMenuOption printMainMenu" +
                    " printInvalidMainMenuOption" +
                    " printMainMenu printInvalidMainMenuOption printMainMenu exitProgram",
            logV.toString().trim());
  }

  @Test
  public void testCreateNewPortfolioInvalidTicker() {
    in = new ByteArrayInputStream("1 invalidTicker GOG 10 GOOG 10 n 5 3 2".getBytes());
    controller = new ControllerImmutable("test", view, in, out, new Scanner(in),
            new MockImmuModel(log, true, "uniqueCode", 0.0));
    controller.goController();
    assertEquals("Input: test invalidTicker test invalidTicker", log.toString());
    assertEquals("Output: printMainMenu printEnterNewPortfolioName " +
                    "printChooseOtherName printMainMenu printInvalidMainMenuOption printMainMenu" +
                    " printInvalidMainMenuOption printMainMenu printInvalidMainMenuOption " +
                    "printMainMenu printInvalidMainMenuOption printMainMenu printInvalid" +
                    "MainMenuOption printMainMenu exitProgram",
            logV.toString().trim());
  }

  @Test
  public void testUploadPortfolio() {
    in = new ByteArrayInputStream(("2 E:/Profile/desktop/PDP/Group/" +
            "Assignment-4_Stocks_1/data/Muskaan/muskaan.xml 5 2").getBytes());
    controller = new ControllerImmutable("test", view, in, out, new Scanner(in),
            new MockImmuModel(log, true, "uniqueCode", 0.0));
    controller.goController();
    assertEquals("Input: test E:/Profile/desktop/PDP/Group/Assignment-4_Stocks_1/data" +
            "/Muskaan/muskaan.xml E:/Profile/desktop/PDP/Group/Assignment-4_Stocks_1/data" +
            "/Muskaan/muskaan.xml test E:/Profile/desktop/PDP/Group/Assignment-4_Stock" +
            "s_1/data/Muskaan/muskaan.xml test muskaan  test muskaan  test muskaan" +
            "  test muskaan", log.toString().trim());
    assertEquals("Output: printMainMenu printAskForUploadPath printPortfolioUploaded" +
            " printFileComposition: uniqueCode printMainMenu exitProgram", logV.toString().trim());
    //mainModCheckPathExists, checkFileIsXML, mainModUploadPortfolio
  }


  @Test
  public void testUploadPortfolioWrongFileType() {
    in = new ByteArrayInputStream(("1 muskaan 2 listOfStock.csv 5 2").getBytes());
    controller = new ControllerImmutable("test", view, in, out, new Scanner(in),
            new MockImmuModel(log, true, "uniqueCode", 0.0));
    controller.goController();
    assertEquals("Input: test muskaan test muskaan listOfStock.csv listOfStock.csv test" +
                    " listOfStock.csv test listOfStock.csv  test listOfStock.csv  test " +
                    "listOfStock.csv  test listOfStock.csv",
            log.toString().trim());
    assertEquals("Output: printMainMenu printEnterNewPortfolioName printChooseOtherName" +
            " printMainMenu printAskForUploadPath printPortfolioUploaded printFileComposition:" +
            " uniqueCode printMainMenu exitProgram ", logV.toString());
    //mainModCheckPathExists, checkFileIsXML, mainModUploadPortfolio
  }


  @Test
  public void testViewComposition() {
    in = new ByteArrayInputStream(("3 m 5 3 2").getBytes());
    controller = new ControllerImmutable("test", view, in, out, new Scanner(in),
            new MockImmuModel(log, true, "uniqueCode", 0.0));
    controller.goController();
    assertEquals("Input: test test m  test m  test m  test m", log.toString().trim());
    assertEquals("Output: printMainMenu printEnterPortfolioNameToOpen " +
            "printFileComposition: uniqueCode printMainMenu exitProgram ", logV.toString());
  }

  @Test
  public void testExamineValue() {
    in = new ByteArrayInputStream(("4 muskaan 2022-11-01 5 2").getBytes());
    controller = new ControllerImmutable("test", view, in, out, new Scanner(in),
            new MockImmuModel(log, true, "uniqueCode", 0.0));
    controller.goController();
    assertEquals("Input: test muskaan 2022-11-01 test muskaan 2022-11-01 test muskaan" +
                    " 2022-11-01 2022-11-01"
            , log.toString().trim());
    assertEquals("Output: printMainMenu printEnterPortfolioName printEnterDate " +
            "printErrorFutureDate printMainMenu exitProgram", logV.toString().trim());
  }

  @Test
  public void testInvalidStructureInUpload() {
    in = new ByteArrayInputStream(("2 E:/Profile/desktop/PDP/incorrect-format-1.xml 5 3 2")
            .getBytes());
    controller = new ControllerImmutable("test", view, in, out, new Scanner(in),
            new MockImmuModel(log, true, "uniqueCode", 0.0));
    controller.goController();
    assertEquals("Input: test E:/Profile/desktop/PDP/incorrect-format-1.xml E:/Profile/" +
            "desktop/PDP/incorrect-format-1.xml test E:/Profile/desktop/PDP/incorrect-format" +
            "-1.xml test incorrect-format-1  test incorrect-format-1  test incorrect-format-1  " +
            "test incorrect-format-1", log.toString().trim());
    assertEquals("Output: printMainMenu printAskForUploadPath printPortfolioUploaded " +
            "printFileComposition: uniqueCode printMainMenu exitProgram", logV.toString().trim());
  }

  @Test
  public void testInvalidTickerUploadFile() {
    in = new ByteArrayInputStream(("2 E:/Profile/desktop/PDP/invalid-ticker-1.xml 5 3 2")
            .getBytes());
    controller = new ControllerImmutable("test", view, in, out, new Scanner(in),
            new MockImmuModel(log, true, "uniqueCode", 0.0));
    controller.goController();
    assertEquals("Input: test E:/Profile/desktop/PDP/invalid-ticker-1.xml E:/Profile/" +
                    "desktop/PDP/invalid-ticker-1.xml test E:/Profile/desktop/PDP/invalid-ticker" +
                    "-1.xml test invalid-ticker-1  test invalid-ticker-1  test invalid-ticker-1" +
                    "  test invalid-ticker-1"
            , log.toString().trim());
    assertEquals("Output: printMainMenu printAskForUploadPath printPortfolioUploaded" +
            " printFileComposition: uniqueCode printMainMenu exitProgram", logV.toString().trim());
  }
}