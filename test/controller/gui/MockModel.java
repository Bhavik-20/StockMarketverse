package controller.gui;

import java.util.List;

/**
 * This class represents a Mock of the MainFlexiModel to aid the isolation testing
 * of ControllerGuiImpl.
 */

public class MockModel extends MockAbstractModel {

  private final String uniqueCode;

  private final double uniqueDouble;

  /**
   * Constructor initializes all the fields of the AbstractMainModel object.
   *
   * @param log        signifies a log to check all methods have been called
   * @param uniqueBool signifies a random boolean value
   * @param uniqueCode signifies a random unique string
   */
  public MockModel(StringBuilder log, boolean uniqueBool, String uniqueCode, double uniqueDouble) {
    super(log, uniqueBool);
    this.uniqueCode = uniqueCode;
    this.uniqueDouble = uniqueDouble;
  }

  @Override
  public String checkValidDateAndName(String uname, String portfolioName, String date) {
    log.append(" " + uname + " " + portfolioName + " " + date);
    return "valid";
  }

  @Override
  public boolean createUserDirectory(String uname) {
    log.append(" " + uname);
    return true;
  }

  @Override
  public String mainModGetValueOnDate(String uname, String portfolioName, String date) {
    log.append(" " + uname + " " + portfolioName + " " + date);
    return "1000";
  }

  @Override
  public boolean mainModCheckPortfolioNameExists(String uname, String portfolioName) {
    log.append(" " + uname + " " + portfolioName);
    return uniqueBool;
  }

  @Override
  public void mainModConvertAllStocksToStockObj() {
    log.append(" mainModConvertAllStocksToStockObj");
  }

  private void convertAllStocksToStockObj() {
    log.append(" convertAllStocksToStockObj");
  }

  @Override
  public boolean mainModCreatePortfolio(String uname, String portfolioName) {
    log.append(" " + uname + " " + portfolioName);
    return uniqueBool;
  }

  @Override
  public boolean mainModUploadPortfolio(String uname, String path) {
    log.append(" " + uname + " " + path);
    return uniqueBool;
  }

  @Override
  public String mainModViewPortfolio(String uname, String portfolioName, String date) {
    log.append(" " + uname + " " + portfolioName + " " + date);
    return uniqueCode;
  }

  @Override
  public void deleteFile(String uname, String fname) {
    log.append(" " + uname + " " + fname);
  }

  @Override
  public boolean mainModHandleData(String stockName, String stockQty) {
    log.append(" " + stockName + " " + stockQty);
    return uniqueBool;
  }

  @Override
  public boolean addStockToPortfolio(String stockName, String stockQty, String purDate,
                                     String commFee, int flag) {
    log.append(" " + stockName + " " + stockQty + " " + purDate);
    return uniqueBool;
  }

  @Override
  public String removeStockFromPortfolio(String stockName, String stockQty, String purDate,
                                         String commFee) {
    log.append(" " + stockName + " " + stockQty + " " + purDate);
    return uniqueCode;
  }

  @Override
  public boolean loadPortfolio(String uname, String portfolioName) {
    log.append(" " + uname + " " + portfolioName);
    return uniqueBool;
  }

  @Override
  public String calculateCostBasis(String portfolioName, String date) {
    log.append(" " + portfolioName + " " + date);
    return uniqueCode;
  }

  @Override
  public boolean returnPerformanceTimestampScale(String fromDate, String toDate) {
    log.append(" " + fromDate + " " + toDate);
    return uniqueBool;
  }

  @Override
  public double getScaleBarGraph(String fromDate, String tillDate) {
    log.append(" " + fromDate + " " + tillDate);
    return uniqueDouble;
  }

  @Override
  public List<String> getTimeStampForYaxis() {
    log.append(" getTimeStampForYaxis");
    return null;
  }

  @Override
  public List<Integer> getNumOfStars(double scale) {
    log.append(" " + scale);
    return null;
  }

  @Override
  public boolean calcQuantityOfUploadedFile() {
    log.append("calcQuantityOfUploadedFile ");
    return uniqueBool;
  }

  @Override
  public boolean checkAmount(double commissionFee) {
    log.append(" " + commissionFee);
    return uniqueBool;
  }

  @Override
  public String investFixedAmt(String uname, String portfolioName, String maps,
                               String purDate, String commFee, String investValue) {
    log.append(" " + uname + " " + portfolioName + " " + maps + " " + purDate + " " + commFee + " "
            + investValue);
    return uniqueCode;
  }

  @Override
  public boolean checkInvestWeightFormat(String stockW) {
    log.append(" " + stockW);
    return uniqueBool;
  }

  @Override
  public String applyDCAOnPortfolio(String uname, String portfolioName, String startDate,
                                    String endDate, String iVal, String interval, String commFee,
                                    String tickerWts) {
    log.append("" + uname + " " + portfolioName + " " + startDate + " " + endDate + " " + iVal
            + " " + interval + " " + commFee + " " + tickerWts);
    return uniqueCode;
  }
}
