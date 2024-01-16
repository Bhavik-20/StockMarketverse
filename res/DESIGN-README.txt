Name: Stock-Marketverse (Assignment 4 & 5)
Authors: Bhavik Bhatt (bhatt.bh@northeastern.edu) and Muskaan Nandu(nandu.mu@northeastern.edu)

-------------------------------------- ASSIGNMENT 6 -----------------------------------------------

CHANGES:

1) Command design pattern
 - To maintain modularity and not change existing code, the application now has a new controller
   interface named "ControllerGui" which is implemented using the Command Design Pattern.
   Each switch case statement is a feature of the application. The controller provides itself as the
   Action event listener to the GuiViewImpl class (The design of the view is explained below).
   Further, the controller now has a package called "commands" which consists of various command
   classes that each represent a feature of the application. The command classes take in the model
   from the ControllerGuiImpl and calls appropriate methods from the MainModel object.
 - This design increases the scalability of the application and incase many other switch cases need
   to be added, the controller can simply be turned into a map that takes in a string key and
   executes a function based on that which is a feature.

2) Controller
  - The old controller is untouched and there are no changes in the class implementations of the
    "Controller" interface. It is separated logically and the text based controller can be invoked
    when running the jar file (The instruction for the same is given in the SETUP-README.txt file)

3) Model
  - There is now an Investment Strategy object that represents a strategy like Dollar Cost Average.
    This helps to make the implementation modular and in the future when a new strategy needs to be
    implemented, this interface can simply have another implementation of it and there will be no
    changes in the method signatures of the existing strategy functions.
  - There are new methods in the existing MainModel implementation which contains the logic to
    need to implement the strategy. This results in no change in any other method signatures
    of the existing functions.

4) View
  - There is now a new GuiView interface that is implemented by the GuiViewImpl class.
    This interface acts as the main view class that receives the GuiController class to be set
    as its Action Listener as explained above. This view class is the only class that implements
    the JFrame and is responsible to delegate tasks to other view classes that are different
    JPanels.

5) Api
  - The Api interface now only has 1 method responsible for returning the value of a stock on a
    certain date. We removed the callApi() method as that was not require to be a part of the
    interface because the user simply needs the value of a stock and does not bother if it comes
    from an Api or some other source.
  - This also leaves the possibility to incorporate other Api's in the future as the user would
    never know where the data comes from and has no access to the method that communicates with the
    AlphaVantage Api (callApi()).
  - The ApiImpl class also no longer takes in the stock symbol and the date in the constructor
    as that requires creating a new object for every change in the stock symbol. The
    getStockValueOnDate method now simply takes in the stock and date and does not require creating
    new objects everytime for new Api Calls.

-------------------------------------- ASSIGNMENT 5 -----------------------------------------------

CHANGES:

1) The controller package now has 3 controllers and 1 interface:
    - This allows us to logically segregate the implementation of the 2 different flows of the application
      (Immutable portfolio flow and flexible portfolio flow). There is a main controller (ControllerImpl)
      that accepts user name and the type of portfolio the user wishes to work and based on input transfers
      flow to the respective type of controller (ControllerFlexi & ControllerImmutable which both implement
      Controller Interface). ControllerFlexi contains all the features applicable to the flexible portfolio
      and ControllerImmutable contains features for inflexible portfolio.
    - There is no change in the controller interface.

2) The model package now has different sub-packages:
    - This helps us separate the models into packages based on the purpose they serve.

3) Database Package has 2 types of database models (DatabaseFlexibleImpl & DatabaseImmutableImpl)
   with the common methods between them contained in the AbstractDatabase model. The interface is
   implemented by the Abstract model and this Abstract model is extended by the DatabaseFlexibleImpl
   & DatabaseImmutableImpl models.
    - This now makes the database functionality more scalable as any new functionalities get added
     to the interface and the common methods can be derived from the abstract class.

4) A new package has been added named "performance":
    - This package contains a new interface named "PerformanceModel" which has functionalities
      that help implement the new feature of viewing portfolio performance over time.
    - A new class named "PerformanceImpl" implements the above interface and contains the logic
      that helps plot a bar graph depicting the performance of a portfolio over a certain time
      range.

