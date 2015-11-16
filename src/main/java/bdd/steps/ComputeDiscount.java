package bdd.steps;

import org.givwenzen.GivWenZen;
import org.givwenzen.annotations.DomainStep;
import org.givwenzen.annotations.DomainSteps;

/**
 * Created by ekim on 2015-11-11.
 */
@DomainSteps
public class ComputeDiscount {
    private GivWenZen givWenZen;
    private Float total;
    private String rating;
    private Integer discount;

    public ComputeDiscount(GivWenZen gwz) {
        this.givWenZen = gwz;
    }

    @DomainStep("total is (\\d+\\.\\d+)")
    public void enterTotal(Float total) throws Exception {
        this.total = Float.valueOf(total);
    }

    @DomainStep("rating is (.*)")
    public void enterRating(String rating) throws Exception {
        this.rating = String.valueOf(rating);
    }

    @DomainStep("i compute discount")
    public void computeDiscount() {
        this.discount = Integer.valueOf(1);
        if (this.rating.toLowerCase().equals("excellent") && this.total > 50.00) {
            this.discount = 5;
        }
        else if (this.rating.toLowerCase().equals("good") && this.total <= 10.00) {
            this.discount = 0;
        }
    }

    @DomainStep("percent is")
    public Integer getDiscount() {
        return this.discount;
    }
}
