package controller.textbased;

import java.util.List;

import view.textbased.View;

/**
 * Mock Class to replicate the behaviour of the View in order to test Controller in isolation.
 */
class MockView implements View {

  private StringBuilder log;

  public MockView(StringBuilder logV) {
    this.log = logV;
  }

  @Override
  public void printWelcome() {
    log.append("printWelcome ");
  }

  @Override
  public void printMainMenu() {
    log.append("printMainMenu ");
  }

  @Override
  public void printEnterStockName() {
    log.append("printEnterStockName ");
  }

  @Override
  public void printEnterStockQty() {
    log.append("printEnterStockQty ");
  }

  @Override
  public void printAddMoreStocksToPortfolio() {
    log.append("printAddMoreStocksToPortfolio ");

  }

  @Override
  public void printInvalidStockData() {
    log.append("printInvalidStockData ");
  }

  @Override
  public void printInvalidMainMenuOption() {
    log.append("printInvalidMainMenuOption ");
  }

  @Override
  public void printAskForUploadPath() {
    log.append("printAskForUploadPath ");
  }

  @Override
  public void printFileCreationFailed() {
    log.append("printFileCreationFailed ");
  }

  @Override
  public void printFileCreated() {
    log.append("printFileCreated ");
  }

  @Override
  public void printFileNotFound() {
    log.append("printFileNotFound ");
  }

  @Override
  public void printInvalidFileDirectory() {
    log.append("printInvalidFileDirectory ");
  }

  @Override
  public void printInvalidUploadFileType() {
    log.append("printInvalidUploadFileType ");
  }

  @Override
  public void printFileComposition(String composition) {
    log.append("printFileComposition: " + composition + " ");
  }

  @Override
  public void printPortfolioUploaded() {
    log.append("printPortfolioUploaded ");
  }

  @Override
  public void printErrorPortfolioUpload() {
    log.append("printErrorPortfolioUpload ");
  }

  @Override
  public void printEnterNewPortfolioName() {
    log.append("printEnterNewPortfolioName ");
  }

  @Override
  public void printEnterPortfolioNameToOpen() {
    log.append("printEnterPortfolioNameToOpen ");
  }

  @Override
  public void printChooseOtherName() {
    log.append("printChooseOtherName ");
  }

  @Override
  public void printEnterPortfolioName() {
    log.append("printEnterPortfolioName ");
  }

  @Override
  public void printEnterDate() {
    log.append("printEnterDate ");
  }

  @Override
  public void printIncorrectDateFormat() {
    log.append("printIncorrectDateFormat ");
  }

  @Override
  public void printUserMenu() {
    log.append("printUserMenu ");
  }

  @Override
  public void printErrorFutureDate() {
    log.append("printErrorFutureDate ");
  }

  @Override
  public void printPortfolioValueOnDate(String val) {
    log.append("printPortfolioValueOnDate ");
  }

  @Override
  public void askForUserName() {
    log.append("askForUserName ");
  }

  @Override
  public void printErrorInUserCreation() {
    log.append("printErrorInUserCreation ");
  }

  @Override
  public void exitProgram(int i) {
    log.append("exitProgram ");
  }

  @Override
  public void printInvalidFileNameFormat() {
    log.append("printInvalidFileNameFormat ");
  }

  @Override
  public void printInvalidDateFormat() {
    log.append("printInvalidDateFormat ");
  }

  @Override
  public void printSuccessNewFile() {
    log.append("printSuccessNewFile ");
  }

  @Override
  public void printErrorHoliday() {
    log.append("printErrorHoliday ");
  }

  @Override
  public void printIncorrectFileFormatUploaded() {
    log.append("printIncorrectFileFormatUploaded ");
  }

  @Override
  public void printTodayDateCondition() {
    log.append("printTodayDateCondition ");
  }

  @Override
  public void printPortfolioMenu() {
    log.append("printPortfolioMenu ");
  }

  @Override
  public void printFlexiblePortfolioMenu() {
    log.append("printFlexiblePortfolioMenu ");
  }

  @Override
  public void printWhichTransaction() {
    log.append("printWhichTransaction ");
  }

  @Override
  public void printEnterPurchaseDate() {
    log.append("printEnterPurchaseDate ");
  }

  @Override
  public void printEnterSellDate() {
    log.append("printEnterSellDate ");
  }

  @Override
  public void printEnterFromDate() {
    log.append("printEnterFromDate ");
  }

  @Override
  public void printEnterTillDate() {
    log.append("printEnterTillDate ");
  }

  @Override
  public void printPerformancePlotTopic(String portfolioName, String fromDate, String tillDate) {
    log.append("printPerformancePlotTopic: " + portfolioName + " " + fromDate + " " + tillDate
            + " ");
  }

  @Override
  public void printPerformancePlotScale(String scale) {
    log.append("printPerformancePlotScale: " + scale + " ");
  }

  @Override
  public void printPortfolioDeleted() {
    log.append("printPortfolioDeleted ");
  }

  @Override
  public void printTodayBuyCondition() {
    log.append("printTodayBuyCondition ");
  }

  @Override
  public void printErrorLoadingPortfolio() {
    log.append("printErrorLoadingPortfolio ");
  }

  @Override
  public void printStockAddedToList() {
    log.append("printStockAddedToList ");
  }

  @Override
  public void printStockSold() {
    log.append("printStockSold ");
  }

  @Override
  public void printFileUpdated() {
    log.append("printFileUpdated ");
  }

  @Override
  public void printNegativeQuantity() {
    log.append("printNegativeQuantity ");
  }

  @Override
  public void printFractionalQuantity() {
    log.append("printFractionalQuantity ");
  }

  @Override
  public void printStocksNotAvailableToSell() {
    log.append("printStocksNotAvailableToSell ");
  }

  @Override
  public void printInvalidTicker() {
    log.append("printInvalidTicker ");
  }

  @Override
  public void printStockAlreadySoldInFuture() {
    log.append("printStockAlreadySoldInFuture ");
  }

  @Override
  public void askForCommissionFee() {
    log.append("askForCommissionFee ");
  }

  @Override
  public void printTotalCostBasis(String totalCost) {
    log.append("printTotalCostBasis: " + totalCost + " ");
  }

  @Override
  public void printEnterDateCostBasis() {
    log.append("printEnterDateCostBasis ");
  }

  @Override
  public void printInvalidCommissionFee() {
    log.append("printInvalidCommissionFee ");
  }

  @Override
  public void printErrorDifferenceInDatesRequired() {
    log.append("printErrorDifferenceInDatesRequired ");
  }

  @Override
  public void printPerformancePlot(List<Integer> numStars, List<String> timeStampForYaxis) {
    log.append("printPerformancePlot: " + numStars + " " + timeStampForYaxis + " ");
  }

  @Override
  public void printEnterDateForViewFlexi() {
    log.append("printEnterDateForViewFlexi ");
  }

  @Override
  public void printQuantityIsNegative() {
    log.append("printQuantityIsNegative ");
  }

  @Override
  public void printInvalidUserNameFormat() {
    log.append("printInvalidUserNameFormat ");
  }

  @Override
  public void printTickerIsInvalid() {
    log.append("printTickerIsInvalid ");
  }
}