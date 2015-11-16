import java.util.List;

/**
 * Created by ekim on 2015-11-11.
 */
public class DiscountFixture {
    private float total;
    private String rating;
    private int discount;

    public void setOrderTotal(float total) {
        this.total = total;
    }

    public void setCustomerRating(String rating) {
        this.rating = rating;
    }

    public String discountPercentage() {
        this.discount = 1;
        if (this.rating.toLowerCase().equals("excellent") && this.total > 50.00) {
                this.discount = 5;
        }
        else if (this.rating.toLowerCase().equals("good") && this.total <= 10.00) {
            this.discount = 0;
        }
        return this.discount + "%";
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
