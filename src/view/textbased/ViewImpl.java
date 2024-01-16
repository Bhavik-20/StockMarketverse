package view.textbased;

import java.io.PrintStream;
import java.util.Formatter;
import java.util.List;

/**
 * This class implements the Portfolio View interface.
 */
public class ViewImpl implements View {
  PrintStream outP;

  public ViewImpl(PrintStream out) {
    this.outP = out;
  }

  public void printWelcome() {
    outP.println("------------------------------------------------------------------\n"
            + "Welcome to Stock-Marketverse: a virtual stock gambling application!");
  }

  @Override
  public void printUserMenu() {
    outP.print("------------------------------------------------------------------\n"
            + "Please select an option from the Menu below: \n"
            + "1. Enter Username \n"
            + "2. Exit \n"
            + "Enter your choice here: ");
  }

  @Override
  public void askForUserName() {
    outP.print("Please enter your user name: ");
  }

  @Override
  public void printErrorInUserCreation() {
    outP.println("Error creating user. Please Try Again.");
  }

  @Override
  public void printSuccessNewFile() {
    outP.println("------------------------------------------------------------------\n"
            + "\nNew user created successfully!\n");
  }

  @Override
  public void printPortfolioMenu() {
    outP.print("------------------------------------------------------------------\n"
            + "Select which type of PORTFOLIO do you want to work with:\n"
            + "1. IMMUTABLE Portfolio\n"
            + "2. FLEXIBLE Portfolio\n"
            + "3. Return to previous menu\n"
            + "Enter your choice here: ");
  }

  //----------- Immutable ----------


  @Override
  public void printMainMenu() {
    outP.print("------------------------Immutable Portfolio-----------------------------\n"
            + "Please select an option from the Menu below: \n"
            + "1. CREATE a New Portfolio in the application \n"
            + "2. UPLOAD an existing Portfolio (XML format) \n"
            + "3. VIEW composition of Portfolio \n"
            + "4. EXAMINE Portfolio value on certain date \n"
            + "5. Return to previous menu \n"
            + "Enter your choice here: ");
  }

  @Override
  public void printEnterStockName() {
    outP.print("Please enter the ticker symbol of the stock (eg: GOOG): ");
  }

  @Override
  public void printEnterStockQty() {
    outP.print("Please enter the quantity of the stock (eg:10): ");
  }

  @Override
  public void printInvalidStockData() {
    outP.println("------------------------------------------------------------------\n"
            + "ERROR: The ticker or quantity entered is invalid. \nPlease enter correct ticker "
            + "symbol (eg: GOOG) and quantity as a whole number greater than 0 (eg: 10)"
            + " for the stock.\n"
            + "------------------------------------------------------------------");
  }


  @Override
  public void printFileCreationFailed() {
    outP.println("------------------------------------------------------------------\n"
            + "ERROR: Portfolio could not be created. Please try again.");
  }

  @Override
  public void printFileCreated() {
    outP.println("------------------------------------------------------------------\n"
            + "\nPORTFOLIO CREATED SUCCESSFULLY! \n");
  }

  @Override
  public void printFileNotFound() {
    outP.println("------------------------------------------------------------------\n"
            + "ERROR: This Portfolio does not exist. Please enter a valid portfolio name.");
  }

  @Override
  public void printFileComposition(String composition) {
    //    outP.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n"
    //            + "Stock Name -> Quantity\n"
    //            + "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
    Formatter fmt = new Formatter();
    Formatter fmt2 = new Formatter();
    composition = composition.replace("->", "");
    fmt.format("-----------------------------------\n");
    fmt.format("|%15s | %15s |\n", "Stock Name", "Quantity");
    fmt.format("-----------------------------------\n");
    String[] list = composition.trim().split("\n");
    for (int i = 0; i < list.length; i++) {
      String[] temp = list[i].trim().split("\\s+");
      fmt2.format("|%15s | %15s |\n", temp[0], temp[1]);
    }
    outP.print(fmt);
    outP.print(fmt2);
    outP.println("-----------------------------------\n");
  }

  @Override
  public void printAskForUploadPath() {
    outP.println("Please enter the path of the XML file that you want to upload (Any existing file"
            + " with the same name will be OVERWRITTEN): ");
  }

  @Override
  public void printInvalidFileDirectory() {
    outP.println("------------------------------------------------------------------\n"
            + "ERROR: File Directory Does not exist. Upload Failed!");
  }

  @Override
  public void printPortfolioUploaded() {
    outP.println("------------------------------------------------------------------\n"
            + "PORTFOLIO UPLOADED SUCCESSFULLY!\n");
  }

  @Override
  public void printErrorPortfolioUpload() {
    outP.println("------------------------------------------------------------------\n"
            + "ERROR in uploading Portfolio. Please Try again. \n");
  }

