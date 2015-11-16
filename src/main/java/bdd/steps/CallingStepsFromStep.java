package bdd.steps;

import org.givwenzen.GivWenZen;
import org.givwenzen.annotations.DomainStep;

/**
 * Created by ekim on 2015-11-10.
 */
public class CallingStepsFromStep {
    private GivWenZen gwz;

    public CallingStepsFromStep(GivWenZen var1) {
        this.gwz = var1;
    }

    @DomainStep("i call the steps to add (\\d+) and (\\d+)")
    public void callTheAddStepTwoTimes(int var1, int var2) throws Exception {
        this.gwz.given("i have entered " + var1 + " into the calculator");
        this.gwz.given("i have entered " + var2 + " into the calculator");
        this.gwz.when("i press add");
    }
}
