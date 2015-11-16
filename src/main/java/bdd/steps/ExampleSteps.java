package bdd.steps;

import org.givwenzen.GivWenZen;
import org.givwenzen.annotations.DomainStep;
import org.givwenzen.annotations.DomainSteps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ekim on 2015-11-05.
 */
@DomainSteps
public class ExampleSteps {
    private static final String SOME_NUMBER = "(\\d+)";
    private GivWenZen givWenZen;
    private List<Integer> numbers = new ArrayList();
    private Integer total;
    private String[] values;
    private int[] ints;
    private ExampleObject[] objects;

    public ExampleSteps(GivWenZen var1) {
        this.givWenZen = var1;
    }

    @DomainStep("계산기를 실행한다.")
    public void reset() {
        this.numbers.clear();
    }

    @DomainStep("계산기에 (\\d+) 숫자를 입력한다.")
    public void enterNumber(int var1) throws Exception {
        this.numbers.add(Integer.valueOf(var1));
    }

    @DomainStep("덧셈 버튼을 클릭한다.")
    public void addNumbers() {
        this.total = Integer.valueOf(0);

        Integer var2;
        for(Iterator var1 = this.numbers.iterator(); var1.hasNext(); this.total = Integer.valueOf(this.total.intValue() + var2.intValue())) {
            var2 = (Integer)var1.next();
        }
    }

    @DomainStep("연산 결과값은 (\\d+)이다.")
    public boolean theTotalIs(int var1) throws Exception {
        return this.givWenZen.then("연산 결과값은 얼마인가").equals(Integer.valueOf(var1));
    }

    @DomainStep("연산 결과값은 얼마인가")
    public Integer getTotal() {
        return this.total;
    }

    @DomainStep("an array parameter method (.*)")
    public void arrayParameterMethod(String[] var1) {
        this.values = var1;
    }

    @DomainStep("array (\\d+) has (.*)")
    public boolean verifyArrayParameterMethod(int var1, String var2) {
        System.out.println("value[" + var1 + "]=" + this.values[var1]);
        return this.values[var1].equals(var2);
    }

    @DomainStep("a native array method (.*)")
    public void nativeArrayParameterMethod(int... var1) {
        this.ints = var1;
    }

    @DomainStep("native array (\\d+) has (\\d+)")
    public boolean verifyNativeArrayParameterMethod(int var1, int var2) {
        return this.ints[var1] == var2;
    }

    @DomainStep("a var arg method of TestObjects (.*)")
    public void objectArrayParameterMethod(ExampleObject... var1) {
        this.objects = var1;
    }

    @DomainStep("var arg (\\d+) is (.*)")
    public boolean verifyObjectVarArgs(int var1, ExampleObject var2) {
        return this.objects[var1].equals(var2);
    }
}