  @Override
  public void printInvalidUploadFileType() {
    outP.println("------------------------------------------------------------------\n"
            + "ERROR: Invalid File Type provided. Please Upload only files of XML "
            + "type.\n");
  }

  @Override
  public void printEnterNewPortfolioName() {
    outP.print("------------------------------------------------------------------\n"
            + "Please enter the name of the Portfolio File you want to create: ");
  }

  @Override
  public void printEnterPortfolioNameToOpen() {
    outP.print("------------------------------------------------------------------\n"
            + "Please enter the name of the portfolio: ");
  }

  @Override
  public void printChooseOtherName() {
    outP.println("------------------------------------------------------------------\n"
            + "ERROR: A portfolio with this name exists. Please choose a different "
            + "Portfolio Name.");
  }

  @Override
  public void printEnterPortfolioName() {
    outP.print("------------------------------------------------------------------\n"
            + "Please enter the name of the portfolio whose total value needs to be calculated: ");
  }

  @Override
  public void printEnterDate() {
    outP.print("Please enter the date at which the total value needs to be "
            + "calculated in the yyyy-mm-dd format (in EST): ");
  }

  @Override
  public void printIncorrectDateFormat() {
    outP.println("------------------------------------------------------------------\n"
            + "ERROR: The date entered has an incorrect format. Please enter date in the "
            + "format yyyy-mm-dd (in EST).");
  }

  @Override
  public void printErrorFutureDate() {
    outP.println("------------------------------------------------------------------\n"
            + "ERROR: The entered date is in the future. Please enter a past date. ");
  }

  @Override
  public void printPortfolioValueOnDate(String val) {
    outP.println("------------------------------------------------------------------\n"
            + "\nThe total value of the portfolio on given date is $" + val + "\n");
  }

  @Override
  public void exitProgram(int i) {
    if (i == 1) {
      outP.println("Bye Bye!");
    } else {
      outP.println("------------------------------------------------------------------\n"
              + "\nReturning to Previous Menu.\n");
    }
  }

  @Override
  public void printInvalidFileNameFormat() {
    outP.println("------------------------------------------------------------------\n"
            + "ERROR: Invalid File Name entered. Please restrict filename to letters, " +
            "digits,- and _");
  }

  @Override
  public void printInvalidDateFormat() {
    outP.println("------------------------------------------------------------------\n"
            + "ERROR: Date entered is an invalid date. Please enter date in yyyy-mm-dd. ");
  }


  @Override
  public void printErrorHoliday() {
    outP.println("------------------------------------------------------------------\n"
            + "ERROR: Specified date was a Weekend! ");
  }

  @Override
  public void printIncorrectFileFormatUploaded() {
    outP.println("------------------------------------------------------------------\n"
            + "ERROR in viewing file. Incorrect File format Uploaded! ");
  }

  @Override
  public void printTodayDateCondition() {
    outP.println("** If you get the total value of portfolio equal to 0, " +
            "please try again after the stock " + "market closes, i.e. after 4 PM EST. **");
  }


  @Override
  public void printPortfolioDeleted() {
    outP.println("ALERT: PORTFOLIO DELETED!");
  }

  @Override
  public void printInvalidMainMenuOption() {
    outP.println("------------------------------------------------------------------\n"
            + "ERROR: Invalid option selected. Please choose an option from the menu.");
  }

  @Override
  public void printAddMoreStocksToPortfolio() {
    outP.print("Do you want to add more stocks to the portfolio? (Y/N): ");
  }

  @Override
  public void printTickerIsInvalid() {
    outP.println("------------------------------------------------------------------\n" +
            "ERROR: File contains Invalid Tickers");
  }

  // ------------ Flexible --------------------

  @Override
  public void printFlexiblePortfolioMenu() {
    outP.print("--------------------------- Flexible Portfolio ---------------------------------\n"
            + "Please select an option from the Menu below:\n"
            + "1. CREATE a Portfolio\n"
            + "2. UPLOAD a Portfolio\n"
            + "3. Make a transaction\n"
            + "4. VIEW composition of a Portfolio \n"
            + "5. EXAMINE Portfolio value on certain date \n"
            + "6. CALCULATE the COST BASIS of a portfolio \n"
            + "7. View Portfolio PERFORMANCE\n"
            + "8. Return to previous menu\n"
            + "Enter your choice here: ");
  }

  @Override
  public void printWhichTransaction() {
    outP.print("---------------------------------------------------------------------\n"
            + "Please select one of the following: \n"
            + "1. BUY stocks\n"
            + "2. SELL stocks\n"
            + "3. SAVE changes and return to previous menu\n"
            + "Enter your choice here: "
    );
  }

