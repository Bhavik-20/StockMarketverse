package view.gui;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import controller.gui.ControllerGui;

/**
 * This class represents the page that asks the user for inputs to calculate the value of the
 * portfolio on a certain date.
 */
class Page3GetPortfolioValDate implements Page {

  JFrame frame;
  CommissionFee.DatePanel datePanel;
  JPanel mainPanel;
  JButton getVal;

  /**
   * The constructor sets up the panel which can be added to the main frame.
   *
   * @param frame frame signifies JFrame which represents the application window.
   */
  public Page3GetPortfolioValDate(JFrame frame) {

    CommissionFee.MyColors color;
    CommissionFee.HeadingPanel headingPanel;
    this.frame = frame;
    color = new CommissionFee.MyColors();

    mainPanel = new JPanel();
    mainPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10,
            10, 10),  new EtchedBorder()));
    mainPanel.setBackground(color.bgcol);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    headingPanel = new CommissionFee.HeadingPanel(mainPanel,"Get Portfolio Value");

    datePanel = new CommissionFee.DatePanel(mainPanel, "date: ", 2022);

    JPanel submitPanel = new JPanel();
    submitPanel.setBackground(color.panecol);
    getVal = new JButton("Get Value");
    submitPanel.add(getVal);

    headingPanel.setPanel();
    datePanel.setPanel();
    mainPanel.add(submitPanel);
  }

  @Override
  public void setPanel() {
    frame.add(mainPanel, BorderLayout.CENTER);
    frame.setVisible(true);
  }

  @Override
  public void removePanel() {
    frame.remove(mainPanel);
  }

  @Override
  public Map<String, String> getInputs() {
    Map<String, String> inp = new HashMap<>();
    inp.put("date_portfolioValue", datePanel.yearComboBox.getSelectedItem() + "-"
            + datePanel.monthComboBox.getSelectedItem()
            + "-" + datePanel.dayComboBox.getSelectedItem());
    return inp;
  }

  @Override
  public void addFeatures(ControllerGui features) {
    getVal.addActionListener(evt -> features.goGui("Portfolio Value Date"));
  }

  @Override
  public void resetInputs() {
    datePanel.reset();
  }
}
