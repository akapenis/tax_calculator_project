package incometaxcalculator.data.management;

public class HeadOfHouseholdTaxpayer extends Taxpayer {
    
  private double oria[] = {0, 30390, 90000, 122110, 203390};
  
  public HeadOfHouseholdTaxpayer(String fullname, int taxRegistrationNumber, float income) {
    super(fullname, taxRegistrationNumber, income);
    super.oria = this.oria;
  }

}
