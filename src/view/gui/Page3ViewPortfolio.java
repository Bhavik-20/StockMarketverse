package view.gui;

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
 * This class represents the page that asks the user for inputs when they wish to view the
 * composition of the portfolio.
 */
class Page3ViewPortfolio implements Page {

  private JFrame frame;
  private JPanel mainPanel;
  private JButton getVal;
  CommissionFee.DatePanel datePanel;
  CommissionFee.MyColors colors;
  CommissionFee.HeadingPanel headingPanel;

  /**
   * The constructor sets up the panel which can be added to the main frame.
   *
   * @param frame frame signifies JFrame which represents the application window.
   */
  public Page3ViewPortfolio(JFrame frame) {
    this.frame = frame;
    colors = new CommissionFee.MyColors();

    mainPanel = new JPanel();
    mainPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10,
            10, 10), new EtchedBorder()));
    mainPanel.setBackground(colors.bgcol);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    headingPanel = new CommissionFee.HeadingPanel(mainPanel, "View Composition of Portfolio");

    datePanel = new CommissionFee.DatePanel(mainPanel, "date: ", 2022);
    JPanel submitPanel = new JPanel();
    submitPanel.setBackground(colors.panecol);
    getVal = new JButton("View Composition");
    submitPanel.add(getVal);

    headingPanel.setPanel();
    datePanel.setPanel();
    mainPanel.add(submitPanel);
  }

  @Override
  public void setPanel() {
    frame.add(mainPanel);
    frame.setVisible(true);
  }

  @Override
  public void removePanel() {
    frame.remove(mainPanel);
  }

  @Override
  public Map<String, String> getInputs() {
    Map<String, String> inp = new HashMap<>();
    inp.put("date_viewPortfolio", datePanel.yearComboBox.getSelectedItem() + "-"
            + datePanel.monthComboBox.getSelectedItem()
            + "-" + datePanel.dayComboBox.getSelectedItem());
    return inp;
  }

  @Override
  public void addFeatures(ControllerGui features) {
    getVal.addActionListener(evt -> features.goGui("View Composition"));
  }

  @Override
  public void resetInputs() {
    datePanel.reset();
  }
}
