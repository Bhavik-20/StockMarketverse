package controller.gui.commands;

import java.time.LocalDate;

import model.MainModel;
import view.gui.GuiView;

/**
 * This class is responsible for calling the model methods related to implementing the upload a
 * portfolio from the users desktop.
 */
public class P2UploadPortfolio implements Command {
  private String uname;
  private String path;
  private GuiView view;

  /**
   * The constructor initializes the variables needed to implement the feature of uploading a file.
   *
   * @param uname username
   * @param path  the path from where the file needs to be uploaded
   * @param view  the GuiView object to show messages to the user
   */
  public P2UploadPortfolio(String uname, String path, GuiView view) {
    this.uname = uname;
    this.path = path;
    this.view = view;
  }

  @Override
  public void runCommand(MainModel flexibleModel) {
    if (flexibleModel.mainModCheckPathExists(path)) {
      if (flexibleModel.checkFileIsXML(path)) {
        if (flexibleModel.mainModUploadPortfolio(uname, path)) {
          view.showMessage("Portfolio Uploaded Successfully!");
          String[] p = path.split("/");
          String[] fname = p[p.length - 1].split(".xml");
          viewFlexiContentsHelper(fname[0], LocalDate.now().toString(), flexibleModel);
        } else {
          view.showError("An Error Occurred in uploading the portfolio!");
        }
      } else {
        view.showError("Only XML file types allowed!");
      }
    } else {
      view.showError("This file directory does not exist!");
    }
  }

  private void viewFlexiContentsHelper(String fname, String date, MainModel flexibleModel) {
    if (flexibleModel.mainModViewPortfolio(uname, fname, date).equals("File Not Found")) {
      view.showError("Error: This Portfolio does not exist. Please enter a valid"
              + " portfolio name.");
    } else if (flexibleModel.mainModViewPortfolio(uname, fname, date)
            .equals("Incorrect file format")) {
      view.showError("Error: Incorrect File format Uploaded!\nALERT: PORTFOLIO DELETED!");
      flexibleModel.deleteFile(uname, fname);
    } else if (flexibleModel.mainModViewPortfolio(uname, fname, date)
            .equals("Error: Portfolio contains invalid ticker symbols")) {
      flexibleModel.deleteFile(uname, fname);
      view.showError("Error: File contains Invalid Tickers!\nALERT: PORTFOLIO DELETED!");
    } else if (flexibleModel.loadPortfolio(uname, fname)
            && !flexibleModel.calcQuantityOfUploadedFile()) {
      flexibleModel.deleteFile(uname, fname);
      view.showError("Error: Portfolio has illegal transactions! (Negative number of stocks"
              + " for a company)\nALERT: PORTFOLIO DELETED!");
    } else {
      view.showComposition(flexibleModel.mainModViewPortfolio(uname, fname, date));
    }
  }
}
