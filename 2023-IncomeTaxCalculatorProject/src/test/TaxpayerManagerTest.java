package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.Taxpayer;
import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.ReceiptAlreadyExistsException;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class TaxpayerManagerTest {

  @Test
  public void createTaxpayerTest() throws WrongTaxpayerStatusException {

    TaxpayerManager manager = new TaxpayerManager();
    manager.createTaxpayer("Christos Parzigkas", 133773310, "Single", 25000);
    Taxpayer taxpayer = manager.getTaxpayer(133773310);

    assertEquals("Christos Parzigkas", taxpayer.getFullname());
    assertEquals(133773310, taxpayer.getTaxRegistrationNumber());
    assertEquals("Single", manager.getTaxpayerStatus(133773310));
    assertEquals(25000, taxpayer.getIncome(), 0);
  }

  @Test
  public void createReceiptTest()
      throws WrongReceiptKindException, WrongReceiptDateException, WrongTaxpayerStatusException {

    TaxpayerManager manager = new TaxpayerManager();
    manager.createTaxpayer("Christos Parzigkas", 133773310, "Single", 25000);
    manager.createReceipt(123, "28/11/2022", 1340, "Other", "Italos", "Greece", "Ioannina",
        "Dodonis", 4, 133773310);
    Taxpayer taxpayer = manager.getTaxpayer(133773310);
    Receipt receipt = taxpayer.getReceiptHashMap().get(123);

    assertEquals(123, receipt.getId());
    assertEquals("28/11/2022", receipt.getIssueDate());
    assertEquals(1340, receipt.getAmount(), 0);
    assertEquals("Other", receipt.getKind());
    assertEquals("Italos", receipt.getCompany().getName());
    assertEquals("Greece", receipt.getCompany().getCountry());
    assertEquals("Ioannina", receipt.getCompany().getCity());
    assertEquals("Dodonis", receipt.getCompany().getStreet());
    assertEquals(4, receipt.getCompany().getNumber());
  }

  @Test
  public void loadTaxpayerTest()
      throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException,
      WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException {

    TaxpayerManager manager = new TaxpayerManager();
    String filename = "123456789_INFO.txt";
    manager.loadTaxpayer(filename);
    Taxpayer taxpayer = manager.getTaxpayer(123456789);

    assertEquals("Apostolos Zarras", taxpayer.getFullname());
    assertEquals(123456789, taxpayer.getTaxRegistrationNumber());
    assertEquals("Married Filing Jointly", manager.getTaxpayerStatus(123456789));
    assertEquals(22570.0, taxpayer.getIncome(), 0);
  }

  @Test
  public void removeTaxpayerTest() throws WrongTaxpayerStatusException {

    TaxpayerManager manager = new TaxpayerManager();
    manager.createTaxpayer("Alexandros Kapenis", 987654321, "Married Filing Jointly", 60000);
    manager.removeTaxpayer(987654321);

    assertFalse(manager.containsTaxpayer(987654321));
  }

  @Test
  public void addReceiptTest() throws WrongTaxpayerStatusException, IOException,
      WrongReceiptKindException, WrongReceiptDateException, ReceiptAlreadyExistsException {

    TaxpayerManager manager = new TaxpayerManager();
    manager.createTaxpayer("Christos Parzigkas", 133773310, "Single", 25000);
    manager.addReceipt(123, "28/11/2022", 1340, "Other", "Italos", "Greece", "Ioannina", "Dodonis",
        4, 133773310);
    Taxpayer taxpayer = manager.getTaxpayer(133773310);

    assertEquals(1, taxpayer.getTotalReceiptsGathered());
  }

  @Test
  public void removeReceiptTest() throws IOException, WrongReceiptKindException,
      WrongTaxpayerStatusException, WrongReceiptDateException, ReceiptAlreadyExistsException {

    TaxpayerManager manager = new TaxpayerManager();
    manager.createTaxpayer("Alexandros Kapenis", 987654321, "Married Filing Jointly", 60000);
    manager.addReceipt(777, "27/11/2022", 15, "Other", "Sanammena Karvouna", "Greece", "Ioannina",
        "Mitropolews", 9, 987654321);
    Taxpayer taxpayer = manager.getTaxpayer(987654321);
    manager.removeReceipt(777);

    assertEquals(0, taxpayer.getTotalReceiptsGathered());
  }

  @Test
  public void saveLogFileTest()
      throws WrongTaxpayerStatusException, IOException, WrongReceiptKindException,
      WrongReceiptDateException, ReceiptAlreadyExistsException, WrongFileFormatException {

    TaxpayerManager manager = new TaxpayerManager();
    manager.createTaxpayer("Christos Parzigkas", 133773310, "Single", 25000);
    manager.addReceipt(444, "26/11/2022", 250, "Other", "Kotsovolos", "Greece", "Ioannina",
        "Mitropolews", 88, 133773310);
    manager.saveLogFile(133773310, "txt");
    File file = new File(133773310 + "_LOG.txt");

    assertTrue(file.exists());
  }

}
