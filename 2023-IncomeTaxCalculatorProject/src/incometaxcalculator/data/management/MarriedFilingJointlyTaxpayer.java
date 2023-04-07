package incometaxcalculator.data.management;

public class MarriedFilingJointlyTaxpayer extends Taxpayer {
  
  private double oria[] = {0, 36080, 90000, 143350, 254240};

  public MarriedFilingJointlyTaxpayer(String fullname, int taxRegistrationNumber, float income) {
    super(fullname, taxRegistrationNumber, income);
    super.oria = this.oria;
  }

}