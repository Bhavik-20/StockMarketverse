package view.textbased;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import view.textbased.View;
import view.textbased.ViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * This is a jUnit class that is used to test all the methods of the PortfolioView class.
 */
public class PortfolioViewImplTest {

  View v;
  PrintStream out;
  ByteArrayOutputStream bytes;

  @Before
  public void testSetUp() {
    bytes = new ByteArrayOutputStream();
    out = new PrintStream(bytes);
    v = new ViewImpl(out);
  }

  @Test
  public void testPrintWelcome() {
    v.printWelcome();
    assertEquals("------------------------------------------------------------------\n"
                    + "Welcome to Stock-Marketverse: a virtual stock gambling application!",
            bytes.toString().trim());
  }

  @Test
  public void testPrintEnterStockName() {
    v.printEnterStockName();
    assertEquals("Please enter the ticker symbol of the stock (eg: GOOG): ",
            bytes.toString());
  }

  @Test
  public void testPrintEnterStockQty() {
    v.printEnterStockQty();
    assertEquals("Please enter the quantity of the stock (eg:10): ",
            bytes.toString());
  }

  @Test
  public void testPrintInvalidStockData() {
    v.printInvalidStockData();
    assertEquals("------------------------------------------------------------------\n"
                    + "ERROR: The ticker or quantity entered is invalid. \n"
                    + "Please enter correct ticker "
                    + "symbol (eg: GOOG) and quantity as a whole number greater than 0 (eg: 10)"
                    + " for the stock.\n"
                    + "------------------------------------------------------------------",
            bytes.toString().trim());
  }

  @Test
  public void testPrintFileCreationFailed() {
    v.printFileCreationFailed();
    assertEquals("------------------------------------------------------------------\n"
            + "ERROR: Portfolio could not be created. Please try again.", bytes.toString().trim());
  }

  @Test
  public void testPrintFileCreated() {
    v.printFileCreated();
    assertEquals("------------------------------------------------------------------\n"
            + "\nPORTFOLIO CREATED SUCCESSFULLY!", bytes.toString().trim());
  }

  @Test
  public void testPrintFileNotFound() {
    v.printFileNotFound();
    assertEquals("------------------------------------------------------------------\n"
                    + "ERROR: This Portfolio does not exist. Please enter a valid portfolio name.",
            bytes.toString().trim());
  }

  // check
  @Test
  public void testPrintFileComposition() {
    v.printFileComposition("");
    Formatter fmt = new Formatter();
    fmt.format("-----------------------------------\n");
    fmt.format("|%15s | %15s |\n", "Stock Name", "Quantity");
    fmt.format("-----------------------------------\n");
    assertEquals(fmt + "" + "-----------------------------------",
            bytes.toString().trim());
  }

  @Test
  public void testPrintAskForUploadPath() {
    v.printAskForUploadPath();
    assertEquals("Please enter the path of the XML file that you want to upload (Any "
                    + "existing file with the same name will be OVERWRITTEN):",
            bytes.toString().trim());
  }

  @Test
  public void testPrintInvalidFileDirectory() {
    v.printInvalidFileDirectory();
    assertEquals("------------------------------------------------------------------\n"
            + "ERROR: File Directory Does not exist. Upload Failed!", bytes.toString().trim());
  }

  @Test
  public void testPrintPortfolioUploaded() {
    v.printPortfolioUploaded();
    assertEquals("------------------------------------------------------------------\n"
            + "PORTFOLIO UPLOADED SUCCESSFULLY!", bytes.toString().trim());
  }

  @Test
  public void testPrintErrorPortfolioUpload() {
    v.printErrorPortfolioUpload();
    assertEquals("------------------------------------------------------------------\n"
            + "ERROR in uploading Portfolio. Please Try again.", bytes.toString().trim());
  }

  @Test
  public void testPrintInvalidUploadFileType() {
    v.printInvalidUploadFileType();
    assertEquals("------------------------------------------------------------------\n"
            + "ERROR: Invalid File Type provided. Please Upload only files of XML "
            + "type.", bytes.toString().trim());
  }

  @Test
  public void testPrintEnterNewPortfolioName() {
    v.printEnterNewPortfolioName();
    assertEquals("------------------------------------------------------------------\n"
                    + "Please enter the name of the Portfolio File you want to create:",
            bytes.toString().trim());
  }

  @Test
  public void testPrintEnterPortfolioNameToOpen() {
    v.printEnterPortfolioNameToOpen();
    assertEquals("------------------------------------------------------------------\n"
                    + "Please enter the name of the portfolio:",
            bytes.toString().trim());
  }

