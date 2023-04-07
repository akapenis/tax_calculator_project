package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

public abstract class LogWriter implements  FileWriter{
  
  protected abstract PrintWriter chooseFiletype(int taxRegistrationNumber) throws IOException;
  protected abstract void writeLogFile(PrintWriter outputStream, int taxRegistrationNumber);
  
  public void generateFile(int taxRegistrationNumber) throws IOException {
    PrintWriter outputStream = chooseFiletype(taxRegistrationNumber);
    writeLogFile(outputStream, taxRegistrationNumber);
  }

}
