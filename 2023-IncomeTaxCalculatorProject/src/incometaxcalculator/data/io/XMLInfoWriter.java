package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;


import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

public class XMLInfoWriter extends InfoWriter {
  
  TaxpayerManager manager = new TaxpayerManager();
  
  protected PrintWriter chooseFiletype(int taxRegistrationNumber) throws IOException {
    return new PrintWriter(new java.io.FileWriter(taxRegistrationNumber + "_INFO.xml"));
  }
  
  protected void writeInfoPerson(PrintWriter outputStream, int taxRegistrationNumber) {
    outputStream.println("<Name> " + manager.getTaxpayerName(taxRegistrationNumber) + " </Name>");
    outputStream.println("<AFM> " + taxRegistrationNumber + " </AFM>");
    outputStream.println("<Status> " + manager.getTaxpayerStatus(taxRegistrationNumber) + " </Status>");
    outputStream.println("<Income> " + manager.getTaxpayerIncome(taxRegistrationNumber) + " </Income>");
    outputStream.println();
    outputStream.println("<Receipts>");
    outputStream.println();
  }
  
  protected void writeInfoReceipts(Receipt receipt, PrintWriter outputStream) {
    outputStream.println("<ReceiptID> " + getReceiptId(receipt) + " </ReceiptID>");
    outputStream.println("<Date> " + getReceiptIssueDate(receipt) + " </Date>");
    outputStream.println("<Kind> " + getReceiptKind(receipt) + " </Kind>");
    outputStream.println("<Amount> " + getReceiptAmount(receipt) + " </Amount>");
    outputStream.println("<Company> " + getCompanyName(receipt) + " </Company>");
    outputStream.println("<Country> " + getCompanyCountry(receipt) + " </Country>");
    outputStream.println("<City> " + getCompanyCity(receipt) + " </City>");
    outputStream.println("<Street> " + getCompanyStreet(receipt) + " </Street>");
    outputStream.println("<Number> " + getCompanyNumber(receipt) + " </Number>");
    outputStream.println();
  }
  
}
