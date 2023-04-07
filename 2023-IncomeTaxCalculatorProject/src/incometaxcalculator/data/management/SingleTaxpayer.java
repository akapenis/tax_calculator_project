package incometaxcalculator.data.management;

public class SingleTaxpayer extends Taxpayer {
  
  private double oria[] = {0, 24680, 81080, 90000, 152540};

  public SingleTaxpayer(String fullname, int taxRegistrationNumber, float income) {
    super(fullname, taxRegistrationNumber, income);
    super.oria = this.oria;
  }

}