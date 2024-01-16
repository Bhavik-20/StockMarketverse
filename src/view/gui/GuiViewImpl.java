package view.gui;

import java.awt.BorderLayout;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.JTableHeader;

import controller.gui.ControllerGui;

/**
 * This class implements the GuiView interface and is used to design different pages of the
 * Stock-Marketverse application by extending JFrame.
 */

public class GuiViewImpl extends JFrame implements GuiView {
  Page1Welcome welcomePage;
  Page2PortfolioOption portfolioOption;
  Page2PortfolioName portfolioName;
  Page3ExistingPortfolio existingPortfolio;
  Page2ExistingPortfolioName existingPortfolioName;
  Page3GetPortfolioValDate getPortfolioValDate;
  Page3MakeTx makeTx;
  Page3GetCostBasisValDate costBasisDate;
  Page3ViewPortfolio viewPortfolio;
  Page3GetDatesPerformanceAnalysis pfmAnalysis;
  Page3InvestFixedAmt investFixedAmt;
  Page3CreatePortfolioDCA createPortfolioDCA;

  CommissionFee.PrintBarChart printBarChart;

  Page3GetDCAInputs getDCAInputs;
  File fileToUpload;
  String uploadPath;

  CommissionFee.MyColors color;

  /**
   * The constructor initialises all the page classes and sets the window of the application.
   *
   * @param caption signifies the title of the JFrame window.
   */
  public GuiViewImpl(String caption) {
    super(caption);
    welcomePage = new Page1Welcome(this);
    portfolioName = new Page2PortfolioName(this);
    portfolioOption = new Page2PortfolioOption(this);
    existingPortfolio = new Page3ExistingPortfolio(this);
    existingPortfolioName = new Page2ExistingPortfolioName(this);
    getPortfolioValDate = new Page3GetPortfolioValDate(this);
    makeTx = new Page3MakeTx(this);
    costBasisDate = new Page3GetCostBasisValDate(this);
    viewPortfolio = new Page3ViewPortfolio(this);
    pfmAnalysis = new Page3GetDatesPerformanceAnalysis(this);
    investFixedAmt = new Page3InvestFixedAmt(this);
    createPortfolioDCA = new Page3CreatePortfolioDCA(this);
    getDCAInputs = new Page3GetDCAInputs(this);
    color = new CommissionFee.MyColors();

    setSize(700, 600);
    setLocation(325, 100);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);

    getContentPane().setBackground(color.bgcol);
    getContentPane().setLayout(new BorderLayout());

