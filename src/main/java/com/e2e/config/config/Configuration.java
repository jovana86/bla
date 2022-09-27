package com.e2e.config.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:configuration.properties"
})

public interface Configuration extends Config {
    @Key("browser")
    String browser();

    @Key("waitTime")
    int waitTime();

    @Key("path.screenShots")
    String path25screenShots();

    @Key("path.testData")
    String path2testData();

    @Key("ctrl.highLight")
    boolean ctrlHighLight();

    @Key("ctrl.hlTime")
    int ctrlHighLightTime();

    @Key("showReport")
    boolean showReport();

    @Key("dockingAllowed")
    boolean dockingAllowed();

    @Key("dockSide")
    String dockSide();


}
