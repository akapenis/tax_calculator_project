package incometaxcalculator.gui;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RefineryUtilities;

class ChartDisplay {

  static JFrame createPieAndBarChart( String name ,double entertainment, double basic, double travel, double health,double other,
      double basicTax, double taxVariation, double totalTax) {

    JFrame pieAndBarChartFrame = new JFrame("Analysis of receipts and tax for "+name);
    pieAndBarChartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pieAndBarChartFrame.setLayout(new GridLayout(1, 2));

    // Create the pie chart and bar chart panels
    JPanel pieChartPanel = createPieChartPanel(entertainment, basic, travel, health, other);
    JPanel barChartPanel = createBarChartPanel(basicTax, taxVariation, totalTax);

    // Add the pie chart and bar chart panels to the frame
    pieAndBarChartFrame.add(pieChartPanel);
    pieAndBarChartFrame.add(barChartPanel);

    pieAndBarChartFrame.pack();
    RefineryUtilities.centerFrameOnScreen(pieAndBarChartFrame);
    pieAndBarChartFrame.setVisible(true);
    return pieAndBarChartFrame;
  }
  
  private static JPanel createPieChartPanel(double entertainment, double basic, double travel,
      double health, double other) {
    JFreeChart pieChart = ChartFactory.createPieChart(
        "Percentage of the total amount of each kind of receipt.",
        createDefaultPieDataset(entertainment, basic, travel, health, other), true, true, false);
    JPanel pieChartPanel = new ChartPanel(pieChart);
    pieChartPanel.setPreferredSize(new java.awt.Dimension(450, 550));
    return pieChartPanel;
  }

  private static JPanel createBarChartPanel(double basicTax, double taxVariation,
      double totalTax) {
    JFreeChart barChart = ChartFactory.createBarChart("Tax analysis", "", "Tax analysis in $",
        createDefaultCategoryDataset(basicTax, taxVariation, totalTax), PlotOrientation.VERTICAL,
        true, true, false);
    JPanel barChartPanel = new ChartPanel(barChart);
    barChartPanel.setPreferredSize(new java.awt.Dimension(450, 550));
    return barChartPanel;
  }

  private static DefaultPieDataset createDefaultPieDataset(double entertainment, double basic,
      double travel, double health, double other) {

    DefaultPieDataset pieChartDataset = new DefaultPieDataset();
    pieChartDataset.setValue("Entertainment", entertainment);
    pieChartDataset.setValue("Basic", basic);
    pieChartDataset.setValue("Travel", travel);
    pieChartDataset.setValue("Health", health);
    pieChartDataset.setValue("Other", other);
    return pieChartDataset;
  }

  private static DefaultCategoryDataset createDefaultCategoryDataset(double basicTax,
      double taxVariation, double totalTax) {
    DefaultCategoryDataset barChartDataset = new DefaultCategoryDataset();
    barChartDataset.addValue(basicTax, "Tax", "Basic");
    if (taxVariation > 0) {
      barChartDataset.addValue(taxVariation, "Tax", "Increase");
    } else {
      barChartDataset.addValue(-taxVariation, "Tax", "Decrease");
    }
    barChartDataset.addValue(totalTax, "Tax", "Total");
    return barChartDataset;
  }
}