    welcomePage.setPanel();
    getContentPane().revalidate();
    setVisible(true);
    fileToUpload = null;
  }

  @Override
  public void printBarChart(String startDate, String endDate, String portfolioName,
                            List<String> xaxis, List<Integer> yaxis, double scale) {
    printBarChart = new CommissionFee.PrintBarChart(this, startDate, endDate, portfolioName, xaxis,
            yaxis, scale);
    printBarChart.setPanel();
  }

  @Override
  public void setAddPanel(String ch) {
    switch (ch) {
      case "Page1":
        welcomePage.setPanel();
        break;
      case "Page2":
        portfolioOption.setPanel();
        break;
      case "Page2b":
        portfolioName.setPanel();
        break;
      case "Page2b_existing":
        existingPortfolioName.setPanel();
        break;
      case "Dialog_path":
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "XML File", "xml");
        fileChooser.setFileFilter(filter);
        int retVal = fileChooser.showOpenDialog(null);
        if (retVal == JFileChooser.APPROVE_OPTION) {
          uploadPath = fileChooser.getSelectedFile().getPath().replace("\\", "/");
        } else {
          uploadPath = "null";
        }
        break;
      case "Page3":
        existingPortfolio.setPanel();
        break;
      case "Page3_GetPortfolioValue":
        getPortfolioValDate.setPanel();
        break;
      case "Page3_MakeTx":
        makeTx.setPanel();
        break;
      case "Page3_GetCostBasisValue":
        costBasisDate.setPanel();
        break;
      case "Page3_ViewPortfolio":
        viewPortfolio.setPanel();
        break;
      case "Page3_GetDatesPerformanceAnalysis":
        pfmAnalysis.setPanel();
        break;
      case "Page3_CreatePortfolioDCA":
        createPortfolioDCA.setPanel();
        break;
      case "Page3_InvestFixedAmt":
        investFixedAmt.setPanel();
        break;
      case "Page3_GetDCAInputs":
        getDCAInputs.setPanel();
        break;
      default:
        //invalid
        break;
    }
    this.revalidate();
    this.repaint();
  }

  @Override
  public void setRemovePanel(String ch) {
    switch (ch) {
      case "Page1":
        welcomePage.removePanel();
        break;
      case "Page2":
        portfolioOption.removePanel();
        portfolioName.removePanel();
        break;
      case "Page2b_existing":
        existingPortfolioName.removePanel();
        break;
      case "Page3":
        existingPortfolio.removePanel();
        break;
      case "Page3_GetPortfolioValue":
        getPortfolioValDate.removePanel();
        break;
      case "Page3_MakeTx":
        makeTx.removePanel();
        break;
      case "Page3_ViewPortfolio":
        viewPortfolio.removePanel();
        break;
      case "Page3_GetCostBasisValue":
        costBasisDate.removePanel();
        break;
      case "Page3_GetDatesPerformanceAnalysis":
        pfmAnalysis.removePanel();
        break;
      case "Page3_CreatePortfolioDCA":
        createPortfolioDCA.removePanel();
        break;
      case "Page3_InvestFixedAmt":
        investFixedAmt.removePanel();
        break;
      case "Page3_GetDCAInputs":
        getDCAInputs.removePanel();
        break;
      case "remove_all":
        this.getContentPane().removeAll();
        break;
      default:
        // invalid
        break;
    }
    this.revalidate();
    this.repaint();
  }

  @Override
  public void showError(String toString) {
    JOptionPane.showMessageDialog(null, toString, "An Error Occurred!",
            JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void showMessage(String msg) {
    JOptionPane.showMessageDialog(null, msg, "Message",
            JOptionPane.PLAIN_MESSAGE);
  }

  @Override
  public Map<String, String> getUsernameInput() {
    return welcomePage.getInputs();
  }

  @Override
  public void showComposition(String composition) {
    this.setBackground(color.bgcol);
    if (composition.trim().isEmpty()) {
      this.showError("No stocks available on the specified day");
    } else {
      String[] list = composition.trim().split("\n");

      String[][] data = new String[list.length][2];
      String[] colNames = {"Ticker", "Quantity"};

      for (int i = 0; i < data.length; i++) {
        String[] temp = list[i].trim().split("\\s+");
        data[i] = temp;
      }
      JTable newTable = new JTable(data, colNames);
      JTableHeader header = newTable.getTableHeader();
      header.setBackground(color.bgcol);

      CommissionFee.PrintPortfolioScrollPanel scrollPane =
              new CommissionFee.PrintPortfolioScrollPanel(this, newTable);
      scrollPane.setPanel();
    }
  }

  @Override
  public void reset(String ch) {
    switch (ch) {
      case "Page1":
        welcomePage.resetInputs();
        break;
      case "Page2":
        portfolioOption.resetInputs();
        portfolioName.resetInputs();
        break;
      case "Page2b_existing":
        existingPortfolioName.resetInputs();
        break;
      case "Page3":
        existingPortfolio.resetInputs();
        break;
      case "Page3_GetPortfolioValue":
        getPortfolioValDate.resetInputs();
        break;
      case "Page3_GetCostBasisValue":
        costBasisDate.resetInputs();
        break;
      case "Page3_MakeTx":
        makeTx.resetInputs();
        break;
      case "Page3_ViewPortfolio":
        viewPortfolio.resetInputs();
        break;
      case "Page3_GetDatesPerformanceAnalysis":
        pfmAnalysis.resetInputs();
        break;
      case "Page3_CreatePortfolioDCA":
        createPortfolioDCA.resetInputs();
        break;
      case "Page3_InvestFixedAmt":
        investFixedAmt.resetInputs();
        break;
      case "Page3_GetDCAInputs":
        getDCAInputs.resetInputs();
        break;
      default:
        // invalid
        break;
    }
  }

  @Override
  public String getUploadPath() {
    return uploadPath;
  }

  @Override
  public Map<String, String> getPortfolioNameCreate() {
    return portfolioName.getInputs();
  }

  @Override
  public Map<String, String> getPortfolioNameExisting() {
    return existingPortfolioName.getInputs();
  }

  @Override
  public Map<String, String> getDatePortfolioValue() {
    return getPortfolioValDate.getInputs();
  }

  @Override
  public Map<String, String> getDateViewPortfolio() {
    return viewPortfolio.getInputs();
  }

  @Override
  public Map<String, String> getTransactionInputs() {
    return makeTx.getInputs();
  }

  @Override
  public Map<String, String> getCostBasisInputs() {
    return costBasisDate.getInputs();
  }

  @Override
  public Map<String, String> getInvestFixedAmtInputs() {
    return investFixedAmt.getInputs();
  }

  @Override
  public Map<String, String> getDCAInputs() {
    return getDCAInputs.getInputs();
  }

  @Override
  public Map<String, String> getPerformanceInputs() {
    return pfmAnalysis.getInputs();
  }

  @Override
  public Map<String, String> getCreateDCA() {
    return createPortfolioDCA.getInputs();
  }

  @Override
  public void addFeatures(ControllerGui features) {
    welcomePage.addFeatures(features);
    portfolioOption.addFeatures(features);
    portfolioName.addFeatures(features);
    existingPortfolio.addFeatures(features);
    getPortfolioValDate.addFeatures(features);
    makeTx.addFeatures(features);
    existingPortfolioName.addFeatures(features);
    viewPortfolio.addFeatures(features);
    costBasisDate.addFeatures(features);
    pfmAnalysis.addFeatures(features);
    createPortfolioDCA.addFeatures(features);
    investFixedAmt.addFeatures(features);
    getDCAInputs.addFeatures(features);
  }

}
