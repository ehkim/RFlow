import java.util.List;

/**
 * Created by ekim on 2015-11-04.
 */
public class ShouldIBuyMilk {
    private int dollars;
    private int pints;
    private boolean creditCard;

    public void setCashInWallet(int dollars) {
        this.dollars = dollars;
    }

    public void setPintsOfMilkRemaining(int pints) {
        this.pints = pints;
    }

    public void setCreditCard(String valid) {
        creditCard = "yes".equals(valid);
    }

    public String goToStore() {
        return (pints == 0 && (dollars > 2 || creditCard)) ? "yes" : "no";
    }

    // The following functions are optional.  If they aren't declared they'll be ignored.
    public void execute() {
    }

    public void reset() {
    }

    public void table(List<List<String>> table) {
    }

    public void beginTable() {
    }

    public void endTable() {
    }
}
