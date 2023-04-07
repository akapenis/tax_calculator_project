package incometaxcalculator.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class GraphicalInterface extends JFrame {

  private JPanel contentPane;
  private TaxpayerManager taxpayerManager = new TaxpayerManager();
  //private String taxpayersTRN = new String();
  private JTextField txtTaxRegistrationNumber;

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          GraphicalInterface frame = 
              new GraphicalInterface();
          
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public GraphicalInterface() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(50, 50, 370, 455);
    contentPane = new JPanel();
    contentPane.setBackground(new Color(150, 150, 150));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    try {
      UIManager.setLookAndFeel(
          UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException 
        | InstantiationException 
        | IllegalAccessException
        | UnsupportedLookAndFeelException e2) {
      e2.printStackTrace();
    }

    JTextPane textPane = new JTextPane();
    textPane.setEditable(false);
    textPane.setBackground(new Color(100, 100, 100));
    textPane.setBounds(0, 21, 433, 441);

    JPanel fileLoaderPanel = new JPanel(new BorderLayout());
    JPanel boxPanel = new JPanel(new BorderLayout());
    JPanel taxRegistrationNumberPanel = 
        new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
    JLabel TRN = new JLabel("Give the tax registration number:");
    JTextField taxRegistrationNumberField = new JTextField(20);
    taxRegistrationNumberPanel.add(TRN);
    taxRegistrationNumberPanel.add(taxRegistrationNumberField);
    JPanel loadPanel = new JPanel(new GridLayout(1, 2));
    loadPanel.add(taxRegistrationNumberPanel);
    fileLoaderPanel.add(boxPanel, BorderLayout.NORTH);
    fileLoaderPanel.add(loadPanel, BorderLayout.CENTER);
    JCheckBox txtBox = new JCheckBox("Txt file");
    JCheckBox xmlBox = new JCheckBox("Xml file");

    txtBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        xmlBox.setSelected(false);
      }
    });

    xmlBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        txtBox.setSelected(false);
      }
    });
    txtBox.doClick();
    boxPanel.add(txtBox, BorderLayout.WEST);
    boxPanel.add(xmlBox, BorderLayout.EAST);

    DefaultListModel<String> taxRegisterNumberModel =
        new DefaultListModel<String>();

    JList<String> taxRegisterNumberList = 
        new JList<String>(taxRegisterNumberModel);
    taxRegisterNumberList.setBackground(new Color(209, 189, 109));
    taxRegisterNumberList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    taxRegisterNumberList.setSelectedIndex(0);
    taxRegisterNumberList.setVisibleRowCount(3);
    
    JScrollPane taxRegisterNumberListScrollPane = 
        new JScrollPane(taxRegisterNumberList);
    taxRegisterNumberListScrollPane.setSize(340, 360);
    taxRegisterNumberListScrollPane.setLocation(10, 50);
    contentPane.add(taxRegisterNumberListScrollPane);

    JButton btnLoadTaxpayer = new JButton("Load Taxpayer");
    btnLoadTaxpayer.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int answer = JOptionPane.showConfirmDialog(null, 
            fileLoaderPanel, " ",JOptionPane.OK_CANCEL_OPTION);
        if (answer == 0) {
          String taxRegistrationNumber = 
              taxRegistrationNumberField.getText();
          while (taxRegistrationNumber.length() != 9 && answer == 0) {
            JOptionPane.showMessageDialog(null,
                "The tax  registration number "
                + "must have 9 digit.\n" + " Try again.");
            answer = JOptionPane.showConfirmDialog(null,
                fileLoaderPanel, "",
                JOptionPane.OK_CANCEL_OPTION);
            taxRegistrationNumber = 
                taxRegistrationNumberField.getText();
          }
          if (answer == 0) {
            int trn = 0;
            String taxRegistrationNumberFile;
            try {
              trn = Integer.parseInt(taxRegistrationNumber);
              if (txtBox.isSelected()) {
                taxRegistrationNumberFile =
                    taxRegistrationNumber + "_INFO.txt";
              } else {
                taxRegistrationNumberFile = 
                    taxRegistrationNumber + "_INFO.xml";
              }
              if (taxpayerManager.containsTaxpayer(trn)) {
                JOptionPane.showMessageDialog(null,
                    "This taxpayer is already loaded.");
              } else {
                taxpayerManager.loadTaxpayer(taxRegistrationNumberFile);
                taxRegisterNumberModel.addElement(taxRegistrationNumber);
              }
              // textPane.setText(taxpayersTRN);
            } catch (NumberFormatException e1) {
              JOptionPane.showMessageDialog(null,
                  "The tax registration number"
                  + " must have only digits.");
            } catch (IOException e1) {
              JOptionPane.showMessageDialog(null,
                  "The file doesn't exists.");
            } catch (WrongFileFormatException e1) {
              JOptionPane.showMessageDialog(null,
                  "Please check your file format and try again.");
            } catch (WrongFileEndingException e1) {
              JOptionPane.showMessageDialog(null,
                  "Please check your file ending and try again.");
            } catch (WrongTaxpayerStatusException e1) {
              JOptionPane.showMessageDialog(null,
                  "Please check taxpayer's status and try again.");
            } catch (WrongReceiptKindException e1) {
              JOptionPane.showMessageDialog(null,
                  "Please check receipts kind and try again.");
            } catch (WrongReceiptDateException e1) {
              JOptionPane.showMessageDialog(null,
                  "Please make sure your date is " +
              "DD/MM/YYYY and try again.");
            }
          }

        }
      }
    });
    btnLoadTaxpayer.setBounds(0, 0, 120, 23);
    contentPane.add(btnLoadTaxpayer);

    JButton btnSelectTaxpayer = new JButton("Select Taxpayer");
    btnSelectTaxpayer.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (taxpayerManager.containsTaxpayer()) {
          if (taxRegisterNumberList.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(null,
                "Select a tax registration number from the list below");
          }else {
            if (taxpayerManager.containsTaxpayer()) {
              int taxRegistrationNumber;
              taxRegistrationNumber = 
                  Integer.parseInt(taxRegisterNumberList.getModel()
                  .getElementAt(taxRegisterNumberList.getSelectedIndex()));
              TaxpayerData taxpayerData = 
                  new TaxpayerData(taxRegistrationNumber,
                taxpayerManager);
              taxpayerData.setVisible(true);
          }
        }
        } else {
          JOptionPane.showMessageDialog(null,
              "There isn't any taxpayer loaded. Please load one first.");
        }
      }
    });
    btnSelectTaxpayer.setBounds(121, 0, 120, 23);
    contentPane.add(btnSelectTaxpayer);

    JButton btnDeleteTaxpayer = new JButton("Delete Taxpayer");
    btnDeleteTaxpayer.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (taxpayerManager.containsTaxpayer()) {
          if (taxRegisterNumberList.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(null,
                "Select a tax registration number from the list below");
          }else {
            int taxRegistrationNumber;
            taxRegistrationNumber = 
                Integer.parseInt(taxRegisterNumberList.getModel()
                .getElementAt(taxRegisterNumberList.getSelectedIndex()));
            int r= JOptionPane.showConfirmDialog(null,
                "Delete Taxpayer with \n tax registration number :"
            + taxRegistrationNumber, 
            "Confirmation",
            JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
              taxpayerManager.removeTaxpayer(taxRegistrationNumber);
              taxRegisterNumberModel.removeElement(taxRegisterNumberList.getModel()
                  .getElementAt(taxRegisterNumberList.getSelectedIndex()));
            } 
          }
        } else {
          JOptionPane.showMessageDialog(null,
              "There isn't any taxpayer loaded. Please load one first.");
        }
      }
    });
    btnDeleteTaxpayer.setBounds(241, 0, 120, 23);
    contentPane.add(btnDeleteTaxpayer);

    txtTaxRegistrationNumber = new JTextField();
    txtTaxRegistrationNumber.setEditable(false);
    txtTaxRegistrationNumber.setBackground(new Color(209, 189, 109));
    txtTaxRegistrationNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
    txtTaxRegistrationNumber.setText("Tax Registration Number:");
    txtTaxRegistrationNumber.setBounds(10, 25, 340, 20);
    contentPane.add(txtTaxRegistrationNumber);
    txtTaxRegistrationNumber.setColumns(10);

  }
}