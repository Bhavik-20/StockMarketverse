package view.gui;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;

import controller.gui.ControllerGui;

/**
 * This page represents the 1st screen of the application and asks the user to enter their username.
 */
class Page1Welcome implements Page {

  private JButton submitUsername;
  private JTextField username;
  private JPanel heading1;
  private JPanel inputPanel1;

  CommissionFee.MyColors color;
  private JFrame frame;

  /**
   * The constructor takes in the frame as input and sets up the welcome page.
   *
   * @param frame signifies JFrame which represents the application window.
   */
  public Page1Welcome(JFrame frame) {
    this.frame = frame;
    color = new CommissionFee.MyColors();

    heading1 = new JPanel();
    heading1.setPreferredSize(new Dimension(700, 100));
    heading1.setBackground(color.bgcol);
    JLabel title = new JLabel("<html><h2>Welcome to Stock Marketverse!</h2></html>");
    heading1.add(title);

    inputPanel1 = new JPanel();
    inputPanel1.setPreferredSize(new Dimension(700, 200));
    inputPanel1.setLayout(new FlowLayout());
    inputPanel1.setBackground(color.bgcol);

    JLabel askUserName = new JLabel("Enter Username: ");
    inputPanel1.add(askUserName);

    username = new JTextField(10);
    inputPanel1.add(username);

    submitUsername = new JButton("Submit");
    inputPanel1.add(submitUsername);
  }

  @Override
  public void setPanel() {
    frame.add(heading1, BorderLayout.PAGE_START);
    frame.add(inputPanel1, BorderLayout.CENTER);
    frame.setVisible(true);
  }

  @Override
  public void removePanel() {
    frame.getLayout().removeLayoutComponent(heading1);
    frame.getLayout().removeLayoutComponent(inputPanel1);
  }

  @Override
  public Map<String, String> getInputs() {
    Map<String, String> inp = new HashMap<>();
    inp.put("username", username.getText());
    return inp;
  }

  @Override
  public void addFeatures(ControllerGui features) {
    submitUsername.addActionListener(evt -> features.goGui("Username"));
  }

  @Override
  public void resetInputs() {
    username.setText("");
  }
}

