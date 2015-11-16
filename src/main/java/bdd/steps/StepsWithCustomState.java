package bdd.steps;

import org.givwenzen.annotations.DomainStep;
import org.givwenzen.annotations.DomainSteps;
import org.givwenzen.CustomState;

/**
 * Created by ekim on 2015-11-10.
 */
@DomainSteps
public class StepsWithCustomState {
    private CustomState state;

    public StepsWithCustomState(CustomState var1) {
        this.state = var1;
    }

    @DomainStep("the custom state is (.*)")
    public boolean verifyCustomState(String var1) {
        return var1.equals(this.state.someValue());
    }
}
