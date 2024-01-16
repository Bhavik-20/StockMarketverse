package view.gui;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import controller.gui.ControllerGui;

/**
 * This class represents the page which asks the user the inputs to calculate the cost basis of the
 * portfolio.
 */

class Page3GetCostBasisValDate implements Page {
  private JFrame frame;
  private JPanel mainPanel;
  private JButton getVal;

  CommissionFee.MyColors colors;
  CommissionFee.DatePanel datePanel;

  CommissionFee.HeadingPanel headingPanel;

  /**
   * The constructor sets up the panel which can be added to the main frame.
   *
   * @param frame frame signifies JFrame which represents the application window.
   */

  public Page3GetCostBasisValDate(JFrame frame) {
    this.frame = frame;
    colors = new CommissionFee.MyColors();

    mainPanel = new JPanel();
    mainPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10,
            10, 10), new EtchedBorder()));
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setBackground(colors.bgcol);

    headingPanel = new CommissionFee.HeadingPanel(mainPanel, "Get Cost Basis of Portfolio");

    datePanel = new CommissionFee.DatePanel(mainPanel, "date: ", 2022);

    JPanel submitPanel = new JPanel();
    submitPanel.setBackground(colors.panecol);
    getVal = new JButton("Get Cost Basis");
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
    inp.put("date_costBasis", datePanel.yearComboBox.getSelectedItem() + "-"
            + datePanel.monthComboBox.getSelectedItem()
            + "-" + datePanel.dayComboBox.getSelectedItem());
    return inp;
  }

  @Override
  public void addFeatures(ControllerGui features) {
    getVal.addActionListener(evt -> features.goGui("Cost Basis"));
  }

  @Override
  public void resetInputs() {
    datePanel.reset();
  }
}
