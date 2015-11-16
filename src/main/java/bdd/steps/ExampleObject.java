package bdd.steps;

/**
 * Created by ekim on 2015-11-10.
 */
public class ExampleObject {
    String value;

    ExampleObject(String var1) {
        this.value = var1;
    }

    public boolean equals(Object var1) {
        if(this == var1) {
            return true;
        } else if(var1 != null && this.getClass() == var1.getClass()) {
            boolean var10000;
            label35: {
                ExampleObject var2 = (ExampleObject)var1;
                if(this.value != null) {
                    if(this.value.equals(var2.value)) {
                        break label35;
                    }
                } else if(var2.value == null) {
                    break label35;
                }

                var10000 = false;
                return var10000;
            }

            var10000 = true;
            return var10000;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.value != null?this.value.hashCode():0;
    }
}
