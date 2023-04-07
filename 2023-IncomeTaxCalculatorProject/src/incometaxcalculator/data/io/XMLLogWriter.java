package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

import incometaxcalculator.data.management.TaxpayerManager;

public class XMLLogWriter extends LogWriter {
  
  TaxpayerManager manager = new TaxpayerManager();

  private static final short ENTERTAINMENT = 0;
  private static final short BASIC = 1;
  private static final short TRAVEL = 2;
  private static final short HEALTH = 3;
  private static final short OTHER = 4;
  
  protected PrintWriter chooseFiletype(int taxRegistrationNumber) throws IOException{
    return new PrintWriter(new java.io.FileWriter(taxRegistrationNumber + "_LOG.xml"));
  }
  
  protected void writeLogFile(PrintWriter outputStream, int taxRegistrationNumber) {
    outputStream.println("<Name> " + manager.getTaxpayerName(taxRegistrationNumber) + " </Name>");
    outputStream.println("<AFM> " + taxRegistrationNumber + " </AFM>");
    outputStream.println("<Income> " + manager.getTaxpayerIncome(taxRegistrationNumber) + " </Income>");
    outputStream.println("<BasicTax> " + manager.getTaxpayerBasicTax(taxRegistrationNumber) + " </BasicTax>");
    if (manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) > 0) {
      outputStream.println("<TaxIncrease> "+ manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) + " </TaxIncrease>");
    } else {
      outputStream.println("<TaxDecrease> "+ manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) + " </TaxDecrease>");
    }
    outputStream.println("<TotalTax> " + manager.getTaxpayerTotalTax(taxRegistrationNumber) + " </TotalTax>");
    outputStream.println("<Receipts> " + manager.getTaxpayerTotalReceiptsGathered(taxRegistrationNumber) + " </Receipts>");
    outputStream.println("<Entertainment> " + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, ENTERTAINMENT)+ " </Entertainment>");
    outputStream.println("<Basic> " + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, BASIC) + " </Basic>");
    outputStream.println("<Travel> " + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, TRAVEL) + " </Travel>");
    outputStream.println("<Health> " + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, HEALTH) + " </Health>");
    outputStream.println("<Other> " + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, OTHER) + " </Other>");
    outputStream.close(); 
  }

}
