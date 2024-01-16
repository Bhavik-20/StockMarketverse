package view.gui;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;


import controller.gui.ControllerGui;

/**
 * This class represents the page which asks for inputs to visualize the performance of the
 * portfolio in a bar chart.
 */

class Page3GetDatesPerformanceAnalysis implements Page {

  private JFrame frame;
  private JPanel mainPanel;
  private JButton getVal;
  CommissionFee.MyColors colors;
  CommissionFee.DatePanel startDatePanel;
  CommissionFee.DatePanel endDatePanel;
  CommissionFee.HeadingPanel headingPanel;

  /**
   * The constructor sets up the panel which can be added to the main frame.
   *
   * @param frame frame signifies JFrame which represents the application window.
   */
  public Page3GetDatesPerformanceAnalysis(JFrame frame) {
    this.frame = frame;
    colors = new CommissionFee.MyColors();
    mainPanel = new JPanel();
    mainPanel.setBackground(colors.bgcol);
    mainPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10,
            10, 10), new EtchedBorder()));
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    headingPanel = new CommissionFee.HeadingPanel(mainPanel, "Analyze Performance of Portfolio");

    startDatePanel = new CommissionFee.DatePanel(mainPanel, "start date:", 2022);

    endDatePanel = new CommissionFee.DatePanel(mainPanel, "end date: ", 2022);

    JPanel note = new JPanel();
    note.setBackground(colors.panecol);
    note.add(new JLabel("<html><h4 style='color: #2980B9'>Note: The difference between" +
            " the start date and the end date must be more than a day and less than " +
            "30 years. </h4></html>"));

    JPanel submitPanel = new JPanel();
    submitPanel.setBackground(colors.panecol);
    getVal = new JButton("Visualize Performance");
    submitPanel.add(getVal);

    headingPanel.setPanel();
    startDatePanel.setPanel();
    endDatePanel.setPanel();
    mainPanel.add(note);
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
    inp.put("startDate_pfm", startDatePanel.yearComboBox.getSelectedItem() + "-"
            + startDatePanel.monthComboBox.getSelectedItem()
            + "-" + startDatePanel.dayComboBox.getSelectedItem());
    inp.put("endDate_pfm", endDatePanel.yearComboBox.getSelectedItem() + "-"
            + endDatePanel.monthComboBox.getSelectedItem()
            + "-" + endDatePanel.dayComboBox.getSelectedItem());
    return inp;
  }

  @Override
  public void addFeatures(ControllerGui features) {
    getVal.addActionListener(evt -> features.goGui("Visualize Performance"));
  }

  @Override
  public void resetInputs() {
    startDatePanel.reset();
    endDatePanel.reset();
  }
}
