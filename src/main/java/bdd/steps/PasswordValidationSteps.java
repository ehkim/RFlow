package bdd.steps;

import org.givwenzen.annotations.DomainStep;
import org.givwenzen.annotations.DomainSteps;

/**
 * Created by ekim on 2015-11-10.
 */
@DomainSteps
public class PasswordValidationSteps {
    private static final String START_OF_LINE = "^";
    private static final String ANY_CHARS_AND_END_OF_LINE = ".*$";
    private static final String AT_LEAST_ONE_ALPHA_CHAR = "(?=.*[a-zA-Z])";
    private static final String AT_LEAST_ONE_SPECIAL_CHAR = "(?=.*\\W)";
    private static final String AT_LEAST_ONE_DIGIT = "(?=.*\\d)";
    private static final String NO_MORE_THAN_16_CHARS = "(?!.{17,}$)";
    private static final String AT_LEAST_6_CHARS = "(?=.{6,}$)";
    private static final String DOES_NOT_CONTAIN_SPACES = "(?!.* )";
    private String passwordToVerify = "";

    public PasswordValidationSteps() {
    }

    @DomainStep("a new account is created with a password of (.*)")
    public String createAccountWithPassword(String var1) {
        this.passwordToVerify = var1 == null?"":var1;
        return var1;
    }

    @DomainStep("the password is accepted (?:.*)")
    public boolean passwordMeetsSecurityRequirements() {
        return this.isAcceptedPassword();
    }

    @DomainStep("the password is rejected (?:.*)")
    public boolean isRejectedPassword() {
        return !this.isAcceptedPassword();
    }

    @DomainStep("a new account is created with password (.*) then it is rejected because (?:.*)")
    public boolean createAccountWithInvalidPasswordAndVerifyItIsRejected(String var1) {
        this.createAccountWithPassword(var1);
        return this.isRejectedPassword();
    }

    @DomainStep("the password is valid (?:.*)")
    public boolean isAcceptedPassword() {
        System.out.println(this.passwordToVerify);
        return this.passwordToVerify.matches("^(?=.{6,}$)(?!.{17,}$)(?=.*\\d)(?=.*\\W)(?=.*[a-zA-Z])(?!.* ).*$");
    }
}
