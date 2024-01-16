package controller.textbased;

import java.util.List;

/**
 * This class is a Mock model for the actual MainFlexibleModel to help test controller in isolation.
 */
public class MockFlexiModel extends MockAbstractModel {


  //  private PerformanceModel pModel;
  private final String uniqueCode;

  private final double uniqueDouble;

  /**
   * The constructor initializes all the fields of the MainFlexibleModel object.
   */
  public MockFlexiModel(StringBuilder log, boolean uniqueBool, String uniqueCode,
                        double uniqueDouble) {
    super(log, uniqueBool);
    this.uniqueCode = uniqueCode;
    this.uniqueDouble = uniqueDouble;
  }

  @Override
  public String checkValidDateAndName(String uname, String portfolioName, String date) {
    log.append(" " + uname + " " + portfolioName + " " + date);
    return uniqueCode;
  }

  @Override
  public boolean createUserDirectory(String uname) {
    log.append(" " + uname);
    return uniqueBool;
  }

  @Override
  public String mainModGetValueOnDate(String uname, String portfolioName, String date) {
    log.append(" " + uname + " " + portfolioName + " " + date);
    return uniqueCode;
  }

  @Override
  public boolean mainModCheckPortfolioNameExists(String uname, String portfolioName) {
    log.append(" " + uname + " " + portfolioName);
    return uniqueBool;
  }

  @Override
  public void mainModConvertAllStocksToStockObj() {
    // ?
  }

  private void convertAllStocksToStockObj() {
    // ?
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
  public boolean addStockToPortfolio(String stockName, String stockQty, String purDate, String
          commFee, int flag) {
    log.append(" " + stockName + " " + stockQty + " " + purDate);
    return uniqueBool;
  }

  @Override
  public String removeStockFromPortfolio(String stockName, String stockQty, String purDate, String
          commFee) {
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
    return null;
  }

  @Override
  public List<Integer> getNumOfStars(double scale) {
    log.append(" " + scale);
    return null;
  }

  @Override
  public boolean calcQuantityOfUploadedFile() {
    return uniqueBool;
  }

  @Override
  public boolean checkAmount(double commissionFee) {
    return uniqueBool;
  }

  @Override
  public String investFixedAmt(String uname, String portfolioName,
                               String maps, String purDate, String commFee, String investValue) {
    return uniqueCode;
  }

  @Override
  public boolean checkInvestWeightFormat(String stockW) {
    return uniqueBool;
  }

  @Override
  public String applyDCAOnPortfolio(String uname, String portfolioName, String startDate, String
          endDate, String iVal, String interval, String commFee, String tickerWts) {
    return uniqueCode;
  }

}
