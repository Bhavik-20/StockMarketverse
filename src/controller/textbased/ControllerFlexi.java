package controller.textbased;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Scanner;

import model.MainModel;
import view.textbased.View;

/**
 * This class implements the controller interface and defines all the methods that can be
 * implemented on a flexible portfolio.
 */
public class ControllerFlexi implements Controller {

  private String uname;
  private View view;
  final InputStream in;
  final PrintStream out;
  Scanner sc;
  private MainModel flexibleModel;


  /**
   * The constructor initializes the values of all the fields in the ControllerFlexi object.
   *
   * @param uname signifies the username
   * @param view  signifies the object of the view (PortfolioView) of the application
   * @param in    signifies the Input stream
   * @param out   signifies the print stream
   * @param model which signifies which kind of model the controller will work with
   */
  protected ControllerFlexi(String uname, View view, InputStream in,
                            PrintStream out, Scanner sc, MainModel model) {
    this.uname = uname;
    this.view = view;
    this.in = in;
    this.out = out;
    this.flexibleModel = model;
    this.sc = sc;

    if (!flexibleModel.userDirectoryExist(uname)) {
      if (!flexibleModel.createUserDirectory(uname)) {
        view.printErrorInUserCreation();
      } else {
        view.printSuccessNewFile();
      }
    }
  }

  @Override
  public void goController() {
    boolean mainMenu = true;

    while (mainMenu) {
      view.printFlexiblePortfolioMenu();
      String choice = sc.next();

      switch (choice) {
        case "1":
          // create new flexible portfolio
          createFlexiPortfolio();
          break;
        case "2":
          // upload flexible portfolio
          uploadFlexiPortfolio();
          break;
        case "3":
          // update
          addTransactionToPortfolio();
          break;
        case "4":
          viewFlexiContents();
          // view components
          break;
        case "5":
          // get value at a particular date?
          getFlexiValueOnDate();
          break;
        case "6":
          // get cost basis.
          getCostBasis();
          break;
        case "7":
          // Portfolio performance.
          portolioPerformance();
          break;
        case "8":
          view.exitProgram(2);
          mainMenu = false;
          break;
        default:
          view.printInvalidMainMenuOption();
          break;
      }
    }
  }

  private void addTransactionToPortfolio() {
    boolean mainMenu = true;

    view.printEnterPortfolioNameToOpen();

    String portfolioName = sc.next();

    if (flexibleModel.mainModCheckPortfolioNameExists(uname, portfolioName)) {
      if (flexibleModel.loadPortfolio(uname, portfolioName)) {
        while (mainMenu) {
          view.printWhichTransaction();
          String ch = sc.next();

          switch (ch) {
            case "1":
              buyStock(portfolioName);
              break;
            case "2":
              sellStock(portfolioName);
              break;
            case "3":
              convertToStockWriteToFile(portfolioName);
              mainMenu = false;
              break;
            default:
              view.printInvalidMainMenuOption();
          }
        }
      } else {
        // error loading portfolio
        view.printErrorLoadingPortfolio();
      }
    } else {
      view.printFileNotFound();
    }
  }

  private void convertToStockWriteToFile(String portfolioName) {
    flexibleModel.mainModConvertAllStocksToStockObj();

    if (flexibleModel.mainModCreatePortfolio(uname, portfolioName)) {
      view.printFileUpdated();
    } else {
      view.printFileCreationFailed();
    }
  }


  private void uploadFlexiPortfolio() {
    view.printAskForUploadPath();
    String path = sc.next();

    if (flexibleModel.mainModCheckPathExists(path)) {
      if (flexibleModel.checkFileIsXML(path)) {
        if (flexibleModel.mainModUploadPortfolio(uname, path)) {
          view.printPortfolioUploaded();
          String[] p = path.split("/");
          String[] fname = p[p.length - 1].split(".xml");
          viewFlexiContentsHelper(fname[0], LocalDate.now().toString());
        } else {
          view.printErrorPortfolioUpload();
        }
      } else {
        view.printInvalidUploadFileType();
      }
    } else {
      view.printInvalidFileDirectory();
    }
  }

