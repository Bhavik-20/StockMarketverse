import controller.gui.ControllerGui;
import controller.gui.ControllerGuiImpl;
import controller.textbased.Controller;
import controller.textbased.ControllerImpl;
import model.MainFlexibleModel;
import model.MainModel;
import view.gui.GuiView;
import view.gui.GuiViewImpl;
import view.textbased.View;
import view.textbased.ViewImpl;

/**
 * Stock-Marketverse is an application which allows you to create a portfolio of stocks, upload a
 * file that consists of a portfolio of stocks, view its composition and calculate the total value
 * of a portfolio.
 */
public class Main {

  /**
   * This method represents the invokes the application.
   */
  public static void main(String[] args) {

    MainModel mainModel = new MainFlexibleModel();

    // Create View Object
    View view = new ViewImpl(System.out);

    if (args.length > 0 && args[0].equals("text")) {
//                  GuiView guiView = new GuiViewImpl("Stock-Marketverse");
//                  ControllerGui guiController = new ControllerGuiImpl(mainModel, guiView);
      Controller controller = new ControllerImpl(mainModel, view, System.in,
              System.out);
      controller.goController();
    } else {
      GuiView guiView = new GuiViewImpl("Stock-Marketverse");
      ControllerGui guiController = new ControllerGuiImpl(mainModel, guiView);
//                  Controller controller = new ControllerImpl(mainModel, view, System.in,
//                          System.out);
//                  controller.goController();
    }
  }

}
