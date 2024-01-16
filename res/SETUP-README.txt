Name: Stock-Marketverse (Assignment 4)
Authors: Bhavik Bhatt (bhatt.bh@northeastern.edu) and Muskaan Nandu(nandu.mu@northeastern.edu)

1. To access the application the jar file: Assignment-5_Stocks_2.jar needs to be executed from
commandline.
2. The jar file needs to be in the same folder along with listOfTicker.csv and directory named
   "data".

--------------------------- Assignment 6 ----------------------------------------------------------

 1. To access the application the jar file: Assignment-6_Stocks_1.jar needs to be executed from
 commandline.
 2. The jar file does require the listOftickers.csv to be in the same folder as it is.
 3. If the user chooses to work with text based interface they must run the command:
 "java -jar Assignment-6_Stocks_1.jar text".
 4. If the user wishes to work with the graphical user interface they must run the command:
  "java -jar Assignment-6_Stocks_1.jar".
 5. Once the program starts, the user can login to the system by entering their username and click
 on submit.
 6. The user can then select from the 4 options (Create an empty portfolio, upload a portfolio, work
    with existing portfolio and create a portfolio with dollar cost avg strategy) by clicking the
    appropriate buttons. The user can choose to go back by clicking the back button.


--------------------------- Assignment 5 ----------------------------------------------------------

 1. To access the application the jar file: Assignment-4_Stocks_1.jar needs to be executed from
 commandline.
 2. The jar file does not require to be in any specific folder.
 3. When the program begins, user is given the option to login with their username or exit the
 program by selecting option 1.
 4. If user enters the username, the user is given the option to choose if they want to work with
 immutable portfolio or flexible portfolio.
 5. By selecting 1, the user can work with immutable portfolio in the same way as described for
 assignment 4.
 6. By selecting 2, the user chooses to work with flexible portfolio. The user is now presented
 with following menu:
             ------------------------------------------
             Please select an option from the Menu below:
             1. CREATE a Portfolio
             2. UPLOAD a Portfolio
             3. Make a transaction
             4. VIEW composition of a Portfolio
             5. EXAMINE Portfolio value on certain date
             6. CALCULATE the COST BASIS of a portfolio
             7. View Portfolio PERFORMANCE
             8. Return to previous menu
             ---------------------------------------------
             Enter your choice here: 1
             ------------------------------------------------------------------
             Please enter the name of the portfolio: final1
             ------------------------------------------------------------------

             PORTFOLIO CREATED SUCCESSFULLY!

 [CREATE PORTFOLIO WITH -> PURCHASING STOCKS OF 3 DIFFERENT COMPANIES]
 7. The user can create a flexible portfolio by selecting option 1 where the user will be asked to
 enter the name of the portfolio.
 8. Then, the user can select option 3 to make a transaction. On selecting option 3 the user will be
 presented with the following options:
             -------------------------------------------
             Please select one of the following:
             1. BUY stocks
             2. SELL stocks
             3. SAVE changes and return to previous menu
             Enter your choice here:
             ---------------------------------------------
The user will be given this option until the user selects save.

9. The user can buy stocks by selecting option 1. The user will be asked to enter the ticker symbol,
quantity of stocks and the date at which they wish to buy the stock.
[This can be repeated 3 times to purchase stocks at 3 different dates]

            Enter your choice here: 3
            ------------------------------------------------------------------
            Please enter the name of the portfolio: final1
            ---------------------------------------------------------------------
            Please select one of the following:
            1. BUY stocks
            2. SELL stocks
            3. SAVE changes and return to previous menu
            Enter your choice here: 1
            Please enter the ticker symbol of the stock (eg: GOOG): GOOG
            Please enter the quantity of the stock (eg:10): 10
            Enter the date at which you want to buy the stock in yyyy-mm-dd format(in EST): 2022-11-01
            ---------------------------------------------------------------------
            SUCCESS: Stock has been added to the list successfully!
            Select save changes to add this list to the portfolio, if you don't wish to buy/sell any other stock.
            ---------------------------------------------------------------------
            Please select one of the following:
            1. BUY stocks
            2. SELL stocks
            3. SAVE changes and return to previous menu
            Enter your choice here: 1
            Please enter the ticker symbol of the stock (eg: GOOG): AAPL
            Please enter the quantity of the stock (eg:10): 20
            Enter the date at which you want to buy the stock in yyyy-mm-dd format(in EST): 2022-11-02
            ---------------------------------------------------------------------
            SUCCESS: Stock has been added to the list successfully!
            Select save changes to add this list to the portfolio, if you don't wish to buy/sell any other stock.
            ---------------------------------------------------------------------
            Please select one of the following:
            1. BUY stocks
            2. SELL stocks
            3. SAVE changes and return to previous menu
            Enter your choice here: 1
            Please enter the ticker symbol of the stock (eg: GOOG): TSLA
            Please enter the quantity of the stock (eg:10): 30
            Enter the date at which you want to buy the stock in yyyy-mm-dd format(in EST): 2022-11-03
            ---------------------------------------------------------------------
            SUCCESS: Stock has been added to the list successfully!
            Select save changes to add this list to the portfolio, if you don't wish to buy/sell any other stock.
            ---------------------------------------------------------------------
            Please select one of the following:
            1. BUY stocks
            2. SELL stocks
            3. SAVE changes and return to previous menu
            Enter your choice here: 3
            ---------------------------------------------------------------------

            SUCCESS: The list of transaction/s have been logged in the portfolio successfully!