  @Test
  public void testPrintChooseOtherName() {
    v.printChooseOtherName();
    assertEquals("------------------------------------------------------------------\n"
            + "ERROR: A portfolio with this name exists. Please choose a different "
            + "Portfolio Name.", bytes.toString().trim());
  }

  @Test
  public void testPrintEnterPortfolioName() {
    v.printEnterPortfolioName();
    assertEquals("------------------------------------------------------------------\n"
                    + "Please enter the name of the portfolio whose total value needs to be " +
                    "calculated:",
            bytes.toString().trim());
  }

  @Test
  public void testPrintEnterDate() {
    v.printEnterDate();
    assertEquals("Please enter the date at which the total value needs to be "
            + "calculated in the yyyy-mm-dd format (in EST):", bytes.toString().trim());
  }

  @Test
  public void testPrintIncorrectDateFormat() {
    v.printIncorrectDateFormat();
    assertEquals("------------------------------------------------------------------\n"
            + "ERROR: The date entered has an incorrect format. Please enter date in the "
            + "format yyyy-mm-dd (in EST).", bytes.toString().trim());
  }

  @Test
  public void testPrintErrorFutureDate() {
    v.printErrorFutureDate();
    assertEquals("------------------------------------------------------------------\n"
                    + "ERROR: The entered date is in the future. Please enter a past date.",
            bytes.toString().trim());
  }

  @Test
  public void testPrintPortfolioValueOnDate() {
    v.printPortfolioValueOnDate("73");
    assertEquals("------------------------------------------------------------------\n"
            + "\nThe total value of the portfolio on given date is $73", bytes.toString().trim());
  }

  @Test
  public void testAskForUserName() {
    v.askForUserName();
    assertEquals("Please enter your user name:", bytes.toString().trim());
  }

  @Test
  public void testPrintErrorInUserCreation() {
    v.printErrorInUserCreation();
    assertEquals("Error creating user. Please Try Again.", bytes.toString().trim());
  }

  @Test
  public void testExitProgram1() {
    v.exitProgram(1);
    assertEquals("Bye Bye!", bytes.toString().trim());
  }

  @Test
  public void testExitProgram2() {
    v.exitProgram(0);
    assertEquals("------------------------------------------------------------------\n"
            + "\nReturning to Previous Menu.", bytes.toString().trim());
  }

  @Test
  public void testPrintInvalidFileNameFormat() {
    v.printInvalidFileNameFormat();
    assertEquals("------------------------------------------------------------------\n"
                    + "ERROR: Invalid File Name entered. Please restrict filename to letters, "
                    + "digits,- and _",
            bytes.toString().trim());
  }

  @Test
  public void testPrintInvalidDateFormat() {
    v.printInvalidDateFormat();
    assertEquals("------------------------------------------------------------------\n"
                    + "ERROR: Date entered is an invalid date. Please enter date in yyyy-mm-dd.",
            bytes.toString().trim());
  }

  @Test
  public void testPrintSuccessNewFile() {
    v.printSuccessNewFile();
    assertEquals("------------------------------------------------------------------\n"
            + "\nNew user created successfully!", bytes.toString().trim());
  }

  @Test
  public void testPrintErrorHoliday() {
    v.printErrorHoliday();
    assertEquals("------------------------------------------------------------------\n"
            + "ERROR: Specified date was a Weekend!", bytes.toString().trim());
  }

  @Test
  public void testPrintUserMenu() {
    v.printUserMenu();
    assertEquals("------------------------------------------------------------------\n"
            + "Please select an option from the Menu below: \n"
            + "1. Enter Username \n"
            + "2. Exit \n"
            + "Enter your choice here:", bytes.toString().trim());
  }

  @Test
  public void testPrintMainMenu() {
    v.printMainMenu();
    assertEquals(
            "------------------------Immutable Portfolio-----------------------------\n"
                    + "Please select an option from the Menu below: \n"
                    + "1. CREATE a New Portfolio in the application \n"
                    + "2. UPLOAD an existing Portfolio (XML format) \n"
                    + "3. VIEW composition of Portfolio \n"
                    + "4. EXAMINE Portfolio value on certain date \n"
                    + "5. Return to previous menu \n"
                    + "Enter your choice here:", bytes.toString().trim());
  }

  @Test
  public void testPrintInvalidMainMenuOption() {
    v.printInvalidMainMenuOption();
    assertEquals("------------------------------------------------------------------\n"
                    + "ERROR: Invalid option selected. Please choose an option from the menu.",
            bytes.toString().trim());
  }

