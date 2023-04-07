package incometaxcalculator.data.io;

import java.io.IOException;

import incometaxcalculator.exceptions.WrongFileFormatException;

public class XMLFileReader extends FileReader {
  
  protected int findReceiptId(String [] values) throws NumberFormatException, IOException {
    int receiptId = Integer.parseInt(values[1].trim());
    return receiptId;
  }
  
  protected String findFieldValue(String fieldsLine) throws WrongFileFormatException {
    String valueWithTail[] = fieldsLine.split(" ", 2);
    String valueReversed[] = new StringBuilder(valueWithTail[1]).reverse().toString().trim()
        .split(" ", 2);
    return new StringBuilder(valueReversed[1]).reverse().toString();
  }
}
