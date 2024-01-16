package view.gui;

import java.awt.FlowLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;

import controller.gui.ControllerGui;

/**
 * This page represents the panel which asks user for portfolio name when the user choose to work
 * with an existing portfolio.
 */
class Page2ExistingPortfolioName implements Page {

  private JTextField portfolioname;
  private JPanel inputPanel2;
  private JButton submitPortfolioname;
  private JFrame frame;

  /**
   * The constructor sets up the panel which can be added to the main frame.
   *
   * @param frame frame signifies JFrame which represents the application window.
   */
  public Page2ExistingPortfolioName(JFrame frame) {
    this.frame = frame;
    // create input panel2
    inputPanel2 = new JPanel();
    inputPanel2.setLayout(new FlowLayout());
    inputPanel2.setBackground(new Color(0xAED6F1));

    JLabel askPortfolioName = new JLabel("Enter Existing Portfolio Name: ");
    inputPanel2.add(askPortfolioName);

    portfolioname = new JTextField(10);
    inputPanel2.add(portfolioname);

    submitPortfolioname = new JButton("Submit");
    submitPortfolioname.setActionCommand("Submit");
    inputPanel2.add(submitPortfolioname);
  }

  @Override
  public void setPanel() {
    frame.add(inputPanel2);
    frame.setVisible(true);
  }

  @Override
  public void removePanel() {
    portfolioname.setText("");
    frame.remove(inputPanel2);
  }

  @Override
  public Map<String, String> getInputs() {
    Map<String, String> inp = new HashMap<>();
    inp.put("portfolioName", portfolioname.getText());
    return inp;
  }

  @Override
  public void addFeatures(ControllerGui features) {
    submitPortfolioname.addActionListener(evt -> features.goGui("Existing Portfolio"));
  }

  @Override
  public void resetInputs() {
    portfolioname.setText("");
  }
}