  @Test
  public void testPrintAddMoreStocksToPortfolio() {
    v.printAddMoreStocksToPortfolio();
    assertEquals("Do you want to add more stocks to the portfolio? (Y/N):",
            bytes.toString().trim());
  }

  @Test
  public void testPrintInvalidTicker() {
    v.printTickerIsInvalid();
    assertEquals("------------------------------------------------------------------\n"
            + "ERROR: File contains Invalid Tickers", bytes.toString().trim());
  }

  @Test
  public void testPrintTodayDateCondition() {
    v.printTodayDateCondition();
    assertEquals("** If you get the total value of portfolio equal to 0, " +
                    "please try again after the stock market closes, i.e. after 4 PM EST. **",
            bytes.toString().trim());
  }

  @Test
  public void testPrintPortfolioMenu() {
    v.printPortfolioMenu();
    assertEquals("------------------------------------------------------------------\n"
            + "Select which type of PORTFOLIO do you want to work with:\n"
            + "1. IMMUTABLE Portfolio\n"
            + "2. FLEXIBLE Portfolio\n"
            + "3. Return to previous menu\n"
            + "Enter your choice here:", bytes.toString().trim());
  }

  @Test
  public void testPrintIncorrectFileFormatUploaded() {
    v.printIncorrectFileFormatUploaded();
    assertEquals("------------------------------------------------------------------\n"
            + "ERROR in viewing file. Incorrect File format Uploaded!", bytes.toString().trim());
  }

  @Test
  public void testPrintPortfolioDeleted() {
    v.printPortfolioDeleted();
    assertEquals("ALERT: PORTFOLIO DELETED!", bytes.toString().trim());
  }

  @Test
  public void testPrintTickerIsInvalid() {
    v.printTickerIsInvalid();
    assertEquals("------------------------------------------------------------------\n"
            + "ERROR: File contains Invalid Tickers", bytes.toString().trim());
  }

  @Test
  public void testPrintFlexiblePortfolioMenu() {
    v.printFlexiblePortfolioMenu();
    assertEquals("--------------------------- Flexible Portfolio ------------------"
            + "---------------\n"
            + "Please select an option from the Menu below:\n"
            + "1. CREATE a Portfolio\n"
            + "2. UPLOAD a Portfolio\n"
            + "3. Make a transaction\n"
            + "4. VIEW composition of a Portfolio \n"
            + "5. EXAMINE Portfolio value on certain date \n"
            + "6. CALCULATE the COST BASIS of a portfolio \n"
            + "7. View Portfolio PERFORMANCE\n"
            + "8. Return to previous menu\n"
            + "Enter your choice here:", bytes.toString().trim());
  }

  @Test
  public void testPrintWhichTransaction() {
    v.printWhichTransaction();
    assertEquals("---------------------------------------------------------------------\n"
            + "Please select one of the following: \n"
            + "1. BUY stocks\n"
            + "2. SELL stocks\n"
            + "3. SAVE changes and return to previous menu\n"
            + "Enter your choice here:", bytes.toString().trim());
  }

  @Test
  public void testPrintEnterPurchaseDate() {
    v.printEnterPurchaseDate();
    assertEquals("Enter the date at which you want to buy the stock in yyyy-mm-dd"
            + " format(in EST):", bytes.toString().trim());
  }

  @Test
  public void testPrintEnterSellDate() {
    v.printEnterSellDate();
    assertEquals("Enter the date at which you want to sell the stock in yyyy-mm-dd "
            + "format(in EST):", bytes.toString().trim());
  }

  @Test
  public void testPrintEnterFromDate() {
    v.printEnterFromDate();
    assertEquals("Enter the start date for performance visualization in yyyy-mm-dd" +
            " format (in EST):", bytes.toString().trim());
  }

  @Test
  public void testPrintStockAddedToList() {
    v.printStockAddedToList();
    assertEquals(
            "---------------------------------------------------------------------\n"
                    + "SUCCESS: Stock has been added to the list successfully! \n"
                    + "Select save changes to add"
                    + " this list to the portfolio, if you don't wish to buy/sell any other stock.",
            bytes.toString().trim());
  }

  @Test
  public void testPrintStockSold() {
    v.printStockSold();
    assertEquals("---------------------------------------------------------------------\n"
                    + "SUCCESS: Stock has been sold successfully! \nSelect save changes to log "
                    + "your changes in portfolio, if you don't wish to buy/sell any other stock.",
            bytes.toString().trim());
  }

  @Test
  public void testPrintFileUpdated() {
    v.printFileUpdated();
    assertEquals("---------------------------------------------------------------------\n"
            + "\nSUCCESS: The list of transaction/s have been logged in the portfolio " +
            "successfully!", bytes.toString().trim());
  }

