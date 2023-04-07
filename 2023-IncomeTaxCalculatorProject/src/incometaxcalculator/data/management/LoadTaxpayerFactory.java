package incometaxcalculator.data.management;

import incometaxcalculator.data.io.FileReader;
import incometaxcalculator.data.io.TXTFileReader;
import incometaxcalculator.data.io.XMLFileReader;
import incometaxcalculator.exceptions.WrongFileEndingException;

public class LoadTaxpayerFactory {
  public static FileReader createFileReader(String fileName) throws WrongFileEndingException {
    
    String ending[] = fileName.split("\\.");
    
    if (ending[1].equals("txt")) {
      return new TXTFileReader();
    } else if (ending[1].equals("xml")) {
      return new XMLFileReader();
    } else {
      throw new WrongFileEndingException();
    }
  }
}
