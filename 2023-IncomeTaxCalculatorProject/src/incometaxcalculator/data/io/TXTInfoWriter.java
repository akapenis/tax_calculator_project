package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;


import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

public class TXTInfoWriter extends InfoWriter {
  
  TaxpayerManager manager = new TaxpayerManager();
  
  protected PrintWriter chooseFiletype(int taxRegistrationNumber) throws IOException {
    return new PrintWriter(new java.io.FileWriter(taxRegistrationNumber + "_INFO.txt"));
  }
  
  protected void writeInfoPerson(PrintWriter outputStream, int taxRegistrationNumber) {
    outputStream.println("Name: " + manager.getTaxpayerName(taxRegistrationNumber));
    outputStream.println("AFM: " + taxRegistrationNumber);
    outputStream.println("Status: " + manager.getTaxpayerStatus(taxRegistrationNumber));
    outputStream.println("Income: " + manager.getTaxpayerIncome(taxRegistrationNumber));
    outputStream.println();
    outputStream.println("Receipts:");
    outputStream.println();
  }
  
  protected void writeInfoReceipts(Receipt receipt, PrintWriter outputStream) {
    outputStream.println("Receipt ID: " + getReceiptId(receipt));
    outputStream.println("Date: " + getReceiptIssueDate(receipt));
    outputStream.println("Kind: " + getReceiptKind(receipt));
    outputStream.println("Amount: " + getReceiptAmount(receipt));
    outputStream.println("Company: " + getCompanyName(receipt));
    outputStream.println("Country: " + getCompanyCountry(receipt));
    outputStream.println("City: " + getCompanyCity(receipt));
    outputStream.println("Street: " + getCompanyStreet(receipt));
    outputStream.println("Number: " + getCompanyNumber(receipt));
    outputStream.println();
  }
  
}
