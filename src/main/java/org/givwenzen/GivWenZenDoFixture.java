package org.givwenzen;

import fitlibrary.DoFixture;

/**
 * Created by ekim on 2015-11-10.
 */
public class GivWenZenDoFixture extends DoFixture {
    public GivWenZenDoFixture() {
        super(GivWenZenExecutorCreator.instance().customStepState(new Object[]{new CustomState()}).create());
    }

}
