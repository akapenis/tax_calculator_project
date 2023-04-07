package incometaxcalculator.data.management;

import java.util.HashMap;

import incometaxcalculator.exceptions.WrongReceiptKindException;

public class Taxpayer {

  protected final String fullname;
  protected final int taxRegistrationNumber;
  protected final float income;
  private float amountPerReceiptsKind[] = new float[5];
  private int totalReceiptsGathered = 0;
  private static final String receiptKinds[] = {"Entertainment", "Basic", "Travel", "Health", "Other"};
  private HashMap<Integer, Receipt> receiptHashMap = new HashMap<Integer, Receipt>(0);
  protected double oria[];
  
  protected Taxpayer(String fullname, int taxRegistrationNumber, float income) {
    this.fullname = fullname;
    this.taxRegistrationNumber = taxRegistrationNumber;
    this.income = income;
  }

  public double calculateBascTax() {
    double pososta[] = {0.0535, 0.0705, 0.0705, 0.0785, 0.0985};
    double flatTax = 0;
    
    for (int i=0; i < oria.length-1; i++) {
      if (income < oria[i+1]) {
        return ((income-oria[i]) * pososta[i]) + flatTax;
      }
      flatTax += ((oria[i+1] - oria[i]) * pososta[i]);
    }
    return ((income-oria[oria.length-1]) * pososta[pososta.length-1]) + flatTax;
  }
  
  public void addReceipt(Receipt receipt) throws WrongReceiptKindException {
    String receiptKind = receipt.getKind();
    for (int i = 0; i < receiptKinds.length; i++) {
      if (receiptKind.equals(receiptKinds[i])) {
        amountPerReceiptsKind[i] += receipt.getAmount();
        receiptHashMap.put(receipt.getId(), receipt);
        totalReceiptsGathered++;
        return;
      }
    }
    throw new WrongReceiptKindException();
  }
  
  public void removeReceipt(int receiptId) throws WrongReceiptKindException {
    Receipt receipt = receiptHashMap.get(receiptId);
    for (int i = 0; i < receiptKinds.length; i++) {
      if (receipt.getKind().equals(receiptKinds[i])) {
        amountPerReceiptsKind[i] -= receipt.getAmount();
        receiptHashMap.remove(receiptId);
        totalReceiptsGathered--;
        return;
      }
    }
    throw new WrongReceiptKindException();
  }

  public String getFullname() {
    return fullname;
  }

  public int getTaxRegistrationNumber() {
    return taxRegistrationNumber;
  }

  public float getIncome() {
    return income;
  }

  public HashMap<Integer, Receipt> getReceiptHashMap() {
    return receiptHashMap;
  }
  
  public double getVariationTaxOnReceipts() {
    float totalAmountOfReceipts = getTotalAmountOfReceipts();
    double refundPercent[] = {0.08, 0.04, -0.15, -0.3};
    for (int i = 0; i<3; i++) {
      if (totalAmountOfReceipts < (i+1) * 0.2 * income) {
        return calculateBascTax() * refundPercent[i];
      }
    }
    return calculateBascTax() * refundPercent[refundPercent.length-1];
  }

  private float getTotalAmountOfReceipts() {
    int sum = 0;
    for (int i = 0; i < 5; i++) {
      sum += amountPerReceiptsKind[i];
    }
    return sum;
  }

  public int getTotalReceiptsGathered() {
    return totalReceiptsGathered;
  }

  public float getAmountOfReceiptKind(short kind) {
    return amountPerReceiptsKind[kind];
  }

  public double getTotalTax() {
    return calculateBascTax() + getVariationTaxOnReceipts();
  }

  public double getBasicTax() {
    return calculateBascTax();
  }

}