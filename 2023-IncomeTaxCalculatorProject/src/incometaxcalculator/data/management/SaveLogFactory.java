package incometaxcalculator.data.management;

import incometaxcalculator.data.io.FileWriter;
import incometaxcalculator.data.io.TXTLogWriter;
import incometaxcalculator.data.io.XMLLogWriter;
import incometaxcalculator.exceptions.WrongFileFormatException;

public class SaveLogFactory {
  
  public FileWriter getFileWriter(String fileType) throws WrongFileFormatException {
    
    if (fileType.equals("txt")) {
      return new TXTLogWriter();
    } else if (fileType.equals("xml")) {
      return new XMLLogWriter();
    } else {
      throw new WrongFileFormatException();
    }
  }
}
