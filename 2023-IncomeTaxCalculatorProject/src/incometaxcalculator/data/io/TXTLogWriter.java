package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

import incometaxcalculator.data.management.TaxpayerManager;

public class TXTLogWriter extends LogWriter {
  
  TaxpayerManager manager = new TaxpayerManager();

  private static final short ENTERTAINMENT = 0;
  private static final short BASIC = 1;
  private static final short TRAVEL = 2;
  private static final short HEALTH = 3;
  private static final short OTHER = 4;
  
  protected PrintWriter chooseFiletype(int taxRegistrationNumber) throws IOException{
    return new PrintWriter(new java.io.FileWriter(taxRegistrationNumber + "_LOG.txt"));
  }
  
  protected void writeLogFile(PrintWriter outputStream, int taxRegistrationNumber) {
    outputStream.println("Name: " + manager.getTaxpayerName(taxRegistrationNumber));
    outputStream.println("AFM: " + taxRegistrationNumber);
    outputStream.println("Income: " + manager.getTaxpayerIncome(taxRegistrationNumber));
    outputStream.println("Basic Tax: " + manager.getTaxpayerBasicTax(taxRegistrationNumber));
    if (manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) > 0) {
      outputStream.println("Tax Increase: " + manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber));
    } else {
      outputStream.println("Tax Decrease: " + manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber));
    }
    outputStream.println("Total Tax: " + manager.getTaxpayerTotalTax(taxRegistrationNumber));
    outputStream.println("TotalReceiptsGathered: " + manager.getTaxpayerTotalReceiptsGathered(taxRegistrationNumber));
    outputStream.println("Entertainment: " + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, ENTERTAINMENT));
    outputStream.println("Basic: " + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, BASIC));
    outputStream.println("Travel: " + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, TRAVEL));
    outputStream.println("Health: " + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, HEALTH));
    outputStream.println("Other: " + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, OTHER));
    outputStream.close();
  }

}
