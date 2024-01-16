package controller.gui;

import org.junit.Before;
import org.junit.Test;

import view.gui.GuiView;

import static org.junit.Assert.assertEquals;

/**
 * A Junit class that tests the ControllerGuiImpl class.
 */
public class ControllerGuiImplTest {
  GuiView view;

  ControllerGui controller;
  StringBuilder log;
  StringBuilder logV;

  @Before
  public void setUp() throws Exception {
    log = new StringBuilder();
    log.append("Input:");

    logV = new StringBuilder();
    logV.append("Output: ");
    view = new MockGuiView(logV);
  }

  @Test
  public void testSubmitUserName() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("Username");
    assertEquals("Output: addFeatures: - getUsernameInput - Set Remove Panel: Page1 - " +
            "Set Remove Panel: remove_all - Set Add Panel: Page2 - ", logV.toString());
    assertEquals("Input: test test", log.toString());
  }

  @Test
  public void testCreatePortfolioPortName() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("Portfolio Name");
    assertEquals("Output: addFeatures: - reset: Page2 - Set Remove Panel: remove_all - " +
                    "Set Add Panel: Page2 - Set Add Panel: Page2b - ",
            logV.toString());
    assertEquals("Input:", log.toString());
  }

  @Test
  public void testCreatePortfolio() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("Create Portfolio");
    assertEquals("Output: addFeatures: - getPortfolioNameCreate - getUsernameInput - " +
                    "showError: Error: Portfolio with this name already exist! - ",
            logV.toString());
    assertEquals("Input: test test test", log.toString());
  }

  @Test
  public void testUploadPortfolio() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("Upload Portfolio");
    assertEquals("Output: addFeatures: - Set Remove Panel: remove_all - Set Add Panel:" +
                    " Dialog_path - getUploadPath - getUsernameInput - Set Add Panel: Page2 - " +
                    "showMess" +
                    "age: Portfolio Uploaded Successfully! - showComposition: uniqueCode - ",
            logV.toString());
    assertEquals("Input: E:\\Profile\\desktop\\PDP\\valid-2.xml E:\\Profile\\desktop" +
            "\\PDP\\valid-2.xml test E:\\Profile\\desktop\\PDP\\valid-2.xml test E:\\Profile" +
            "\\desktop\\PDP\\valid-2 2022-12-01 test E:\\Profile\\desktop\\PDP\\valid-2 2022-" +
            "12-01 test E:\\Profile\\desktop\\PDP\\valid-2 2022-12-01 test E:\\Profile\\desktop" +
            "\\PDP\\valid-2calcQuantityOfUploadedFile  test E:\\Profile\\desktop\\PDP\\valid-2" +
            " 2022-12-01", log.toString());
  }

  @Test
  public void testExistingPortfolioName() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("Existing Portfolio Name");
    assertEquals("Output: addFeatures: - reset: Page2b_existing - Set Remove Panel: " +
                    "remove_all - Set Add Panel: Page2 - Set Add Panel: Page2b_existing - ",
            logV.toString());
    assertEquals("Input:", log.toString());
  }

  @Test
  public void testExistingPortfolio() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("Existing Portfolio");
    assertEquals("Output: addFeatures: -  getPortfolioNameExisting -getUsernameInput" +
                    " - Set Remove Panel: remove_all - Set Add Panel: Page3 - ",
            logV.toString());
    assertEquals("Input: test test2 test test2", log.toString());
  }

  @Test
  public void testPortfolioValue() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("Portfolio Value");
    assertEquals("Output: addFeatures: - reset: Page3_GetPortfolioValue - " +
                    "Set Remove Panel: remove_all - Set Add Panel: Page3 - Set Add Panel: " +
                    "Page3_GetPortfolioValue - ",
            logV.toString());
    assertEquals("Input:", log.toString());
  }

  @Test
  public void testPortfolioValueDate() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("Portfolio Value Date");
    assertEquals("Output: addFeatures: -  getPortfolioNameExisting -getUsernameInput" +
                    " - getDatePortfolioValue - showError: Error: The entered date is in the" +
                    " future. Please enter a past date. - ",
            logV.toString());
    assertEquals("Input: test test2 2022-12-01 test test2 2022-12-01 test test2 " +
            "2022-12-01 2022-12-01", log.toString());
  }

  @Test
  public void testMakeTx() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("Make Transaction");
    assertEquals("Output: addFeatures: - reset: Page3_MakeTx -  getPortfolioName_" +
                    "Existing -getUsernameInput - Set Remove Panel: remove_all - Set Add Panel:" +
                    " Page3 - Set Add Panel: Page3_MakeTx - ",
            logV.toString());
    assertEquals("Input: test test2", log.toString());
  }

  @Test
  public void testBuyStock() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("Buy Stock");
    assertEquals("Output: addFeatures: - getTransactionInputs - getUsernameInput - " +
                    " getPortfolioNameExisting -getTransactionInputs - showError: Error: The" +
                    " entered date is in the future. - ",
            logV.toString());
    assertEquals("Input: test test2 2022-11-01 test test2 2022-11-01 test test2 " +
            "2022-11-01 2022-11-01", log.toString());
  }

  @Test
  public void testSellStock() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("Sell Stock");
    assertEquals("Output: addFeatures: - getUsernameInput -  getPortfolioNameExisting" +
                    " -getTransactionInputs - showError: Error: The entered date is in the" +
                    " future. - ",
            logV.toString());
    assertEquals("Input: test test2 2022-11-01 test test2 2022-11-01" +
            " 2022-11-01", log.toString());
  }

  @Test
  public void testSaveTx() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("Save Tx");
    assertEquals("Output: addFeatures: - getUsernameInput -  getPortfolioNameExisting" +
                    " -showMessage: \n" +
                    "SUCCESS: The list of transaction/s have been logged in the portfolio " +
                    "successfully!\n" +
                    " - Set Remove Panel: remove_all - Set Add Panel: Page3 - ",
            logV.toString());
    assertEquals("Input: mainModConvertAllStocksToStockObj test test2 " +
            "test test2", log.toString());
  }

  @Test
  public void testViewPortfolio() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("View Portfolio");
    assertEquals("Output: addFeatures: - reset: Page3_ViewPortfolio - Set Remove Panel:" +
                    " remove_all - Set Add Panel: Page3 - Set Add Panel: Page3_ViewPortfolio - ",
            logV.toString());
    assertEquals("Input:", log.toString());
  }

  @Test
  public void testViewComposition() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("View Composition");
    assertEquals("Output: addFeatures: - getUsernameInput -  getPortfolioNameExisting" +
                    " -getDateViewPortfolio - showError: Error: The entered date is in the " +
                    "future. - ",
            logV.toString());
    assertEquals("Input: 2022-12-01 2022-12-01", log.toString());
  }

  @Test
  public void testCostBasis() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("Cost Basis");
    assertEquals("Output: addFeatures: - getUsernameInput -  getPortfolioNameExisting" +
                    " -getCostBasisInputs - showMessage: Cost basis: uniqueCode - Set Remove " +
                    "Panel: remove_all - Set Add Panel: Page3 - ",
            logV.toString());
    assertEquals("Input: test test2 2022-12-01 test test2 2022-12-01 test test2" +
            " 2022-12-01 test2 2022-12-01", log.toString());
  }


  @Test
  public void testPerformanceAnalysis() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("Performance Analysis");
    assertEquals("Output: addFeatures: - reset: Page3_GetDatesPerformanceAnalysis - Set " +
                    "Remove Panel: remove_all - Set Add Panel: Page3 - Set Add Panel: " +
                    "Page3_GetDatesPerformanceAnalysis - ",
            logV.toString());
    assertEquals("Input:", log.toString());
  }

  @Test
  public void testVisualizePerformance() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("Visualize Performance");
    assertEquals("Output: addFeatures: - getUsernameInput -  getPortfolioNameExisting" +
                    " -getPerformanceInputs - Set Remove Panel: Page3_GetDatesPerformanceAn" +
                    "alysis - showError: Error: The entered start date is in the future. Please" +
                    " enter a past date. - ",
            logV.toString());
    assertEquals("Input: 2022-01-01 test test2 2022-01-01 2022-01-01", log.toString());
  }

  @Test
  public void testCreatePortfolioDCA() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("Create Portfolio DCA");
    assertEquals("Output: addFeatures: - reset: Page3_CreatePortfolioDCA - Set Remove" +
                    " Panel: remove_all - Set Add Panel: Page3_CreatePortfolioDCA - ",
            logV.toString());
    assertEquals("Input:", log.toString());
  }

  @Test
  public void testCostBasisDate() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("Cost Basis Date");
    assertEquals("Output: addFeatures: - reset: Page3_GetCostBasisValue - Set Remove" +
                    " Panel: remove_all - Set Add Panel: Page3 - Set Add Panel: " +
                    "Page3_GetCostBasisValue - ",
            logV.toString());
    assertEquals("Input:", log.toString());
  }

  @Test
  public void testViewDCAOutput() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("DCA_show");
    assertEquals("Output: addFeatures: - reset: Page3_GetDCAInputs - Set Remove" +
                    " Panel: remove_all - Set Add Panel: Page3 - Set Add Panel:" +
                    " Page3_GetDCAInputs - ",
            logV.toString());
    assertEquals("Input:", log.toString());
  }

  @Test
  public void testViewDCAsubmit() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("DCA_submit");
    assertEquals("Output: addFeatures: - getUsernameInput -  " +
                    "getPortfolioNameExisting -getDCAInputs - showError: uniqueCode - ",
            logV.toString());
    assertEquals("Input: test test2 2022-01-01 2022-11-01 test test2 2022-01-01" +
                    " 10.0 1000.0test test2 2022-01-01 2022-11-01 1000 15 days 10 GOOG 50, AAPL 50",
            log.toString());
  }

  @Test
  public void testViewIFA() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("IFA_show");
    assertEquals("Output: addFeatures: - reset: Page3_InvestFixedAmt - Set Remove" +
                    " Panel: remove_all - Set Add Panel: Page3 - Set Add Panel: " +
                    "Page3_InvestFixedAmt - ",
            logV.toString());
    assertEquals("Input:", log.toString());
  }

  @Test
  public void testViewIFAOutput() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("IFA_submit");
    assertEquals("Output: addFeatures: - getUsernameInput -  getPortfolioNameExisting" +
                    " -getInvestFixedAmtInputs - showError: uniqueCode - ",
            logV.toString());
    assertEquals("Input: test test2 2022-01-01 test test2 2022-01-01 test test2" +
            " 2022-01-01 2.0 1000.0 GOOG 50, META 50 test test2 GOOG 50, META 50 2022-01-01" +
            " 2 1000", log.toString());
  }

  @Test
  public void testBactToWp() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("Back To Welcome Pg");
    assertEquals("Output: addFeatures: - reset: Page1 - Set Remove Panel: remove_all" +
                    " - Set Add Panel: Page1 - ",
            logV.toString());
    assertEquals("Input:", log.toString());
  }

  @Test
  public void testBackToPortfolioOPtion() {
    controller = new ControllerGuiImpl(new MockModel(log, true, "uniqueCode",
            100.0), view);
    controller.goGui("BackFromCreateDCA");
    assertEquals("Output: addFeatures: - reset: Page2 - reset: Page2b_existing - " +
                    "Set Remove Panel: remove_all - Set Add Panel: Page2 - ",
            logV.toString());
    assertEquals("Input:", log.toString());
  }


}

