package controller.gui.commands;

import model.MainModel;
import view.gui.GuiView;

/**
 * This command class is responsible for creating a new user if the username does not exist.
 * Else, log in the registered user.
 */
public class P1Username implements Command {

  private String uname;
  private GuiView view;

  /**
   * Initializes the username and the GuiView object.
   *
   * @param uname string containing username to be processed on.
   * @param view  GuiView object to which messages need to be passed.
   */
  public P1Username(String uname, GuiView view) {
    this.uname = uname;
    this.view = view;
  }

  @Override
  public void runCommand(MainModel mainMod) {
    if (mainMod.checkFileNameFormat(uname)) {
      if (!mainMod.userDirectoryExist(uname)) {
        if (mainMod.createUserDirectory(uname)) {
          view.setRemovePanel("Page1");
          view.setRemovePanel("remove_all");
          view.setAddPanel("Page2");
          view.showMessage("User Created Successfully!");
        } else {
          view.showError("Error: Could not create a new user with this name!");
        }
      } else {
        view.setRemovePanel("Page1");
        view.setRemovePanel("remove_all");
        view.setAddPanel("Page2");
      }
    } else {
      view.showError("Error: Username should only contain letters & digits.");
    }
  }
}