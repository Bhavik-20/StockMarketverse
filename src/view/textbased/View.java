package view.textbased;

import java.util.List;

/**
 * This interface defines all the functions of the view. It prints all the messages on the console
 * and helps the controller communicate with the user.
 */
public interface View {

  /**
   * This method is used to print the welcome message on the console.
   */
  void printWelcome();

  /**
   * This method is used to print a menu of operations that a user can perform in this application.
   */
  void printMainMenu();

  /**
   * This method asks user to enter the ticker symbol of the stock that they want to add to the
   * portfolio (for eg: GOOG).
   */
  void printEnterStockName();

  /**
   * This method asks user to enter the quantity of the stock that they wish to add to the
   * portfolio. This should be a valid integer number (for eg: 10).
   */
  void printEnterStockQty();

  /**
   * This method is used to ask user if they want to add more stocks to the portfolio.
   */
  void printAddMoreStocksToPortfolio();

  /**
   * This method is used to depict an error to the user when an invalid ticker symbol or quantity
   * is entered.
   */
  void printInvalidStockData();

  /**
   * This method is used to depict an error message when an invalid menu option is seleccted.
   */
  void printInvalidMainMenuOption();

  /**
   * This method is used to ask the user to enter the path of the file to be uploaded when the
   * user wants to upload a portfolio to the system.
   */
  void printAskForUploadPath();

  /**
   * This method is used to depict an error message when a file creation fails.
   */
  void printFileCreationFailed();

  /**
   * This method is used to print a success message when a file is created successfully.
   */
  void printFileCreated();

  /**
   * This method is used to depict an error message when the file that user wants is not found.
   */
  void printFileNotFound();

  /**
   * This method is used to print an error message when an invalid file directory is provided.
   */
  void printInvalidFileDirectory();

  /**
   * This method is used to print an error message when a path with invalid file type is provided.
   */
  void printInvalidUploadFileType();

  /**
   * This method is used to print the composition of the portfolio in the file.
   */
  void printFileComposition(String composition);

  /**
   * This method is used to print a success message when a portfolio is uploaded successfully.
   */
  void printPortfolioUploaded();

  /**
   * This method is used to depict an error message when an error occurs in portfolio upload.
   */
  void printErrorPortfolioUpload();

  /**
   * This method asks user to enter the name of the portfolio that they want to create.
   */
  void printEnterNewPortfolioName();

  /**
   * This method asks user to enter the name of the portfolio that they want to open.
   */
  void printEnterPortfolioNameToOpen();

  /**
   * This method is used to depict an error message when user enters a portfolio name that already
   * exists.
   */
  void printChooseOtherName();

  /**
   * This method asks users to enter the name of the portfolio whose total value they wish to
   * calculate.
   */
  void printEnterPortfolioName();

  /**
   * This method asks users to enter the date at which they wish to calculate the total value of a
   * portfolio.
   */
  void printEnterDate();

  /**
   * This method is used to depict an error when a user enters incorrect date format.
   */
  void printIncorrectDateFormat();

  /**
   * This method is used to ask the user to print their username or exit from the program.
   */
  void printUserMenu();

  /**
   * This method is used to print an error message when the date entered is a future date.
   */
  void printErrorFutureDate();

  /**
   * This method is used to print the value of a portfolio on certain date.
   *
   * @param val value of the portfolio on certain date.
   */
  void printPortfolioValueOnDate(String val);

  /**
   * This method is used to print a message asking the user for their username.
   */
  void askForUserName();

  /**
   * This method is used to print an error message when user is failed to be created.
   */
  void printErrorInUserCreation();

  /**
   * This method is used to print a message when the user wants to exit a menu.
   *
   * @param i value to indicate which menu is being exited.
   */
  void exitProgram(int i);

  /**
   * This method is used to print an error message when the file name is incorrect.
   */
  void printInvalidFileNameFormat();

  /**
   * This method is used to print an error message when the date entered is invalid format i.e. not
   * in yyy-mm-dd format.
   */
  void printInvalidDateFormat();

  /**
   * This method is used to print a message when the portfolio is created.
   */
  void printSuccessNewFile();

  /**
   * This method is used to print a message when the date entered is a holiday date.
   */
  void printErrorHoliday();

  /**
   * This method is used to print a message when the ticker is invalid.
   */
  void printTickerIsInvalid();

  /**
   * This method is used to print an error message if the file format is incorrect.
   */
  void printIncorrectFileFormatUploaded();

  /**
   * This method is used to print a message when the date entered is today's date.
   */
  void printTodayDateCondition();

