package controller.textbased;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import model.MainFlexibleModel;
import model.MainImmutableModel;
import model.MainModel;
import view.gui.GuiView;
import view.textbased.View;

/**
 * This class implements the PortfolioController and contains the corresponding methods which
 * delegate all the work to the sub-controllers for Flexible and Immutable Portfolios.
 */
public class ControllerImpl implements Controller {

  private View view;

  private GuiView guiView;
  private String uname;
  final InputStream in;
  final PrintStream out;
  Controller cFlexi;
  Controller cImmu;

  MainModel mainMod;

  Scanner sc;

  /**
   * This constructor initializes the values of all the fields in the portfolio.
   *
   * @param view signifies the object of the view (PortfolioView) of the application
   * @param in   signifies the Input stream
   * @param out  signifies the print stream
   */
  public ControllerImpl(MainModel mainMod, View view, InputStream in,
                        PrintStream out) {
    this.view = view;
    this.in = in;
    this.out = out;
    this.uname = "";
    this.sc = new Scanner(in);
    this.mainMod = mainMod;
  }

  /**
   * This method is the starting point of the main controller and is responsible for redirecting
   * control to either ControllerFlexi or ControllerImmutable based on user inputs.
   */
  public void goController() {
    boolean userMenu = true;
    view.printWelcome();

    while (userMenu) {
      view.printUserMenu();

      String ch = sc.next();

      switch (ch) {
        case "1":
          userInfo();
          break;
        case "2":
          view.exitProgram(1);
          userMenu = false;
          break;
        default:
          view.printInvalidMainMenuOption();
          break;
      }
    }
  }

  private void portfolioOption() {
    boolean mainMenu = true;

    cFlexi = new ControllerFlexi(uname, view, in, out, sc, new MainFlexibleModel());
    cImmu = new ControllerImmutable(uname, view, in, out, sc, new MainImmutableModel());

    while (mainMenu) {
      view.printPortfolioMenu();
      String choice = sc.next();

      switch (choice) {
        case "1":
          cImmu.goController();
          break;
        case "2":
          cFlexi.goController();
          break;
        case "3":
          view.exitProgram(2);
          mainMenu = false;
          break;
        default:
          view.printInvalidMainMenuOption();
          break;
      }
    }
  }


  private void userInfo() {
    view.askForUserName();
    String un = sc.next();

    if (mainMod.checkFileNameFormat(un)) {
      this.uname = un;
      portfolioOption();
    } else {
      // Invalid username, should only contain Letters and digits and no whitespaces
      view.printInvalidUserNameFormat();
    }
  }
}