  private void viewFlexiContentsHelper(String fname, String date) {

    if (flexibleModel.mainModViewPortfolio(uname, fname, date).equals("File Not Found")) {
      view.printFileNotFound();
    } else if (flexibleModel.mainModViewPortfolio(uname, fname, date)
            .equals("Incorrect file format")) {
      view.printIncorrectFileFormatUploaded();
      flexibleModel.deleteFile(uname, fname);
      view.printPortfolioDeleted();
    } else if (flexibleModel.mainModViewPortfolio(uname, fname, date)
            .equals("Portfolio contains invalid ticker symbols")) {
      view.printTickerIsInvalid();
      flexibleModel.deleteFile(uname, fname);
      view.printPortfolioDeleted();
    } else if (flexibleModel.loadPortfolio(uname, fname)
            && !flexibleModel.calcQuantityOfUploadedFile()) {
      view.printQuantityIsNegative();
      flexibleModel.deleteFile(uname, fname);
      view.printPortfolioDeleted();
    } else {
      String res = flexibleModel.mainModViewPortfolio(uname, fname, date);
      if (res.equals("Incorrect file format") || res.equals("You have no stocks in this portfolio")
              || res.equals("Portfolio contains invalid ticker symbols")) {
        res = "No Stocks";
      }
      view.printFileComposition(res);
    }
  }

  private void viewFlexiContents() {
    view.printEnterPortfolioNameToOpen();
    String portfolioName = sc.next();

    view.printEnterDateForViewFlexi();
    String date = sc.next();

    if (flexibleModel.checkDateFormat(date)) {
      if (!flexibleModel.checkFutureDate(date)) {
        viewFlexiContentsHelper(portfolioName, date);
      } else {
        view.printErrorFutureDate();
      }
    } else {
      view.printInvalidDateFormat();
    }

  }

  private void createFlexiPortfolio() {
    view.printEnterPortfolioNameToOpen();
    String portfolioName = sc.next();
    if (flexibleModel.checkFileNameFormat(portfolioName)) {
      if (flexibleModel.mainModCheckPortfolioNameExists(uname, portfolioName)) {
        view.printChooseOtherName();
      } else {
        if (flexibleModel.mainModCreatePortfolio(uname, portfolioName)) {
          view.printFileCreated();
        } else {
          view.printFileCreationFailed();
        }
      }
    } else {
      view.printInvalidFileNameFormat();
    }
  }

  private void getFlexiValueOnDate() {
    view.printEnterPortfolioName();
    String portfolioName = sc.next();

    view.printEnterDate();
    String date = sc.next();

    if (flexibleModel.mainModCheckPortfolioNameExists(uname, portfolioName)) {
      if (flexibleModel.checkDateFormat(date)) {
        if (flexibleModel.checkValidDateAndName(uname, portfolioName, date)
                .equals("File Not Found")) {
          view.printFileNotFound();
        } else if (flexibleModel.checkValidDateAndName(uname, portfolioName, date)
                .equals("Incorrect Date Format")) {
          view.printIncorrectDateFormat();
        } else if (flexibleModel.checkFutureDate(date)) {
          view.printErrorFutureDate();
        } else {
          if (flexibleModel.loadPortfolio(uname, portfolioName)) {
            String res = flexibleModel.mainModGetValueOnDate(uname, portfolioName, date);
            view.printPortfolioValueOnDate(res);
            if (flexibleModel.checkTodayDate(date)) {
              view.printTodayDateCondition();
            }
          }
        }
      } else {
        view.printInvalidDateFormat();
      }
    } else {
      view.printFileNotFound();
    }
  }

  private void sellStock(String portfolioName) {
    view.printEnterStockName();
    String stockName = sc.next();

    view.printEnterStockQty();
    String stockQty = sc.next();

    view.printEnterSellDate();
    String purDate = sc.next();

    view.askForCommissionFee();
    String commissionFee = sc.next();

    if (flexibleModel.checkFileNameFormat(portfolioName)) {
      if (flexibleModel.checkDateFormat(purDate)) {
        if (flexibleModel.checkValidDateAndName(uname, portfolioName, purDate)
                .equals("File Not Found")) {
          view.printFileNotFound();
        } else if (flexibleModel.checkValidDateAndName(uname, portfolioName, purDate)
                .equals("Incorrect Date Format")) {
          view.printIncorrectDateFormat();
        } else if (flexibleModel.checkFutureDate(purDate)) {
          view.printErrorFutureDate();
        } else if (!flexibleModel.checkAmount(Double.parseDouble(commissionFee))) {
          view.printInvalidCommissionFee();
        } else {
          String res = flexibleModel
                  .removeStockFromPortfolio(stockName.toUpperCase(), stockQty, purDate,
                          commissionFee);
          if (res.equals("true")) {
            view.printStockSold();
          } else if (res.equals("negative")) {
            // negative quantity entered
            view.printNegativeQuantity();
          } else if (res.equals("fractional")) {
            // fractional quantity entered
            view.printFractionalQuantity();
          } else if (res.equals("NA")) {
            // These many stocks not available to sell for the given date
            view.printStocksNotAvailableToSell();
          } else if (res.equals("invalidTicker")) {
            // Ticker is invalid
            view.printInvalidTicker();
          } else if (res.equals("NP")) {
            view.printStockAlreadySoldInFuture();
          }
        }
      } else {
        view.printInvalidDateFormat();
      }
    } else {
      view.printFileNotFound();
    }
  }

