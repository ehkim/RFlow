package org.givwenzen;

/**
 * Created by ekim on 2015-11-10.
 */
public class GivWenZenWithCustomState extends GivWenZenForSlim {
    public GivWenZenWithCustomState() {
        super(GivWenZenExecutorCreator.instance().customStepState(new Object[]{new CustomState()}).create());
    }
}