  @Override
  public void printEnterPurchaseDate() {
    outP.print("Enter the date at which you want to buy the stock in yyyy-mm-dd format(in EST): ");
  }

  @Override
  public void printEnterSellDate() {
    outP.print("Enter the date at which you want to sell the stock in yyyy-mm-dd format(in EST): ");
  }

  @Override
  public void printEnterFromDate() {
    outP.print("Enter the start date for performance visualization in yyyy-mm-dd format " +
            "(in EST): ");
  }


  @Override
  public void printStockAddedToList() {
    outP.println("---------------------------------------------------------------------\n"
            + "SUCCESS: Stock has been added to the list successfully! \nSelect save changes to add"
            + " this list to the portfolio, if you don't wish to buy/sell any other stock. ");
  }

  @Override
  public void printStockSold() {
    outP.println("---------------------------------------------------------------------\n"
            + "SUCCESS: Stock has been sold successfully! \nSelect save changes to log your " +
            "changes in portfolio, if you don't wish to buy/sell any other stock. ");
  }

  @Override
  public void printFileUpdated() {
    outP.println("---------------------------------------------------------------------\n"
            + "\nSUCCESS: The list of transaction/s have been logged in the portfolio " +
            "successfully!\n");
  }

  @Override
  public void printNegativeQuantity() {
    outP.println("---------------------------------------------------------------------\n"
            + "ERROR: Negative Quantity of Stocks Entered");
  }

  @Override
  public void printFractionalQuantity() {
    outP.println("---------------------------------------------------------------------\n"
            + "ERROR: Fractional Quantity of Stocks Entered");
  }

  @Override
  public void printStocksNotAvailableToSell() {
    outP.println("---------------------------------------------------------------------\n"
            + "ERROR: You do not have stocks for this company.");
  }

  @Override
  public void askForCommissionFee() {
    outP.print("Please enter the commission fee charged by the Broker: $");
  }

  @Override
  public void printTotalCostBasis(String totalCost) {
    outP.println("---------------------------------------------------------------------\n"
            + "\nThe total cost basis for this portfolio is $" + totalCost + "\n");
  }

  @Override
  public void printEnterDateCostBasis() {
    outP.print("Please enter the date till which the cost basis should be "
            + "calculated in the yyyy-mm-dd format (in EST): ");
  }

  @Override
  public void printInvalidCommissionFee() {
    outP.println("---------------------------------------------------------------------\n"
            + "ERROR: Commission fee cannot be a negative value!");
  }

  @Override
  public void printStockAlreadySoldInFuture() {
    outP.println("---------------------------------------------------------------------\n"
            + "ERROR: Cannot perform transaction! (Insufficient stocks of the company)");
  }


  @Override
  public void printInvalidTicker() {
    outP.println("---------------------------------------------------------------------\n"
            + "ERROR: Invalid Ticker Entered");
  }

  @Override
  public void printEnterTillDate() {
    outP.print("Enter the end date for performance visualization in yyyy-mm-dd format (in EST): ");
  }

  @Override
  public void printPerformancePlotTopic(String portfolioName, String fromDate,
                                        String tillDate) {
    outP.println("---------------------------------------------------------------------\n"
            + "Performance of portfolio " + portfolioName + " from " + fromDate + " to " +
            tillDate + "\n---------------------------------------------------------------------");
  }

  @Override
  public void printPerformancePlot(List<Integer> numStars, List<String> timeStampForYaxis) {
    for (int i = 0; i < timeStampForYaxis.size(); i++) {
      System.out.println(timeStampForYaxis.get(i) + ": "
              + new String(new char[numStars.get(i)]).replace('\0', '*'));
    }
  }

  @Override
  public void printEnterDateForViewFlexi() {
    outP.print("Enter date: ");
  }

  @Override
  public void printQuantityIsNegative() {
    outP.println("Portfolio has illegal transactions. (Negative number of stocks for a company)");
  }

  @Override
  public void printInvalidUserNameFormat() {
    outP.println();
  }

  @Override
  public void printPerformancePlotScale(String scale) {
    outP.println("---------------------------------------------------------------------\n"
            + "Scale: * = $" + scale);
  }

  @Override
  public void printErrorDifferenceInDatesRequired() {
    outP.println("---------------------------------------------------------------------\n"
            + "ERROR: The difference between the start date mentioned should be more than 5 " +
            "days and less than 30 years.");
  }

  @Override
  public void printTodayBuyCondition() {
    outP.println("If the stock market has not shut we will consider yesterdays closing value.");
  }

  @Override
  public void printErrorLoadingPortfolio() {
    outP.println("------------------------------------------------------------------\n"
            + "ERROR: Could not load the portfolio!");
  }
}