10. The user can select 2, to sell stocks. The user will be asked to enter the ticker symbol,
quantity of stocks and the date at which they wish to sell the stock.
11. The user must select 3, to save and reflect these transactions in the portfolio and go
back to previous menu.

[QUERY FOR VALUE]

12. The user must select option 5 from the menu to examine the value of a portfolio at a certain
date.
[The user can repeat this twice to query on 2 different dates]
Enter your choice here: 5
            ------------------------------------------------------------------
            Please enter the name of the portfolio whose total value needs to be calculated: final1
            Please enter the date at which the total value needs to be calculated in the yyyy-mm-dd format (in EST): 2022-11-01
            ------------------------------------------------------------------

            The total value of the portfolio on given date is $905.0
            =======================================================================================
            Enter your choice here: 5
            ------------------------------------------------------------------
            Please enter the name of the portfolio whose total value needs to be calculated: final1
            Please enter the date at which the total value needs to be calculated in the yyyy-mm-dd format (in EST): 2022-11-05
            ------------------------------------------------------------------

            The total value of the portfolio on given date is $9858.7
[QUERY FOR COST BASIS ON 2 DATES]
13. The user must select option 6 from the menu to query the cost basis of a portfolio at any
particular date.
[The user can repeat this twice to query on 2 different dates]
            Enter your choice here: 6
            ------------------------------------------------------------------
            Please enter the name of the portfolio: final1
            Please enter the date till which the cost basis should be calculated in the yyyy-mm-dd format (in EST): 2022-11-05
            Please enter the commission fee charged by the Broker: $5
            ---------------------------------------------------------------------

            The total cost basis for this portfolio is $10279.9

            =============================================================================
            Enter your choice here: 6
            ------------------------------------------------------------------
            Please enter the name of the portfolio: final1
            Please enter the date till which the cost basis should be calculated in the yyyy-mm-dd format (in EST): 2022-11-02
            Please enter the commission fee charged by the Broker: $7
            ---------------------------------------------------------------------

            The total cost basis for this portfolio is $3819.6

--------------------------------------------- ASSIGNMENT 4 --------------------------------------
[Create a portfolio with 3 different stocks]
1. When the program begins the user is given an option to enter the username or exit. To proceed the
user must select option 1 by entering 1.
2. Next, the user must enter a username which must be a valid folder name.
3. The user will be presented with a menu of options where user must select 1 to create a new portfolio.
4. The user will be asked to enter a name of portfolio which they want to create.
5. Next the user must enter the Ticker symbol of the stock they want to add to the portfolio followed
by the quantity of that stock. (eg. GOOG-10)
6. Next the user shall select y if they want to proceed.
7. This should be repeated 2 more times to create a portfolio with 3 different stocks.
8. The user can type n when done.
[Create another portfolio with 2 stocks]
9. The user will be taken to the menu of options, where user can select 1 to create a portfolio
with 2 stocks by following the above steps or upload a portfolio with 2 stocks.
10. Once done, the user can view the composition of file by selecting option 3 from menu.
[Calculate value on certain date]
11. The user must select option 4 to calculate total value of a portfolio on certain date.
12. The user will be asked to enter the name of the portfolio whose value must be calculated.
13. Next, the user must enter the date in yyyy-mm-dd format for which the value must be calculated.
14. On clicking enter the total value of portfolio will be displayed.
----------------------------------------------------------------------------------------------------
eg: For portfolio having following stocks:
Stocks -> Quantity
------------------
GOOG -> 10.0 stocks
A -> 10.0 stocks
IBM -> 10.0 stocks
-------------------
Enter Date: 2022-11-03
The total value of the portfolio on given date is $3524.2


The list of ticker symbols supported are in the listOfStockTickers.csv.
Value is calculated using AlphaVantageAPI.

