package view.gui;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;


import controller.gui.ControllerGui;

/**
 * This class represents the page which asks the user for inputs when the user wishes to log a
 * transaction in the portfolio.
 */
public class Page3MakeTx implements Page {
  JFrame frame;
  JPanel transactionType;
  JPanel submitPanel;

  JPanel mainPanel;
  JLabel txType;
  JButton submit;
  JButton mkTxn;
  CommissionFee.DatePanel datePanel;
  CommissionFee commissionFee;
  CommissionFee.MyColors colors;
  CommissionFee.TickerSymbol tickerSymbol;
  CommissionFee.QtyStock qtyPanel;
  JComboBox<String> combobox;
  CommissionFee.HeadingPanel headingPanel;

  /**
   * The constructor sets up the panel which can be added to the main frame.
   *
   * @param frame frame signifies JFrame which represents the application window.
   */
  public Page3MakeTx(JFrame frame) {
    this.frame = frame;
    colors = new CommissionFee.MyColors();

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setBackground(colors.bgcol);
    mainPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10,
            10, 10), new EtchedBorder()));

    headingPanel = new CommissionFee.HeadingPanel(mainPanel, "Make Transaction");

    transactionType = new JPanel();
    transactionType.setBackground(colors.panecol);
    transactionType.setLayout(new FlowLayout());
    txType = new JLabel("Select the type of transaction you wish to make:");
    transactionType.add(txType);
    String[] options = {"Buy stocks", "Sell stocks"};
    combobox = new JComboBox<String>();
    for (int i = 0; i < options.length; i++) {
      combobox.addItem(options[i]);
    }
    transactionType.add(combobox);

    datePanel = new CommissionFee.DatePanel(mainPanel, "date: ", 2022);
    tickerSymbol = new CommissionFee.TickerSymbol(mainPanel);
    qtyPanel = new CommissionFee.QtyStock(mainPanel);
    commissionFee = new CommissionFee(mainPanel);

    submitPanel = new JPanel();
    submitPanel.setBackground(colors.panecol);
    mkTxn = new JButton("Make Transaction");
    submit = new JButton("Save & Exit");
    submitPanel.add(mkTxn);
    submitPanel.add(submit);

    headingPanel.setPanel();
    mainPanel.add(transactionType);
    datePanel.setPanel();
    tickerSymbol.setPanel();
    qtyPanel.setPanel();
    commissionFee.setPanel();
    mainPanel.add(submitPanel);
  }

  @Override
  public void setPanel() {
    frame.add(mainPanel, BorderLayout.CENTER);
    frame.setVisible(true);
  }

  @Override
  public void removePanel() {
    // frame.remove(mainPanel);
  }

  @Override
  public Map<String, String> getInputs() {
    Map<String, String> inp = new HashMap<>();
    inp.put("transType", combobox.getSelectedItem().toString());
    inp.put("ticker", tickerSymbol.getTicker());
    inp.put("quantity", qtyPanel.getQty());
    inp.put("date_Transaction", datePanel.yearComboBox.getSelectedItem() + "-"
            + datePanel.monthComboBox.getSelectedItem()
            + "-" + datePanel.dayComboBox.getSelectedItem());
    inp.put("commissionFee", commissionFee.getCommission());
    return inp;
  }

  @Override
  public void addFeatures(ControllerGui features) {
    mkTxn.addActionListener(evt -> features.goGui("Buy Stock"));
    submit.addActionListener(evt -> features.goGui("Save Tx"));
  }

  @Override
  public void resetInputs() {
    commissionFee.reset();
    tickerSymbol.reset();
    qtyPanel.reset();
    datePanel.reset();
  }
}
