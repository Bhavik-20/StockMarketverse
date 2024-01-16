package controller.gui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import view.gui.GuiView;

/**
 * This class represents a Mock of the GuiViewImpl to aid the isolation testing
 * of ControllerGuiImpl.
 */
public class MockGuiView implements GuiView {
  private StringBuilder logV;

  private Map<String, String> hashmap;

  MockGuiView(StringBuilder logV) {
    this.logV = logV;
  }

  @Override
  public void setAddPanel(String ch) {
    logV.append("Set Add Panel: " + ch + " - ");
  }

  @Override
  public void setRemovePanel(String ch) {
    logV.append("Set Remove Panel: " + ch + " - ");
  }

  @Override
  public void addFeatures(ControllerGui controllerGui) {
    logV.append("addFeatures: - ");
  }

  @Override
  public void showMessage(String msg) {
    logV.append("showMessage: " + msg + " - ");
  }

  @Override
  public void showError(String error) {
    logV.append("showError: " + error + " - ");
  }

  @Override
  public void showComposition(String composition) {
    logV.append("showComposition: " + composition + " - ");
  }

  @Override
  public void reset(String ch) {
    logV.append("reset: " + ch + " - ");
  }

  @Override
  public void printBarChart(String startDate, String endDate, String portfolioName,
                            List<String> xaxis, List<Integer> yaxis, double scale) {
    logV.append("printBarChart: " + startDate + " " + endDate + " " + portfolioName + " "
            + xaxis + " " + yaxis + " " + scale + " - ");
  }

  @Override
  public Map<String, String> getPortfolioNameCreate() {
    logV.append("getPortfolioNameCreate - ");
    hashmap = new HashMap<>();
    hashmap.put("portfolioName", "test");
    return hashmap;
  }

  @Override
  public String getUploadPath() {
    logV.append("getUploadPath - ");
    return "E:\\Profile\\desktop\\PDP\\valid-2.xml";
  }

  @Override
  public Map<String, String> getPortfolioNameExisting() {
    logV.append(" getPortfolioNameExisting -");
    hashmap = new HashMap<>();
    hashmap.put("portfolioName", "test2");
    return hashmap;
  }

  @Override
  public Map<String, String> getDatePortfolioValue() {
    logV.append("getDatePortfolioValue - ");
    hashmap = new HashMap<>();
    hashmap.put("date_portfolioValue", "2022-12-01");
    return hashmap;
  }

  @Override
  public Map<String, String> getDateViewPortfolio() {
    logV.append("getDateViewPortfolio - ");
    hashmap = new HashMap<>();
    hashmap.put("date_viewPortfolio", "2022-12-01");
    return hashmap;
  }

  @Override
  public Map<String, String> getTransactionInputs() {
    logV.append("getTransactionInputs - ");
    hashmap = new HashMap<>();
    hashmap.put("transType", "Buy Stocks");
    hashmap.put("ticker", "GOOG");
    hashmap.put("quantity", "10");
    hashmap.put("date_Transaction", "2022-11-01");
    hashmap.put("commissionFee", "10");
    return hashmap;
  }

  @Override
  public Map<String, String> getCostBasisInputs() {
    logV.append("getCostBasisInputs - ");
    hashmap = new HashMap<>();
    hashmap.put("date_costBasis", "2022-12-01");
    return hashmap;
  }

  @Override
  public Map<String, String> getInvestFixedAmtInputs() {
    logV.append("getInvestFixedAmtInputs - ");
    hashmap = new HashMap<>();
    hashmap.put("investAmt", "1000");
    hashmap.put("commFee", "2");
    hashmap.put("tickerWts", "GOOG 50, META 50");
    hashmap.put("date_IFA", "2022-01-01");
    return hashmap;
  }

  @Override
  public Map<String, String> getDCAInputs() {
    logV.append("getDCAInputs - ");
    hashmap = new HashMap<>();
    hashmap.put("portfolioname_DCA", "testCreateDCA");
    hashmap.put("startDate", "2022-01-01");
    hashmap.put("endDate", "2022-11-01");
    hashmap.put("freq", "15 days");
    hashmap.put("investAmt", "1000");
    hashmap.put("commFee", "10");
    hashmap.put("tickerWts", "GOOG 50, AAPL 50");
    return hashmap;
  }

  @Override
  public Map<String, String> getPerformanceInputs() {
    logV.append("getPerformanceInputs - ");
    hashmap = new HashMap<>();
    hashmap.put("startDate_pfm", "2022-01-01");
    hashmap.put("endDate_pfm", "2022-11-01");
    return hashmap;
  }

  @Override
  public Map<String, String> getCreateDCA() {
    logV.append("getCreateDCA -");
    hashmap = new HashMap<>();
    hashmap.put("portfolioname_DCA", "testCreateDCA");
    hashmap.put("startDate", "2022-01-01");
    hashmap.put("endDate", "2022-11-01");
    hashmap.put("freq", "15 days");
    hashmap.put("investAmt", "1000");
    hashmap.put("commFee", "10");
    hashmap.put("tickerWts", "GOOG 50, AAPL 50");
    return hashmap;
  }

  @Override
  public Map<String, String> getUsernameInput() {
    logV.append("getUsernameInput - ");
    hashmap = new HashMap<>();
    hashmap.put("username", "test");
    return hashmap;
  }
}
