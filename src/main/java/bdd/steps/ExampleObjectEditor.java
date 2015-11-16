package bdd.steps;

import java.beans.PropertyEditorSupport;

/**
 * Created by ekim on 2015-11-10.
 */
public class ExampleObjectEditor extends PropertyEditorSupport {
    public ExampleObjectEditor() {
    }

    public void setAsText(String var1) throws IllegalArgumentException {
        this.setValue(new ExampleObject(var1));
    }
}
