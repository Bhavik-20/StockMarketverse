package view.gui;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;

import controller.gui.ControllerGui;

/**
 * This page represents the panel which gives users the options to work with or create new
 * portfolios.
 */
class Page2PortfolioOption extends JFrame implements Page {

  private JButton createPortfolio;
  private JButton uploadPortfolio;
  private JButton existingPortfolio;
  private JButton createPortfolioDCA;
  private JButton back;

  private JPanel buttonPanel1;
  private JFrame frame;

  CommissionFee.MyColors colors;

  /**
   * The constructor sets up the panel which can be added to the main frame.
   *
   * @param frame frame signifies JFrame which represents the application window.
   */
  public Page2PortfolioOption(JFrame frame) {
    this.frame = frame;

    buttonPanel1 = new JPanel();
    buttonPanel1.setLayout(new FlowLayout());
    colors = new CommissionFee.MyColors();
    buttonPanel1.setBackground(colors.bgcol);
    buttonPanel1.setPreferredSize(new Dimension(700, 100));

    //    Image prev = new ImageIcon("src/previous.png").getImage();
    //    Icon backIcon = new ImageIcon(prev.getScaledInstance(20,20,java.awt.Image
    //            .SCALE_SMOOTH),"Back");
    back = new JButton("< Back");
    buttonPanel1.add(back);

    createPortfolio = new JButton("Create Empty Portfolio");
    buttonPanel1.add(createPortfolio);

    uploadPortfolio = new JButton("Upload a Portfolio");
    buttonPanel1.add(uploadPortfolio);

    existingPortfolio = new JButton("Work with Existing Portfolio");
    buttonPanel1.add(existingPortfolio);

    createPortfolioDCA = new JButton("Create Portfolio with Dollar Cost Averaging Strategy");
    buttonPanel1.add(createPortfolioDCA);

  }

  @Override
  public void setPanel() {
    frame.add(buttonPanel1, BorderLayout.PAGE_START);
    frame.setVisible(true);
  }

  @Override
  public void removePanel() {
    frame.remove(buttonPanel1);
  }

  @Override
  public Map<String, String> getInputs() {
    Map<String, String> inp = new HashMap<>();
    return null;
  }

  @Override
  public void addFeatures(ControllerGui features) {
    back.addActionListener(evt -> features.goGui("Back To Welcome Pg"));
    createPortfolio.addActionListener(evt -> features.goGui("Portfolio Name"));
    uploadPortfolio.addActionListener(evt -> features.goGui("Upload Portfolio"));
    existingPortfolio.addActionListener(evt -> features.goGui("Existing Portfolio Name"));
    createPortfolioDCA.addActionListener(evt -> features.goGui("Create Portfolio DCA"));
  }

  @Override
  public void resetInputs() {
    // nothing to reset
  }
}