  private void buyStock(String portfolioName) {
    view.printEnterStockName();
    String stockName = sc.next();

    view.printEnterStockQty();
    String stockQty = sc.next();

    view.printEnterPurchaseDate();
    String purDate = sc.next();

    view.askForCommissionFee();
    String commissionFee = sc.next();

    if (flexibleModel.checkFileNameFormat(portfolioName)) {
      if (flexibleModel.checkDateFormat(purDate)) {
        if (flexibleModel
                .checkValidDateAndName(uname, portfolioName, purDate).equals("File Not Found")) {
          view.printFileNotFound();
        } else if (flexibleModel.checkValidDateAndName(uname, portfolioName, purDate)
                .equals("Incorrect Date Format")) {
          view.printIncorrectDateFormat();
        } else if (flexibleModel.checkFutureDate(purDate)) {
          view.printErrorFutureDate();
        } else if (!flexibleModel.checkAmount(Double.parseDouble(commissionFee))) {
          view.printInvalidCommissionFee();
        } else {
          if (flexibleModel.addStockToPortfolio(stockName.toUpperCase(), stockQty, purDate,
                  commissionFee, 0)) {
            view.printStockAddedToList();
          } else {
            view.printInvalidStockData();
          }
        }
      } else {
        view.printInvalidDateFormat();
      }
    } else {
      view.printFileNotFound();
    }
  }


  private void portolioPerformance() {
    view.printEnterPortfolioNameToOpen();
    String portfolioName = sc.next();
    double scale;

    if (flexibleModel.mainModCheckPortfolioNameExists(uname, portfolioName)) {

      view.printEnterFromDate();
      String fromDate = sc.next();

      if (flexibleModel.checkDateFormat(fromDate)) {
        if (flexibleModel.checkValidDateAndName(uname, portfolioName, fromDate)
                .equals("Incorrect Date Format")) {
          view.printInvalidDateFormat();
        } else if (flexibleModel.checkFutureDate(fromDate)) {
          view.printErrorFutureDate();
        } else {
          view.printEnterTillDate();
          String tillDate = sc.next();
          // validation
          if (flexibleModel.checkDateFormat(tillDate)) {
            if (flexibleModel.checkValidDateAndName(uname, portfolioName, tillDate)
                    .equals("Incorrect Date Format")) {
              view.printInvalidDateFormat();
            } else if (flexibleModel.checkFutureDate(tillDate)) {
              view.printErrorFutureDate();
            } else {
              // ========== main logic for view performance
              if (flexibleModel.loadPortfolio(uname, portfolioName)) {
                if (flexibleModel.returnPerformanceTimestampScale(fromDate, tillDate)) {
                  scale = flexibleModel.getScaleBarGraph(fromDate, tillDate);
                  view.printPerformancePlotTopic(portfolioName, fromDate, tillDate);
                  view.printPerformancePlot(flexibleModel.getNumOfStars(scale),
                          flexibleModel.getTimeStampForYaxis());
                  view.printPerformancePlotScale(String.valueOf(scale));
                } else {
                  // print minimum 5 days needed to evaluate and maximum 30 years
                  view.printErrorDifferenceInDatesRequired();
                }
              }
            }
          } else {
            view.printIncorrectDateFormat();
          }
        }
      } else {
        view.printIncorrectDateFormat();
      }
    } else {
      view.printFileNotFound();
    }

  }

  private void getCostBasis() {
    view.printEnterPortfolioNameToOpen();
    String portfolioName = sc.next();

    view.printEnterDateCostBasis();
    String date = sc.next();

    if (flexibleModel.mainModCheckPortfolioNameExists(uname, portfolioName)) {
      if (flexibleModel.checkDateFormat(date)) {
        if (flexibleModel.checkValidDateAndName(uname, portfolioName, date)
                .equals("File Not Found")) {
          view.printFileNotFound();
        } else if (flexibleModel.checkValidDateAndName(uname, portfolioName, date)
                .equals("Incorrect Date Format")) {
          view.printIncorrectDateFormat();
        } else {
          if (flexibleModel.loadPortfolio(uname, portfolioName)) {
            String costBasis = flexibleModel.calculateCostBasis(portfolioName,
                    date);
            view.printTotalCostBasis(costBasis);
          } else {
            // error loading portfolio
            view.printErrorLoadingPortfolio();
          }
        }
      } else {
        view.printInvalidDateFormat();
      }
    } else {
      view.printFileNotFound();
    }
  }
}
