package controller.textbased;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import model.MainModel;
import view.textbased.View;

/**
 * This class implements the controller interface and defines all the methods that can be
 * implemented on the immutable portfolio.
 */
public class ControllerImmutable implements Controller {

  private String uname;
  private View view;
  final InputStream in;
  final PrintStream out;
  Scanner sc;
  private MainModel immutableModel;


  /**
   * The constructor initializes the values of all the fields in the ControllerImmutable object.
   *
   * @param uname signifies the username
   * @param view  signifies the object of the view (PortfolioView) of the application
   * @param in    signifies the Input stream
   * @param out   signifies the print stream
   * @param model which signifies which kind of model the controller will work with
   */
  protected ControllerImmutable(String uname, View view, InputStream in,
                                PrintStream out, Scanner sc, MainModel model) {
    this.uname = uname;
    this.view = view;
    this.in = in;
    this.out = out;
    //    sc = new Scanner(in);
    this.sc = sc;
    this.immutableModel = model;

    if (!immutableModel.createUserDirectory(uname)) {
      view.printErrorInUserCreation();
    }

  }

  @Override
  public void goController() {
    boolean mainMenu = true;

    while (mainMenu) {
      view.printMainMenu();

      String choice = sc.next();

      switch (choice) {
        case "1":
          createNewPortfolio();
          break;
        case "2":
          uploadNewPortfolio();
          break;
        case "3":
          viewPortfolioContents();
          break;
        case "4":
          valueOnDate();
          break;
        case "5":
          view.exitProgram(2);
          mainMenu = false;
          break;
        default:
          view.printInvalidMainMenuOption();
          break;
      }
    }
  }


  private void valueOnDate() {
    view.printEnterPortfolioName();

    String portfolioName = sc.next();

    if (immutableModel.mainModCheckPortfolioNameExists(uname, portfolioName)) {
      view.printEnterDate();
      String date = sc.next();
      if (immutableModel.checkDateFormat(date)) {
        if (immutableModel.checkValidDateAndName(uname, portfolioName, date)
                .equals("File Not Found")) {
          view.printFileNotFound();
        } else if (immutableModel.checkValidDateAndName(uname, portfolioName, date)
                .equals("Incorrect Date " + "Format")) {
          view.printIncorrectDateFormat();
        } else if (immutableModel.checkFutureDate(date)) {
          view.printErrorFutureDate();
        } else {
          view.printPortfolioValueOnDate(immutableModel
                  .mainModGetValueOnDate(uname, portfolioName, date));
          if (immutableModel.checkTodayDate(date)) {
            view.printTodayDateCondition();
          }
        }
      } else {
        view.printInvalidDateFormat();
      }
    } else {
      view.printFileNotFound();
    }

  }

  private void createNewPortfolio() {
    boolean moreStocks = true;

    view.printEnterNewPortfolioName();

    String portfolioName = sc.next();
    if (immutableModel.checkFileNameFormat(portfolioName)) {
      createNewPortfolioHelper(portfolioName, moreStocks);
    } else {
      view.printInvalidFileNameFormat();
    }
  }

  private void createNewPortfolioHelper(String portfolioName, boolean moreStocks) {
    if (immutableModel.mainModCheckPortfolioNameExists(uname, portfolioName)) {
      view.printChooseOtherName();
    } else {
      while (moreStocks) {
        view.printEnterStockName();
        String stockName = sc.next();

        view.printEnterStockQty();
        String stockQty = sc.next();

        if (!immutableModel.mainModHandleData(stockName.toUpperCase(), stockQty)) {
          view.printInvalidStockData();
        } else {
          view.printAddMoreStocksToPortfolio();

          String inp = sc.next();

          moreStocks = inp.equals("Y") || inp.equals("y");
        }
      }
      immutableModel.mainModConvertAllStocksToStockObj();

      if (immutableModel.mainModCreatePortfolio(uname, portfolioName)) {
        view.printFileCreated();
      } else {
        view.printFileCreationFailed();
      }
    }
  }

  private void uploadNewPortfolio() {
    view.printAskForUploadPath();
    String path = sc.next();

    if (immutableModel.mainModCheckPathExists(path)) {
      if (immutableModel.checkFileIsXML(path)) {
        if (immutableModel.mainModUploadPortfolio(uname, path)) {
          view.printPortfolioUploaded();
          String[] p = path.split("/");
          String[] fname = p[p.length - 1].split(".xml");
          viewContentsHelper(fname[0]);
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

  private void viewContentsHelper(String fname) {
    if (immutableModel.mainModViewPortfolio(uname, fname, "").equals("File Not Found")) {
      view.printFileNotFound();
    } else if (immutableModel.mainModViewPortfolio(uname, fname, "")
            .equals("Incorrect file format")) {
      view.printIncorrectFileFormatUploaded();
      immutableModel.deleteFile(uname, fname);
      view.printPortfolioDeleted();
    } else if (immutableModel.mainModViewPortfolio(uname, fname, "")
            .equals("Portfolio contains invalid ticker symbols")) {
      view.printTickerIsInvalid();
      immutableModel.deleteFile(uname, fname);
      view.printPortfolioDeleted();
    } else {
      view.printFileComposition(immutableModel.mainModViewPortfolio(uname, fname, ""));
    }
  }

  private void viewPortfolioContents() {
    view.printEnterPortfolioNameToOpen();
    String portfolioName = sc.next();

    viewContentsHelper(portfolioName);
  }

}
