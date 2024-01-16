package view.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BoxLayout;

import controller.gui.ControllerGui;

/**
 * This class represents the page that gives user different options to work with on the portfolio
 * like get portfolio value, performance analysis, get cost basis, etc.
 */
class Page3ExistingPortfolio extends JFrame implements Page {

  private JFrame frame;
  private JPanel menuPanel;
  private JButton getValueBtn;
  private JButton makeTxBtn;
  private JButton viewCompBtn;
  private JButton performanceBtn;
  private JButton costBasisBtn;
  private JButton fixedAmt;
  private JButton back;
  private JButton applyDCA;

  /**
   * The constructor sets up the panel which can be added to the main frame.
   *
   * @param frame frame signifies JFrame which represents the application window.
   */
  public Page3ExistingPortfolio(JFrame frame) {
    this.frame = frame;
    CommissionFee.MyColors colors = new CommissionFee.MyColors();
    frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
    menuPanel = new JPanel();

    menuPanel.setBackground(colors.bgcol);

    //    Image prev = new ImageIcon("src/previous.png").getImage();
    //    Icon backIcon = new ImageIcon(prev.getScaledInstance(20, 20, java.awt.Image
    //            .SCALE_SMOOTH), "Back");
    back = new JButton("< Back");
    getValueBtn = new JButton("Portfolio Value");
    makeTxBtn = new JButton("Make Transaction");
    viewCompBtn = new JButton("View Composition");
    costBasisBtn = new JButton("Get Cost Basis");
    performanceBtn = new JButton("View Performance");
    fixedAmt = new JButton("Invest Fixed Amount");
    applyDCA = new JButton("Apply Dollar Cost Averaging Strategy");


    menuPanel.setLayout(new FlowLayout());
    menuPanel.setPreferredSize(new Dimension(600, 70));
    menuPanel.add(back);
    menuPanel.add(getValueBtn);
    menuPanel.add(makeTxBtn);
    menuPanel.add(viewCompBtn);
    menuPanel.add(performanceBtn);
    menuPanel.add(costBasisBtn);
    menuPanel.add(fixedAmt);
    menuPanel.add(applyDCA);

    getValueBtn.setActionCommand("Portfolio Value");
  }

  @Override
  public void setPanel() {
    frame.add(menuPanel, BorderLayout.PAGE_START);
    frame.setVisible(true);
  }

  @Override
  public void removePanel() {
    frame.remove(menuPanel);
  }

  @Override
  public Map<String, String> getInputs() {
    return null;
  }

  @Override
  public void addFeatures(ControllerGui features) {
    back.addActionListener(evt -> features.goGui("Back to Portfolio Options"));
    getValueBtn.addActionListener(evt -> features.goGui("Portfolio Value"));
    makeTxBtn.addActionListener(evt -> features.goGui("Make Transaction"));
    viewCompBtn.addActionListener(evt -> features.goGui("View Portfolio"));
    costBasisBtn.addActionListener(evt -> features.goGui("Cost Basis Date"));
    performanceBtn.addActionListener(evt -> features.goGui("Performance Analysis"));
    fixedAmt.addActionListener(evt -> features.goGui("IFA_show"));
    applyDCA.addActionListener(evt -> features.goGui("DCA_show"));
  }

  @Override
  public void resetInputs() {
    // nothing to reset
  }
}
