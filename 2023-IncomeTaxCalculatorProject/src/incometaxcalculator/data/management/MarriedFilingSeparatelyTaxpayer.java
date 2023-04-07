package incometaxcalculator.data.management;

public class MarriedFilingSeparatelyTaxpayer extends Taxpayer {
  
  private double oria[] = {0, 18040, 71680, 90000, 127120};

  public MarriedFilingSeparatelyTaxpayer(String fullname, int taxRegistrationNumber, float income) {
    super(fullname, taxRegistrationNumber, income);
    super.oria = this.oria;
  }

}