  /**
   * This method is used to ask the user which kind of portfolio they want to work with.
   */
  void printPortfolioMenu();

  /**
   * This method is used to print the menu, showcasing the actions that can be performed on a
   * flexible portfolio.
   */
  void printFlexiblePortfolioMenu();

  /**
   * This method is used to ask the user if they wish to buy or sell any stocks.
   */
  void printWhichTransaction();

  /**
   * This method asks the user to enter the date at which they wish to buy stocks in yyyy-mm-dd
   * format.
   */
  void printEnterPurchaseDate();

  /**
   * This method asks the user to enter the date at which the user wishes to sell stocks in
   * yyyy-mm-dd format.
   */
  void printEnterSellDate();

  // ---- Perormance of Portfolio ------

  /**
   * This method is used to ask the user to enter date from which they wish to begin the
   * visualization of performance of a portfolio in yyyy-mm-dd format.
   */
  void printEnterFromDate();

  /**
   * This method is used to ask the user to enter date till which they wish to see the
   * visualization of performance of a portfolio in yyyy-mm-dd format.
   */
  void printEnterTillDate();

  /**
   * This method is used to depict to the user that a portfolio is deleted successfully.
   */
  void printPortfolioDeleted();

  /**
   * This method is used to add a list of transactions to a list which will be written in the
   * portfolio when user saves the changes.
   */
  void printStockAddedToList();

  /**
   * This method is used to display to the user that the sell transaction has been executed
   * successfully and will be reflected in the portfolio once the user selects save changes.
   */
  void printStockSold();

  /**
   * This method is used to display to the user that the file has been updated successfully.
   */
  void printFileUpdated();

  /**
   * This method is used to depict to the user that the quantity entered by the user is negative.
   */
  void printNegativeQuantity();

  /**
   * This method is used to depict an error to the user tha the quantity cannot be a fractional
   * number.
   */
  void printFractionalQuantity();

  /**
   * This method is used to depict an error to the user that the chosen stocks cannot be sold.
   */
  void printStocksNotAvailableToSell();

  /**
   * This method is used to depict to the user that the Ticker entered is invalid.
   */
  void printInvalidTicker();

  /**
   * This method is used to denote to the user that the chosen stocks were sold on a future date.
   */
  void printStockAlreadySoldInFuture();

  /**
   * This method is used to ask the value of commission fee charged by the broker from the user.
   */
  void askForCommissionFee();

  /**
   * This method is used to print the total value invested by the user in the portfolio. i.e. it is
   * used to print the cost basis of the portfolio.
   *
   * @param totalCost signifies the cost basis value of the portfolio
   */
  void printTotalCostBasis(String totalCost);

  /**
   * This method is used to ask the user to enter a date till which a portfolio's cost basis is to
   * be calculated.
   */
  void printEnterDateCostBasis();

  /**
   * This method is used to print an error when an invalid commission fee is entered.
   */
  void printInvalidCommissionFee();

  /**
   * This method is used to print an error when the difference in the start date and end date of
   * visualization entered is less than 5 days or more than 30 years.
   */
  void printErrorDifferenceInDatesRequired();

  /**
   * This method is used to print the title of the bar graph plot.
   *
   * @param portfolioName signifies the name of the portfolio whose performance is to be visualised.
   * @param fromDate      signifies the date at which the visualization begins
   * @param tillDate      signifies the date at which the visualization ends
   */
  void printPerformancePlotTopic(String portfolioName, String fromDate,
                                 String tillDate);

  /**
   * This method is used to print the scale at the bottom of the plot.
   *
   * @param scale signifies the value of a *
   */
  void printPerformancePlotScale(String scale);

  /**
   * This method is use print the bar graph plot.
   *
   * @param numStars          signifies the number of stars that should be printed in the row.
   * @param timeStampForYaxis signifies the time stamps/ labels for the y axis of the graph.
   */
  void printPerformancePlot(List<Integer> numStars, List<String> timeStampForYaxis);

  /**
   * This method is used to ask the user to enter a date at which they wish to view the total
   * value of a flexible portfolio.
   */
  void printEnterDateForViewFlexi();

  /**
   * This method is used to tell the user that if the market is still open, the value of the
   * portfolio on a prior date will be portrayed.
   */
  void printTodayBuyCondition();

  /**
   * This method is used to depict an error to the user, saying that the portfolio could not load.
   */
  void printErrorLoadingPortfolio();

  /**
   * This method is used to depict an error when the portfolio file uploaded by the user tries to
   * sell more stocks than they have bought.
   */
  void printQuantityIsNegative();

  void printInvalidUserNameFormat();
}