5) Portfolio package has 2 types of portfolio models (PortfolioFlexibleImpl & PortfolioImmutableImpl)
   with the common methods between them contained in the AbstractPortfolio model. The interface
   is implemented by the Abstract model and this Abstract model is extended by the PortfolioFlexibleImpl
   & PortfolioImmutableImpl models.
    - This now makes the incorporation of a new type of portfolio more manageable and scalable
      as any new functionalities get added to the interface and implemented by the new type of portfolio
      model class and the common methods can be derived from the abstract class.

6) The stock package has no new classes or interfaces addition. Instead the StockModel and Interface
   class now has more attributes (costPrice, purDate, transactionType) that makes the stock object more
   relatable to the real-life stocks.
     - The flexible portfolio will make use of these new features and the inflexible portfolio
       will simply keep using only the ticker and quantity feature of the StockModel.
     - The additional features are added to support the new type of Flexible portfolio in which
       stocks can be bought and sold on any date.

7) There are now 2 MainModels instead of 1. This helps incorporate the 2 flows of the application.
   - There exist an AbstractMainModel that contains the logic common to both the types of portfolios.
   - This Abstract class is extended by the other 2 Main Models (MainFlexibleModel & MainImmutableModel).
   - This allows the incorporation of a 3rd type of portfolio which will simply require adding new
     methods needed by the new type of portfolio in the interface and the logic for these methods
     is contained in the new portfolio model class that extends the abstract class and derives the common
     methods from it.

8) The Api interface now supports an additional functionality of getting the value of a portfolio
   on the specified date. This feature can be attributed to all types of portfolios and hence will
   support adding new type of portfolio in the future.
    - There are no class additions in the Api package.
    - The application now supports getting the value of a stock on weekends (previously did not
      allow entering a date that is SATURDAY or SUNDAY). This is to incorporate the real world
      scenario where stock prices are static on weekends and not 0.

9) The view package has no interface or class additions. Extra methods have been added to the interface
   and the methods contained in the View interface are used by both the types of the portfolios
   to appropriately display outputs on the console.

-------------------------------------- ASSIGNMENT 4 -----------------------------------------------
The program is divided into 3 different packages:

I) The Model
Consists of the following interfaces: MainModel, PortfolioModel, Stock, FileIO, API interfaces.
-> MainModel: Implemented By MainModelImpl
* This interface and class acts as the central model that is responsible for communicating with
the controller. Based on the user input, the controller communicates with the MainModelImpl and
then this model delegates the task to the portfolio model.
* This model also takes care of various validations like checking if a file is XML file or not,
if the directory and file exists, if the filename is correct etc.

-> PortfolioModel: Implemented By PortfolioModelImpl
* This interface amd class represents a portfolio and the functions that can be performed on a
portfolio. It manages the base directory where user data needs to be stored and also delegates
File input/output tasks to the FileImpl class.
* This model is also responsible for handling the stock ticker symbol and the quantity entered for
that stock. It validates these inputs and allows further processes only if the data entered is
valid.
* Further, this model checks if the stock entered into a portfolio is present in the ticker List
csv or not. It ensures the system takes care of any anomalies associated with the input data and
appropriately takes care of it.

-> Stock: Implemented by StockImpl
* This interface represents a stock entity. It has the stock name and the quantity associated with
that stock. It manages the stock objects that are used to make up a Portfolio.
* Fractional stocks of a company are allowed to be created by the user and all the computation works
normally.

-> FileIO: Implemented by FileImpl
* This interface and class are responsible for interacting with files and directories. All read,
write and update to & from a file are carried out by this class.
* This model is responsible for creating the appropriate XML file based on the user inputs of a
portfolio, reading from a XML file and handling the case if the file has invalid XML structure and
finally, copies a file from a destination folder to the source folder.

-> Api: Implemented by ApiImpl
* This interface and class are responsible for communicating with the AlphaVantage API. All data
related to a stock is obtained from the API using this class.
* This model is also responsible for converting the obtained data from the API to a string format
in the case of calling the api. It returns a double value when the value of a stock needs to be
obtained on a particular date.