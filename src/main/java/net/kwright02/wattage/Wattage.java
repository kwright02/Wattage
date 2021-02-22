package net.kwright02.wattage;

import net.fabricmc.api.ModInitializer;
import net.kwright02.wattage.util.ApiVersionChecker;

public class Wattage implements ModInitializer {

    @Override
    public void onInitialize() {
        ApiVersionChecker.check();
    }

}