  @Test
  public void testPrintNegativeQuantity() {
    v.printNegativeQuantity();
    assertEquals("---------------------------------------------------------------------\n"
            + "ERROR: Negative Quantity of Stocks Entered", bytes.toString().trim());
  }

  @Test
  public void testPrintFractionalQuantity() {
    v.printFractionalQuantity();
    assertEquals("---------------------------------------------------------------------\n"
            + "ERROR: Fractional Quantity of Stocks Entered", bytes.toString().trim());
  }

  @Test
  public void testPrintStocksNotAvailableToSell() {
    v.printStocksNotAvailableToSell();
    assertEquals("---------------------------------------------------------------------\n"
            + "ERROR: You do not have stocks for this company.", bytes.toString().trim());
  }

  @Test
  public void testAskForCommissionFee() {
    v.askForCommissionFee();
    assertEquals("Please enter the commission fee charged by the Broker: $",
            bytes.toString().trim());
  }

  @Test
  public void testPrintTotalCostBasis() {
    v.printTotalCostBasis("");
    assertEquals("---------------------------------------------------------------------\n"
            + "\nThe total cost basis for this portfolio is $", bytes.toString().trim());
  }

  @Test
  public void testPrintEnterDateCostBasis() {
    v.printEnterDateCostBasis();
    assertEquals("Please enter the date till which the cost basis should be calculated " +
            "in the yyyy-mm-dd format (in EST):", bytes.toString().trim());
  }

  @Test
  public void testPrintInvalidCommissionFee() {
    v.printInvalidCommissionFee();
    assertEquals("---------------------------------------------------------------------\n"
            + "ERROR: Commission fee cannot be a negative value!", bytes.toString().trim());
  }

  @Test
  public void testPrintStockAlreadySoldInFuture() {
    v.printStockAlreadySoldInFuture();
    assertEquals("---------------------------------------------------------------------\n"
                    + "ERROR: Cannot perform transaction! (Insufficient stocks of the company)",
            bytes.toString().trim());
  }

  @Test
  public void testPrintEnterTillDate() {
    v.printEnterTillDate();
    assertEquals("Enter the end date for performance visualization in yyyy-mm-dd " +
            "format (in EST):", bytes.toString().trim());
  }

  @Test
  public void testPrintPerformancePlotTopic() {
    v.printPerformancePlotTopic("portfolioName", "fromDate", "tillDate");
    assertEquals("---------------------------------------------------------------------\n"
                    + "Performance of portfolio portfolioName from fromDate to tillDate"
                    + "\n---------------------------------------------------------------------",
            bytes.toString().trim());
  }

  @Test
  public void testPrintPerformancePlot() {
    List<String> timeStampForYaxis = new ArrayList<>();
    timeStampForYaxis.add(List.of("Jan 2022", "Feb 2022", "Mar 2022", "Apr 2022", "May 2022")
            .toString());
    List<Integer> numStars = new ArrayList<>();
    numStars.addAll(List.of(49, 48, 50, 45, 44, 42, 5, 5, 4, 4, 5));
    v.printPerformancePlot(numStars, timeStampForYaxis);
    assertEquals("", bytes.toString().trim());

  }

  @Test
  public void testPrintEnterDateForViewFlexi() {
    v.printEnterDateForViewFlexi();
    assertEquals("Enter date:", bytes.toString().trim());
  }

  @Test
  public void testPrintQuantityIsNegative() {
    v.printQuantityIsNegative();
    assertEquals("Portfolio has illegal transactions. (Negative number of stocks for"
            + " a company)", bytes.toString().trim());
  }

  @Test
  public void testPrintPerformancePlotScale() {
    v.printPerformancePlotScale("");
    assertEquals("---------------------------------------------------------------------\n"
            + "Scale: * = $", bytes.toString().trim());
  }

  @Test
  public void testPrintErrorDifferenceInDatesRequired() {
    v.printErrorDifferenceInDatesRequired();
    assertEquals("---------------------------------------------------------------------\n"
            + "ERROR: The difference between the start date mentioned should be more than 5 "
            + "days and less than 30 years.", bytes.toString().trim());
  }

  @Test
  public void testPrintTodayBuyCondition() {
    v.printTodayBuyCondition();
    assertEquals("If the stock market has not shut we will consider "
            + "yesterdays closing value.", bytes.toString().trim());
  }

  @Test
  public void testPrintErrorLoadingPortfolio() {
    v.printErrorLoadingPortfolio();
    assertEquals("------------------------------------------------------------------\n"
            + "ERROR: Could not load the portfolio!", bytes.toString().trim());
  }

}