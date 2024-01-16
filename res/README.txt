Name: Stock-Marketverse (Assignment 4 & Assignment 5)
Authors: Bhavik Bhatt (bhatt.bh@northeastern.edu) and Muskaan Nandu(nandu.mu@northeastern.edu)

Stock Marketverse

The following features work for Stock-Marketverse:

Stock-Marketverse now supports 2 interfaces - GUI and text-based interface.

The text based interface of Stock-Marketverse gives options to create 2 types of portfolios:
  - Immutable or dummy portfolio which serves as a simpler tool for "if I had this composition in
   my portfolio, what would its value be on certain dates?" type of queries
  - Flexible portfolios which are more realistic and allows the user to manage the stocks they have
     and support additional functionalities which are stated below.

Apart from the previous assignment features, the GUI based interface of Stock-Marketverse gives
users the following options:
1. Create a new Portfolio with Dollar Cost Average strategy (Start-to-finish single operation).
2. Apply the Dollar Cost Average strategy to an existing FLEXIBLE portfolio.
 - If the strategy applied has no end date (or future date) then the application executes the
   strategy until yesterday and sets the start date to be equal to yesterday's date and PERSISTED
   in the file. Next time when the user loads this same portfolio, the application first applies
   the Dollar cost average strategy based on the start and end date and updates the portfolio.
   - The above step repeats in the future till the end date is not met.
3. Invest a fixed amount into a number of stocks based on the specified weightage for each stock
   with respect to the invest amount. The investment amount is first deducted by the CommissionFee *
   No.of transactions and the weightage is applied. This is the same scenario for the above 2
   points as well.

A user can login to the system with their username which will create the user's directory. Next the
user will be given the option of choosing if they wish to work with immutable portfolio (dummy
portfolios) or flexible portfolio(realistic portfolio).

------------------------------------- IMMUTABLE ---------------------------------------------------
If the user selects Immutable portfolio the user can do the following:

1. A user can create a portfolio of stocks from the list of ticker symbols that Stock-Marketverse
supports. This will create a xml file with the filename that the user enters at the user's
directory (a folder with the username).

 - Stock-Marketverse supports over 12000 Ticker Symbols which can be found in the listOfStockTicker.csv
   attached to the submission.
 - The username can be a valid folder name only, else an error is depicted.
 - If a user enters the same stock more than once in the same portfolio then the quantity of the stocks
   are consolidated.

2. A user can upload a file in their directory and load its composition to examine the portfolio.
This file should be of xml format, should have a ticker symbol for the company whose stock a user
wishes to add to the portfolio and it's quantity. The structure of this file should be as follows:
<portfolio>
    <stock>
        <name>A</name>
        <quantity>1</quantity>
    </stock>
    <stock>
        <name>GOOG</name>
        <quantity>1</quantity>
    </stock>
</portfolio>

Where the stock tag represents a company's stocks, the name represents its ticker symbol and
quantity represents the number of stocks for that company.
- The user must input the path of the file that needs to be uploaded with forward slashes.
For eg: C:/desktop/muskaan.xml
- A warning is displayed that if a portfolio file with the same name is uploaded, it gets replaced.
- If the structure of the uploaded file does not match the above requirement, an error message is
displayed and the file is deleted from the user directory.

3. A user can view the composition of a portfolio which was created/uploaded before.

4. A user can find the total value of a portfolio on a current date. The current date should be in
the yyyy-mm-dd format in EST.
- The user can calculate the total values for a fractional stock.
- The closing value of the given date is used to calculate the total value of the portfolio.
- An error is displayed if value for a stock is requested for a future date.
- An error is displayed if value for a stock is requested for a weekend as stock market is closed
on weekends.

Stock-Marketverse uses values of a stock from the AlphaVantage API to calculate the value of a
portfolio.

Limitations:
* For now the application supports a maximum of 5 stocks per portfolio due to the limitations
of API.

------------------------------------- FLEXIBLE ---------------------------------------------------
If the user selects flexible portfolio, the user can do the following:

1. The user can create a portfolio by selecting option 1 from the menu displayed. This will create
   an empty portfolio (.xml) file for the user in the data/username/flexible directory.

2. The user can upload a portfolio to the application by selecting option 2. The path to the file to
   uploaded must be entered using forward slashes (eg: C:/android/valid-2.xml). This will copy
   the file to the users directory and then checked against its structure and logical data contained
   in it (more stocks sold than bought, negative number of stock).

3. Next the user can make any transaction by selecting option 3. The user will be presented with
   3 Other options - buy a stock, sell a stock and save changes.

    a. When the user selects buy stocks, the ticker symbol and quantity of the stock is asked,
    followed by the date at which the user wishes to buy these stocks.

    b. When the user selects sell stocks, the ticker symbol and quantity of the stocks the user
    wishes to sell are asked, followed by the date at which they wish to sell these stocks.
        - The inputs are logically checked for invalid inputs like trying to sell more stocks
          than the current withholding and trying to sell stocks before they are bought.

    c. Once the user is done with all the transactions, he must save and exit the transactions
    entered for it to be added to the portfolio.

4. The user can also upload an xml file containing the portfolio. This file must end with a .xml
extension and must have the following structure.

<portfolio>
    <stock>
        <name>GOOG</name>
        <quantity>10.0</quantity>
        <costPrice>2893.59</costPrice>
        <purchaseDate>2022-01-01</purchaseDate>
        <transactionType>Buy</transactionType>
    </stock>
    <stock>
        <name>AAPL</name>
        <quantity>1.0</quantity>
        <costPrice>96.73</costPrice>
        <purchaseDate>2022-11-11</purchaseDate>
        <transactionType>Buy</transactionType>
    </stock>
</portfolio>

Where the stock tag represents a company's stocks, the name represents its ticker symbol, the
quantity represents the number of stocks for that company, costPrice represents the cost at which
the stock was bought, purchase date represents the date at which the stock was bought and the
transaction type describes the type of transaction.

- The user must input the path of the file that needs to be uploaded with forward slashes.
For eg: C:/desktop/muskaan.xml
- A warning is displayed that if a portfolio file with the same name is uploaded, it gets replaced.
- If the structure of the uploaded file does not match the above requirement, an error message is
displayed and the file is deleted from the user directory.
- If the quantity/ticker symbol of stocks in uploaded file are invalid an error is displayed.
- If the user tries to sell more stocks than what they have for a specific company, an error is
displayed.

5. The user can view the composition of a portfolio on a specific date by selecting the option 4
of flexible portfolio menu. The user will be asked to enter the name of the portfolio and the date
at which they wish to view the composition of the portfolio.

6. The user can calculate the cost basis (total money invested) of the portfolio on a specific date
by selecting the option 6 on the menu. The user will be asked to enter the name of the portfolio,
the date till which they want to calculate the cost basis of the portfolio and the commission fee
charged by their broker.

7. The user can view the performance of their portfolio by selecting the option 7 in the menu. The
user will be asked to enter the name of the portfolio the date from which they wish to begin the
visualization of the performance and the date till which they wish to visualize the performance.