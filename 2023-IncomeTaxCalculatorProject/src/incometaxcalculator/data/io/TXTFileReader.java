package incometaxcalculator.data.io;

import java.io.IOException;

import incometaxcalculator.exceptions.WrongFileFormatException;

public class TXTFileReader extends FileReader {
  
  protected int findReceiptId(String [] values) throws NumberFormatException, IOException { 
    int receiptId = Integer.parseInt(values[2].trim());
    return receiptId;
  }
  
  protected String findFieldValue(String fieldsLine) throws WrongFileFormatException {
    String values[] = fieldsLine.split(" ", 2);
    values[1] = values[1].trim();
    return values[1];
  }
}
